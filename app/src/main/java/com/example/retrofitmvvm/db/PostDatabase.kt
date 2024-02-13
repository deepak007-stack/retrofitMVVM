package com.example.retrofitmvvm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.retrofitmvvm.model.PostItem

@Database(entities = [PostItem::class], version = 1)
abstract class PostDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao

    companion object {

        @Volatile
        private var instance: PostDatabase? = null

        fun getDatabase(context: Context): PostDatabase {

            if (instance == null) {

                synchronized(this) {
                    instance = Room.databaseBuilder(context, PostDatabase::class.java, "postDB").build()
                }
            }
            return instance!!
        }

    }
}

















