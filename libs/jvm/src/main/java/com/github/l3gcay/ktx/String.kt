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
 *
 * Created by J!nl!n on 2023/7/19.
 *
 * Copyright © 2023 J!nl!n™ Inc. All Rights Reserved.
 *
 */

/**
 * Returns a copy of this string having its first letter titlecased, or the original string if it's empty or already
 * starts with a title case letter.
 *
 * The title case of a character is usually the same as its upper case with several exceptions.
 * The particular list of characters with the special title case form depends on the underlying platform.
 */
public val String.capitalize: String
  get() = replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }

/**
 * Returns a copy of this string having its first letter lowercased, or the original string if it's empty or already
 * starts with a lower case letter.
 */
public val String.decapitalize: String
  get() = replaceFirstChar { it.lowercase() }
