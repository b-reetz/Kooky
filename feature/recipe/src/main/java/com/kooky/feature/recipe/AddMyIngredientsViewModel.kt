package com.kooky.feature.recipe

import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

data class IngredientsTest(
    val name: String,
    val quantity: Int,
    val id: String = UUID.randomUUID().toString()
)

data class AddMyIngredientsState(
    val ingredients: List<IngredientsTest> = emptyList()
)

@HiltViewModel
class AddMyIngredientsViewModel @Inject constructor() :
    StateViewModel<AddMyIngredientsState>(AddMyIngredientsState()) {

    fun onIngredientAdded(name: String, quantity: Int) {
        updateState {
            copy(
                ingredients = ingredients.plus(
                    IngredientsTest(name = name, quantity = quantity)
                )
            )
        }
    }
}