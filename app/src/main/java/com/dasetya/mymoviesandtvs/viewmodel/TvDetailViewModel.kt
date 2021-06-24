package com.dasetya.mymoviesandtvs.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dasetya.mymoviesandtvs.entity.FavEntity
import com.dasetya.mymoviesandtvs.entity.TvEntity
import com.dasetya.mymoviesandtvs.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvDetailViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    fun getFavById(id: Int) = repository.getFavById(id)

    fun insertFav(Fav: FavEntity) = repository.insertFav(Fav)

    fun deleteFav(id: Int) = repository.deleteFav(id)

    fun getTv(id:Int): LiveData<TvEntity> = repository.getTv(id)

}