package com.example.retrofitmvvm.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofitmvvm.ApiService
import com.example.retrofitmvvm.db.PostDatabase
import com.example.retrofitmvvm.model.Post
import com.example.retrofitmvvm.model.PostItem

class UserRepository(private val apiService: ApiService, private val postDatabase: PostDatabase) {

    private val userLiveData = MutableLiveData<Post?>()

    val post: MutableLiveData<Post?>     // public live data for access userLiveData in view model
        get() = userLiveData

//    suspend fun getPost() {
//        val result = apiService.getPost()     // getting response form Remote API
//
//        if (result.body() != null) {
//            Log.d("test",result.body().toString())
//            val posts = apiService.getPost() as ArrayList<PostItem>
//            for (post in posts) {
//                postDatabase.postDao().addPost(post)         // adding to database
//            }
//            userLiveData.postValue(result.body())
//        }
//    }

    suspend fun getPost() {
        val result = apiService.getPost()

        if (result.isSuccessful) {
            val posts = result.body()
            if (posts != null) {
                for (post in posts) {
                    postDatabase.postDao().addPost(post)
                }
                userLiveData.postValue(posts)
            }
        } else {
            Log.d("test1","Room Database setup failed")
        }
    }
}