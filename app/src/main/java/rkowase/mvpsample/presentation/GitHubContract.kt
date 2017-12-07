package rkowase.mvpsample.presentation

import rkowase.mvpsample.data.entity.RepoEntity
import rkowase.mvpsample.view.BaseView

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