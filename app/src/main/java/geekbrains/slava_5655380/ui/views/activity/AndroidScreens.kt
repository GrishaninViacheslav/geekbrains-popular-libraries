package geekbrains.slava_5655380.ui.views.activity

import com.github.terrakok.cicerone.androidx.FragmentScreen
import geekbrains.slava_5655380.ui.views.fragments.users.UsersFragment

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
}