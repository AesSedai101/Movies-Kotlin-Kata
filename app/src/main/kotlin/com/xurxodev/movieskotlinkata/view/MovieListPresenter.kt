package com.xurxodev.movieskotlinkata.view

import android.view.View
import com.xurxodev.moviesandroidkotlin.R
import com.xurxodev.movieskotlinkata.data.MovieRepository
import com.xurxodev.movieskotlinkata.model.Movie
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CommonPool
import kotlinx.coroutines.android.UI
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.net.InterfaceAddress

class MovieListPresenter(private val movieRepository: MovieRepository) {
    private var viewInterface: ViewInterface? = null

    fun attach(viewInterface: ViewInterface) {
        this.viewInterface = viewInterface
        loadMovies()
    }

    fun detach() {
        viewInterface = null
    }

    private fun loadMovies() {
        loadingMovies()

        launch(UI) {
            val movies = asyncLoadMovies().await()
            loadedMovies(movies)
        }
    }

    private fun asyncLoadMovies() = async(CommonPool) {
        movieRepository.getAll()
    }

    private fun loadingMovies() {
        viewInterface?.clearMovies()
        viewInterface?.showLoading()
    }

    private fun loadedMovies(movies: List<Movie>) {
        viewInterface?.hideLoading()
        viewInterface?.setMovies(movies)
        refreshTitleWithMoviesCount(movies)
    }

    fun refresh() {
        viewInterface?.clearMovies()
        loadMovies()
    }

    private fun refreshTitleWithMoviesCount(movies: List<Movie>) {
        viewInterface?.setMovieCount(movies.size)
    }

    interface ViewInterface {
        fun clearMovies()
        fun showLoading()
        fun hideLoading()
        fun setMovies(movies: List<Movie>)
        fun setMovieCount(size: Int)
    }
}
