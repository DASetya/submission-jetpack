package com.dasetya.mymoviesandtvs.viewmodel

import androidx.lifecycle.ViewModel
import com.dasetya.mymoviesandtvs.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavMovieViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    fun getFavMovie() = repository.getFav()

}