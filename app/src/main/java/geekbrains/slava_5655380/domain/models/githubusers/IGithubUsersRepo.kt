package geekbrains.slava_5655380.domain.models.githubusers

import io.reactivex.Maybe
import io.reactivex.Single

interface IGithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
    fun getUser(id: String): Maybe<GithubUser>
}