package de.webtogo.wtgkotlinmvvm

import android.util.Log
import de.webtogo.wtgkotlinmvvm.api.PostsApiInterface
import de.webtogo.wtgkotlinmvvm.dao.PostsDao
import de.webtogo.wtgkotlinmvvm.model.Post
import de.webtogo.wtgkotlinmvvm.utils.NetworkUtils
import io.reactivex.Observable
import javax.inject.Inject

// Created by martinsmelkis on 29/06/2018.
open class PostsRepository @Inject constructor(val apiInterface: PostsApiInterface,
                                          val postsDao: PostsDao,
                                          val utils: NetworkUtils) {

    open fun getPosts(): Observable<List<Post>> {
        val hasConnection = utils.isConnectedToInternet()
        var observableFromApi: Observable<List<Post>>? = null
        if (hasConnection){
            observableFromApi = getPostsFromApi()
        }
        val observableFromDb = getPostsFromDb()

        return if (hasConnection) Observable.concatArrayEager(observableFromApi, observableFromDb)
        else observableFromDb
    }

    open fun getPostsFromApi(): Observable<List<Post>> {
        return apiInterface.getPosts()
                .doOnNext {
                    Thread.sleep(4000) // added timeout to test rotation case
                    Log.e("REPOSITORY API * ", it.size.toString())
                    for (item in it) {
                        postsDao.insertPost(item)
                    }
                }
    }

    open fun getPostsFromDb(): Observable<List<Post>> {
        return postsDao.queryPosts()
                .toObservable()
                .doOnNext {
                    Log.e("REPOSITORY DB *** ", it.size.toString())
                }
    }
}