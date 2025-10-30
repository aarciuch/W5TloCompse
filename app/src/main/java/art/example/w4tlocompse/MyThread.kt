package art.example.w4tlocompse

import kotlinx.coroutines.flow.MutableStateFlow

class MyThread(val input1: Int) : Thread() {
    val progress = MutableStateFlow<Int>(input1)
    val result = MutableStateFlow<Int>(0)
    val iteration = MutableStateFlow<Int>(0)

    override fun run() {
        for (i in 0 ..input1) {
            progress.value++
            iteration.value = i
            sleep(500)
        }
        result.value = progress.value
    }
}