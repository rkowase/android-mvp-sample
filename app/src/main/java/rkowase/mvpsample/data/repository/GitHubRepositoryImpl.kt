package rkowase.mvpsample.data.repository

import android.content.Context
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import rkowase.mvpsample.R
import rkowase.mvpsample.data.entity.RepoEntity
import rkowase.mvpsample.service.GitHubService

class GitHubRepositoryImpl(private val context: Context): GitHubRepository {
    private lateinit var mService: GitHubService

    override fun initService() {
        val retrofit = Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        mService = retrofit.create(GitHubService::class.java)
    }

    override fun request(user: String): Observable<List<RepoEntity>> = mService.listRepos(user)
}