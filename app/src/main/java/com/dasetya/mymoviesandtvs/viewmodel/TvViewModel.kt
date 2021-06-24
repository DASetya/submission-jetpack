package com.dasetya.mymoviesandtvs.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dasetya.mymoviesandtvs.entity.TvEntity
import com.dasetya.mymoviesandtvs.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    fun getTvs(): LiveData<List<TvEntity>> = repository.getPopularTv()

}