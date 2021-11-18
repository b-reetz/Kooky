package com.kooky.feature.recipe

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class IngredientsInteractor @Inject constructor(
    private val repository: IngredientsRepository
) {
    fun getAllIngredientNames() = repository.getIngredients()
        .mapEachItem { it.name }

    fun saveIngredients(ingredientList: List<String>) = repository.saveIngredients(ingredientList)
}

fun <T, R> Flow<List<T>>.mapEachItem(block: (T) -> R): Flow<List<R>>  {
    return map { list -> list.map(block) }
}