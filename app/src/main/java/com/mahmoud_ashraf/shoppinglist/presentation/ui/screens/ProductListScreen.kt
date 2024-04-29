package com.mahmoud_ashraf.shoppinglist.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mahmoud_ashraf.shoppinglist.R
import com.mahmoud_ashraf.shoppinglist.data.local.models.ProductEntity
import com.mahmoud_ashraf.shoppinglist.presentation.viewmodel.ProductsListViewModel

/**
 * Created by Mahmoud Ashraf on 23,April,2024
 */

@Composable
fun ProductListScreen(
) {
  val showDialogState = remember {
    mutableStateOf(false)
  }

  Scaffold(
    topBar = {
      AppBar()
    },
    content = { padding ->
      ProductListScreenBody(padding, showDialogState)
      if (showDialogState.value) CreateProductDialog(showDialogState)

    },
    floatingActionButton = {
      Fab(onFabClicked = { showDialogState.value = true })
    }
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProductDialog(
  showDialogState: MutableState<Boolean>,
  viewModel: ProductsListViewModel = hiltViewModel(),
) {
  AlertDialog(
    onDismissRequest = {}, modifier = Modifier
      .wrapContentHeight()
      .background(Color.Gray)
  ) {
    Column {
      Spacer(Modifier.height(16.dp))
      ProductNameEditText(viewModel)
      Spacer(Modifier.height(16.dp))
      ProductDescEditText(viewModel)
      Spacer(Modifier.height(16.dp))
      QuantityEditText(viewModel)
      Spacer(Modifier.height(16.dp))
      Button(onClick = {
        if (viewModel.isUpdateModeEnabled)
          viewModel.updateProduct(
            ProductEntity(
              name = viewModel.productNameState.value,
              desc = viewModel.productDescState.value,
              quantity = viewModel.productQuantityState.value.toLong(),
              isBought = false,
              id = viewModel.requiredToUpdateProductId
            )
          )
        else
          viewModel.createProduct(
            ProductEntity(
              name = viewModel.productNameState.value,
              desc = viewModel.productDescState.value,
              quantity = viewModel.productQuantityState.value.toLong(),
              isBought = false
            )
          )
        showDialogState.value = false
      }, Modifier.align(Alignment.CenterHorizontally)) {
        Text(text = "Save")
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {
  TopAppBar(
    title = { Text(text = stringResource(id = R.string.app_name)) }
  )
}

@Composable
fun Fab(onFabClicked: () -> Unit) {
  FloatingActionButton(
    onClick = { onFabClicked() },
    content = { Icon(Icons.Filled.Add, "") }
  )
}

@Composable
fun ProductListScreenBody(
  padding: PaddingValues,
  showDialogState: MutableState<Boolean>,
  viewModel: ProductsListViewModel = hiltViewModel(),
) {
  Column(
    Modifier
      .fillMaxWidth()
      .padding(padding)
  ) {
    val products = viewModel.products.value

    Row {
      Button(onClick = { viewModel.getProducts(isBought = true) }) {
        Text(text = "Bought")
      }
      Spacer(Modifier.width(16.dp))
      Button(onClick = {
        viewModel.getProducts(isBought = false)
      }) {
        Text(text = "Not Bought")
      }
      Spacer(Modifier.width(16.dp))

    }
    Row {
      Button(onClick = {
        viewModel.getProductsSorted(true)
      }) {
        Text(text = "Sort (ASC)")
      }
      Spacer(Modifier.width(16.dp))
      Button(onClick = {
        viewModel.getProductsSorted(false)
      }) {
        Text(text = "Sort (DESC)")
      }
    }

    LazyColumn(
      modifier = Modifier.fillMaxSize()
    ) {
      items(
        count = products.size,
        key = { index -> products[index].id ?: -1 }
      ) { index ->
        Row(modifier = Modifier
          .fillMaxWidth()
          .height(70.dp)
          .padding(top = 32.dp)) {
          Text(text = products[index].name, modifier = Modifier.weight(1f, true))
          Button(onClick = { viewModel.deleteProduct(products[index].id ?: -1) }) {
            Text(text = "Delete")
          }
          Button(onClick = {
            showDialogState.value = true
            viewModel.isUpdateModeEnabled = true
            viewModel.requiredToUpdateProductId = products[index].id ?: -1
          }
          ) {
            Text(text = "Edit")
          }
        }

      }
    }
  }
}


@Composable
private fun ProductNameEditText(viewModel: ProductsListViewModel) {

  Column {
    Box {
      TextField(
        value = TextFieldValue(
          viewModel.productNameState.value,
          viewModel.editTextProductNameSelectionState.value
        ),
        modifier = Modifier
          .fillMaxWidth()
          .onFocusChanged {
          },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        textStyle = TextStyle(fontSize = 18.sp),
        onValueChange = {
          viewModel.productNameState.value = it.text
          viewModel.editTextProductNameSelectionState.value =
            TextRange(it.text.length, it.text.length)
        },

        placeholder = {
          Text(
            text = "Name",
            color = Color.Gray,
            style = TextStyle(fontSize = 18.sp),
          )
        },
      )
    }
  }
}

@Composable
private fun ProductDescEditText(viewModel: ProductsListViewModel) {
  Column {
    Box {
      TextField(
        value = TextFieldValue(
          viewModel.productDescState.value,
          viewModel.editTextProductDescSelectionState.value
        ),
        modifier = Modifier
          .fillMaxWidth()
          .onFocusChanged {
          },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        textStyle = TextStyle(fontSize = 18.sp),
        onValueChange = { it ->
          viewModel.productDescState.value = it.text
          viewModel.editTextProductDescSelectionState.value =
            TextRange(it.text.length, it.text.length)
        },

        placeholder = {
          Text(
            text = "Desc",
            color = Color.Gray,
            style = TextStyle(fontSize = 18.sp),
          )
        },
      )
    }
  }
}


@Composable
private fun QuantityEditText(viewModel: ProductsListViewModel) {
  Column {
    Box {
      TextField(
        value = TextFieldValue(
          viewModel.productQuantityState.value,
          viewModel.editTextProductQuantitySelectionState.value
        ),
        modifier = Modifier
          .fillMaxWidth()
          .onFocusChanged {
          },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        textStyle = TextStyle(fontSize = 18.sp),
        onValueChange = {
          viewModel.productQuantityState.value = it.text
          viewModel.editTextProductQuantitySelectionState.value =
            TextRange(it.text.length, it.text.length)
        },

        placeholder = {
          Text(
            text = "Qty",
            color = Color.Gray,
            style = TextStyle(fontSize = 18.sp),
          )
        },
      )
    }
  }
}


