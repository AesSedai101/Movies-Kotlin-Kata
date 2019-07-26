package com.xurxodev.movieskotlinkata.presentation.presenter.boundary

import com.xurxodev.movieskotlinkata.domain.entities.Movie

interface Navigator {
    fun openMovieDetail(movie: Movie)
}
