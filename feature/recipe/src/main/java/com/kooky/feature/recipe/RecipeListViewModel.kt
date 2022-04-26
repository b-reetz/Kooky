package com.kooky.feature.recipe

import com.kooky.viewmodel.StateViewModel
import com.kooky.viewmodel.StateViewModelConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class RecipeListState(
    val text: String
)

@HiltViewModel
class RecipeListViewModel @Inject constructor(): StateViewModel<RecipeListState>() {
    override val config = configure(RecipeListState(""))
}