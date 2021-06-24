package com.dasetya.mymoviesandtvs.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dasetya.mymoviesandtvs.data.DataDummy
import com.dasetya.mymoviesandtvs.entity.FavEntity
import com.dasetya.mymoviesandtvs.entity.MovieEntity
import com.dasetya.mymoviesandtvs.repository.AppRepository
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
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
class MovieDetailViewModelTest{
    private lateinit var viewModel: MovieDetailViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.id
    private val dummyFavorite = DataDummy.generateDummyFavorite()[0]
    private val favoriteId = dummyFavorite.id ?: 460465

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: AppRepository

    @Mock
    private lateinit var observer: Observer<MovieEntity>

    @Mock
    private lateinit var observerFavorite: Observer<FavEntity>

    @Before
    fun setUp() {
        viewModel = MovieDetailViewModel(repository)
    }

    @Test
    fun testGetMovie() {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovie

        Mockito.`when`(repository.getMovie(movieId)).thenReturn(movie)
        val movieEntity = movieId?.let { viewModel.getMovie(it).value } as MovieEntity
        verify(repository).getMovie(movieId)

        Assert.assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity.id)
        assertEquals(dummyMovie.backdropPath, movieEntity.backdropPath)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.voteAverage, movieEntity.voteAverage, 0.0)
        assertEquals(dummyMovie.releaseDate, movieEntity.releaseDate)
        assertEquals(dummyMovie.overview, movieEntity.overview)

        viewModel.getMovie(movieId).observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun testGetMovieFavoriteById() {
        val favorite = MutableLiveData<FavEntity>()
        favorite.value = dummyFavorite

        Mockito.`when`(favoriteId.let { repository.getFavById(it) }).thenReturn(favorite)
        val favoriteEntity =
            favoriteId.let { viewModel.getFavById(it).value } as FavEntity
        verify(repository).getFavById(favoriteId)

        Assert.assertNotNull(favoriteEntity)
        assertEquals(dummyFavorite.id, favoriteEntity.id)
        assertEquals(dummyFavorite.title, favoriteEntity.title)
        assertEquals(dummyFavorite.category, favoriteEntity.category)
        assertEquals(dummyFavorite.posterPath, favoriteEntity.posterPath)
        assertEquals(dummyFavorite.voteAverage, favoriteEntity.voteAverage, 0.0)
        assertEquals(dummyFavorite.releaseDate, favoriteEntity.releaseDate)

        if (favoriteId != null) {
            viewModel.getFavById(favoriteId).observeForever(observerFavorite)
        }
        verify(observerFavorite).onChanged(dummyFavorite)
    }

    @Test
    fun testInsertMovieFavorite() {
        viewModel.insertFav(dummyFavorite)
        verify(repository, times(1)).insertFav(dummyFavorite)
    }

    @Test
    fun testDeleteMovieFavorite() {
        viewModel.deleteFav(favoriteId)
        verify(repository, times(1)).deleteFav(favoriteId)
    }
}