package com.mahmoud_ashraf.shoppinglist.data.di

import com.mahmoud_ashraf.shoppinglist.data.local.ShoppingListDao
import com.mahmoud_ashraf.shoppinglist.data.local.ShoppingListLocalDataSource
import com.mahmoud_ashraf.shoppinglist.data.local.ShoppingListLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Mahmoud Ashraf on 29,April,2024
 */
@Module
@InstallIn(SingletonComponent::class)
object ShoppingListLocalDataSourceModule {
  @Provides
  @Singleton
  fun provideShoppingListLocalDataSource(shoppingListDao: ShoppingListDao): ShoppingListLocalDataSource {
    return ShoppingListLocalDataSourceImpl(shoppingListDao)
  }
}