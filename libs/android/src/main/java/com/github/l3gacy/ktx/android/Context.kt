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

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

/**
 * Returns true if the system is currently in a dark theme.
 *
 * @see Configuration.UI_MODE_NIGHT_YES
 * @see Configuration.UI_MODE_NIGHT_NO
 * @see Configuration.UI_MODE_NIGHT_UNDEFINED
 */
public inline val Context.isSystemInDarkTheme: Boolean
  get() = (resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)) == Configuration.UI_MODE_NIGHT_YES

/**
 * Returns true if the device is in landscape orientation.
 *
 * @see Configuration.ORIENTATION_LANDSCAPE
 */
public inline val Context.isLandscape: Boolean
  get() = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

context(Context)
public val <T : Number> T.dp: Float
  get() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    resources.displayMetrics,
  )

context(View)
public val <T : Number> T.dp: Float
  get() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    resources.displayMetrics,
  )

context(Context)
public val <T : Number> T.sp: Float
  get() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_SP,
    this.toFloat(),
    resources.displayMetrics
  )

context(View)
public val <T : Number> T.sp: Float
  get() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_SP,
    this.toFloat(),
    resources.displayMetrics
  )

context(Context)
public val @receiver:StringRes Int.text: CharSequence
  get() = getText(this)

context(View)
public val @receiver:StringRes Int.text: CharSequence
  get() = context.getText(this)

context(Context)
public val @receiver:StringRes Int.string: String
  get() = ContextCompat.getString(this@Context, this)

context(View)
public val @receiver:StringRes Int.string: String
  get() = ContextCompat.getString(context,this)

context(Context)
public val @receiver:ColorRes Int.color: Int
  @ColorInt
  get() = ContextCompat.getColor(this@Context, this)

context(View)
public val @receiver:ColorRes Int.color: Int
  @ColorInt
  get() = ContextCompat.getColor(context, this)

context(Context)
public val @receiver:DrawableRes Int.drawable: Drawable?
  get() = ContextCompat.getDrawable(this@Context, this)

context(View)
public val @receiver:DrawableRes Int.drawable: Drawable?
  get() = ContextCompat.getDrawable(context, this)

context(Context)
public val @receiver:ColorRes Int.states: ColorStateList?
  get() = ContextCompat.getColorStateList(this@Context, this)

context(View)
public val @receiver:ColorRes Int.states: ColorStateList?
  get() = ContextCompat.getColorStateList(context, this)
