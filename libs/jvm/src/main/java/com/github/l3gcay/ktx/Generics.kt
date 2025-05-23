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

import kotlin.contracts.contract

/**
 *
 * Created by J!nl!n on 2023/7/13.
 *
 * Copyright © 2023 J!nl!n™ Inc. All Rights Reserved.
 *
 */

/**
 * Checks if the object is null.
 *
 * @return `true` if the object is null, `false` otherwise.
 */
public inline val <T> T?.isNull: Boolean
  get() = this == null

/**
 * Checks if the object is not null.
 *
 * @return `true` if the object is not null, `false` otherwise.
 */
public inline val <T> T?.isNotNull: Boolean
  get() = this != null

public fun <T> T?.isNonNull(): Boolean {
  contract {
    returns() implies (this@isNonNull != null)
  }
  return this != null
}
