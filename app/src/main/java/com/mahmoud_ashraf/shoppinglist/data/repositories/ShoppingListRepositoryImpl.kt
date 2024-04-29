package com.mahmoud_ashraf.shoppinglist.data.repositories

import com.mahmoud_ashraf.shoppinglist.data.local.ShoppingListLocalDataSource
import com.mahmoud_ashraf.shoppinglist.data.local.models.ProductEntity
import com.mahmoud_ashraf.shoppinglist.domain.repository.ShoppingListRepository
import javax.inject.Inject

/**
 * Created by Mahmoud Ashraf on 23,April,2024
 */
class ShoppingListRepositoryImpl @Inject constructor(private val shoppingListLocalDataSource: ShoppingListLocalDataSource) : ShoppingListRepository {
  override suspend fun insertProduct(productEntity: ProductEntity) {
    return shoppingListLocalDataSource.insertProduct(productEntity)
  }

  override suspend fun updateProduct(productEntity: ProductEntity) {
    return shoppingListLocalDataSource.updateProduct(productEntity)
  }

  override fun getProducts(isBought : Boolean): List<ProductEntity> {
   return shoppingListLocalDataSource.getProducts(isBought)
  }

  override fun getProductsSorted(isBought: Boolean, asc : Boolean): List<ProductEntity> {
    return shoppingListLocalDataSource.getProductsSorted(isBought, asc)
  }

  override suspend fun deleteProduct(id: Int) {
    return shoppingListLocalDataSource.deleteProduct(id)
  }


}