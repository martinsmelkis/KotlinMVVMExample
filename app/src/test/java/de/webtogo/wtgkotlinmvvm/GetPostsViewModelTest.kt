package de.webtogo.wtgkotlinmvvm

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import de.webtogo.wtgkotlinmvvm.api.PostsApiInterface
import de.webtogo.wtgkotlinmvvm.dao.PostsDao
import de.webtogo.wtgkotlinmvvm.data.MockPostsRepository
import de.webtogo.wtgkotlinmvvm.di.component.DaggerTestAppComponent
import de.webtogo.wtgkotlinmvvm.di.modules.TestNetModule
import de.webtogo.wtgkotlinmvvm.model.Post
import de.webtogo.wtgkotlinmvvm.viewmodel.GetPostViewModel
import de.webtogo.wtgkotlinmvvm.di.component.TestAppComponent
import de.webtogo.wtgkotlinmvvm.di.modules.TestAppModule
import de.webtogo.wtgkotlinmvvm.utils.NetworkUtils
import org.mockito.MockitoAnnotations
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import javax.inject.Inject
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.plugins.RxAndroidPlugins

// Created by martinsmelkis on 03/07/2018.
class GetPostsViewModelTest {

    // TODO use MockWebServer library?
    private var mDataModel: MockPostsRepository? = null

    @Inject lateinit var apiInterface: PostsApiInterface
    @Inject lateinit var postsDao: PostsDao
    @Inject lateinit var utils: NetworkUtils

    private var mMainViewModel: GetPostViewModel? = null

    lateinit var component: TestAppComponent

    @Mock
    val observer = androidx.lifecycle.Observer<List<Post>> {
        Log.d(javaClass.simpleName, "Posts: $it")
    }

    @Mock
    val app: App? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }

        MockitoAnnotations.initMocks(this)

        component = DaggerTestAppComponent.builder().appModule(TestAppModule(app!!)).netModule(TestNetModule("https://jsonplaceholder.typicode.com")).build()
        component.inject(this)

        mDataModel = MockPostsRepository(apiInterface, postsDao, utils)

        mMainViewModel = GetPostViewModel(mDataModel!!)
    }

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
    @Test
    fun testGetPostsReturnsCorrectData() {

        val post = Post("", "", -100, -1)
        val posts = ArrayList<Post>()
        posts.add(post)

        mDataModel?.mockData = posts

        mMainViewModel?.postsResult?.observeForever(observer)

        mMainViewModel?.loadPosts()
        verify(observer).onChanged(posts)
        assert(mMainViewModel?.postsResult?.value?.size == 1)
        assert(mMainViewModel?.postsResult()?.value == posts)
    }

}
