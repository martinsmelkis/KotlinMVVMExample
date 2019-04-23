package de.webtogo.wtgkotlinmvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import de.webtogo.wtgkotlinmvvm.ui.GetPostsFragment
import de.webtogo.wtgkotlinmvvm.ui.inTransaction

open class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.inTransaction {
            add(R.id.content_frame, GetPostsFragment())
        }

    }

}
