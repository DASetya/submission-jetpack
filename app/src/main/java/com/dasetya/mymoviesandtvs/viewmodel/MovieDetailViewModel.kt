package com.dasetya.mymoviesandtvs.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dasetya.mymoviesandtvs.entity.FavEntity
import com.dasetya.mymoviesandtvs.entity.MovieEntity
import com.dasetya.mymoviesandtvs.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    fun getMovie(id: Int): LiveData<MovieEntity> = repository.getMovie(id)

    fun getFavById(id: Int) = repository.getFavById(id)

    fun insertFav(Fav: FavEntity) = repository.insertFav(Fav)

    fun deleteFav(id: Int) = repository.deleteFav(id)

}