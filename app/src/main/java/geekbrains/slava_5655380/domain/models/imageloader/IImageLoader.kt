package geekbrains.slava_5655380.domain.models.imageloader

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}