package com.kooky.feature.recipe

import android.util.Log
import com.kooky.data.Ingredient
import com.kooky.data.IngredientQueries
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IngredientsRepository @Inject constructor(
    private val ingredients: IngredientQueries
) {

    fun getAllIngredients(): Flow<List<Ingredient>> {
        return ingredients.selectAll()
            .asFlow()
            .mapToList()
    }

    fun getIngredients(filterString: String = "", limit: Int = 0): Flow<List<Ingredient>> {
        return ingredients.selectByName(filterString, limit.toLong())
            .asFlow()
            .mapToList()
    }

    fun saveIngredients(ingredientList: List<String>) {
        Log.d("INGREDIENTS", "Saving $ingredientList")
        ingredientList.forEach {
            ingredients.insertOrReplace(it)
        }
    }
}