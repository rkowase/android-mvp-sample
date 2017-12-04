package rkowase.mvpsample.view

import rkowase.mvpsample.data.entity.RepoEntity

class GitHubContract {
    interface View: BaseView<Presenter> {
        fun showList(list: List<RepoEntity>)
        fun showError()
        fun hideButton()
    }

    interface Presenter: BasePresenter {
        fun request(user: String)
    }
}