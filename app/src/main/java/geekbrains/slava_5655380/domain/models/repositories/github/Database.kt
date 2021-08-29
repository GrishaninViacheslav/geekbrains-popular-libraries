package geekbrains.slava_5655380.domain.models.repositories.github

import androidx.room.RoomDatabase
import geekbrains.slava_5655380.domain.models.repositories.github.repository.RepositoryDao
import geekbrains.slava_5655380.domain.models.repositories.github.repository.RoomGithubRepository
import geekbrains.slava_5655380.domain.models.repositories.github.user.RoomGithubUser
import geekbrains.slava_5655380.domain.models.repositories.github.user.UserDao

@androidx.room.Database(
    entities = [RoomGithubUser::class, RoomGithubRepository::class],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        const val DB_NAME = "database.db"
    }
}