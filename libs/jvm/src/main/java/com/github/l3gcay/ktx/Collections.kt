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
package com.github.l3gcay.ktx

/**
 * Returns this collection if it is not null and not empty, or the specified default collection otherwise.
 *
 * @param default the collection to return if this collection is null or empty.
 * @return this collection if it is not null and not empty, or the default collection otherwise.
 */
public fun <T> Collection<T>?.orDefault(default: Collection<T>?): Collection<T> =
  (if (isNullOrEmpty()) default else this).orEmpty()

/**
 * Returns this map if it is not null and not empty, or the specified default map otherwise.
 *
 * @param default the map to return if this map is null or empty.
 * @return this map if it is not null and not empty, or the default map otherwise.
 */
public fun <K, V> Map<K, V>?.orDefault(default: Map<K, V>?): Map<K, V> =
  (if (isNullOrEmpty()) default else this).orEmpty()
