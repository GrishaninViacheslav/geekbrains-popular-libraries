package geekbrains.slava_5655380.ui.presenters

import geekbrains.slava_5655380.domain.models.CountersModel

class MainPresenter(
    private val view: MainView,
    private val model: CountersModel = CountersModel()
) {
    fun incrementFirstCounter() {
        val nextValue = model.next(0)
        view.setFirstCounterText(nextValue.toString())
    }

    fun incrementSecondCounter() {
        val nextValue = model.next(1)
        view.setSecondCounterText(nextValue.toString())
    }

    fun incrementThirdCounter() {
        val nextValue = model.next(2)
        view.setThirdCounterText(nextValue.toString())
    }
}