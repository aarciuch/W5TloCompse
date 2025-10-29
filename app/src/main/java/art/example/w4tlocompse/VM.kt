package art.example.w4tlocompse

import android.app.Application
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asFlow
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.core.component.KoinComponent

class VM(application: Application): AndroidViewModel(application), KoinComponent {

    private val workManager = WorkManager.getInstance(application)
    val progress = MutableStateFlow<Int>(0)
    val result = MutableStateFlow<Int>(0)
    val iteracje = MutableStateFlow<Int>(0)

    fun startWorkManagerTast(input: Int) {
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
}