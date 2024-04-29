package com.mahmoud_ashraf.shoppinglist.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mahmoud_ashraf.shoppinglist.data.local.models.ProductEntity
import com.mahmoud_ashraf.shoppinglist.data.local.models.ShoppingListLocalDataSourcesKeys.IS_PRODUCT_BOUGHT
import com.mahmoud_ashraf.shoppinglist.data.local.models.ShoppingListLocalDataSourcesKeys.PRODUCT_DATE

/**
 * Created by Mahmoud Ashraf on 23,April,2024
 */

@Dao
interface ShoppingListDao {
  @Insert
  fun insertProduct(productEntity: ProductEntity)

  @Update
  fun updateProduct(productEntity: ProductEntity)

  @Query("SELECT * FROM products WHERE $IS_PRODUCT_BOUGHT = :isBought")
  fun getProducts(isBought: Int): List<ProductEntity>

  @Query("SELECT * FROM products WHERE $IS_PRODUCT_BOUGHT = :isBought ORDER BY $PRODUCT_DATE DESC")
  fun getProductsSortedDesc(isBought: Int): List<ProductEntity>

  @Query("SELECT * FROM products WHERE $IS_PRODUCT_BOUGHT = :isBought ORDER BY $PRODUCT_DATE ASC")
  fun getProductsSortedAsc(isBought: Int): List<ProductEntity>

  @Query("DELETE FROM products WHERE id = :id")
  fun deleteProduct(id: Int)
}