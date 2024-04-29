package com.mahmoud_ashraf.shoppinglist.domain.usecase

import com.mahmoud_ashraf.shoppinglist.domain.repository.ShoppingListRepository
import javax.inject.Inject

/**
 * Created by Mahmoud Ashraf on 29,April,2024
 */
class DeleteProductsUseCase @Inject constructor(private val shoppingListRepository: ShoppingListRepository) {
    suspend operator fun invoke(id : Int) = shoppingListRepository.deleteProduct(id)
}