package geekbrains.slava_5655380.domain.modules

import android.widget.ImageView
import dagger.Module
import dagger.Provides
import geekbrains.slava_5655380.domain.models.imageloader.GlideImageLoader
import geekbrains.slava_5655380.domain.models.imageloader.IImageLoader
import javax.inject.Singleton

@Module
class ImageLoaderModule {
    @Singleton
    @Provides
    fun imageLoader(): IImageLoader<ImageView> = GlideImageLoader()
}