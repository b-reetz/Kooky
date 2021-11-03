package com.kooky.feature.recipe

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IngredientsRepository @Inject constructor() {
    fun getIngredients(): Flow<List<String>> {
        return flow {  }
    }

    fun saveIngredients(ingredientList: List<String>) {
    }
}