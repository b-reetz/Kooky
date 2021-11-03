package com.kooky.feature.recipe

import javax.inject.Inject

data class AddIngredientsState(
    val existingIngredients: List<String> = emptyList()
)

class AddIngredientsViewModel @Inject constructor(
    interactor: IngredientsInteractor
) : StateViewModel<AddIngredientsState>(AddIngredientsState()) {

    init {
        interactor.getAllIngredientNames()
            .onEachUpdateState { copy(existingIngredients = it) }
    }
}