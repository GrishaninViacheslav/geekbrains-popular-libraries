package geekbrains.slava_5655380.ui.presenters.history

import android.net.Uri
import com.github.terrakok.cicerone.Router
import geekbrains.slava_5655380.domain.models.BitmapFromUriFactory
import geekbrains.slava_5655380.domain.models.conversions_history.Conversion
import geekbrains.slava_5655380.domain.models.conversions_history.ConversionMetadata
import geekbrains.slava_5655380.domain.models.conversions_history.ConversionsHistoryRepo
import geekbrains.slava_5655380.ui.views.fragments.history.HistoryView
import geekbrains.slava_5655380.ui.views.fragments.history.adapter.HistoryItemView
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter
import java.io.*

class HistoryPresenter(
    private val usersRepo: ConversionsHistoryRepo,
    private val router: Router
) :
    MvpPresenter<HistoryView>() {
    private inner class AddToHistoryObserver : DisposableSingleObserver<Conversion>() {
        override fun onSuccess(value: Conversion) {
            addToHistory(value)
            inProgressDisposables.add(
                value.convertAndSave()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribeWith(ConversionCompletionObserver())
            )
        }

        override fun onError(error: Throwable) {
            error.printStackTrace()
        }
    }

    private inner class ConversionCompletionObserver : DisposableCompletableObserver() {
        override fun onComplete() {
            viewState.updateList()
        }

        override fun onError(error: Throwable) {
            error.printStackTrace()
        }
    }

    private val inProgressDisposables: MutableList<Disposable> = mutableListOf()

    private fun addToHistory(conversion: Conversion) {
        usersRepo.add(conversion.metadata)
        usersListPresenter.addItem(conversion)
        viewState.updateList()
    }

    private fun makeConversion(imageUri: Uri): Single<Conversion> = Single.fromCallable {
        return@fromCallable Conversion(
            ConversionMetadata(imageUri),
            BitmapFromUriFactory.decodeImageUri(imageUri)
        )
    }

    inner class UsersListPresenter : IHistoryListPresenter {
        private val history = mutableListOf<Conversion>()

        override fun getCount() = history.size

        override fun bindView(view: HistoryItemView) {
            val conversion = history[view.pos]
            view.setName(conversion.metadata.name)
            view.setPreview(conversion.bitmap)
        }

        override fun getItemType(position: Int) = history[position].metadata.status

        override fun addItem(item: Conversion) {
            history.add(item)
        }

        override fun cancelItem(position: Int) {
            history.removeAt(position)
            viewState.updateList()
            inProgressDisposables[position].dispose()
            inProgressDisposables.removeAt(position)
        }
    }

    val usersListPresenter: UsersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun convertImage(imageUri: Uri) {
        makeConversion(imageUri)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.computation())
            .subscribeWith(AddToHistoryObserver())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}