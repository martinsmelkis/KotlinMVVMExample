package de.webtogo.wtgkotlinmvvm.di.components

import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import de.webtogo.wtgkotlinmvvm.App
import de.webtogo.wtgkotlinmvvm.di.modules.AppModule
import de.webtogo.wtgkotlinmvvm.di.modules.BuildersModule
import de.webtogo.wtgkotlinmvvm.di.modules.NetModule
import javax.inject.Singleton

// Created by martinsmelkis on 29/06/2018.
@Singleton
@Component(
        modules = arrayOf(AndroidSupportInjectionModule::class, BuildersModule::class, AppModule::class, NetModule::class)
)
interface AppComponent {
    fun inject(app: App)
}