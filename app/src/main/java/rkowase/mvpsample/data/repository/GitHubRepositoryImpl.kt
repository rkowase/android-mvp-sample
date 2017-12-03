package rkowase.mvpsample.data.repository

import android.content.Context
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import rkowase.mvpsample.GitHubService
import rkowase.mvpsample.R

class GitHubRepositoryImpl(private val context: Context): GitHubRepository {
    override fun createService(): GitHubService {
        val retrofit = Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return retrofit.create(GitHubService::class.java)
    }
}