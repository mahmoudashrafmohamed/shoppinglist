package com.mahmoud_ashraf.shoppinglist.data.repositories

import com.mahmoud_ashraf.shoppinglist.MainCoroutineRule
import com.mahmoud_ashraf.shoppinglist.data.local.ShoppingListLocalDataSource
import com.mahmoud_ashraf.shoppinglist.data.local.models.ProductEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`


/**
 * Created by Mahmoud Ashraf on 29,April,2024
 */
class ShoppingListRepositoryImplTest{

  private val shoppingListLocalDataSource = Mockito.mock(ShoppingListLocalDataSource::class.java)
  private val repo = ShoppingListRepositoryImpl(shoppingListLocalDataSource)

  @JvmField
  @ExperimentalCoroutinesApi
  val mainCoroutineRule = MainCoroutineRule()

  @Test
  fun insertProduct() = runBlockingTest {
    val productEntity = ProductEntity("test",1,"desc",false)
    repo.insertProduct(productEntity)
    verify(shoppingListLocalDataSource).insertProduct(productEntity)
  }

  @Test
  fun updateProduct() = runBlockingTest {
    val productEntity = ProductEntity("test",1,"desc",false)
    repo.updateProduct(productEntity)
    verify(shoppingListLocalDataSource).updateProduct(productEntity)
  }

  @Test
  fun deleteProduct() = runBlockingTest {
    val productEntity = ProductEntity("test",1,"desc",false)
    repo.deleteProduct(productEntity.id?.toInt()?:-1)
    verify(shoppingListLocalDataSource).deleteProduct(productEntity.id?.toInt()?:-1)
  }

  @Test
  fun getProductList() {
    val product = emptyList<ProductEntity>()
    `when`(shoppingListLocalDataSource.getProducts(false)).thenReturn(product)
    repo.getProducts(false)
    verify(shoppingListLocalDataSource).getProducts(false)
  }
}