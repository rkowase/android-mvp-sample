package rkowase.mvpsample.view

import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import rkowase.mvpsample.data.entity.RepoEntity
import rkowase.mvpsample.data.repository.GitHubRepository
import rkowase.mvpsample.scheduler.BaseSchedulerProvider
import rkowase.mvpsample.scheduler.ImmediateSchedulerProvider

class GitHubPresenterTest {
    @Mock private lateinit var mRepository: GitHubRepository
    @Mock private lateinit var mView: GitHubContract.View

    private lateinit var mPresenter: GitHubPresenter
    private lateinit var mSchedulerProvider: BaseSchedulerProvider

    companion object {
        const val USER = "test_user"
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mSchedulerProvider = ImmediateSchedulerProvider()
        mPresenter = GitHubPresenter(mRepository, mView, mSchedulerProvider)
    }

    @Test
    fun start() {
        mPresenter.start()
        verify(mRepository).initService()
    }

    @Test
    fun requestError() {
        `when`(request()).thenReturn(Observable.error(Exception()))
        mPresenter.request(USER)
        verify(mView).showError()
    }

    @Test
    fun requestEmpty() {
        `when`(request()).thenReturn(Observable.just(listOf()))
        mPresenter.request(USER)
        verify(mView).showError()
    }

    @Test
    fun requestSuccess() {
        val list = listOf(RepoEntity("name"))
        `when`(request()).thenReturn(Observable.just(list))
        mPresenter.request(USER)
        verify(mView).showList(list)
        verify(mView).hideButton()
    }

    private fun request() = mRepository.request(USER)

}