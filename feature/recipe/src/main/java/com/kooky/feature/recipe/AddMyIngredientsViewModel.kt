package com.kooky.feature.recipe

import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

enum class Measure(val abbreviation: String, val displayName: String) {
    CUP("c", "cup"),
    TEASPOON("tsp", "teaspoon"),
    TABLESPOON("Tbsp", "tablespoon"),
    PINCH("pinch", "pinch")
}

data class IngredientsTest(
    val name: String? = null,
    val quantity: String? = null,
    val measure: Measure = Measure.CUP,
    val id: String = UUID.randomUUID().toString()
) {
    fun getFields() = Triple(name, quantity, measure)
}

data class AddMyIngredientsState(
    val ingredients: List<IngredientsTest> = listOf(IngredientsTest())
)

@HiltViewModel
class AddMyIngredientsViewModel @Inject constructor() :
    StateViewModel<AddMyIngredientsState>(AddMyIngredientsState()) {

    fun onIngredientAdded(name: String, quantity: String) {
        updateState {
            copy(
                ingredients = ingredients.plus(
                    IngredientsTest(name = name, quantity = quantity)
                )
            )
        }
    }

    fun onIngredientUpdated(ingredient: IngredientsTest) {
        val indexOfExisting = state.ingredients.indexOfFirst { it.id == ingredient.id }
        val newList = state.ingredients.toMutableList().apply { set(indexOfExisting, ingredient) }
        if (newList.last().quantity != null && newList.last().name.isNotNullOrBlank()) newList.add(IngredientsTest())
        updateState { copy(ingredients = newList) }
    }

    fun onIngredientDismissed(ingredient: IngredientsTest) {
        val indexOfExisting = state.ingredients.indexOfFirst { it.id == ingredient.id }
        val newList = state.ingredients.toMutableList().apply { removeAt(indexOfExisting) }
        updateState { copy(ingredients = newList) }
    }
}