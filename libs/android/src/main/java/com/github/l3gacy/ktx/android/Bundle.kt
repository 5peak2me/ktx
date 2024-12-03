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

import android.os.Bundle
import android.os.Parcelable
import android.util.SparseArray
import androidx.core.os.BundleCompat
import java.io.Serializable

/**
 * Retrieve a value from the Bundle using the given key. The value should
 * have been added using [Bundle.putSerializable] with a value that implements
 * the [Serializable] interface.
 *
 * @param key the key of the value
 * @return the value of the key, or null if no such value was found
 */
public inline fun <reified T : Serializable> Bundle.serializable(key: String): T? =
  BundleCompat.getSerializable(this, key, T::class.java)

/**
 * Retrieve a Parcelable value from the Bundle using the given key. The
 * value should have been added using [Bundle.putParcelable] with a value
 * that implements the [Parcelable] interface.
 *
 * @param key the key of the value
 * @return the value of the key, or null if no such value was found
 */
public inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? =
  BundleCompat.getParcelable(this, key, T::class.java)

/**
 * Retrieve a Parcelable array from the Bundle using the given key. The
 * array should have been added using [Bundle.putParcelableArray] with values
 * that implement the [Parcelable] interface.
 *
 * @param key the key of the value
 * @return the array of the key, or null if no such value was found
 */
public inline fun <reified T : Parcelable> Bundle.parcelableArray(key: String): Array<T>? =
  BundleCompat.getParcelableArray(this, key, T::class.java)?.map { it as T }?.toTypedArray()

/**
 * Retrieve a Parcelable ArrayList from the Bundle using the given key. The
 * ArrayList should have been added using [Bundle.putParcelableArrayList] with
 * values that implement the [Parcelable] interface.
 *
 * @param key the key of the value
 * @return the ArrayList of the key, or null if no such value was found
 */
public inline fun <reified T : Parcelable> Bundle.parcelableArrayList(key: String): ArrayList<T>? =
  BundleCompat.getParcelableArrayList(this, key, T::class.java)

/**
 * Retrieve a SparseArray of Parcelable values from the Bundle using the given key.
 * The SparseArray should have been added using [Bundle.putSparseParcelableArray]
 * with values that implement the [Parcelable] interface.
 *
 * @param key the key of the value
 * @return the SparseArray of the key, or null if no such value was found
 */
public inline fun <reified T : Parcelable> Bundle.sparseParcelableArray(key: String): SparseArray<T>? =
  BundleCompat.getSparseParcelableArray(this, key, T::class.java)
