package com.mahmoud_ashraf.shoppinglist.data.di

import android.content.Context
import androidx.room.Room
import com.mahmoud_ashraf.shoppinglist.data.local.ShoppingListDao
import com.mahmoud_ashraf.shoppinglist.data.local.ShoppingListDatabase
import com.mahmoud_ashraf.shoppinglist.data.local.models.ShoppingListLocalDataSourcesKeys.SHOPPING_LIST_DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

/**
 * Created by Mahmoud Ashraf on 23,April,2024
 */
@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
  @Provides
  @Singleton
  fun provideShoppingListDatabase(@ApplicationContext context: Context): ShoppingListDatabase {
    return  Room.databaseBuilder(
      context,
      ShoppingListDatabase::class.java,
      SHOPPING_LIST_DB_NAME
    )
      .fallbackToDestructiveMigration()
      .build()
  }
  @Provides
  @Singleton
  fun provideShoppingListDao(shoppingListDatabase: ShoppingListDatabase): ShoppingListDao{
    return shoppingListDatabase.shoppingListDao
  }

  @Provides
  @Singleton
  fun provideIODispatcher():CoroutineDispatcher{
    return Dispatchers.IO
  }
}