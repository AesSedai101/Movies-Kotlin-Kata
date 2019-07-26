package com.xurxodev.movieskotlinkata.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.xurxodev.moviesandroidkotlin.R
import com.xurxodev.movieskotlinkata.App
import com.xurxodev.movieskotlinkata.data.MovieRepository
import com.xurxodev.movieskotlinkata.model.Movie
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CommonPool
import kotlinx.coroutines.android.UI
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListActivity : AppCompatActivity(), MovieListPresenter.ViewInterface {
    @Inject lateinit var movieListPresenter: MovieListPresenter

    lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).moviesComponent.inject(this)

        initializeRecyclerView()
        initializeRefreshButton()

        movieListPresenter.attach(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        movieListPresenter.detach()
    }

    private fun initializeRecyclerView() {
        this.itemAdapter = ItemAdapter() { item ->
            startActivity(MovieDetailActivity.getIntent(this, item.id))
        }

        recycler.adapter = itemAdapter
    }

    private fun initializeRefreshButton() = refresh_button.setOnClickListener { movieListPresenter.refresh() }

    override fun clearMovies() {
        itemAdapter.clearMovies();
    }

    override fun showLoading() {
        pb_loading.visibility = View.VISIBLE
        movies_title_text_view.text =getString(R.string.loading_movies_text);
    }

    override fun setMovies(movies: List<Movie>) {
        itemAdapter.setMovies(movies)
    }

    override fun hideLoading() {
        pb_loading.visibility = View.GONE
    }

    override fun setMovieCount(size: Int) {
        val countText = getString(R.string.movies_count_text)
        movies_title_text_view.text = String.format(countText, size)
    }
}