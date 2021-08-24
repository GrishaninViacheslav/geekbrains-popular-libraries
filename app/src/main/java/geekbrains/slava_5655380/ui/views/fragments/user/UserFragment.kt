package geekbrains.slava_5655380.ui.views.fragments.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import geekbrains.slava_5655380.App
import geekbrains.slava_5655380.R
import geekbrains.slava_5655380.databinding.FragmentUserBinding
import geekbrains.slava_5655380.domain.models.networkstatus.AndroidNetworkStatus
import geekbrains.slava_5655380.domain.models.repositories.github.Database
import geekbrains.slava_5655380.domain.models.repositories.github.IDataSource
import geekbrains.slava_5655380.domain.models.repositories.github.user.GithubUser
import geekbrains.slava_5655380.domain.models.repositories.github.RetrofitGithubUsersRepo
import geekbrains.slava_5655380.domain.models.repositories.github.RoomGithubCache
import geekbrains.slava_5655380.ui.presenters.user.UserPresenter
import geekbrains.slava_5655380.ui.views.Screens
import geekbrains.slava_5655380.ui.views.fragments.user.adapter.RepositoryRVAdapter
import geekbrains.slava_5655380.ui.views.fragments.users.BackButtonListener
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {
    private val view: FragmentUserBinding by viewBinding(createMethod = CreateMethod.INFLATE)

    @Inject
    lateinit var database: Database
    @Inject lateinit var router: Router
    @Inject lateinit var api: IDataSource

    val presenter: UserPresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser
        UserPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGithubUsersRepo(api, AndroidNetworkStatus(App.instance), RoomGithubCache(database)),
            router,
            user,
            Screens()
        )
    }

    var adapter: RepositoryRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = view.root

    companion object {
        private const val USER_ARG = "user"

        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
            App.instance.appComponent.inject(this)
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