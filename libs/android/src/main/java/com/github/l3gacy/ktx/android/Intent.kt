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

import android.content.Intent
import androidx.core.content.IntentCompat
import java.io.Serializable

public inline fun <reified T : Serializable> Intent.serializable(key: String): T? =
  IntentCompat.getSerializableExtra(this, key, T::class.java)

public inline fun <reified T : Serializable> Intent.parcelable(key: String): T? =
  IntentCompat.getParcelableExtra(this, key, T::class.java)
