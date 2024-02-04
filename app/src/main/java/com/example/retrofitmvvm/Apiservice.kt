package com.example.retrofitmvvm

import com.example.retrofitmvvm.model.Post
import com.example.retrofitmvvm.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    // https://jsonplaceholder.typicode.com/

    @GET("/posts/1")
    suspend fun getUser() : Response<User>

    @GET("/posts/")
    suspend fun getPost() : Response<Post>

}