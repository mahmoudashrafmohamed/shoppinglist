package com.mahmoud_ashraf.shoppinglist.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mahmoud_ashraf.shoppinglist.data.local.models.ShoppingListLocalDataSourcesKeys.IS_PRODUCT_BOUGHT
import com.mahmoud_ashraf.shoppinglist.data.local.models.ShoppingListLocalDataSourcesKeys.PRODUCTS_TABLE
import com.mahmoud_ashraf.shoppinglist.data.local.models.ShoppingListLocalDataSourcesKeys.PRODUCT_DATE
import com.mahmoud_ashraf.shoppinglist.data.local.models.ShoppingListLocalDataSourcesKeys.PRODUCT_DESC
import com.mahmoud_ashraf.shoppinglist.data.local.models.ShoppingListLocalDataSourcesKeys.PRODUCT_NAME
import com.mahmoud_ashraf.shoppinglist.data.local.models.ShoppingListLocalDataSourcesKeys.PRODUCT_QUANTITY

/**
 * Created by Mahmoud Ashraf on 23,April,2024
 */
@Entity(tableName = PRODUCTS_TABLE)
data class ProductEntity(
  @ColumnInfo(name = PRODUCT_NAME) val name: String,
  @ColumnInfo(name = PRODUCT_QUANTITY) val quantity: Long,
  @ColumnInfo(name = PRODUCT_DESC) val desc: String,
  @ColumnInfo(name = IS_PRODUCT_BOUGHT) val isBought: Boolean,
  @PrimaryKey(autoGenerate = true) val id: Int? = null,
  @ColumnInfo(name = PRODUCT_DATE) val timestamp: Long = System.currentTimeMillis(),
)
