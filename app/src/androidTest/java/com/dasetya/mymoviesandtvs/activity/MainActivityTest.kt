package com.dasetya.mymoviesandtvs.activity

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.dasetya.mymoviesandtvs.R
import com.dasetya.mymoviesandtvs.data.DataDummy
import com.dasetya.mymoviesandtvs.utils.EspressoIdlingResource
import org.hamcrest.Description
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    var rule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    private val dummyMovies = DataDummy.generateDummyMovies()
    private val dummyTvShows = DataDummy.generateDummyTvs()
    private val dummyFavoriteMovie = DataDummy.generateDummyMovies()
    private val dummyFavoriteTv = DataDummy.generateDummyTvs()

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResouce)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResouce)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.txtTitle)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.txtDate)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.txtRating)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.txtOverview)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.imgBackdrop)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.imgPoster)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    @Test
    fun addFavMovie() {
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.imgFav)).perform(ViewActions.click())
    }

    @Test
    fun loadTv() {
        onView(ViewMatchers.withText("Tv Show")).perform(ViewActions.click())
        onView(withId(R.id.rv_tv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_tv))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShows.size))
    }

    @Test
    fun loadDetailTv() {
        onView(ViewMatchers.withText("TV SHOW")).perform(ViewActions.click())
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.txtTitle)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.txtDate)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.txtRating)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.txtOverview)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.imgBackdrop)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.imgPoster)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    @Test
    fun addFavTv() {
        onView(ViewMatchers.withText("TV SHOW")).perform(ViewActions.click())
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.imgFav)).perform(ViewActions.click())
    }

    @Test
    fun loadFavoritesMovies() {
        onView(withId(R.id.action_Fav)).perform(ViewActions.click())
        onView(withId(R.id.rv_movie)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyFavoriteMovie.size))
        loadDetailMovie()
    }

    @Test
    fun deleteFavoriteMovies() {
        onView(withId(R.id.action_Fav)).perform(ViewActions.click())
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.imgFav)).perform(ViewActions.click())
    }


    @Test
    fun loadFavoritesTv() {
        onView(withId(R.id.action_Fav)).perform(ViewActions.click())
        onView(ViewMatchers.withText("Tv Show")).perform(ViewActions.click())
        onView(withId(R.id.rv_tv)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyFavoriteTv.size))
    }

    @Test
    fun deleteFavoriteTV() {
        onView(withId(R.id.action_Fav)).perform(ViewActions.click())
        onView(ViewMatchers.withText("Tv Show")).perform(ViewActions.click())
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.imgFav)).perform(ViewActions.click())
    }

}