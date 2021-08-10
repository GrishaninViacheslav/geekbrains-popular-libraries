package geekbrains.slava_5655380.ui.presenters.history

import geekbrains.slava_5655380.domain.models.conversions_history.Conversion
import geekbrains.slava_5655380.domain.models.conversions_history.ConversionStatus
import geekbrains.slava_5655380.ui.views.fragments.history.adapter.HistoryItemView

interface IHistoryListPresenter : IListPresenter<HistoryItemView>{
    fun getItemType(position: Int): ConversionStatus
    fun addItem(item: Conversion)
    fun cancelItem(position: Int)
}