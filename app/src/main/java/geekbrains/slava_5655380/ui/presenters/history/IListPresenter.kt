package geekbrains.slava_5655380.ui.presenters.history

import geekbrains.slava_5655380.ui.views.fragments.history.adapter.IItemView

interface IListPresenter<V : IItemView> {
    fun bindView(view: V)
    fun getCount(): Int
}