package art.example.w4tlocompse

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class VM(application: Application) : AndroidViewModel(application), KoinComponent {

    private val workManager = WorkManager.getInstance(application)
    val progress = MutableStateFlow<Int>(0)
    val result = MutableStateFlow<Int>(0)
    val iteracje = MutableStateFlow<Int>(0)

    fun startWorkManagerTask(input: Int) {
        val inputInt = workDataOf("inputInt" to input)
        val workRequest = OneTimeWorkRequestBuilder<MyWorker>()
            .addTag("inputProgress")
            .addTag("itaretionProgress")
            .setInputData(inputInt)
            .build()
        workManager.enqueue(workRequest)
        workManager.getWorkInfoByIdLiveData(workRequest.id)
            .observeForever { workInfo ->
                if (workInfo != null && workInfo.state.isFinished) {
                    result.value = workInfo.outputData.getInt("inputOut", 0)
                    iteracje.value = workInfo.outputData.getInt("iteration", 0)
                }
                if (workInfo != null && !workInfo.state.isFinished) {
                    progress.value = workInfo.progress.getInt("inputProgress", 0)
                    iteracje.value = workInfo.progress.getInt("itaretionProgress", 0)
                }
            }
    }

    fun startThread(input : Int) {
        val th1 =  MyThread(input)
        viewModelScope.launch {
            th1.progress.collect { progress.value = it }
        }
        viewModelScope.launch {
            th1.result.collect { result.value = it }
        }
        viewModelScope.launch {
            th1.iteration.collect { iteracje.value = it }
        }
        th1.start()
    }

    fun starCoroutine(input : Int) {
        val coroutineA = MyCoroutine(input)
        viewModelScope.launch {
            coroutineA.progress.collect { progress.value = it }
        }
        viewModelScope.launch {
            coroutineA.iteration.collect { iteracje.value = it }
        }
        viewModelScope.launch {
            coroutineA.result.collect { result.value = it }
        }
        coroutineA.start(viewModelScope)

    }
}