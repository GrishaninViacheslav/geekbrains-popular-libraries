package geekbrains.slava_5655380.ui.views.fragments.repository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import geekbrains.slava_5655380.App
import geekbrains.slava_5655380.databinding.FragmentRepositoryBinding
import geekbrains.slava_5655380.domain.models.githubusers.githubrepository.GithubRepository
import geekbrains.slava_5655380.ui.presenters.repository.RepositoryPresenter
import geekbrains.slava_5655380.ui.views.fragments.user.adapter.RepositoryRVAdapter
import geekbrains.slava_5655380.ui.views.fragments.users.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepositoryFragment : MvpAppCompatFragment(), RepositoryView, BackButtonListener {
    private val repository by lazy { requireArguments().getParcelable<GithubRepository>(ARG_REPOSITORY) }
    private val ARG_REPOSITORY = "REPOSITORY"
    private val view: FragmentRepositoryBinding by viewBinding(createMethod = CreateMethod.INFLATE)
    private val presenter by moxyPresenter {
        RepositoryPresenter(
            App.instance.router,
            repository!!
        )
    }

    var adapter: RepositoryRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = view.root

    companion object {
        @JvmStatic
        fun newInstance(repository: GithubRepository) =
            RepositoryFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_REPOSITORY, repository)
                }
            }
    }

    override fun backPressed() = presenter.backPressed()

    override fun setName(name: String) {
        view.tvRepositoryName.text = "NAME:\n$name"
    }

    override fun setDescription(description: String) {
        view.tvRepositoryDescription.text = "DESCRIPTION:\n$description"
    }
}