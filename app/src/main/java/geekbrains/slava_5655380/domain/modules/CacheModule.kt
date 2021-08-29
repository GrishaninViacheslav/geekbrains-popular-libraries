package geekbrains.slava_5655380.domain.modules

import androidx.room.Room
import dagger.Module
import dagger.Provides
import geekbrains.slava_5655380.App
import geekbrains.slava_5655380.domain.models.repositories.github.Database
import geekbrains.slava_5655380.domain.models.repositories.github.IGithubCache
import geekbrains.slava_5655380.domain.models.repositories.github.RoomGithubCache
import javax.inject.Singleton

@Module
class CacheModule {
    @Singleton
    @Provides
    fun database(app: App): Database =
        Room.databaseBuilder(app, Database::class.java, Database.DB_NAME)
            .build()

    @Singleton
    @Provides
    fun usersCache(database: Database): IGithubCache = RoomGithubCache(database)
}