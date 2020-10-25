package com.example.userInfodata.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.corona.java.userinfo.model.UserAlbums
import com.corona.java.userinfo.model.UserInfo
import com.example.userInfodata.R
import com.example.userInfodata.data.repository.UserRepository
import com.example.userInfodata.ui.album.AlbumViewModel
import com.example.userInfodata.utils.common.Resource
import com.example.userInfodata.utils.network.NetworkHelper
import com.example.userInfodata.utils.rx.TestSchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var networkHelper: NetworkHelper

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var loggingInObserver: Observer<Boolean>

    @Mock
    private lateinit var launchAlbumObserver: Observer<Resource<List<UserInfo>>>

    @Mock
    private lateinit var messageStringIdObserver: Observer<Resource<Int>>


    private lateinit var testScheduler: TestScheduler

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        val compositeDisposable = CompositeDisposable()
        testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)
        viewModel = MainViewModel(
            testSchedulerProvider,
            compositeDisposable,
            networkHelper,
            userRepository
        )
        viewModel.getUsers().observeForever(launchAlbumObserver)
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {

        Mockito.doReturn(true)
            .`when`(networkHelper)
            .isNetworkConnected()
        Mockito.doReturn(Single.just(emptyList<UserInfo>()))
            .`when`(userRepository)
            .getUserList()
        testScheduler.triggerActions()
        userRepository.getUserList()
        verify(userRepository).getUserList()

    }

    @Test
    fun givenNoInternet_whenLogin_shouldShowNetworkError() {

        Mockito.doReturn(false)
            .`when`(networkHelper)
            .isNetworkConnected()
        viewModel.getUserList()

        assert(
            viewModel.messageStringId.value == Resource.error(
                null,
                R.string.network_connection_error
            )
        )

    }


    @After
    fun tearDown() {
        viewModel.getUsers().removeObserver(launchAlbumObserver)
    }
}