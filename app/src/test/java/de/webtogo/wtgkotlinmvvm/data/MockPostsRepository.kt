package de.webtogo.wtgkotlinmvvm.data

import de.webtogo.wtgkotlinmvvm.PostsRepository
import de.webtogo.wtgkotlinmvvm.api.PostsApiInterface
import de.webtogo.wtgkotlinmvvm.dao.PostsDao
import de.webtogo.wtgkotlinmvvm.model.Post
import de.webtogo.wtgkotlinmvvm.utils.NetworkUtils
import io.reactivex.Observable
import javax.inject.Inject

// Created by martinsmelkis on 04/07/2018.
// Returns dummy data for testing purposes, to model different cases.
class MockPostsRepository @Inject constructor(mapiInterface: PostsApiInterface,
                                              mpostsDao: PostsDao,
                                              mutils: NetworkUtils) : PostsRepository(mapiInterface, mpostsDao, mutils) {

    internal var mockData: Any? = null
    internal var e: Exception? = null
    internal var async: Boolean = false
    internal var waitingTime: Long = 0

    fun mockData(data: Any) {
        this.mockData = data
    }

    override fun getPosts(): Observable<List<Post>> {
        // TODO pass connected/not connected in Constructor/Builder
        val hasConnection = true
        var observableFromApi: Observable<List<Post>>? = null
        if (hasConnection) {
            observableFromApi = getPostsFromApi()
        }
        val observableFromDb = getPostsFromDb()

        return if (true) Observable.concatArrayEager(observableFromApi, observableFromDb)
        else observableFromDb
    }

    @Throws(Exception::class)
    override fun getPostsFromApi(): Observable<List<Post>> {
        return (Observable.fromArray(mockData) as Observable<List<Post>>)
    }

    override fun getPostsFromDb(): Observable<List<Post>> {
        return (Observable.fromArray(mockData) as Observable<List<Post>>)
    }

}
