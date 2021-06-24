package com.dasetya.mymoviesandtvs.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dasetya.mymoviesandtvs.data.LocalDataSource
import com.dasetya.mymoviesandtvs.entity.FavEntity
import com.dasetya.mymoviesandtvs.entity.MovieEntity
import com.dasetya.mymoviesandtvs.entity.TvEntity
import com.dasetya.mymoviesandtvs.remote.RemoteDataCallback
import com.dasetya.mymoviesandtvs.remote.RemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    fun getFav(): LiveData<PagedList<FavEntity>> =
        LivePagedListBuilder(localDataSource.getFav(), 5).build()

    fun getFavById(id: Int) = localDataSource.getFavById(id)

    fun insertFav(fav: FavEntity) = localDataSource.insertFav(fav)

    fun deleteFav(id: Int) = localDataSource.deleteFav(id)

    fun getPopularMovies(): LiveData<List<MovieEntity>> {
        val movieResult = MutableLiveData<List<MovieEntity>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getPopularMovies(object : RemoteDataCallback<List<MovieEntity>> {
                override fun onDataReceived(response: List<MovieEntity>) {
                    val movieList = ArrayList<MovieEntity>()
                    for (data in response) {
                        val movie = MovieEntity(
                            data.overview,
                            data.title,
                            data.posterPath,
                            data.backdropPath,
                            data.releaseDate,
                            data.voteAverage,
                            data.id
                        )
                        movieList.add(movie)
                    }
                    movieResult.postValue(movieList.take(10))
                }
            })
        }
        return movieResult
    }

    fun getMovie(id: Int?): LiveData<MovieEntity> {
        val movieResult = MutableLiveData<MovieEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getMovie(id, object : RemoteDataCallback<MovieEntity> {
                override fun onDataReceived(response: MovieEntity) {
                    val movie = MovieEntity(
                        response.overview,
                        response.title,
                        response.posterPath,
                        response.backdropPath,
                        response.releaseDate,
                        response.voteAverage,
                        response.id
                    )
                    movieResult.postValue(movie)
                }
            })
        }
        return movieResult
    }

    fun getPopularTv(): LiveData<List<TvEntity>> {
        val TvResult = MutableLiveData<List<TvEntity>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getPopularTv(object : RemoteDataCallback<List<TvEntity>> {
                override fun onDataReceived(response: List<TvEntity>) {
                    val Tvist = ArrayList<TvEntity>()
                    for (data in response) {
                        val Tv = TvEntity(
                            data.overview,
                            data.name,
                            data.posterPath,
                            data.backdropPath,
                            data.firstAirDate,
                            data.voteAverage,
                            data.id
                        )
                        Tvist.add(Tv)
                    }
                    TvResult.postValue(Tvist.take(10))
                }
            })
        }
        return TvResult
    }

    fun getTv(id: Int?): LiveData<TvEntity> {
        val TvResult = MutableLiveData<TvEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getTv(id, object : RemoteDataCallback<TvEntity> {
                override fun onDataReceived(response: TvEntity) {
                    val Tv = TvEntity(
                        response.overview,
                        response.name,
                        response.posterPath,
                        response.backdropPath,
                        response.firstAirDate,
                        response.voteAverage,
                        response.id
                    )
                    TvResult.postValue(Tv)
                }
            })
        }
        return TvResult
    }
}