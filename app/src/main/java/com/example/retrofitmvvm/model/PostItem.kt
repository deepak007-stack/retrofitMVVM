package com.example.retrofitmvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post")
data class PostItem(
    @PrimaryKey(autoGenerate = true)
    val postId : Int,
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)