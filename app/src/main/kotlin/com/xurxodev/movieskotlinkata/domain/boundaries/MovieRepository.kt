package com.xurxodev.movieskotlinkata.domain.boundaries

import com.xurxodev.movieskotlinkata.domain.entities.Movie

interface MovieRepository {
    fun getAll (): List<Movie>
    fun getById (id: Long): Movie
}