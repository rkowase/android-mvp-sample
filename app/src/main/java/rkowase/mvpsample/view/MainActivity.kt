package rkowase.mvpsample.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import rkowase.mvpsample.R
import rkowase.mvpsample.data.entity.RepoEntity
import rkowase.mvpsample.data.repository.GitHubRepositoryImpl
import rkowase.mvpsample.scheduler.SchedulerProvider


class MainActivity : AppCompatActivity(), GitHubContract.View {

    override lateinit var presenter: GitHubContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = GitHubPresenter(GitHubRepositoryImpl(this), this, SchedulerProvider)
        presenter.start()

        button.setOnClickListener({
            presenter.request(getString(R.string.user))
        })
    }

    override fun hideButton() {
        button.visibility = View.GONE
    }

    override fun showError() {
        Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_LONG).show()
    }

    override fun showList(it: List<RepoEntity>) {
        var list = listOf<String>()
        it.forEach { list += it.name }
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list)
        listView.adapter = adapter
        listView.visibility = View.VISIBLE
    }
}
