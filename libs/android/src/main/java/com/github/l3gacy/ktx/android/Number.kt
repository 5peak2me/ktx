/*
 * Copyright © 2023 J!nl!n™ Inc. All rights reserved.
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
package com.github.l3gacy.ktx.android

import android.content.res.Resources
import android.graphics.Color
import android.util.TypedValue
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.core.graphics.alpha
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red

/**
 *
 * Created by J!nl!n on 2023/7/13.
 *
 * Copyright © 2023 J!nl!n™ Inc. All Rights Reserved.
 *
 */

public val <T : Number> T.dp: Float
  get() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    Resources.getSystem().displayMetrics,
  )

public val <T : Number> T.sp: Float
  get() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_SP,
    this.toFloat(),
    Resources.getSystem().displayMetrics,
  )

/**
 * Modify the alpha channel of the color, and return the resulting color.
 *
 * @param alpha the new alpha channel value, in the range [0.0f, 1.0f].
 * @return the resulting color.
 */
@ColorInt
public inline fun @receiver:ColorInt Int.alpha(
  @FloatRange(from = 0.0, to = 1.0) alpha: Float
): Int = Color.argb((this.alpha * alpha).toInt(), red, green, blue)
