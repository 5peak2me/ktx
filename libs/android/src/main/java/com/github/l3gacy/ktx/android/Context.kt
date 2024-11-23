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
import android.content.res.Configuration

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
