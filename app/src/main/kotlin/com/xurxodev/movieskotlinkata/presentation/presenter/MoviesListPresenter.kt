package com.xurxodev.movieskotlinkata.presentation.presenter

import com.xurxodev.movieskotlinkata.domain.entities.Movie
import com.xurxodev.movieskotlinkata.domain.boundaries.MovieRepository
import com.xurxodev.movieskotlinkata.presentation.presenter.boundary.Navigator
import kotlinx.coroutines.*
import kotlinx.coroutines.android.Main
import kotlinx.coroutines.android.UI
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class MoviesListPresenter(private val movieRepository: MovieRepository,
                          private val navigator: Navigator,
                          override val coroutineContext: CoroutineContext = EmptyCoroutineContext): CoroutineScope {

    var view: View? = null

    fun attachView(view: View) {
        this.view = view

        loadMovies()
    }

    fun detachView() {
        this.view = null
    }

    fun onRefreshAction() {
        loadMovies()
    }

    fun  onMovieClicked(movie: Movie) {
        navigator.openMovieDetail(movie)
    }

    private fun loadMovies() {
        loadingMovies()

        launch(Dispatchers.Main) {
            val movies = asyncLoadMovies().await()

            showMovies(movies)
        }
    }

    private fun asyncLoadMovies() = async(CommonPool) {
        movieRepository.getAll()
    }

    private fun loadingMovies() {
        view?.showLoading()
        view?.clearMovies()
    }

    private fun showMovies(movies: List<Movie>) {
        view?.hideLoading()
        view?.showMovies(movies)
        view?.showTotalMovies(movies.size)
    }

    interface View {
        fun showMovies(movies: List<Movie>)
        fun clearMovies()
        fun showLoading()
        fun hideLoading()
        fun showTotalMovies(count: Int)
    }


}

