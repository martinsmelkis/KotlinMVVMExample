package de.webtogo.wtgkotlinmvvm

import android.app.Activity
import android.app.Application
import android.os.StrictMode
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import de.webtogo.wtgkotlinmvvm.di.components.DaggerAppComponent
import de.webtogo.wtgkotlinmvvm.di.modules.AppModule
import de.webtogo.wtgkotlinmvvm.di.modules.NetModule
import javax.inject.Inject

// Created by martinsmelkis on 29/06/2018.
open class App : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build())
            StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder().penaltyLog().detectAll().build())
        }
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    fun initializeDagger() {
        DaggerAppComponent.builder().appModule(AppModule(this))
                .netModule(NetModule("https://jsonplaceholder.typicode.com")).build().inject(this)
    }

}
