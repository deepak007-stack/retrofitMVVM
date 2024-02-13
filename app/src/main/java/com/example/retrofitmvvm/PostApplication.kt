package com.example.retrofitmvvm

import android.app.Application
import com.example.retrofitmvvm.db.PostDatabase
import com.example.retrofitmvvm.repository.UserRepository
import dagger.hilt.android.HiltAndroidApp

class PostApplication : Application() {

    lateinit var repository: UserRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val apiService = RetrofitReq.service
        val postDatabase = PostDatabase.getDatabase(applicationContext)
        repository = UserRepository(apiService, postDatabase)
    }
}