package com.kooky.feature.recipe

import com.kooky.infrastructure.viewmodel.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class RecipeListState(
    val text: String
)

@HiltViewModel
class RecipeListViewModel @Inject constructor() :
    StateViewModel<RecipeListState>(RecipeListState(""))