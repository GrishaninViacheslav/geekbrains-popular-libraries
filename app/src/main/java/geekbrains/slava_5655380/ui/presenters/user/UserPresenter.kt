package geekbrains.slava_5655380.ui.presenters.user

import com.github.terrakok.cicerone.Router
import geekbrains.slava_5655380.domain.models.githubusers.GithubUser
import geekbrains.slava_5655380.domain.models.githubusers.IGithubUsersRepo
import geekbrains.slava_5655380.domain.models.githubusers.githubrepository.GithubRepository
import geekbrains.slava_5655380.ui.views.fragments.user.UserView
import geekbrains.slava_5655380.ui.views.fragments.user.adapter.RepositoryItemView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter

class UserPresenter(
    private val userRepository: IGithubUsersRepo,
    private val router: Router,
    private val user: GithubUser,
    private var disposable: Disposable? = null,
    val repositoryListPresenter: RepositoryListPresenter = RepositoryListPresenter()
) : MvpPresenter<UserView>() {
    class RepositoryListPresenter : IRepositoriesListPresenter {
        val repositories = mutableListOf<GithubRepository>()
        override var itemClickListener: ((RepositoryItemView) -> Unit)? = null

        override fun getCount() = repositories.size

        override fun bindView(view: RepositoryItemView) {
            val repository = repositories[view.pos]
            with(view) {
                repository.name?.let { setName(it) }
                repository.description?.let { setDescription(it) }
            }
        }
    }

    private val observer = object : DisposableSingleObserver<List<GithubRepository>>() {
        override fun onSuccess(value: List<GithubRepository>) {
            repositoryListPresenter.repositories.addAll(value)
            viewState.updateList()
        }

        override fun onError(error: Throwable) {
            error.printStackTrace()
        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showUserData("LOGIN: ${user.login}")
        viewState.init()
        loadData()
    }

    private fun loadData() {
        disposable = user.repos_url?.let {
            userRepository
                .getRepositories(it)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribeWith(observer)
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}