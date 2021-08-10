package geekbrains.slava_5655380.ui.presenters.users

import com.github.terrakok.cicerone.Router
import geekbrains.slava_5655380.domain.models.conversions_history.ConversionMetadata
import geekbrains.slava_5655380.domain.models.conversions_history.ConversionsHistoryRepo
import geekbrains.slava_5655380.ui.views.Screens
import geekbrains.slava_5655380.ui.views.fragments.users.UsersView
import geekbrains.slava_5655380.ui.views.fragments.users.adapter.ConversionItemView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter

class HistoryPresenter(
    private val disposables: CompositeDisposable = CompositeDisposable(),
    val usersListPresenter: UsersListPresenter = UsersListPresenter(),
    val usersRepo: ConversionsHistoryRepo,
    val router: Router
) :
    MvpPresenter<UsersView>() {
    class UsersListPresenter : IConversionListPresenter {
        val users = mutableListOf<ConversionMetadata>()
        override var itemClickListener: ((ConversionItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: ConversionItemView) {
            val user = users[view.pos]
            view.setLogin(user.name)
        }
    }

    private val observer = object : DisposableSingleObserver<List<ConversionMetadata>>() {
        override fun onSuccess(value: List<ConversionMetadata>) {
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
            val login = usersListPresenter.users[itemView.pos].name
            router.navigateTo(Screens.user(login))
        }
    }

    fun loadData() {
        disposables.add(usersRepo
            .getHistory()
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