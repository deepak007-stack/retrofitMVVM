package com.example.retrofitmvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofitmvvm.ApiService
import com.example.retrofitmvvm.model.Post

class UserRepository(private val apiService: ApiService) {

    private val userLiveData = MutableLiveData<Post>()

    val post: LiveData<Post>     // public live data for access userLiveData in view model
        get() = userLiveData

    suspend fun getPost() {
        val result = apiService.getPost()
        if (result?.body() != null) {
            userLiveData.postValue(result.body())
        }
    }
}