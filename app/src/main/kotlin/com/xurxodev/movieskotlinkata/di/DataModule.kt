package com.xurxodev.movieskotlinkata.di

import android.app.Application
import com.xurxodev.movieskotlinkata.data.FakeMovieRepository
import com.xurxodev.movieskotlinkata.data.MovieRepository
import com.xurxodev.movieskotlinkata.view.MovieDetailPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module class DataModule () {
    @Provides @Singleton
    fun providesMovieRepository(app: Application): MovieRepository = FakeMovieRepository(app)

    @Provides @Singleton
    fun providesMovieDetailPresenter(movieRepository: MovieRepository): MovieDetailPresenter = MovieDetailPresenter(movieRepository)
}
