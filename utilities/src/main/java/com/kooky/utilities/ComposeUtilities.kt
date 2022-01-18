package com.kooky.utilities

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier

typealias Mod = Modifier

val maxWidth = Mod.maxWidth
val Modifier.maxWidth: Modifier
    get() = this.fillMaxWidth()

val maxHeight = Mod.maxHeight
val Modifier.maxHeight: Modifier
    get() = this.fillMaxHeight()

val maxSize = Mod.maxSize
val Modifier.maxSize: Modifier
    get() = this.fillMaxSize()