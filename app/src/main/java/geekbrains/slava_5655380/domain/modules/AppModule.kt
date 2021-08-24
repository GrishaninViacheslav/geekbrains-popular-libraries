package geekbrains.slava_5655380.domain.modules

import dagger.Module
import dagger.Provides
import geekbrains.slava_5655380.App

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }

}