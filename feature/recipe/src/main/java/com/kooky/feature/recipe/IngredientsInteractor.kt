package com.kooky.feature.recipe

import javax.inject.Inject

class IngredientsInteractor @Inject constructor(
    private val repository: IngredientsRepository
) {
    //TODO map ingredient object to what is required by view model
    fun getAllIngredientNames() = repository.getIngredients()
}