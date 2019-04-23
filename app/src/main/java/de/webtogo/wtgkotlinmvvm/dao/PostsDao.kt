package de.webtogo.wtgkotlinmvvm.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.webtogo.wtgkotlinmvvm.model.Post
import io.reactivex.Single

// Created by martinsmelkis on 29/06/2018.
@Dao
interface PostsDao {

    @Query("SELECT * FROM posts ORDER BY body ") //limit :limit offset :offset
    fun queryPosts(): Single<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(post: Post)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPosts(posts: List<Post>)
}