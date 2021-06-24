package com.dasetya.mymoviesandtvs.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dasetya.mymoviesandtvs.data.DataDummy
import com.dasetya.mymoviesandtvs.entity.FavEntity
import com.dasetya.mymoviesandtvs.entity.TvEntity
import com.dasetya.mymoviesandtvs.repository.AppRepository
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class  TvDetailViewModelTest {
    private lateinit var viewModel: TvDetailViewModel
    private val dummyTv = DataDummy.generateDummyTvs()[0]
    private val tvId = dummyTv.id
    private val dummyFavorite = DataDummy.generateDummyFavorite()[0]
    private val favoriteId = dummyFavorite.id ?: 460465

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: AppRepository

    @Mock
    private lateinit var observer: Observer<TvEntity>

    @Mock
    private lateinit var observerFavorite: Observer<FavEntity>

    @Before
    fun setUp() {
        viewModel = TvDetailViewModel(repository)
    }

    @Test
    fun testGetTv() {
        val tv = MutableLiveData<TvEntity>()
        tv.value = dummyTv

        Mockito.`when`(repository.getTv(tvId)).thenReturn(tv)
        val tvShowEntity = tvId?.let { viewModel.getTv(it).value } as TvEntity
        verify(repository).getTv(tvId)

        Assert.assertNotNull(tvShowEntity)
        Assert.assertEquals(dummyTv.id, tvShowEntity.id)
        Assert.assertEquals(dummyTv.backdropPath, tvShowEntity.backdropPath)
        Assert.assertEquals(dummyTv.name, tvShowEntity.name)
        Assert.assertEquals(dummyTv.voteAverage, tvShowEntity.voteAverage, 0.0)
        Assert.assertEquals(dummyTv.firstAirDate, tvShowEntity.firstAirDate)
        Assert.assertEquals(dummyTv.overview, tvShowEntity.overview)

        viewModel.getTv(tvId).observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }

    @Test
    fun testGetTvShowFavoriteById() {
        val favorite = MutableLiveData<FavEntity>()
        favorite.value = dummyFavorite

        Mockito.`when`(favoriteId.let { repository.getFavById(it) }).thenReturn(favorite)
        val favoriteEntity =
            favoriteId.let { viewModel.getFavById(it).value } as FavEntity
        verify(repository).getFavById(favoriteId)

        Assert.assertNotNull(favoriteEntity)
        Assert.assertEquals(dummyFavorite.id, favoriteEntity.id)
        Assert.assertEquals(dummyFavorite.title, favoriteEntity.title)
        Assert.assertEquals(dummyFavorite.category, favoriteEntity.category)
        Assert.assertEquals(dummyFavorite.posterPath, favoriteEntity.posterPath)
        Assert.assertEquals(dummyFavorite.voteAverage, favoriteEntity.voteAverage, 0.0)
        Assert.assertEquals(dummyFavorite.releaseDate, favoriteEntity.releaseDate)

        viewModel.getFavById(favoriteId).observeForever(observerFavorite)
        verify(observerFavorite).onChanged(dummyFavorite)
    }

    @Test
    fun testInsertTvShowFavorite() {
        viewModel.insertFav(dummyFavorite)
        verify(repository, times(1)).insertFav(dummyFavorite)
    }

    @Test
    fun testDeleteTvShowFavorite() {
        viewModel.deleteFav(favoriteId)
        verify(repository, times(1)).deleteFav(favoriteId)
    }
}