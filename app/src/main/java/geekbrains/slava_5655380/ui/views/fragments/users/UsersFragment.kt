package geekbrains.slava_5655380.ui.views.fragments.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import geekbrains.slava_5655380.ApiHolder
import geekbrains.slava_5655380.App
import geekbrains.slava_5655380.databinding.FragmentUsersBinding
import geekbrains.slava_5655380.domain.models.imageloader.GlideImageLoader
import geekbrains.slava_5655380.domain.models.networkstatus.AndroidNetworkStatus
import geekbrains.slava_5655380.domain.models.repositories.github.Database
import geekbrains.slava_5655380.domain.models.repositories.github.RetrofitGithubUsersRepo
import geekbrains.slava_5655380.ui.presenters.users.UsersPresenter
import geekbrains.slava_5655380.ui.views.fragments.users.adapter.UsersRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            usersRepo = RetrofitGithubUsersRepo(ApiHolder.api, AndroidNetworkStatus(requireContext()), Database.getInstance()),
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
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}