package geekbrains.slava_5655380.domain.models.githubusers

import io.reactivex.schedulers.Schedulers

class RetrofitGithubUsersRepo(val api: IDataSource) : IGithubUsersRepo {
    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())
    override fun getRepositories(url: String) = api.getRepositories(url).subscribeOn(Schedulers.io())
}