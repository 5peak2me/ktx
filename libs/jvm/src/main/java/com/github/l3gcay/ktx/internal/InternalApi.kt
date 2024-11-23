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
package com.github.l3gcay.ktx.internal

/**
 * Marks declarations that are **internal** in ktx API, which means that should not be used outside of
 * `com.github.l3gcay`, because their signatures and semantics will change between future releases without any
 * warnings and without providing any migration aids.
 */
@MustBeDocumented
@Retention(value = AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.TYPEALIAS, AnnotationTarget.PROPERTY)
@RequiresOptIn(
  level = RequiresOptIn.Level.ERROR,
  message = "This is an internal ktx API that " +
    "should not be used from outside of com.github.l3gcay.ktx. No compatibility guarantees are provided. " +
    "It is recommended to report your use-case of internal API to J!nl!n, " +
    "so stable API could be provided instead",
)
public annotation class InternalApi
