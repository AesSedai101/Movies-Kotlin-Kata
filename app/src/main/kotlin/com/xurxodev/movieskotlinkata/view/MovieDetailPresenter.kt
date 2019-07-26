package com.xurxodev.movieskotlinkata.view

import android.view.View
import com.xurxodev.movieskotlinkata.data.MovieRepository
import com.xurxodev.movieskotlinkata.model.Movie
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.CommonPool
import kotlinx.coroutines.android.UI
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.net.InterfaceAddress

class MovieDetailPresenter(private val movieRepository: MovieRepository) {
    private var viewInterface: ViewInterface? = null

    fun attach(viewInterface: ViewInterface, id: Long) {
        this.viewInterface = viewInterface;

        loadMovie(id)
    }

    fun detach() {
        viewInterface = null
    }


    private fun loadMovie(id: Long) {
        loadingMovie()

        launch(UI) {
            val movies = asyncLoadMovies(id).await()

            loadedMovie(movies)
        }
    }

    private fun asyncLoadMovies(id: Long) = async(CommonPool) {
        movieRepository.getById(id)
    }

    private fun loadingMovie() {
        viewInterface?.showLoading()
    }

    private fun loadedMovie(movie: Movie) {
        viewInterface?.hideLoading()

        viewInterface?.showMovie(movie.url, movie.title, movie.overview)
    }

    interface ViewInterface {
        fun showLoading()
        fun hideLoading()
        fun showMovie(url: String, title: String, overview: String)
    }
}
