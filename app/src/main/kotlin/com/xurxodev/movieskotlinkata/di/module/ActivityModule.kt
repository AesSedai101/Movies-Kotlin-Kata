package com.xurxodev.movieskotlinkata.di.module

import android.app.Activity
import android.content.Context
import com.xurxodev.movieskotlinkata.di.scope.ActivityScope
import com.xurxodev.movieskotlinkata.presentation.presenter.MoviesDetailPresenter
import com.xurxodev.movieskotlinkata.presentation.presenter.MoviesListPresenter
import com.xurxodev.movieskotlinkata.domain.boundaries.MovieRepository
import com.xurxodev.movieskotlinkata.presentation.presenter.boundary.Navigator
import com.xurxodev.movieskotlinkata.presentation.view.SimpleNavigator
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activityContext: Activity) {

    @Provides @ActivityScope
    fun provideActivityContext(): Context {
        return activityContext
    }

    @Provides @ActivityScope
    fun provideNavigator(activityContext: Context): Navigator {
        return SimpleNavigator(activityContext)
    }

    @Provides @ActivityScope
    fun providesMovieListPresenter(movieRepository: MovieRepository,
                                   navigator:Navigator): MoviesListPresenter
            = MoviesListPresenter(movieRepository, navigator)

    @Provides @ActivityScope
    fun providesMoviesDetailPresenter(movieRepository: MovieRepository): MoviesDetailPresenter
            = MoviesDetailPresenter(movieRepository)
}
