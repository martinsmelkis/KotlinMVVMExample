package de.webtogo.wtgkotlinmvvm.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import de.webtogo.wtgkotlinmvvm.model.Post

// Created by martinsmelkis on 29/06/2018.
@Database(entities = arrayOf(Post::class), version = 1)
abstract class PostsDatabase : RoomDatabase() {
    abstract fun postsDao(): PostsDao
}