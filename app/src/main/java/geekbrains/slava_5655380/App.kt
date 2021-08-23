package geekbrains.slava_5655380

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import geekbrains.slava_5655380.domain.models.repositories.github.Database

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        instance = this
        Database.create(this)
    }
}