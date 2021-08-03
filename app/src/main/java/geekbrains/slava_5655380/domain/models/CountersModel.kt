package geekbrains.slava_5655380.domain.models

class CountersModel(private val counters: MutableList<Int> = mutableListOf(0, 0, 0)) {
    fun getCurrent(index: Int): Int {
        return counters[index]
    }

    fun next(index: Int): Int {
        counters[index]++
        return getCurrent(index)
    }

    fun set(index: Int, value: Int) {
        counters[index] = value
    }
}