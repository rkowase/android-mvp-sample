package rkowase.mvpsample.data.service

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import rkowase.mvpsample.data.entity.RepoEntity

interface GitHubService {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Observable<List<RepoEntity>>
}