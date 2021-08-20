package geekbrains.slava_5655380.domain.models.githubusers

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}