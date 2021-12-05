package com.kooky

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dev.enro.annotations.NavigationComponent
import dev.enro.core.controller.NavigationApplication
import dev.enro.core.controller.navigationController
import timber.log.Timber

@HiltAndroidApp
@NavigationComponent
class KookyApplication : Application(), NavigationApplication {
    override val navigationController = navigationController()

    init {
        Timber.plant(Timber.DebugTree())
    }
}