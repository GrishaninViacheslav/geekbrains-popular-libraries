package geekbrains.slava_5655380.domain.models.repositories.github

import geekbrains.slava_5655380.domain.models.repositories.github.repository.GithubRepository
import geekbrains.slava_5655380.domain.models.repositories.github.repository.RoomGithubRepository
import geekbrains.slava_5655380.domain.models.repositories.github.user.GithubUser
import geekbrains.slava_5655380.domain.models.repositories.github.user.RoomGithubUser

class RoomGithubCache(private val db: Database = Database.getInstance()) : IRoomGithubCache {
    override fun cacheUsers(users: List<GithubUser>) = with(users.map { user ->
        RoomGithubUser(
            user.id ?: "",
            user.login ?: "",
            user.avatarUrl ?: "",
            user.repos_url ?: ""
        )
    }) {
        db.userDao.insert(this)
    }

    override fun getUsersCache() = db.userDao.getAll().map { roomUser ->
        GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
    }

    override fun cacheRepositories(repositories: List<GithubRepository>, owner: GithubUser) {
        val roomUser = owner.login?.let { db.userDao.findByLogin(it) }
            ?: throw RuntimeException("No such user in cache")
        val roomRepos = repositories.map {
            RoomGithubRepository(
                it.id ?: "",
                it.name ?: "",
                it.description ?: "",
                roomUser.id
            )
        }
        db.repositoryDao.insert(roomRepos)
    }

    override fun getRepositories(owner: GithubUser): List<GithubRepository> {
        val roomUser = owner.login?.let { db.userDao.findByLogin(it) }
            ?: throw RuntimeException("No such user in cache")
        return db.repositoryDao.findForUser(roomUser.id)
            .map { GithubRepository(it.id, it.name, it.description) }
    }
}