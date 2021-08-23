package geekbrains.slava_5655380.ui.views.fragments.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import geekbrains.slava_5655380.ApiHolder
import geekbrains.slava_5655380.App
import geekbrains.slava_5655380.R
import geekbrains.slava_5655380.databinding.FragmentUserBinding
import geekbrains.slava_5655380.domain.models.networkstatus.AndroidNetworkStatus
import geekbrains.slava_5655380.domain.models.repositories.github.Database
import geekbrains.slava_5655380.domain.models.repositories.github.user.GithubUser
import geekbrains.slava_5655380.domain.models.repositories.github.RetrofitGithubUsersRepo
import geekbrains.slava_5655380.ui.presenters.user.UserPresenter
import geekbrains.slava_5655380.ui.views.fragments.user.adapter.RepositoryRVAdapter
import geekbrains.slava_5655380.ui.views.fragments.users.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {
    private val user by lazy { requireArguments().getParcelable<GithubUser>(ARG_USER) }
    private val ARG_USER = "USER"
    private val view: FragmentUserBinding by viewBinding(createMethod = CreateMethod.INFLATE)
    private val presenter by moxyPresenter {
        UserPresenter(
            RetrofitGithubUsersRepo(ApiHolder.api, AndroidNetworkStatus(requireContext()), Database.getInstance()),
            App.instance.router,
            user!!
        )
    }

    var adapter: RepositoryRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = view.root

    companion object {
        @JvmStatic
        fun newInstance(user: GithubUser) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_USER, user)
                }
            }
    }

    override fun showUserData(data: String) {
        view.textLogin.text = String.format(getString(R.string.user_login_is), data)
    }

    override fun init() {
        view.rvRepositories.layoutManager = LinearLayoutManager(context)
        adapter = RepositoryRVAdapter(presenter.repositoryListPresenter)
        view.rvRepositories.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}