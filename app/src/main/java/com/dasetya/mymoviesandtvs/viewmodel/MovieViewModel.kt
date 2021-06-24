package com.dasetya.mymoviesandtvs.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dasetya.mymoviesandtvs.entity.MovieEntity
import com.dasetya.mymoviesandtvs.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    fun getMovies(): LiveData<List<MovieEntity>> = repository.getPopularMovies()

}