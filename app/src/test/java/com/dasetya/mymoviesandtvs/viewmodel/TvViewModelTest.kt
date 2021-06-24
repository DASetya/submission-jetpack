package com.dasetya.mymoviesandtvs.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dasetya.mymoviesandtvs.data.DataDummy
import com.dasetya.mymoviesandtvs.entity.TvEntity
import com.dasetya.mymoviesandtvs.repository.AppRepository
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
class TvViewModelTest {

    private lateinit var viewModel: TvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: AppRepository

    @Mock
    private lateinit var observer: Observer<List<TvEntity>>

    @Before
    fun setUp() {
        viewModel = TvViewModel(repository)
    }

    @Test
    fun testGetTv() {
        val dummyTv = DataDummy.generateDummyTvs()
        val tvShow = MutableLiveData<List<TvEntity>>()
        tvShow.value = dummyTv

        Mockito.`when`(repository.getPopularTv()).thenReturn(tvShow)
        val tvResult = viewModel.getTvs().value
        Mockito.verify(repository).getPopularTv()
        Assert.assertNotNull(tvResult)
        Assert.assertEquals(10, tvResult?.size)

        viewModel.getTvs().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTv)
    }

}