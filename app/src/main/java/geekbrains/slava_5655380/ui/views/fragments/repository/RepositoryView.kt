package geekbrains.slava_5655380.ui.views.fragments.repository

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepositoryView : MvpView {
    fun setName(name: String)
    fun setDescription(description: String)
}