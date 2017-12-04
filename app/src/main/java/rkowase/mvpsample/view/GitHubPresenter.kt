package rkowase.mvpsample.view

import rkowase.mvpsample.data.repository.GitHubRepository
import rkowase.mvpsample.scheduler.BaseSchedulerProvider

class GitHubPresenter(
        private val mRepository: GitHubRepository,
        private val mView: GitHubContract.View,
        private val mSchedulerProvider: BaseSchedulerProvider) : GitHubContract.Presenter {

    init {
        mView.presenter = this
    }

    override fun start() {
        mRepository.initService()
    }

    override fun request(user: String) {
        mRepository.request(user)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe({
                    mView.hideButton()
                    mView.showList(it)
                }, {
                    mView.showError()
                })
    }
}