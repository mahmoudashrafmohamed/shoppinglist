package com.mahmoud_ashraf.shoppinglist.domain.di

import com.mahmoud_ashraf.shoppinglist.data.local.ShoppingListLocalDataSource
import com.mahmoud_ashraf.shoppinglist.data.repositories.ShoppingListRepositoryImpl
import com.mahmoud_ashraf.shoppinglist.domain.repository.ShoppingListRepository
import com.mahmoud_ashraf.shoppinglist.domain.usecase.CreateProductUseCase
import com.mahmoud_ashraf.shoppinglist.domain.usecase.DeleteProductsUseCase
import com.mahmoud_ashraf.shoppinglist.domain.usecase.GetProductsUseCase
import com.mahmoud_ashraf.shoppinglist.domain.usecase.GetSortedProductsUseCase
import com.mahmoud_ashraf.shoppinglist.domain.usecase.UpdateProductUseCase
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
object UseCasesModule {
  @Provides
  @Singleton
  fun provideCreateProductUseCase(shoppingListRepository: ShoppingListRepository): CreateProductUseCase {
    return CreateProductUseCase(shoppingListRepository)
  }
  @Provides
  @Singleton
  fun provideGetProductsUseCase(shoppingListRepository: ShoppingListRepository): GetProductsUseCase {
    return GetProductsUseCase(shoppingListRepository)
  }
  @Provides
  @Singleton
  fun provideGetProductsSortedUseCase(shoppingListRepository: ShoppingListRepository): GetSortedProductsUseCase {
    return GetSortedProductsUseCase(shoppingListRepository)
  }
  @Provides
  @Singleton
  fun provideDeleteProductsUseCase(shoppingListRepository: ShoppingListRepository): DeleteProductsUseCase {
    return DeleteProductsUseCase(shoppingListRepository)
  }
  @Provides
  @Singleton
  fun provideUpdateProductUseCase(shoppingListRepository: ShoppingListRepository): UpdateProductUseCase {
    return UpdateProductUseCase(shoppingListRepository)
  }
}