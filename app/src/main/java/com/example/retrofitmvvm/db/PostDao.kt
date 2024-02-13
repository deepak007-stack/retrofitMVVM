package com.example.retrofitmvvm.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.retrofitmvvm.model.PostItem

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPost(post: PostItem)

    @Query("select * from post")
    suspend fun getPost() : List<PostItem>
}