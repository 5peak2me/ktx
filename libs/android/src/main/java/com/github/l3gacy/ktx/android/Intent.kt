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
import android.os.Parcelable
import androidx.core.content.IntentCompat
import java.io.Serializable

/**
 * Retrieve an extra from the intent using the given key. The extra should
 * have been added using [Intent.putExtra] with a value that implements the
 * [Serializable] interface.
 *
 * @param key the key of the extra
 * @return the value of the extra, or null if no such extra was found
 */
public inline fun <reified T : Serializable> Intent.serializableExtra(key: String): T? = IntentCompat.getSerializableExtra(this, key, T::class.java)

/**
 * Retrieve an extra from the intent using the given key. The extra should
 * have been added using [Intent.putExtra].
 *
 * @param key the key of the extra
 * @return the value of the extra, or null if no such extra was found
 */
public inline fun <reified T : Parcelable> Intent.parcelableExtra(key: String): T? = IntentCompat.getParcelableExtra(this, key, T::class.java)

/**
 * Retrieve a Parcelable array extra from the Intent using the given key. The
 * array should have been added using [Intent.putExtra] with values that
 * implement the [Parcelable] interface.
 *
 * @param key the key of the extra
 * @return the array of the extra, or null if no such extra was found
 */
public inline fun <reified T : Parcelable> Intent.parcelableArrayExtra(key: String): Array<T>? = IntentCompat.getParcelableArrayExtra(this, key, T::class.java)?.map { it as T }?.toTypedArray()

/**
 * Retrieve an extra from the intent using the given key. The extra should
 * have been added using [Intent.putExtra] with a value that implements the
 * [Parcelable] interface.
 *
 * @param key the key of the extra
 * @return the value of the extra, or null if no such extra was found
 */
public inline fun <reified T : Parcelable> Intent.parcelableArrayListExtra(key: String): ArrayList<T>? = IntentCompat.getParcelableArrayListExtra<T>(this, key, T::class.java)
