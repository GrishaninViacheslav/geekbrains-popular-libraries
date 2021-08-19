package geekbrains.slava_5655380.ui.views

import com.github.terrakok.cicerone.androidx.FragmentScreen
import geekbrains.slava_5655380.ui.views.fragments.history.HistoryFragment

object Screens : IScreens {
    override fun history() = FragmentScreen { HistoryFragment.newInstance() }
}