package geekbrains.slava_5655380.ui.views

import com.github.terrakok.cicerone.androidx.FragmentScreen
import geekbrains.slava_5655380.domain.models.repositories.github.user.GithubUser
import geekbrains.slava_5655380.domain.models.repositories.github.repository.GithubRepository
import geekbrains.slava_5655380.ui.views.activity.IScreens
import geekbrains.slava_5655380.ui.views.fragments.repository.RepositoryFragment
import geekbrains.slava_5655380.ui.views.fragments.user.UserFragment
import geekbrains.slava_5655380.ui.views.fragments.users.UsersFragment

class Screens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun user(user: GithubUser) = FragmentScreen { UserFragment.newInstance(user) }
    override fun repository(repository: GithubRepository) =
        FragmentScreen { RepositoryFragment.newInstance(repository) }
}