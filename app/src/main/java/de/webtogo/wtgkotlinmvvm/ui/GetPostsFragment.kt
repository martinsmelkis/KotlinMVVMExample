package de.webtogo.wtgkotlinmvvm.ui

// Created by martinsmelkis on 29/06/2018.
import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import de.webtogo.wtgkotlinmvvm.R
import de.webtogo.wtgkotlinmvvm.model.Post
import de.webtogo.wtgkotlinmvvm.viewmodel.GetPostViewModel
import kotlinx.android.synthetic.main.fragment_post_write.*
import java.lang.StringBuilder
import javax.inject.Inject

open class GetPostsFragment : Fragment() {

    @Inject
    //lateinit var getPostsViewModelFactory: AddPostViewModel.Factory
    lateinit var getPostsViewModel: GetPostViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_post_write, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AndroidSupportInjection.inject(this)

        /*
        Provides caching when Factory used
         */
        //addPostsViewModel = ViewModelProviders.of(this, addPostsViewModelFactory).get(
        //        AddPostViewModel::class.java)

        getPostsViewModel.postsResult.observe(this, Observer<List<Post>> {
            val sb = StringBuilder()
            it?.asSequence()?.filter { it.id > 0 }?.forEach {
                sb.append(it.title).append("\n").append(it.body).append("\n\n")
            }
            txt_get_posts.text = sb.toString()
        })

        getPostsViewModel.postsError.observe(this, Observer<String> {
            txt_get_posts.text = it
        })

        btn_get_posts.setOnClickListener { getPosts() }
    }

    private fun getPosts() {
        getPostsViewModel.loadPosts()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        getPostsViewModel.disposeElements()
    }

}