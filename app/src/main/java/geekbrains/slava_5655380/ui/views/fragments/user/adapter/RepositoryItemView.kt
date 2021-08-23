package geekbrains.slava_5655380.ui.views.fragments.user.adapter

import geekbrains.slava_5655380.ui.views.fragments.IItemView

interface RepositoryItemView: IItemView {
    fun setName(name: String)
    fun setDescription(description: String)
}