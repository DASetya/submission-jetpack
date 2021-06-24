package com.dasetya.mymoviesandtvs.remote

import com.dasetya.mymoviesandtvs.BuildConfig.API_TOKEN
import com.dasetya.mymoviesandtvs.entity.MovieEntity
import com.dasetya.mymoviesandtvs.entity.TvEntity
import com.dasetya.mymoviesandtvs.utils.EspressoIdlingResource
import retrofit2.await
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val appService: AppService
) {

    suspend fun getPopularMovies(callback: RemoteDataCallback<List<MovieEntity>>) {
        EspressoIdlingResource.increment()
        appService.getPopularMovies(API_TOKEN).await().results.let {
            if (it != null) {
                callback.onDataReceived(it)
                EspressoIdlingResource.decrement()
            }
        }
    }

    suspend fun getMovie(id: Int?, callback: RemoteDataCallback<MovieEntity>) {
        EspressoIdlingResource.increment()
        appService.getMovie(id, API_TOKEN).await().let {
            callback.onDataReceived(it)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getPopularTv(callback: RemoteDataCallback<List<TvEntity>>) {
        EspressoIdlingResource.increment()
        appService.getPopularTv(API_TOKEN).await().results.let {
            if (it != null) {
                callback.onDataReceived(it)
                EspressoIdlingResource.decrement()
            }
        }
    }

    suspend fun getTv(id: Int?, callback: RemoteDataCallback<TvEntity>) {
        EspressoIdlingResource.increment()
        appService.getTv(id, API_TOKEN).await().let {
            callback.onDataReceived(it)
            EspressoIdlingResource.decrement()
        }
    }

}