package com.dasetya.mymoviesandtvs.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dasetya.mymoviesandtvs.data.DataDummy
import com.dasetya.mymoviesandtvs.data.LocalDataSource
import com.dasetya.mymoviesandtvs.entity.FavEntity
import com.dasetya.mymoviesandtvs.entity.MovieEntity
import com.dasetya.mymoviesandtvs.entity.TvEntity
import com.dasetya.mymoviesandtvs.remote.RemoteDataCallback
import com.dasetya.mymoviesandtvs.remote.RemoteDataSource
import com.dasetya.mymoviesandtvs.utils.LiveDataTestUtil
import com.dasetya.mymoviesandtvs.utils.PagedListUtil
import com.nhaarman.mockitokotlin2.*
import junit.framework.TestCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class AppRepositoryTest : TestCase() {

    @get:Rule
    var instantTaskExecuteRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)

    private val repository = AppRepository(remote, local)

    private val dummyFavorites = DataDummy.generateDummyFavorite()
    private val dummyFavorite = DataDummy.generateDummyFavorite()[0]
    private val favoriteId = dummyFavorite.id ?: 460465
    private val dummyMovies = DataDummy.generateDummyMovies()
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.id
    private val dummyTvShows = DataDummy.generateDummyTvs()
    private val dummyTvShow = DataDummy.generateDummyTvs()[0]
    private val tvShowId = dummyTvShow.id

    @Test
    fun testInsertFavorite() {
        repository.insertFav(dummyFavorite)
        verify(local, times(1)).insertFav(dummyFavorite)
    }

    @Test
    fun testDeleteFavorite() {
        repository.deleteFav(favoriteId)
        verify(local, times(1)).deleteFav(favoriteId)
    }


    @Test
    fun testGetFavorite() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FavEntity>
        Mockito.`when`(local.getFav()).thenReturn(dataSourceFactory)
        repository.getFav()

        val favorite = PagedListUtil.mockPagedList(DataDummy.generateDummyFavorite())
        verify(local).getFav()
        Assert.assertNotNull(favorite)
        assertEquals(dummyFavorites.size.toLong(), favorite.size.toLong())
    }

    @Test
    fun testGetFavoriteById() {
        val favorite = MutableLiveData<FavEntity>()
        Mockito.`when`(favoriteId.let { local.getFavById(it) }).thenReturn(favorite)
        favoriteId.let { repository.getFavById(it) }

        val favoriteEntity = DataDummy.generateDummyFavorite()[0]
        favoriteId.let { verify(local).getFavById(it) }
        Assert.assertNotNull(favoriteEntity)
        assertEquals(dummyFavorite, favoriteEntity)
    }

    @Test
    fun testGetPopularMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataCallback<List<MovieEntity>>)
                    .onDataReceived(dummyMovies)
                null
            }.`when`(remote).getPopularMovies(any())
            val movieEntities = LiveDataTestUtil.getValue(repository.getPopularMovies())
            verify(remote).getPopularMovies(any())
            Assert.assertNotNull(movieEntities)
            assertEquals(dummyMovies.size.toLong(), movieEntities.size.toLong())
        }

    }

    @Test
    fun testGetMovie() {
        CoroutineScope(Dispatchers.IO).launch {

            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataCallback<MovieEntity>)
                    .onDataReceived(dummyMovie)
                null
            }.`when`(remote).getMovie(eq(movieId), any())
            val movieEntities = LiveDataTestUtil.getValue(repository.getMovie(movieId))
            verify(remote).getMovie(eq(movieId), any())
            Assert.assertNotNull(movieEntities)
            assertEquals(dummyMovie.id, movieEntities.id)
        }
    }

    @Test
    fun testGetPopularTvShow() {
        CoroutineScope(Dispatchers.IO).launch {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataCallback<List<TvEntity>>)
                    .onDataReceived(dummyTvShows)
                null
            }.`when`(remote).getPopularTv(any())
            val tvShowEntities = LiveDataTestUtil.getValue(repository.getPopularTv())
            verify(remote).getPopularTv(any())
            Assert.assertNotNull(tvShowEntities)
            assertEquals(dummyTvShows.size.toLong(), dummyTvShows.size.toLong())
        }
    }

    @Test
    fun testGetTvShow() {
        CoroutineScope(Dispatchers.IO).launch {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataCallback<TvEntity>)
                    .onDataReceived(dummyTvShow)
                null
            }.`when`(remote).getTv(eq(tvShowId), any())
            val tvShowEntity = LiveDataTestUtil.getValue(repository.getTv(tvShowId))
            verify(remote).getTv(eq(tvShowId), any())
            Assert.assertNotNull(tvShowEntity)
            assertEquals(dummyTvShow.id, tvShowEntity.id)
        }
    }
}