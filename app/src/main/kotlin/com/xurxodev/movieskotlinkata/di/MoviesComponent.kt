package com.xurxodev.movieskotlinkata.di

import com.xurxodev.movieskotlinkata.view.MovieDetailActivity
import com.xurxodev.movieskotlinkata.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, DataModule::class))
interface MoviesComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainActivityMovie: MovieDetailActivity)
}

