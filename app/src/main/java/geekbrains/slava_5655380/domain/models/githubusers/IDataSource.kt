package geekbrains.slava_5655380.domain.models.githubusers

import geekbrains.slava_5655380.domain.models.githubusers.githubrepository.GithubRepository
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface IDataSource {
    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>

    @GET
    fun getRepositories(@Url url: String): Single<List<GithubRepository>>
}