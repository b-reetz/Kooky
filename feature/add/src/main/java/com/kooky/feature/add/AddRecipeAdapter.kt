package com.kooky.feature.add

import it.skrape.core.htmlDocument
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.response
import it.skrape.fetcher.skrape
import it.skrape.selects.html5.script
import kotlinx.serialization.json.*
import javax.inject.Inject

class AddRecipeAdapter @Inject constructor() {

    private val json = Json { ignoreUnknownKeys = true }

    suspend fun scrapePage(urlToScrape: String): RecipeSchema {
        return skrape(HttpFetcher) {
            request { url = urlToScrape }

            response {
                htmlDocument {
                    val schemaScript = script {
                        withAttribute = "type" to "application/ld+json"
                        findFirst { this }
                    }

                    val recipeJsonElement = json.parseToJsonElement(schemaScript.html)
                        .jsonObject["@graph"]
                        ?.jsonArray
                        ?.firstOrNull {
                            it.jsonObject["@type"]
                                ?.jsonPrimitive
                                ?.content == "Recipe"
                        } ?: throw IllegalStateException("") //TODO return error

                    json.decodeFromJsonElement(recipeJsonElement)
                }
            }
        }
    }
}