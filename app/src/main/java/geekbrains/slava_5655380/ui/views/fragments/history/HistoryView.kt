package geekbrains.slava_5655380.ui.views.fragments.history

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Intent
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface HistoryView : MvpView {
    fun init()
    fun updateList()
}