package com.mahmoud_ashraf.shoppinglist.domain.usecase

import com.mahmoud_ashraf.shoppinglist.data.local.models.ProductEntity
import com.mahmoud_ashraf.shoppinglist.domain.repository.ShoppingListRepository
import javax.inject.Inject

/**
 * Created by Mahmoud Ashraf on 29,April,2024
 */
class GetProductsUseCase @Inject constructor(private val shoppingListRepository: ShoppingListRepository) {
     operator fun invoke(isBought : Boolean) = shoppingListRepository.getProducts(isBought)
}