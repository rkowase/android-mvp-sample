package rkowase.mvpsample.data.repository

import rkowase.mvpsample.GitHubService

interface GitHubRepository {
    fun createService(): GitHubService
}