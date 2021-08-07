package geekbrains.slava_5655380.ui.views.activity

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun user(id: String): Screen
}