package de.webtogo.wtgkotlinmvvm.di.modules

// Created by martinsmelkis on 03/03/17.

import androidx.lifecycle.ViewModelProvider

import org.mockito.Mockito

import de.webtogo.wtgkotlinmvvm.App
import de.webtogo.wtgkotlinmvvm.utils.NetworkUtils
import de.webtogo.wtgkotlinmvvm.viewmodel.GetPostViewModel

class TestAppModule(app: App) : AppModule(app) {

    override fun provideAddPostViewModelFactory(
            factory: GetPostViewModel.Factory): ViewModelProvider.Factory = factory

    override fun provideUtils(): NetworkUtils = Mockito.mock(NetworkUtils::class.java)

}
