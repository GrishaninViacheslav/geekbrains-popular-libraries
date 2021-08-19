package geekbrains.slava_5655380.ui.views.fragments.history.adapter

import android.graphics.Bitmap

interface HistoryItemView : IItemView {
    fun setName(text: String)
    fun setPreview(img: Bitmap)
}