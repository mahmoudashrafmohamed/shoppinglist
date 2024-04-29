package com.mahmoud_ashraf.shoppinglist.data.local

import com.mahmoud_ashraf.shoppinglist.data.local.models.ProductEntity

/**
 * Created by Mahmoud Ashraf on 23,April,2024
 */
interface ShoppingListLocalDataSource {
  suspend fun insertProduct(productEntity: ProductEntity)
  fun updateProduct(productEntity: ProductEntity)
  fun getProducts(isBought : Boolean): List<ProductEntity>
  fun getProductsSorted(isBought : Boolean, asc : Boolean): List<ProductEntity>
  suspend fun deleteProduct(id: Int)
}
