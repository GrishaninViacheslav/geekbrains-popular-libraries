package geekbrains.slava_5655380.ui.views.fragments.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import geekbrains.slava_5655380.App
import geekbrains.slava_5655380.domain.models.conversions_history.ConversionsHistoryRepo
import geekbrains.slava_5655380.databinding.FragmentUsersBinding
import geekbrains.slava_5655380.ui.presenters.users.HistoryPresenter
import geekbrains.slava_5655380.ui.views.fragments.users.adapter.UsersRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    val presenter: HistoryPresenter by moxyPresenter {
        HistoryPresenter(
            usersRepo = ConversionsHistoryRepo(),
            router = App.instance.router
        )
    }
    var adapter: UsersRVAdapter? = null

    private var vb: FragmentUsersBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}