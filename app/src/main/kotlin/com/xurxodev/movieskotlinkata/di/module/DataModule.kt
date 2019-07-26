package com.xurxodev.movieskotlinkata.di.module

import android.app.Application
import com.xurxodev.movieskotlinkata.data.FakeMovieRepository
import com.xurxodev.movieskotlinkata.domain.boundaries.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module class DataModule () {
    @Provides @Singleton
    fun providesMovieRepository(app: Application): MovieRepository = FakeMovieRepository(app)
}
