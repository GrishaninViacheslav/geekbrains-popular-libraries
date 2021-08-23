package geekbrains.slava_5655380.ui.views.activity

import com.github.terrakok.cicerone.Screen
import geekbrains.slava_5655380.domain.models.githubusers.GithubUser
import geekbrains.slava_5655380.domain.models.githubusers.githubrepository.GithubRepository

interface IScreens {
    fun users(): Screen
    fun user(user: GithubUser): Screen
    fun repository(repository: GithubRepository): Screen
}