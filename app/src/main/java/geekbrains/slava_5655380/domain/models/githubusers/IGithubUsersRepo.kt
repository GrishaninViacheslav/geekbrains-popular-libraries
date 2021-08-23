package geekbrains.slava_5655380.domain.models.githubusers

import geekbrains.slava_5655380.domain.models.githubusers.githubrepository.GithubRepository
import io.reactivex.Single

interface IGithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
    fun getRepositories(url: String): Single<List<GithubRepository>>
}