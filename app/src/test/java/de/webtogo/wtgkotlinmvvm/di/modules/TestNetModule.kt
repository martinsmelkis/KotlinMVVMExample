package de.webtogo.wtgkotlinmvvm.di.modules

import de.webtogo.wtgkotlinmvvm.api.PostsApiInterface
import org.mockito.Mockito
import retrofit2.Retrofit

// Created by martinsmelkis on 03/07/2018.
class TestNetModule : NetModule {

    constructor() : super("https://jsonplaceholder.typicode.com") {}

    constructor(url: String) : super("https://jsonplaceholder.typicode.com") {}

    override fun providesApiInterface(retrofit: Retrofit): PostsApiInterface {
        return Mockito.mock(PostsApiInterface::class.java)
    }

}
