package geekbrains.slava_5655380.ui.views

import com.github.terrakok.cicerone.androidx.FragmentScreen
import geekbrains.slava_5655380.ui.views.activity.IScreens
import geekbrains.slava_5655380.ui.views.fragments.user.UserFragment
import geekbrains.slava_5655380.ui.views.fragments.users.UsersFragment

object Screens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun user(id: String) = FragmentScreen {UserFragment.newInstance(id)}
}