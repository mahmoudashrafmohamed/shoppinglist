package com.mahmoud_ashraf.shoppinglist.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mahmoud_ashraf.shoppinglist.presentation.ui.screens.ProductListScreen
import com.mahmoud_ashraf.shoppinglist.presentation.ui.theme.ShoppingListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ShoppingListTheme {
        ProductListScreen()
      }
    }
  }
}
