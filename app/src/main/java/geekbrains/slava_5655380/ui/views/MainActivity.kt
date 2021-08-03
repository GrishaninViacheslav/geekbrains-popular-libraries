package geekbrains.slava_5655380.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import geekbrains.slava_5655380.databinding.ActivityMainBinding
import geekbrains.slava_5655380.ui.presenters.MainPresenter
import geekbrains.slava_5655380.ui.presenters.MainView

class MainActivity : AppCompatActivity(), MainView {

    private val vb: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val presenter = MainPresenter(this)

    private fun initViews() {
        vb.btnCounter1.setOnClickListener { presenter.incrementFirstCounter() }
        vb.btnCounter2.setOnClickListener { presenter.incrementSecondCounter() }
        vb.btnCounter3.setOnClickListener { presenter.incrementThirdCounter() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        initViews()
    }

    override fun setFirstCounterText(text: String) {
        vb.btnCounter1.text = text
    }

    override fun setSecondCounterText(text: String) {
        vb.btnCounter2.text = text
    }

    override fun setThirdCounterText(text: String) {
        vb.btnCounter3.text = text
    }
}