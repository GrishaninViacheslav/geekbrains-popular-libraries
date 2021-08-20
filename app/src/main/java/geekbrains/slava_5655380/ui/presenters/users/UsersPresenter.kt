package geekbrains.slava_5655380.ui.presenters.users

import com.github.terrakok.cicerone.Router
import geekbrains.slava_5655380.domain.models.githubusers.GithubUser
import geekbrains.slava_5655380.domain.models.githubusers.IGithubUsersRepo
import geekbrains.slava_5655380.ui.views.Screens
import geekbrains.slava_5655380.ui.views.fragments.users.UsersView
import geekbrains.slava_5655380.ui.views.fragments.users.adapter.UserItemView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter

class UsersPresenter(
    private val disposables: CompositeDisposable = CompositeDisposable(),
    private val usersRepo: IGithubUsersRepo,
    private val router: Router,
    val usersListPresenter: UsersListPresenter = UsersListPresenter()
) :
    MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            with(view) {
                user.login?.let { setLogin(it) }
                user.avatarUrl?.let { loadAvatar(it) }
            }
        }
    }

    private val observer = object : DisposableSingleObserver<List<GithubUser>>() {
        override fun onSuccess(value: List<GithubUser>) {
            usersListPresenter.users.addAll(value)
            viewState.updateList()
        }

        override fun onError(error: Throwable) {
            error.printStackTrace()
        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            val id = usersListPresenter.users[itemView.pos].id
            router.navigateTo(Screens.user(id))
        }
    }

    fun loadData() {
        disposables.add(
            usersRepo
                .getUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribeWith(observer)
        )
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}