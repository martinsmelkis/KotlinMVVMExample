package de.webtogo.wtgkotlinmvvm.di.modules

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import dagger.Module
import dagger.Provides
import de.webtogo.wtgkotlinmvvm.dao.PostsDao
import de.webtogo.wtgkotlinmvvm.dao.PostsDatabase
import de.webtogo.wtgkotlinmvvm.viewmodel.GetPostViewModel
import de.webtogo.wtgkotlinmvvm.utils.NetworkUtils
import javax.inject.Singleton

// Created by martinsmelkis on 29/06/2018.
@Module
open class AppModule(val app: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun providePostsDatabase(app: Application): PostsDatabase = Room.databaseBuilder(app,
            PostsDatabase::class.java, "posts_db").fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun providePostsDao(database: PostsDatabase): PostsDao = database.postsDao()

    @Provides
    open fun provideAddPostViewModelFactory(
            factory: GetPostViewModel.Factory): ViewModelProvider.Factory = factory

    @Provides
    @Singleton
    open fun provideUtils(): NetworkUtils = NetworkUtils(app)

}