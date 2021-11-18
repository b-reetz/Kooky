package com.kooky.feature.recipe

import android.util.Log
import com.kooky.data.Ingredient
import com.kooky.data.IngredientQueries
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class IngredientsRepository @Inject constructor(
    private val ingredients: IngredientQueries
) {
    init {
        ingredients.empty()
    }

    fun getIngredients(): Flow<List<Ingredient>> {
        return ingredients.selectAll()
            .asFlow()
            .mapToList()
            .onEach {
                Log.d("Ingredients", "$it")
            }
    }

    fun saveIngredients(ingredientList: List<String>) {
        ingredients.transaction {
            ingredientList.forEach {
                ingredients.insertOrReplace(it)
            }
        }
    }
}