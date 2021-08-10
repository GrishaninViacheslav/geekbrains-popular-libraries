package geekbrains.slava_5655380.ui.views.activity

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import geekbrains.slava_5655380.*
import geekbrains.slava_5655380.databinding.ActivityMainBinding
import geekbrains.slava_5655380.ui.presenters.main.MainPresenter
import geekbrains.slava_5655380.ui.views.fragments.users.BackButtonListener
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter { MainPresenter(App.instance.router) }
    private var vb: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }
}