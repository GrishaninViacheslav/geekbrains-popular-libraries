package geekbrains.slava_5655380.ui.presenters.user

import com.github.terrakok.cicerone.Router
import geekbrains.slava_5655380.domain.models.conversions_history.ConversionMetadata
import geekbrains.slava_5655380.domain.models.conversions_history.ConversionsHistoryRepo
import geekbrains.slava_5655380.ui.views.fragments.user.UserView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter

class ConversionPresenter(
    private val userRepository: ConversionsHistoryRepo,
    private val router: Router,
    private val userLogin: String?,
    private var disposable: Disposable? = null
) : MvpPresenter<UserView>() {
    private val observer = object : DisposableSingleObserver<ConversionMetadata>() {
        override fun onSuccess(value: ConversionMetadata) {
            viewState.showData("IMG NAME: ${value.name}")
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
        disposable = userLogin?.let {
            userRepository
                .getConversionMetadata(it)
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