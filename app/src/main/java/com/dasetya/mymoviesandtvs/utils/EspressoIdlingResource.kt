package com.dasetya.mymoviesandtvs.utils

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {

    private const val RESOURCE = "GLOBAL"

    val idlingResouce = CountingIdlingResource(RESOURCE)

    fun increment() {
        idlingResouce.increment()
    }

    fun decrement() {
        idlingResouce.decrement()
    }

}