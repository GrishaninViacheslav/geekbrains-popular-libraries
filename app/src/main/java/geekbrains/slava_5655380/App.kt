package geekbrains.slava_5655380

import android.app.Application
import geekbrains.slava_5655380.domain.modules.AppComponent
import geekbrains.slava_5655380.domain.modules.AppModule
import geekbrains.slava_5655380.domain.modules.DaggerAppComponent

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}