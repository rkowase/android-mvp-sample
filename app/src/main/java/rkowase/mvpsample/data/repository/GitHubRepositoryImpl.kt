package rkowase.mvpsample.data.repository

import android.content.Context
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import rkowase.mvpsample.R
import rkowase.mvpsample.data.entity.RepoEntity
import rkowase.mvpsample.data.service.GitHubService

class GitHubRepositoryImpl(private val mContext: Context): GitHubRepository {
    private lateinit var mService: GitHubService

    override fun initService() {
        val retrofit = Retrofit.Builder()
                .baseUrl(mContext.getString(R.string.base_url))
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        mService = retrofit.create(GitHubService::class.java)
    }

    override fun request(user: String): Observable<List<RepoEntity>> = mService.listRepos(user)
}