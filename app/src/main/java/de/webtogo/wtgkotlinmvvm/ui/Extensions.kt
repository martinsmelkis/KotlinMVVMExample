package de.webtogo.wtgkotlinmvvm.ui

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

// Created by martinsmelkis on 29/06/2018.
inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}