package de.webtogo.wtgkotlinmvvm.api

// Created by martinsmelkis on 29/06/2018.
import de.webtogo.wtgkotlinmvvm.model.Post
import io.reactivex.Observable
import retrofit2.http.*

interface PostsApiInterface {

    @POST("/posts")
    @FormUrlEncoded
    fun savePost(@Field("title") title: String,
                 @Field("body") body: String,
                 @Field("userId") userId: Long): Observable<Post>

    @PUT("/posts/{id}")
    @FormUrlEncoded
    fun updatePost(@Path("id") id: Long,
                   @Field("title") title: String,
                   @Field("body") body: String,
                   @Field("userId") userId: Long): Observable<Post>

    @DELETE("/posts/{id}")
    fun deletePost(@Path("id") id: Long): Observable<Post>

    @GET("/posts")
    fun getPosts(): Observable<List<Post>>
}