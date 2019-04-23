package de.webtogo.wtgkotlinmvvm.viewmodel

// Created by martinsmelkis on 29/06/2018.
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.webtogo.wtgkotlinmvvm.PostsRepository
import de.webtogo.wtgkotlinmvvm.model.Post
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit.MILLISECONDS
import javax.inject.Inject

/*
The ViewModel should expose states for the View, rather than just events.
For example, if we need to display the name and the email address of a User,
rather than creating two streams for this, we create a DisplayableUser object that encapsulates the two fields.
The stream will emit every time the display name or the email changes.
This way, we ensure that our View always displays the current state of the User.
We should make sure that every action of the user goes through the ViewModel
and that any possible logic of the View is moved in the ViewModel.
If the ViewModel needs access to Android classes, we create wrappers that we call Providers.
For example, for Android resources we created a IResourceProvider, that exposes methods like
String getString(@StringRes final int id).
The implementation of the IResourceProvider will contain a reference to the Context but,
the ViewModel will only refer to an IResourceProvider injected.
 */
class GetPostViewModel @Inject constructor(
        private val postRepository: PostsRepository) : ViewModel() {

    var postsResult: MutableLiveData<List<Post>> = MutableLiveData()
    var postsError: MutableLiveData<String> = MutableLiveData()
    private lateinit var disposableObserver: DisposableObserver<List<Post>>

    fun postsResult(): LiveData<List<Post>> {
        return postsResult
    }

    fun postsError(): LiveData<String> {
        return postsError
    }

    fun loadPosts() {

        disposableObserver = object : DisposableObserver<List<Post>>() {
            override fun onComplete() {
                // unused
            }

            override fun onNext(posts: List<Post>) {
                postsResult.postValue(posts)
            }

            override fun onError(e: Throwable) {
                postsError.postValue(e.message)
            }
        }

        postRepository.getPosts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .debounce(400, MILLISECONDS)
                .subscribe(disposableObserver)
    }

    fun disposeElements() {
        if (!disposableObserver.isDisposed) disposableObserver.dispose()
    }

    class Factory @Inject constructor(
            private val getPostsViewModel: GetPostViewModel) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(GetPostViewModel::class.java)) {
                return getPostsViewModel as T
            }
            throw IllegalArgumentException("Unknown class name")
        }
    }

}