package geekbrains.slava_5655380.domain.models.githubusers

import io.reactivex.Single
import retrofit2.http.GET

interface IDataSource {
    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>
}