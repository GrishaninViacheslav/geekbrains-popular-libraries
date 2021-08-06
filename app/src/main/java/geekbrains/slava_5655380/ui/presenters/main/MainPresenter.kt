package geekbrains.slava_5655380.ui.presenters.main

import com.github.terrakok.cicerone.Router
import geekbrains.slava_5655380.ui.views.activity.IScreens
import geekbrains.slava_5655380.ui.views.activity.MainView
import moxy.MvpPresenter

class MainPresenter(val router: Router, val screens: IScreens) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}