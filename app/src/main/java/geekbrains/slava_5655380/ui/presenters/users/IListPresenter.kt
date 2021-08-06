package geekbrains.slava_5655380.ui.presenters.users

import geekbrains.slava_5655380.ui.views.fragments.users.adapter.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}