package com.dasetya.mymoviesandtvs.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.dasetya.mymoviesandtvs.data.DataDummy
import com.dasetya.mymoviesandtvs.entity.FavEntity
import com.dasetya.mymoviesandtvs.repository.AppRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class FavMovieViewModelTest {
    private lateinit var viewModel: FavMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: AppRepository

    @Mock
    private lateinit var observer: Observer<PagedList<FavEntity>>

    @Before
    fun setUp() {
        viewModel = FavMovieViewModel(repository)
    }


    @Test
    fun testGetFavoriteMovie() {
        val expected = MutableLiveData<PagedList<FavEntity>>()
        expected.value = PagedTestDataSources.snapshot(DataDummy.generateDummyFavorite())

        Mockito.`when`(repository.getFav()).thenReturn(expected)

        viewModel.getFavMovie().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getFavMovie().value
        Assert.assertEquals(expectedValue, actualValue)
        Assert.assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        Assert.assertEquals(expectedValue?.size, actualValue?.size)
    }

    @Test
    fun testGetFavoriteMovieEmpty() {
        val expected = MutableLiveData<PagedList<FavEntity>>()
        expected.value = PagedTestDataSources.snapshot()

        Mockito.`when`(repository.getFav()).thenReturn(expected)

        viewModel.getFavMovie().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        val actualValueDataSize = viewModel.getFavMovie().value?.size
        Assert.assertTrue(
            "size of data should be 0, actual is $actualValueDataSize",
            actualValueDataSize == 0
        )
    }

    class PagedTestDataSources private constructor(private val items: List<FavEntity>) :
        PositionalDataSource<FavEntity>() {
        companion object {
            fun snapshot(items: List<FavEntity> = listOf()): PagedList<FavEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 5)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(
            params: LoadInitialParams,
            callback: LoadInitialCallback<FavEntity>
        ) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(
            params: LoadRangeParams,
            callback: LoadRangeCallback<FavEntity>
        ) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}