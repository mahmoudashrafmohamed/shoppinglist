package com.mahmoud_ashraf.shoppinglist.domain.repository

import com.mahmoud_ashraf.shoppinglist.data.local.models.ProductEntity

/**
 * Created by Mahmoud Ashraf on 23,April,2024
 */
interface ShoppingListRepository {
  suspend fun insertProduct(productEntity: ProductEntity)
  suspend fun updateProduct(productEntity: ProductEntity)
  fun getProducts(isBought : Boolean): List<ProductEntity>
  fun getProductsSorted(isBought : Boolean, asc : Boolean): List<ProductEntity>
  suspend fun deleteProduct(id: Int)
}