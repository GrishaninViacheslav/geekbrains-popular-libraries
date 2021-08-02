package geekbrains.slava_5655380.ui.presenters

interface MainView {
    fun setButtonText(index: ButtonIndex, text: String)
    enum class ButtonIndex { COUNTER_1, COUNTER_2, COUNTER_3 }
}