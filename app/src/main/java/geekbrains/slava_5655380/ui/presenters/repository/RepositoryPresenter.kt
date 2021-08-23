package geekbrains.slava_5655380.ui.presenters.repository

import com.github.terrakok.cicerone.Router
import geekbrains.slava_5655380.domain.models.repositories.github.repository.GithubRepository
import geekbrains.slava_5655380.ui.views.fragments.repository.RepositoryView
import moxy.MvpPresenter

class RepositoryPresenter(
    private val router: Router,
    private val repository: GithubRepository
) : MvpPresenter<RepositoryView>() {
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