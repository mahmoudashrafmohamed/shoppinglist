package com.mahmoud_ashraf.shoppinglist.presentation.viewmodel

import com.mahmoud_ashraf.shoppinglist.MainCoroutineRule
import com.mahmoud_ashraf.shoppinglist.data.local.models.ProductEntity
import com.mahmoud_ashraf.shoppinglist.domain.usecase.CreateProductUseCase
import com.mahmoud_ashraf.shoppinglist.domain.usecase.DeleteProductsUseCase
import com.mahmoud_ashraf.shoppinglist.domain.usecase.GetProductsUseCase
import com.mahmoud_ashraf.shoppinglist.domain.usecase.GetSortedProductsUseCase
import com.mahmoud_ashraf.shoppinglist.domain.usecase.UpdateProductUseCase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


/**
 * Created by Mahmoud Ashraf on 29,April,2024
 */

@ExperimentalCoroutinesApi
class ProductsListViewModelTest {

  @ExperimentalCoroutinesApi
  @get:Rule
  var mainCoroutineRule = MainCoroutineRule()

  @OptIn(DelicateCoroutinesApi::class)
  @ObsoleteCoroutinesApi
  private var mainThreadSurrogate = newSingleThreadContext("UI thread")


  private lateinit var viewModel: ProductsListViewModel
  private val createProductUseCase = mock(CreateProductUseCase::class.java)
  private val getProductsUseCase = mock(GetProductsUseCase::class.java)
  private val getSortedProductsUseCase = mock(GetSortedProductsUseCase::class.java)
  private val deleteProductsUseCase = mock(DeleteProductsUseCase::class.java)
  private val updateProductUseCase = mock(UpdateProductUseCase::class.java)

  @ObsoleteCoroutinesApi
  @Before
  fun init() {
    Dispatchers.setMain(mainThreadSurrogate)
    viewModel = ProductsListViewModel(createProductUseCase,getProductsUseCase,getSortedProductsUseCase,deleteProductsUseCase,updateProductUseCase)
  }

  @ObsoleteCoroutinesApi
  @After
  fun tearDown() {
    Dispatchers.resetMain()
    mainThreadSurrogate.close()
  }



  @Test
  fun screenStateProductList() {
    val products = emptyList<ProductEntity>()
    Mockito.`when`(getProductsUseCase.invoke(Mockito.anyBoolean())).thenReturn(products)
    Assert.assertEquals(products, viewModel.products.value)
  }

  @Test
  fun createProduct() = runBlocking {
    val productEntity = ProductEntity("test",1,"desc",false)
    viewModel.createProduct(productEntity)
    unlockThread()
    verify(createProductUseCase).invoke(productEntity)
  }

  @Test
  fun deleteProduct() = runBlocking {
    val productEntity = ProductEntity("test",1,"desc",false)
    viewModel.deleteProduct(productEntity.id ?:-1)
    unlockThread()
    verify(deleteProductsUseCase).invoke(productEntity.id ?:-1)
  }

  suspend fun unlockThread() {
    delay(10)
  }

}

