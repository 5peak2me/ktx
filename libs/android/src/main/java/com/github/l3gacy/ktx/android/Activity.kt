/*
 * Copyright © 2025 J!nl!n™ Inc. All rights reserved.
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

import android.content.res.Configuration
import androidx.activity.ComponentActivity
import androidx.core.util.Consumer
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.distinctUntilChanged

/**
 * Convenience wrapper for dark mode checking
 */
private val Configuration.isSystemInDarkTheme
  get() = (uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

/**
 * Registers listener for configuration changes to retrieve whether system is in dark theme or not.
 * Immediately upon subscribing, it sends the current value and then registers listener for changes.
 */
public fun ComponentActivity.isSystemInDarkTheme(): Flow<Boolean> = callbackFlow {
  channel.trySend(resources.configuration.isSystemInDarkTheme)

  val listener = Consumer<Configuration> {
    channel.trySend(it.isSystemInDarkTheme)
  }

  addOnConfigurationChangedListener(listener)

  awaitClose { removeOnConfigurationChangedListener(listener) }
}
  .distinctUntilChanged()
  .conflate()
