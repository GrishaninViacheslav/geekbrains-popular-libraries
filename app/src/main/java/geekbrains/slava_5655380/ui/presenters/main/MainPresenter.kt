package geekbrains.slava_5655380.ui.presenters.main

import com.github.terrakok.cicerone.Router
import geekbrains.slava_5655380.ui.views.Screens
import geekbrains.slava_5655380.ui.views.activity.MainView
import moxy.MvpPresenter

class MainPresenter(private val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.history())
    }

    fun backClicked() {
        router.exit()
    }
}