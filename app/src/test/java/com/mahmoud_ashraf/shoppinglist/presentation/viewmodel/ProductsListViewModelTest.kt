package com.mahmoud_ashraf.shoppinglist.presentation.viewmodel

import com.mahmoud_ashraf.shoppinglist.MainCoroutineRule
import com.mahmoud_ashraf.shoppinglist.data.local.models.ProductEntity
import com.mahmoud_ashraf.shoppinglist.domain.usecase.CreateProductUseCase
import com.mahmoud_ashraf.shoppinglist.domain.usecase.DeleteProductsUseCase
import com.mahmoud_ashraf.shoppinglist.domain.usecase.GetProductsUseCase
import com.mahmoud_ashraf.shoppinglist.domain.usecase.GetSortedProductsUseCase
import com.mahmoud_ashraf.shoppinglist.domain.usecase.UpdateProductUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


/**
 * Created by Mahmoud Ashraf on 29,April,2024
 */

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ProductsListViewModelTest {

  private lateinit var viewModel: ProductsListViewModel
  private val createProductUseCase = mock(CreateProductUseCase::class.java)
  private val getProductsUseCase = mock(GetProductsUseCase::class.java)
  private val getSortedProductsUseCase = mock(GetSortedProductsUseCase::class.java)
  private val deleteProductsUseCase = mock(DeleteProductsUseCase::class.java)
  private val updateProductUseCase = mock(UpdateProductUseCase::class.java)

  @get:Rule
  val mainCoroutineRule = MainCoroutineRule()

  @ObsoleteCoroutinesApi
  @Before
  fun init() {
    viewModel = ProductsListViewModel(createProductUseCase,getProductsUseCase,getSortedProductsUseCase,deleteProductsUseCase,updateProductUseCase)
  }

  @Test
  fun screenStateProductList() = runBlockingTest {
    val products = emptyList<ProductEntity>()
    Mockito.`when`(getProductsUseCase.invoke(Mockito.anyBoolean())).thenReturn(products)
    Assert.assertEquals(products, viewModel.products.value)
  }

  @Test
  fun createProduct() = runBlockingTest {
    val productEntity = ProductEntity("test",1,"desc",false)
    viewModel.createProduct(productEntity)
    verify(createProductUseCase).invoke(productEntity)
  }

  @Test
  fun deleteProduct() = runBlockingTest {
    val productEntity = ProductEntity("test",1,"desc",false)
    viewModel.deleteProduct(productEntity.id ?:-1)
    verify(deleteProductsUseCase).invoke(productEntity.id ?:-1)
  }

}

