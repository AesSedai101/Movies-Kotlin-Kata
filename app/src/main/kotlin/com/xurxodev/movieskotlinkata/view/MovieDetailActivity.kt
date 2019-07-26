package com.xurxodev.movieskotlinkata.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.xurxodev.moviesandroidkotlin.R
import com.xurxodev.movieskotlinkata.App
import com.xurxodev.movieskotlinkata.model.Movie
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.CommonPool
import kotlinx.coroutines.android.UI
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailActivity : AppCompatActivity(), MovieDetailPresenter.ViewInterface {

    companion object {
        fun getIntent(context: Context, id: Long): Intent? {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(EXTRA_ID, id)
            return intent;
        }

        val EXTRA_ID = "MovieDetailActivity:id"

    }

    @Inject lateinit var presenter: MovieDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        (application as App).moviesComponent.inject(this)

        val id = intent.getLongExtra(EXTRA_ID, -1)

        presenter.attach(this, id);
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    override fun showLoading() {
        movie_detail_container.visibility = View.GONE
        pb_loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_loading.visibility = View.GONE
        movie_detail_container.visibility = View.VISIBLE
    }

    override fun showMovie(url: String, title: String, overview: String) {
        item_image.loadUrl(url)
        item_title.text = title
        item_overview_content.text = overview
    }
}
