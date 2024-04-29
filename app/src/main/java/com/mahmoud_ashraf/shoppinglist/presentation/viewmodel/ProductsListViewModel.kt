package com.mahmoud_ashraf.shoppinglist.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.TextRange
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoud_ashraf.shoppinglist.data.local.models.ProductEntity
import com.mahmoud_ashraf.shoppinglist.domain.usecase.CreateProductUseCase
import com.mahmoud_ashraf.shoppinglist.domain.usecase.DeleteProductsUseCase
import com.mahmoud_ashraf.shoppinglist.domain.usecase.GetProductsUseCase
import com.mahmoud_ashraf.shoppinglist.domain.usecase.GetSortedProductsUseCase
import com.mahmoud_ashraf.shoppinglist.domain.usecase.UpdateProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Mahmoud Ashraf on 29,April,2024
 */
@HiltViewModel
class ProductsListViewModel @Inject constructor(
  private val createProductUseCase: CreateProductUseCase,
  private val getProductsUseCase: GetProductsUseCase,
  private val getSortedProductsUseCase: GetSortedProductsUseCase,
  private val deleteProductsUseCase: DeleteProductsUseCase,
  private val updateProductUseCase: UpdateProductUseCase
) : ViewModel() {
  var isUpdateModeEnabled: Boolean = true
  var requiredToUpdateProductId: Int = -1
  var productNameState = mutableStateOf("")
  var editTextProductNameSelectionState = mutableStateOf(TextRange(0, 0))
  var productDescState = mutableStateOf("")
  var editTextProductDescSelectionState = mutableStateOf(TextRange(0, 0))
  var productQuantityState = mutableStateOf("")
  var editTextProductQuantitySelectionState = mutableStateOf(TextRange(0, 0))
  var products = mutableStateOf<List<ProductEntity>>(emptyList())

  var isBoughtFilterEnabled = false
  init {
    getProducts(false)
  }

  fun createProduct(productEntity: ProductEntity) = viewModelScope.launch(Dispatchers.IO) {
    createProductUseCase.invoke(productEntity)
  }

  fun getProducts(isBought: Boolean) {
    this.isBoughtFilterEnabled = isBought
    viewModelScope.launch(Dispatchers.IO) {
      val data = getProductsUseCase.invoke(isBought)
      Log.e("data+++", "" + data)
      withContext(Dispatchers.Main) {
        products.value = data
      }
    }
  }

  fun getProductsSorted(asc : Boolean) {
    viewModelScope.launch(Dispatchers.IO) {
      val data = getSortedProductsUseCase.invoke(isBoughtFilterEnabled,asc)
      Log.e("data+++", "" + data)
      withContext(Dispatchers.Main) {
        products.value = data
      }
    }
  }

  fun deleteProduct(id: Int) {
    viewModelScope.launch(Dispatchers.IO) {
       deleteProductsUseCase.invoke(id)
      val data = getProductsUseCase.invoke(isBoughtFilterEnabled)
      Log.e("data+++", "" + data)
      withContext(Dispatchers.Main) {
        products.value = data
      }
    }
  }

  fun updateProduct(productEntity: ProductEntity) {
    viewModelScope.launch(Dispatchers.IO) {
      updateProductUseCase.invoke(productEntity)
       updateProductUseCase.invoke(productEntity)
      val data = getProductsUseCase.invoke(isBoughtFilterEnabled)
      Log.e("data+++", "" + data)
      isUpdateModeEnabled = false
      withContext(Dispatchers.Main) {
        products.value = data
      }
    }
  }
}