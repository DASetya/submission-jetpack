package com.dasetya.mymoviesandtvs.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "FavEntity")
data class FavEntity(

    @ColumnInfo(name = "category")
    @field:SerializedName("category")
    val category: String? = null,

    @ColumnInfo(name = "title")
    @field:SerializedName("title")
    val title: String? = null,

    @ColumnInfo(name = "poster_path")
    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @ColumnInfo(name = "release_date")
    @field:SerializedName("release_date")
    val releaseDate: String? = null,

    @ColumnInfo(name = "vote_average")
    @field:SerializedName("vote_average")
    val voteAverage: Double = 0.0,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @field:SerializedName("id")
    val id: Int? = null

)