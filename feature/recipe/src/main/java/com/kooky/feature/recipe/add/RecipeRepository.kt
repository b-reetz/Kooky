package com.kooky.feature.recipe.add

import kotlinx.serialization.Serializable
import javax.inject.Inject

class RecipeRepository @Inject constructor(private val adapter: RecipeAdapter) {
    suspend fun scrapeFromUrl(): RecipeSchema {
        return adapter.scrapePage("http://cookieandkate.com/vegan-sour-cream-recipe")
    }
}

@Serializable
data class RecipeSchema(
    val name: String,
    val description: String,
    val image: List<String>,
    val recipeYield: List<String>,
    val prepTime: String,
    val totalTime: String,
    val recipeIngredient: List<String>,
    val recipeInstructions: List<HowToStep>,
    val aggregateRating: AggregateRating
) {
    @Serializable
    data class HowToStep(val text: String)

    @Serializable
    data class AggregateRating(val ratingValue: String)
}