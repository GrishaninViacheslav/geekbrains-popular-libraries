package geekbrains.slava_5655380.ui.presenters.user

import com.github.terrakok.cicerone.Router
import geekbrains.slava_5655380.domain.models.githubusers.GithubUsersRepo
import geekbrains.slava_5655380.ui.views.fragments.user.UserView
import moxy.MvpPresenter

class UserPresenter(
    private val userRepository: GithubUsersRepo,
    private val router: Router,
    private val userLogin: String?
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    private fun loadData() {
        userLogin?.let {
            val user = userRepository.getUser(it)
            user.let { githubUser ->
                viewState.showData("LOGIN: ${githubUser.login}")
            }
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}