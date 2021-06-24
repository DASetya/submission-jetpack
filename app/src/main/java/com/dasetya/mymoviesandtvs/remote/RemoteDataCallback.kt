package com.dasetya.mymoviesandtvs.remote

interface RemoteDataCallback<T> {
    fun onDataReceived(response: T)
}