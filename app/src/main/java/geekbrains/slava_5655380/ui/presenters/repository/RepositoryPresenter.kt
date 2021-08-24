package geekbrains.slava_5655380.ui.presenters.repository

import com.github.terrakok.cicerone.Router
import geekbrains.slava_5655380.domain.models.repositories.github.repository.GithubRepository
import geekbrains.slava_5655380.ui.views.fragments.repository.RepositoryView
import moxy.MvpPresenter
import javax.inject.Inject

class RepositoryPresenter(
    private val repository: GithubRepository
) : MvpPresenter<RepositoryView>() {
    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        repository.name?.let { viewState.setName(it) }
        repository.description?.let { viewState.setDescription(it) }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}