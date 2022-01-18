package com.kooky.feature.add

import javax.inject.Inject

class RecipeInteractor @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend fun scrapeFromUrl(): RecipeSchema {
        return repository.scrapeFromUrl()
    }
}