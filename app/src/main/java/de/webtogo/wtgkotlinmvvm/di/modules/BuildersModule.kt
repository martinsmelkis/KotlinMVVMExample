package de.webtogo.wtgkotlinmvvm.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.webtogo.wtgkotlinmvvm.MainActivity
import de.webtogo.wtgkotlinmvvm.ui.GetPostsFragment

// Created by martinsmelkis on 29/06/2018.
@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeAddPostFragment(): GetPostsFragment
}