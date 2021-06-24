package com.dasetya.mymoviesandtvs.remote


import com.dasetya.mymoviesandtvs.entity.MovieEntity
import com.dasetya.mymoviesandtvs.response.MovieResponse
import com.dasetya.mymoviesandtvs.entity.TvEntity
import com.dasetya.mymoviesandtvs.response.TvResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AppService {
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String?): Call<MovieResponse>

    @GET("movie/{id}")
    fun getMovie(
        @Path("id") id: Int?,
        @Query("api_key") apiKey: String?
    ): Call<MovieEntity>

    @GET("tv/popular")
    fun getPopularTv(@Query("api_key") apiKey: String?): Call<TvResponse>

    @GET("tv/{id}")
    fun getTv(
        @Path("id") id: Int?,
        @Query("api_key") apiKey: String?
    ): Call<TvEntity>
}