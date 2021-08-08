package geekbrains.slava_5655380.ui.presenters.user

import com.github.terrakok.cicerone.Router
import geekbrains.slava_5655380.domain.models.githubusers.GithubUser
import geekbrains.slava_5655380.domain.models.githubusers.GithubUsersRepo
import geekbrains.slava_5655380.ui.views.fragments.user.UserView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter

class UserPresenter(
    private val userRepository: GithubUsersRepo,
    private val router: Router,
    private val userLogin: String,
    private var disposable: Disposable? = null
) : MvpPresenter<UserView>() {
    private val observer = object : DisposableSingleObserver<GithubUser>() {
        override fun onSuccess(value: GithubUser) {
            viewState.showData("LOGIN: ${value.login}")
        }

        override fun onError(error: Throwable) {
            error.printStackTrace()
        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    private fun loadData() {
        disposable = userRepository
            .getUser(userLogin)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribeWith(observer)

    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}