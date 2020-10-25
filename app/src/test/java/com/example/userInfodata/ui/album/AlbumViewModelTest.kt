package com.example.userInfodata.ui.album

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.corona.java.userinfo.model.UserAlbums
import com.example.userInfodata.R
import com.example.userInfodata.data.repository.ApiHelperImpl
import com.example.userInfodata.data.repository.UserRepository
import com.example.userInfodata.utils.common.Resource
import com.example.userInfodata.utils.coroutine.TestCoroutineRule
import com.example.userInfodata.utils.network.NetworkHelper
import com.example.userInfodata.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AlbumViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var schedulerProvider: SchedulerProvider

    @Mock
    private lateinit var compositeDisposable: CompositeDisposable

    @Mock
    private lateinit var networkHelper: NetworkHelper

    @Mock
    private lateinit var apiUsersObserver: Observer<Resource<List<UserAlbums>>>

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            doReturn(true)
                .`when`(networkHelper)
                .isNetworkConnected()
            doReturn(emptyList<UserAlbums>())
                .`when`(userRepository)
                .getUserAlbum()
            val viewModel = AlbumViewModel(
                schedulerProvider,
                compositeDisposable,
                networkHelper,
                userRepository
            )
            viewModel.getAlbum().observeForever(apiUsersObserver)
            verify(userRepository).getUserAlbum()
            verify(apiUsersObserver).onChanged(Resource.success(emptyList()))
            viewModel.getAlbum().removeObserver(apiUsersObserver)
        }
    }
    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error Message For You"
            doReturn(true)
                .`when`(networkHelper)
                .isNetworkConnected()
            doThrow(RuntimeException(errorMessage))
                .`when`(userRepository)
                .getUserAlbum()
            val viewModel = AlbumViewModel(schedulerProvider, compositeDisposable,networkHelper,userRepository)
            viewModel.getAlbum().observeForever(apiUsersObserver)
            verify(userRepository).getUserAlbum()
            verify(apiUsersObserver).onChanged(
                Resource.error(
                    RuntimeException(errorMessage).toString(),
                    null
                )
            )
            viewModel.getAlbum().removeObserver(apiUsersObserver)
        }
    }
    @Test
    fun givenNoInternet_whenLogin_shouldShowNetworkError() {

        doReturn(false)
            .`when`(networkHelper)
            .isNetworkConnected()
        val viewModel = AlbumViewModel(schedulerProvider, compositeDisposable,networkHelper,userRepository)
        viewModel.getUserAlbums()

        assert(viewModel.messageStringId.value == Resource.error(null,R.string.network_connection_error))

    }


}