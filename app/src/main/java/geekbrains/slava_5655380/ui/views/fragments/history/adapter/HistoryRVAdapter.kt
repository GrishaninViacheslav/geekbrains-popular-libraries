package geekbrains.slava_5655380.ui.views.fragments.history.adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import geekbrains.slava_5655380.databinding.ItemConversionDoneBinding
import geekbrains.slava_5655380.databinding.ItemConversionInProcessBinding
import geekbrains.slava_5655380.domain.models.conversions_history.ConversionStatus
import geekbrains.slava_5655380.ui.presenters.history.IHistoryListPresenter

class HistoryRVAdapter(val presenter: IHistoryListPresenter) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private inner class ConversionDoneViewHolder(private val vb: ItemConversionDoneBinding) :
        RecyclerView.ViewHolder(vb.root),
        HistoryItemView {
        override var pos = -1

        override fun setName(text: String) = with(vb) {
            name.text = text
        }

        override fun setPreview(img: Bitmap) = with(vb) {
            imagePreview.setImageBitmap(img)
            backgroundImagePreview.setImageBitmap(img)
        }
    }

    private inner class ConversionInProgressViewHolder(private val vb: ItemConversionInProcessBinding) :
        RecyclerView.ViewHolder(vb.root),
        HistoryItemView {
        init {
            with(vb) {
                buttonCancel.setOnClickListener { presenter.cancelItem(pos) }
            }
        }

        override var pos = -1

        override fun setName(text: String) = with(vb) {
            name.text = text
        }

        override fun setPreview(img: Bitmap) = with(vb) {
            imagePreview.setImageBitmap(img)
            backgroundImagePreview.setImageBitmap(img)
        }
    }

    override fun getItemViewType(position: Int) = presenter.getItemType(position).ordinal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == ConversionStatus.DONE.ordinal) {
            ConversionDoneViewHolder(
                ItemConversionDoneBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            ConversionInProgressViewHolder(
                ItemConversionInProcessBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) =
        presenter.bindView((viewHolder as HistoryItemView).apply { pos = position })
}