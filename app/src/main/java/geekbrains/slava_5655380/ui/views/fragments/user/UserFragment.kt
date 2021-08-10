package geekbrains.slava_5655380.ui.views.fragments.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import geekbrains.slava_5655380.App
import geekbrains.slava_5655380.databinding.FragmentUserBinding
import geekbrains.slava_5655380.domain.models.githubusers.GithubUsersRepo
import geekbrains.slava_5655380.ui.presenters.user.UserPresenter
import geekbrains.slava_5655380.ui.views.fragments.users.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {
    private val userId by lazy { requireArguments().getString(ARG_USER_ID) }
    private val ARG_USER_ID = "USER_ID"
    private val view: FragmentUserBinding by viewBinding(createMethod = CreateMethod.INFLATE)
    private val presenter by moxyPresenter {
        UserPresenter(
            GithubUsersRepo(),
            App.instance.router,
            userId!!
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = view.root

    companion object {
        @JvmStatic
        fun newInstance(userId: String) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_USER_ID, userId)
                }
            }
    }

    override fun showData(data: String) {
        view.textLogin.text = data
    }

    override fun backPressed() = presenter.backPressed()
}