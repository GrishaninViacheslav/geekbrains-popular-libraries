package geekbrains.slava_5655380.ui.views.fragments.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import geekbrains.slava_5655380.App
import geekbrains.slava_5655380.databinding.FragmentUserBinding
import geekbrains.slava_5655380.domain.models.conversions_history.ConversionsHistoryRepo
import geekbrains.slava_5655380.ui.presenters.user.ConversionPresenter
import geekbrains.slava_5655380.ui.views.fragments.users.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {
    private val userId by lazy { arguments?.getString(ARG_USER_ID) }
    private val ARG_USER_ID = "USER_ID"
    private lateinit var binding: FragmentUserBinding
    private val presenter by moxyPresenter {
        ConversionPresenter(
            ConversionsHistoryRepo(),
            App.instance.router,
            userId
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentUserBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

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
        binding.textLogin.text = data
    }

    override fun backPressed() = presenter.backPressed()
}