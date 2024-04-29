package com.mahmoud_ashraf.shoppinglist.data.local

import com.mahmoud_ashraf.shoppinglist.data.local.models.ProductEntity
import javax.inject.Inject

/**
 * Created by Mahmoud Ashraf on 23,April,2024
 */
class ShoppingListLocalDataSourceImpl @Inject constructor(private val shoppingListDao: ShoppingListDao) : ShoppingListLocalDataSource {
  override suspend fun insertProduct(productEntity: ProductEntity) {
    shoppingListDao.insertProduct(productEntity)
  }

  override fun updateProduct(productEntity: ProductEntity) {
    shoppingListDao.updateProduct(productEntity)
  }

  override fun getProducts(isBought : Boolean): List<ProductEntity> {
   return shoppingListDao.getProducts(if (isBought) 1 else 0)
  }

  override fun getProductsSorted(isBought: Boolean, asc : Boolean): List<ProductEntity> {
    return if (asc)shoppingListDao.getProductsSortedAsc(if (isBought) 1 else 0)
    else shoppingListDao.getProductsSortedDesc(if (isBought) 1 else 0)
  }

  override suspend fun deleteProduct(id: Int) {
    return shoppingListDao.deleteProduct(id)
  }
}