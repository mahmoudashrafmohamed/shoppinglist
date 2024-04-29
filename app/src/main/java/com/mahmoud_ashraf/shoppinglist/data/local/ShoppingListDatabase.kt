package com.mahmoud_ashraf.shoppinglist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mahmoud_ashraf.shoppinglist.data.local.models.ProductEntity
import com.mahmoud_ashraf.shoppinglist.data.local.models.ShoppingListLocalDataSourcesKeys.SHOPPING_LIST_DB_VERSION

/**
 * Created by Mahmoud Ashraf on 23,April,2024
 */
@Database(entities = [ProductEntity::class], version = SHOPPING_LIST_DB_VERSION, exportSchema = false)
abstract class ShoppingListDatabase : RoomDatabase() {
  abstract val shoppingListDao : ShoppingListDao
}