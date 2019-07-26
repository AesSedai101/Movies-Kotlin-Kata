package com.xurxodev.movieskotlinkata.presentation.view

import android.content.Context
import com.xurxodev.movieskotlinkata.domain.entities.Movie
import com.xurxodev.movieskotlinkata.presentation.presenter.boundary.Navigator

class SimpleNavigator(private val activityContext: Context) : Navigator {

    override fun openMovieDetail(movie: Movie) {

        val intent = MovieDetailActivity.newIntent(activityContext, movie.id)

        activityContext.startActivity(intent)
    }
}
