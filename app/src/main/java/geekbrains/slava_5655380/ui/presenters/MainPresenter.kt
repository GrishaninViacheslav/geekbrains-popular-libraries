package geekbrains.slava_5655380.ui.presenters

import geekbrains.slava_5655380.domain.models.CountersModel
import geekbrains.slava_5655380.ui.presenters.MainView.ButtonIndex

class MainPresenter(val view: MainView) {
    val model = CountersModel()

    fun counterClick(id: ButtonIndex){
        when(id){
            ButtonIndex.COUNTER_1-> {
                val nextValue = model.next(0)
                view.setButtonText(ButtonIndex.COUNTER_1, nextValue.toString())
            }
            ButtonIndex.COUNTER_2 -> {
                val nextValue = model.next(1)
                view.setButtonText(ButtonIndex.COUNTER_2, nextValue.toString())
            }
            ButtonIndex.COUNTER_3 -> {
                val nextValue = model.next(2)
                view.setButtonText(ButtonIndex.COUNTER_3, nextValue.toString())
            }
        }
    }
}