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

import java.text.NumberFormat
import java.util.Locale

/**
 *
 * Created by J!nl!n on 2023/7/19.
 *
 * Copyright © 2023 J!nl!n™ Inc. All Rights Reserved.
 *
 */

/**
 * Returns this number if it's not null, or 0 otherwise.
 *
 * This is a concise alternative to the Elvis expression `this ?: 0`.
 */
public inline fun <reified T : Number> T?.orZero(): Number = this ?: 0

/**
 * Formats this number as currency and returns the result as a string.
 *
 * This number is interpreted as a decimal value, and the resulting string
 * is formatted according to the locale's default currency. If this number
 * is null, the result is an empty string.
 *
 * @param locale the locale to which the currency should be formatted.
 * @return the formatted string.
 */
public inline fun <reified T : Number> T?.toCurrency(locale: Locale = Locale.getDefault()): String = NumberFormat.getCurrencyInstance(locale).format(this.orZero())

/**
 * Formats this number as a percentage and returns the result as a string.
 *
 * This number is interpreted as a decimal value, and the resulting string
 * is formatted according to the locale's default rules for percentages. If
 * this number is null, the result is an empty string.
 *
 * @param locale the locale to which the percentage should be formatted.
 * @return the formatted string.
 */
public inline fun <reified T : Number> T?.toPercent(locale: Locale = Locale.getDefault()): String = NumberFormat.getPercentInstance(locale).format(this.orZero())
