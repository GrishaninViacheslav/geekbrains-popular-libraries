package geekbrains.slava_5655380.domain.modules

import dagger.Module
import dagger.Provides
import geekbrains.slava_5655380.domain.models.networkstatus.INetworkStatus
import geekbrains.slava_5655380.domain.models.repositories.github.IDataSource
import geekbrains.slava_5655380.domain.models.repositories.github.IGithubCache
import geekbrains.slava_5655380.domain.models.repositories.github.IGithubUsersRepo
import geekbrains.slava_5655380.domain.models.repositories.github.RetrofitGithubUsersRepo
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IGithubCache
    ): IGithubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, cache)
}