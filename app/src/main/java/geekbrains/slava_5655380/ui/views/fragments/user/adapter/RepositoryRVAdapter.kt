package geekbrains.slava_5655380.ui.views.fragments.user.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import geekbrains.slava_5655380.databinding.ItemRepositoryBinding
import geekbrains.slava_5655380.ui.presenters.user.IRepositoriesListPresenter

class RepositoryRVAdapter(val presenter: IRepositoriesListPresenter) :
    RecyclerView.Adapter<RepositoryRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemRepositoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(val vb: ItemRepositoryBinding) : RecyclerView.ViewHolder(vb.root),
        RepositoryItemView {
        override var pos = -1

        override fun setName(name: String) = with(vb) {
            tvName.text = name
        }

        override fun setDescription(description: String) = with(vb){
            tvDescription.text = description
        }
    }
}