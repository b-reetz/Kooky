package com.kooky.feature.recipe

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.kooky.feature.recipe.add.RecipeInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RecipeListState(
    val text: String
)

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val interactor: RecipeInteractor
): StateViewModel<RecipeListState>(RecipeListState("")) {

    fun importFromUrlSelected() {
        viewModelScope.launch {
            val schema = interactor.scrapeFromUrl()
            Log.d("MYTAG", schema.toString())
        }
    }
}