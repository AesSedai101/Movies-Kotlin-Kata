package com.xurxodev.movieskotlinkata;

import android.app.Application
import org.codejargon.feather.Feather

class App : Application() {

    val feather: Feather by lazy {
        Feather.with(AppModule(this))
    }

}