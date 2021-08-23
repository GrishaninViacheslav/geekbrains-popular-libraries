package geekbrains.slava_5655380.ui.views.activity

import com.github.terrakok.cicerone.Screen
import geekbrains.slava_5655380.domain.models.repositories.github.user.GithubUser
import geekbrains.slava_5655380.domain.models.repositories.github.repository.GithubRepository

interface IScreens {
    fun users(): Screen
    fun user(user: GithubUser): Screen
    fun repository(repository: GithubRepository): Screen
}