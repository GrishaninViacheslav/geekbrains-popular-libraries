package geekbrains.slava_5655380.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import geekbrains.slava_5655380.databinding.ActivityMainBinding
import geekbrains.slava_5655380.ui.presenters.MainPresenter
import geekbrains.slava_5655380.ui.presenters.MainView
import geekbrains.slava_5655380.ui.presenters.MainView.ButtonIndex

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var vb: ActivityMainBinding
    private val presenter = MainPresenter(this)
    private val viewPresenterIdMap: Map<Button, ButtonIndex> by lazy {
        mapOf(
            Pair(vb.btnCounter1, ButtonIndex.COUNTER_1),
            Pair(vb.btnCounter2, ButtonIndex.COUNTER_2),
            Pair(vb.btnCounter3, ButtonIndex.COUNTER_3)
        )
    }
    private val presenterViewIdMap: Map<ButtonIndex, Button> by lazy { viewPresenterIdMap.entries.associate { (k, v) -> v to k } }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)

        val listener = View.OnClickListener {
            viewPresenterIdMap[it]?.let { it1 -> presenter.counterClick(it1) }
        }

        vb.btnCounter1.setOnClickListener(listener)
        vb.btnCounter2.setOnClickListener(listener)
        vb.btnCounter3.setOnClickListener(listener)
    }

    override fun setButtonText(index: ButtonIndex, text: String) {
        presenterViewIdMap[index]?.text = text
    }
}