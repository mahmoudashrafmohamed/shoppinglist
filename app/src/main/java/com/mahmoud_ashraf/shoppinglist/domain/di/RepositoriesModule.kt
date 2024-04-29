package com.mahmoud_ashraf.shoppinglist.domain.di

import com.mahmoud_ashraf.shoppinglist.data.local.ShoppingListDao
import com.mahmoud_ashraf.shoppinglist.data.local.ShoppingListLocalDataSource
import com.mahmoud_ashraf.shoppinglist.data.local.ShoppingListLocalDataSourceImpl
import com.mahmoud_ashraf.shoppinglist.data.repositories.ShoppingListRepositoryImpl
import com.mahmoud_ashraf.shoppinglist.domain.repository.ShoppingListRepository
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
object RepositoriesModule {
  @Provides
  @Singleton
  fun provideShoppingListRepo(shoppingListLocalDataSource: ShoppingListLocalDataSource): ShoppingListRepository {
    return ShoppingListRepositoryImpl(shoppingListLocalDataSource)
  }
}