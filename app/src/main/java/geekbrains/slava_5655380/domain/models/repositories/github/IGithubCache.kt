package geekbrains.slava_5655380.domain.models.repositories.github

import geekbrains.slava_5655380.domain.models.repositories.github.repository.GithubRepository
import geekbrains.slava_5655380.domain.models.repositories.github.user.GithubUser

interface IGithubCache {
    fun cacheUsers(users: List<GithubUser>)
    fun getUsersCache(): List<GithubUser>
    fun cacheRepositories(repositories: List<GithubRepository>, owner: GithubUser)
    fun getRepositories(owner: GithubUser): List<GithubRepository>
}