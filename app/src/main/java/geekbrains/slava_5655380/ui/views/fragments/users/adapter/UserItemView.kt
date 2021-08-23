package geekbrains.slava_5655380.ui.views.fragments.users.adapter

import geekbrains.slava_5655380.ui.views.fragments.IItemView

interface UserItemView : IItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}