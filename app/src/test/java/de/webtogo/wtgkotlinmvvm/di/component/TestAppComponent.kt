package de.webtogo.wtgkotlinmvvm.di.component

// Created by martinsmelkis on 20/03/17.

import javax.inject.Singleton

import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import de.webtogo.wtgkotlinmvvm.GetPostsViewModelTest
import de.webtogo.wtgkotlinmvvm.App
import de.webtogo.wtgkotlinmvvm.di.components.AppComponent
import de.webtogo.wtgkotlinmvvm.di.modules.AppModule
import de.webtogo.wtgkotlinmvvm.di.modules.BuildersModule
import de.webtogo.wtgkotlinmvvm.di.modules.NetModule

@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class, BuildersModule::class, AppModule::class, NetModule::class))
interface TestAppComponent : AppComponent {
    override fun inject(app: App)
    fun inject(test: GetPostsViewModelTest)
    //override fun inject(test: AddPostPresenterImpl)
    //override fun inject(test: GetPostsPresenterImpl)
}