package com.xurxodev.movieskotlinkata.presentation.presenter

import com.xurxodev.movieskotlinkata.domain.entities.Movie
import com.xurxodev.movieskotlinkata.domain.boundaries.MovieRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.android.Main
import kotlinx.coroutines.android.UI
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class MoviesDetailPresenter(private val movieRepository: MovieRepository, override val coroutineContext: CoroutineContext = EmptyCoroutineContext): CoroutineScope {

    var view: View? = null

    fun attachView(view: View, id:Long) {
        this.view = view

        loadMovie(id)
    }

    fun detachView() {
        this.view = null
    }

    private fun loadMovie(id: Long) {
        loadingMovie()

        launch(Dispatchers.Main) {
            val movie = asyncLoadMovie(id).await()

            showMovie(movie)
        }
    }

    private fun asyncLoadMovie(id: Long) = async(Dispatchers.Default) {
        movieRepository.getById(id)
    }

    private fun loadingMovie() {
        view?.showLoading()
    }

    private fun showMovie(movie: Movie) {
        view?.hideLoading()
        view?.showMovie(movie)
    }

    interface View {
        fun showMovie(movie: Movie)
        fun showLoading()
        fun hideLoading()
    }
}

