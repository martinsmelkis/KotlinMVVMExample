package de.webtogo.wtgkotlinmvvm.api

// Created by martinsmelkis on 29/06/2018.
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class PostsApiClient {

    companion object {

        private const val BASE_URL = "https://jsonplaceholder.typicode.com"

        fun getClient(): Retrofit {
            val okHttpClient = OkHttpClient.Builder().build()
            val moshi = Moshi.Builder().build()

            return Builder().client(okHttpClient).baseUrl(BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }
    }
}