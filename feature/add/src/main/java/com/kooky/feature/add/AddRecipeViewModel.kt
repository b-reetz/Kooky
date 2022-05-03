package com.kooky.feature.add

import com.kooky.navigation.RecipeAddKey
import com.kooky.viewmodel.StateViewModel
import dev.enro.core.result.registerForNavigationResult
import dev.enro.viewmodel.navigationHandle
import javax.inject.Inject

data class AddRecipeState(
    val ingredients: List<IngredientsTest> = emptyList(),
    val description: List<String> = emptyList()
)

class AddRecipeViewModel @Inject constructor(): StateViewModel<AddRecipeState>() {
    override val config = configure(AddRecipeState())

    private val navigation by navigationHandle<RecipeAddKey>()

    private val editIngredients by registerForNavigationResult<List<IngredientsTest>>(navigation) {
        updateState { copy(ingredients = it) }
    }

    private val editDescription by registerForNavigationResult<List<String>>(navigation) {
        updateState { copy(description = it) }
    }

    fun onIngredientsSelected() {
        editIngredients.open(AddIngredientsKey(state.ingredients))
    }

    fun onDescriptionSelected() {
        editDescription.open(AddDescriptionKey(state.description))
    }
}