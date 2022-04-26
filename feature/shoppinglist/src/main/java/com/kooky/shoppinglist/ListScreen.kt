package com.kooky.shoppinglist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kooky.navigation.LocalToolbar
import com.kooky.navigation.ShoppingListKey
import com.kooky.navigation.ToolbarProps
import com.kooky.utilities.SwipeableRow
import dev.enro.annotations.ExperimentalComposableDestination
import dev.enro.annotations.NavigationDestination

data class ShoppingListItem(
    val item: String,
    val quantity: Int = 0
)

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
@ExperimentalComposableDestination
@NavigationDestination(ShoppingListKey::class)
public fun ShoppingListScreen() {

    LocalToolbar.current.value = ToolbarProps(title = "Shopping List")

    //TODO read from view model
    val listItems = remember { mutableStateListOf(*items) }

    LazyColumn {
        items(items) {

            SwipeableRow(
                modifier = Modifier
                    .animateItemPlacement()
                    .padding(16.dp),
                onDismiss = { listItems.remove(it) },
                background = {},
            ) {
                Text("${it.quantity} x ", modifier = Modifier.align(Alignment.CenterVertically))
                Text(
                    text = it.item,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = null,
                        tint = MaterialTheme.colors.error
                    )
                }
            }
        }
    }
}

val items = arrayOf(
    ShoppingListItem(
        item = "Capsicum",
        quantity = 1
    ),
    ShoppingListItem(
        item = "Carrot",
        quantity = 1
    ),
    ShoppingListItem(
        item = "Pizza",
        quantity = 1
    ),
    ShoppingListItem(
        item = "Smoked Paprika",
        quantity = 1
    ),
    ShoppingListItem(
        item = "Ice cream",
        quantity = 1
    ),
    ShoppingListItem(
        item = "Olive Oil",
        quantity = 1
    ),
    ShoppingListItem(
        item = "Olive Oil",
        quantity = 1
    ),
    ShoppingListItem(
        item = "Olive Oil",
        quantity = 1
    ),
    ShoppingListItem(
        item = "Olive Oil",
        quantity = 1
    ),
    ShoppingListItem(
        item = "Olive Oil",
        quantity = 1
    ),
    ShoppingListItem(
        item = "Olive Oil",
        quantity = 1
    ),
    ShoppingListItem(
        item = "Olive Oil",
        quantity = 1
    ),
    ShoppingListItem(
        item = "Olive Oil",
        quantity = 1
    ),
    ShoppingListItem(
        item = "Olive Oil",
        quantity = 1
    ),
    ShoppingListItem(
        item = "Olive Oil",
        quantity = 1
    ),
    ShoppingListItem(
        item = "Olive Oil",
        quantity = 1
    ),
    ShoppingListItem(
        item = "Olive Oil",
        quantity = 1
    ),
    ShoppingListItem(
        item = "Olive Oil",
        quantity = 1
    ),
    ShoppingListItem(
        item = "Olive Oil",
        quantity = 1
    ),
    ShoppingListItem(
        item = "Olive Oil",
        quantity = 1
    ),
    ShoppingListItem(
        item = "Olive Oil",
        quantity = 1
    ),
    ShoppingListItem(
        item = "Cashews",
        quantity = 1
    ),
    ShoppingListItem(
        item = "Banana",
        quantity = 1
    ),
    ShoppingListItem(
        item = "Celery",
        quantity = 1
    )
)