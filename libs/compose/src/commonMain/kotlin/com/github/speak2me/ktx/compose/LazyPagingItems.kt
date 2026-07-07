/*
 * Copyright © 2024 J!nl!n™ Inc. All rights reserved.
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
package com.github.speak2me.ktx.compose

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems

public fun <T : Any> LazyListScope.pagingItems(
  items: LazyPagingItems<T>,
  key: ((index: Int) -> Any)? = null,
  contentType: (index: Int) -> Any? = { null },
  itemContent: @Composable LazyItemScope.(index: Int, T) -> Unit,
) {
  items(count = items.itemCount, key, contentType) {
    val item = items[it] ?: return@items
    itemContent(this, it, item)
  }
//    item {
//        Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))
//    }
}

public fun <T : Any> LazyPagingItems<T>.isEmpty(): Boolean = itemCount == 0
