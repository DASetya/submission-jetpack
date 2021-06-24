package com.dasetya.mymoviesandtvs.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dasetya.mymoviesandtvs.entity.FavEntity

@Dao
interface FavDao {

    @Query("SELECT * FROM FavEntity")
    fun getAllFav(): DataSource.Factory<Int, FavEntity>

    @Query("SELECT * FROM FavEntity WHERE id = :id")
    fun getFavById(id: Int?): LiveData<FavEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFav(favorite: FavEntity?)

    @Query("DELETE FROM FavEntity WHERE id = :id")
    fun deleteFav(id: Int?): Int

}