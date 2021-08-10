package geekbrains.slava_5655380.ui.views.fragments.history

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import geekbrains.slava_5655380.App
import geekbrains.slava_5655380.databinding.FragmentHistoryBinding
import geekbrains.slava_5655380.domain.models.conversions_history.ConversionsHistoryRepo
import geekbrains.slava_5655380.ui.presenters.history.HistoryPresenter
import geekbrains.slava_5655380.ui.views.fragments.history.adapter.HistoryRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.io.FileNotFoundException


class HistoryFragment : MvpAppCompatFragment(), HistoryView, BackButtonListener {
    private val view: FragmentHistoryBinding by viewBinding(createMethod = CreateMethod.INFLATE)
    private val presenter: HistoryPresenter by moxyPresenter {
        HistoryPresenter(
            usersRepo = ConversionsHistoryRepo(),
            router = App.instance.router
        )
    }
    private var adapter: HistoryRVAdapter? = null

    companion object {
        fun newInstance() = HistoryFragment()
        const val RESULT_LOAD_IMG = 0
    }

    override fun onActivityResult(reqCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(reqCode, resultCode, data)
        if (reqCode == RESULT_LOAD_IMG && resultCode == Activity.RESULT_OK) {
            try {
                if (data == null || data.data == null) {
                    return
                }
                presenter.convertImage(data.data!!)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        view.apply {
            // TODO: Где и как следует расположить этот listener?
            buttonAdd.setOnClickListener {
                val photoPickerIntent = Intent(Intent.ACTION_PICK)
                photoPickerIntent.type = "image/*"
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG)
            }
        }.root

    override fun init() {
        view.rvHistory.layoutManager = LinearLayoutManager(context)
        adapter = HistoryRVAdapter(presenter.usersListPresenter)
        view.rvHistory.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}