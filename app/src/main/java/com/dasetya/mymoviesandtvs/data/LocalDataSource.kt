package com.dasetya.mymoviesandtvs.data

import androidx.paging.DataSource
import com.dasetya.mymoviesandtvs.entity.FavEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val appDatabase: AppDatabase,
) {

    fun getFav(): DataSource.Factory<Int, FavEntity> = appDatabase.favDao().getAllFav()

    fun getFavById(id: Int) = appDatabase.favDao().getFavById(id)

    fun insertFav(fav: FavEntity) =
        appDatabase.favDao().insertFav(fav)

    fun deleteFav(id: Int) = appDatabase.favDao().deleteFav(id)
}