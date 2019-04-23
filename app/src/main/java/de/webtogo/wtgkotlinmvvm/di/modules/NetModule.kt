package de.webtogo.wtgkotlinmvvm.di.modules

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import de.webtogo.wtgkotlinmvvm.api.PostsApiInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
import retrofit2.Retrofit.Builder

// Created by martinsmelkis on 29/06/2018.
@Module
open class NetModule(private val baseUrl: String) {

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun providesMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Builder().client(okHttpClient).baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    open fun providesApiInterface(retrofit: Retrofit): PostsApiInterface = retrofit.create(
            PostsApiInterface::class.java)
}