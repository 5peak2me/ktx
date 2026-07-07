/*
 * Copyright © 2024 J!nl!n™ Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.speak2me.ktx.compose

import androidx.annotation.FloatRange
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Stable
public fun Modifier.maxPercent(
  @FloatRange(from = 0.0, to = 1.0) width: Float = 1f,
  @FloatRange(from = 0.0, to = 1.0) height: Float = 1f,
): Modifier = this.then(
  Modifier.layout { measurable, constraints ->

    val maxWidthPx = if (constraints.hasBoundedWidth) {
      (constraints.maxWidth * width.coerceIn(0f, 1f)).toInt()
    } else {
      constraints.maxWidth
    }

    val maxHeightPx = if (constraints.hasBoundedHeight) {
      (constraints.maxHeight * height.coerceIn(0f, 1f)).toInt()
    } else {
      constraints.maxHeight
    }

    val newConstraints = constraints.copy(
      maxWidth = maxWidthPx,
      maxHeight = maxHeightPx,
    )

    val placeable = measurable.measure(newConstraints)

    layout(placeable.width, placeable.height) {
      placeable.place(0, 0)
    }
  },
)

public fun Modifier.maxHeightPercent(
  percent: Float,
): Modifier = this.then(
  Modifier.layout { measurable, constraints ->
    // 父布局给的最大高度（px）
    val parentMaxHeight = constraints.maxHeight

    // 百分比上限
    val maxHeightPx = (parentMaxHeight * percent.coerceIn(0f, 1f)).toInt()

    // 允许内容 wrap，但不能超过 maxHeightPx
    val newConstraints = constraints.copy(
      maxHeight = maxHeightPx,
    )

    val placeable = measurable.measure(newConstraints)

    layout(placeable.width, placeable.height) {
      placeable.place(0, 0)
    }
  },
)

public fun Modifier.maxWidthPercent(
  percent: Float,
): Modifier = this.then(
  Modifier.layout { measurable, constraints ->
    // 父布局允许的最大宽度（px）
    val parentMaxWidth = constraints.maxWidth

    // 百分比上限
    val maxWidthPx = (parentMaxWidth * percent.coerceIn(0f, 1f)).toInt()

    // 只限制 maxWidth，不碰 minWidth，保证 wrapContent
    val newConstraints = constraints.copy(
      maxWidth = maxWidthPx,
    )

    val placeable = measurable.measure(newConstraints)

    layout(placeable.width, placeable.height) {
      placeable.place(0, 0)
    }
  },
)

@Stable
public fun Modifier.crossfade(visible: Boolean): Modifier = composed {
  val alpha by animateFloatAsState(if (visible) 1f else 0f)
  graphicsLayer(alpha = alpha)
}

@Stable
public fun Modifier.margin(
  start: Dp = 0.dp,
  top: Dp = 0.dp,
  end: Dp = 0.dp,
  bottom: Dp = 0.dp,
): Modifier = padding(start, top, end, bottom)

@Stable
public fun Modifier.margin(horizontal: Dp = 0.dp, vertical: Dp = 0.dp): Modifier = padding(horizontal = horizontal, vertical = vertical)

@Stable
public fun Modifier.margin(all: Dp): Modifier = padding(all)

@Stable
public fun Modifier.disallowTouchEvent(): Modifier = pointerInput(Unit) {
  detectTapGestures {
  }
}

@Stable
public fun Modifier.horizontalGradient(
  colors: List<Color>,
  shape: Shape = RectangleShape,
): Modifier = background(
  brush = Brush.horizontalGradient(
    colors = colors,
  ),
  shape,
)

@Stable
public fun Modifier.verticalGradient(
  colors: List<Color>,
  shape: Shape = RectangleShape,
): Modifier = background(
  brush = Brush.verticalGradient(
    colors = colors,
  ),
  shape,
)

@Stable
public inline fun Modifier.thenIf(
  condition: Boolean,
  crossinline block: Modifier.() -> Modifier,
): Modifier = if (condition) block(this) else this

@Stable
public fun Modifier.clickableWithoutRipple(onClick: () -> Unit): Modifier = composed {
  Modifier.clickable(
    interactionSource = remember { MutableInteractionSource() },
    indication = null,
    onClick = onClick,
  )
}

private interface MultipleEventsCutter {
  fun processEvent(event: () -> Unit)

  companion object
}

private fun MultipleEventsCutter.Companion.get(): MultipleEventsCutter = MultipleEventsCutterImpl()

private class MultipleEventsCutterImpl(private val timeout: Long = 300L) : MultipleEventsCutter {
  @OptIn(ExperimentalTime::class)
  private val now
    get() = Clock.System.now().toEpochMilliseconds()

  private var lastEventTimeMs: Long = 0

  override fun processEvent(event: () -> Unit) {
    if (now - lastEventTimeMs >= timeout) {
      event.invoke()
    }
    lastEventTimeMs = now
  }
}

@Stable
public fun Modifier.clickableDebounce(
  enabled: Boolean = true,
  onClickLabel: String? = null,
  role: Role? = null,
  onClick: () -> Unit,
): Modifier = composed(
  inspectorInfo = debugInspectorInfo {
    name = "clickable"
    properties["enabled"] = enabled
    properties["onClickLabel"] = onClickLabel
    properties["role"] = role
    properties["onClick"] = onClick
  },
) {
  val multipleEventsCutter = remember { MultipleEventsCutter.get() }
  Modifier.clickable(
    enabled = enabled,
    onClickLabel = onClickLabel,
    onClick = { multipleEventsCutter.processEvent { onClick() } },
    role = role,
    indication = LocalIndication.current,
    interactionSource = remember { MutableInteractionSource() },
  )
}

/**
 * Draws vertical [androidx.compose.material3.HorizontalDivider] at the end of the composable
 * @see drawVerticalDivider
 */
@Stable
public fun Modifier.drawHorizontalDivider(
  color: Color,
  thickness: Dp = 0.3.dp,
  indent: PaddingValues = PaddingValues(0.dp),
): Modifier = drawBehind {
  val startX = indent.calculateStartPadding(layoutDirection).toPx()
  val (width, _) = size
  val endX = width - indent.calculateEndPadding(layoutDirection).toPx()

  drawLine(
    color = color,
    start = Offset(startX, thickness.toPx() / 2),
    end = Offset(endX, thickness.toPx() / 2),
    strokeWidth = thickness.toPx(),
  )
}

/**
 * Draws vertical [androidx.compose.material3.VerticalDivider] at the end of the composable
 * @see drawHorizontalDivider
 */
@Stable
public fun Modifier.drawVerticalDivider(
  color: Color,
  thickness: Dp = 0.3.dp,
  indent: PaddingValues = PaddingValues(0.dp),
): Modifier = drawBehind {
  val topIndentPx = indent.calculateTopPadding().toPx()
  val bottomIndentPx = indent.calculateBottomPadding().toPx()
  val (width, height) = size
  val startX = width - indent.calculateEndPadding(layoutDirection).toPx()

  drawLine(
    color = color,
    start = Offset(startX, topIndentPx),
    end = Offset(startX, height - bottomIndentPx),
    strokeWidth = thickness.toPx(),
  )
}
