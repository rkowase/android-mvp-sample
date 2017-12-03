package rkowase.mvpsample.view

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import rkowase.mvpsample.GitHubService
import rkowase.mvpsample.data.repository.GitHubRepository

class GitHubPresenter(
        private val mRepository: GitHubRepository,
        private val mView: GitHubContract.View) : GitHubContract.Presenter {

    private lateinit var mService: GitHubService

    init {
        mView.presenter = this
    }

    override fun start() {
        mService = mRepository.createService()
    }

    override fun request() {
        mService.listRepos(mView.getUser())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mView.hideButton()
                    mView.showList(it)
                }, {
                    mView.showError()
                })
    }
}