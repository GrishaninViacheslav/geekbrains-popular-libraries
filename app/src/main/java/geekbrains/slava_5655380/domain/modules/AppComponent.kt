package geekbrains.slava_5655380.domain.modules

import dagger.Component
import geekbrains.slava_5655380.domain.models.repositories.github.RetrofitGithubUsersRepo
import geekbrains.slava_5655380.ui.presenters.main.MainPresenter
import geekbrains.slava_5655380.ui.presenters.repository.RepositoryPresenter
import geekbrains.slava_5655380.ui.presenters.user.UserPresenter
import geekbrains.slava_5655380.ui.presenters.users.UsersPresenter
import geekbrains.slava_5655380.ui.views.activity.MainActivity
import geekbrains.slava_5655380.ui.views.fragments.repository.RepositoryFragment
import geekbrains.slava_5655380.ui.views.fragments.user.UserFragment
import geekbrains.slava_5655380.ui.views.fragments.users.UsersFragment
import geekbrains.slava_5655380.ui.views.fragments.users.adapter.UsersRVAdapter
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
        RepoModule::class,
        ImageLoaderModule::class,
        UiSchedulerModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userPresenter: UserPresenter)
    fun inject(repositoryPresenter: RepositoryPresenter)

    fun inject(usersRVAdapter: UsersRVAdapter)
}