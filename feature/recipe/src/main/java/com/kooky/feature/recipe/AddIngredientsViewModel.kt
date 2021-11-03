package com.kooky.feature.recipe

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class AddIngredientsState(
    val existingIngredients: List<String> = emptyList()
)

@HiltViewModel
class AddIngredientsViewModel @Inject constructor(
    private val interactor: IngredientsInteractor
) : StateViewModel<AddIngredientsState>(AddIngredientsState()) {

    fun saveIngredients(ingredientList: List<String>) {
        interactor.saveIngredients(ingredientList)
    }

    init {
        interactor.getAllIngredientNames()
            .onEachUpdateState { copy(existingIngredients = it) }
    }
}