package art.example.w4tlocompse

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MyCoroutine(val input1 : Int) {
    val progress = MutableStateFlow(input1)
    val result = MutableStateFlow(0)
    val iteration = MutableStateFlow(0)

    fun start(scope: CoroutineScope) {
        scope.launch {
            for (i in 0..input1) {
                iteration.value = i
                progress.value ++
                delay(500) // ⏱️ nie blokuje wątku
            }
            result.value = progress.value
        }
    }
}