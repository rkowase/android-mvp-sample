package rkowase.mvpsample.data.repository

import io.reactivex.Observable
import rkowase.mvpsample.data.entity.RepoEntity

interface GitHubRepository {
    fun initService()
    fun request(user: String): Observable<List<RepoEntity>>
}