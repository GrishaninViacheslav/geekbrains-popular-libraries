package geekbrains.slava_5655380.domain.models.githubusers

import io.reactivex.Single

class GithubUsersRepo {
    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )

    fun getUsers(): Single<List<GithubUser>> = Single.just(repositories)

    fun getUser(id: String): Single<GithubUser> =
        Single.just(repositories.first { user -> user.login == id })
}