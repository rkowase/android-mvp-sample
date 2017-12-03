package rkowase.mvpsample.view

import rkowase.mvpsample.data.entity.RepoEntity

class GitHubContract {
    interface View: BaseView<Presenter> {
        fun showList(list: List<RepoEntity>)
        fun showError()
        fun hideButton()
        fun getUser(): String
    }

    interface Presenter: BasePresenter {
        fun request()
    }
}