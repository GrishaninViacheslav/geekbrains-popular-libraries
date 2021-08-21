package geekbrains.slava_5655380.ui.views.activity

import com.github.terrakok.cicerone.Screen
import geekbrains.slava_5655380.domain.models.githubusers.GithubUser

interface IScreens {
    fun users(): Screen
    fun user(user: GithubUser): Screen
}