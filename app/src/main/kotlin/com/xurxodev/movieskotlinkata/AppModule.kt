package com.xurxodev.movieskotlinkata;

import android.app.Application
import com.xurxodev.movieskotlinkata.data.FakeMovieRepository
import com.xurxodev.movieskotlinkata.data.MovieRepository
import org.codejargon.feather.Provides
import javax.inject.Singleton

class AppModule(private val app: Application) {

    @Provides
    @Singleton
    fun movieRepository(): MovieRepository {
        return FakeMovieRepository(app)
    }
}