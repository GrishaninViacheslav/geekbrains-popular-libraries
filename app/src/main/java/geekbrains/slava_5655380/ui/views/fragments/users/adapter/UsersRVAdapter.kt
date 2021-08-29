package geekbrains.slava_5655380.ui.views.fragments.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import geekbrains.slava_5655380.App
import geekbrains.slava_5655380.databinding.ItemUserBinding
import geekbrains.slava_5655380.domain.models.imageloader.IImageLoader
import geekbrains.slava_5655380.ui.presenters.users.IUserListPresenter
import javax.inject.Inject

class UsersRVAdapter(val presenter: IUserListPresenter) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemUserBinding.inflate(
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

    inner class ViewHolder(private val vb: ItemUserBinding) : RecyclerView.ViewHolder(vb.root),
        UserItemView {
        override var pos = -1

        override fun setLogin(text: String) = with(vb) {
            tvLogin.text = text
        }

        override fun loadAvatar(url: String) {
            imageLoader.loadInto(url, vb.ivAvatar)
        }
    }
}