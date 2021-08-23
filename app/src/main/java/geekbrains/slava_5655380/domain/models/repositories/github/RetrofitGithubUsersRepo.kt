package geekbrains.slava_5655380.domain.models.repositories.github

import geekbrains.slava_5655380.domain.models.networkstatus.INetworkStatus
import geekbrains.slava_5655380.domain.models.repositories.github.repository.GithubRepository
import geekbrains.slava_5655380.domain.models.repositories.github.repository.RoomGithubRepository
import geekbrains.slava_5655380.domain.models.repositories.github.user.GithubUser
import geekbrains.slava_5655380.domain.models.repositories.github.user.RoomGithubUser
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class RetrofitGithubUsersRepo(val api: IDataSource, val networkStatus: INetworkStatus, val db: Database) : IGithubUsersRepo {
    // TODO: Практическое задание 1 - вытащить кэширование в отдельный класс RoomUserCache
    //             и внедрить его сюда через интерфейс IUserCache
    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers()
                .flatMap { users ->
                    Single.fromCallable {
                        val roomUsers = users.map { user -> RoomGithubUser(user.id ?: "", user.login ?: "", user.avatarUrl ?: "", user.repos_url ?: "") }
                        db.userDao.insert(roomUsers)
                        users
                    }
                }
        } else {
            Single.fromCallable {
                db.userDao.getAll().map { roomUser ->
                    GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
                }
            }
        }
    }.subscribeOn(Schedulers.io())

    // TODO: Практическое задание 1 - вытащить кэширование в отдельный класс RoomRepositoriesCache
    //             и внедрить его сюда через интерфейс IRepositoriesCache
    override fun getRepositories(user: GithubUser) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            user.repos_url?.let { url ->
                api.getRepositories(url)
                    .flatMap { repositories ->
                        Single.fromCallable {
                            val roomUser = user.login?.let { db.userDao.findByLogin(it) } ?: throw RuntimeException("No such user in cache")
                            val roomRepos = repositories.map { RoomGithubRepository(it.id ?: "", it.name ?: "", it.description ?: "", roomUser.id) }
                            db.repositoryDao.insert(roomRepos)
                            repositories
                        }
                    }
            } ?: Single.error<List<GithubRepository>>(RuntimeException("User has no repos url")).subscribeOn(Schedulers.io())
        } else {
            Single.fromCallable {
                val roomUser = user.login?.let { db.userDao.findByLogin(it) } ?: throw RuntimeException("No such user in cache")
                db.repositoryDao.findForUser(roomUser.id).map { GithubRepository(it.id, it.name, it.description) }
            }

        }
    }.subscribeOn(Schedulers.io())
}