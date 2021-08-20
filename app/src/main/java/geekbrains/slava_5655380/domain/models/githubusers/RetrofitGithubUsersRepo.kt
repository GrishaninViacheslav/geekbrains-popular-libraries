package geekbrains.slava_5655380.domain.models.githubusers

import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers

class RetrofitGithubUsersRepo(val api: IDataSource) : IGithubUsersRepo {
    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())
    override fun getUser(id: String): Maybe<GithubUser> {
        return Maybe.just(GithubUser("default"))
    }
}

//class GithubUsersRepo {
//    private val repositories = listOf(
//        GithubUser("login1"),
//        GithubUser("login2"),
//        GithubUser("login3"),
//        GithubUser("login4"),
//        GithubUser("login5")
//    )
//
//    fun getUsers(): Single<List<GithubUser>> = Single.just(repositories)
//
//    fun getUser(id: String): Maybe<GithubUser> {
//        return try{
//            Maybe.just(repositories.first { user -> user.login == id })
//        }catch (e: NoSuchElementException){
//            Maybe.empty()
//        }
//    }
//
//}