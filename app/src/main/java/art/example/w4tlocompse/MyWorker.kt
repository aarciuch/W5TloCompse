package art.example.w4tlocompse

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay

class MyWorker(context: Context, params: WorkerParameters) :
                        CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        var inputInt = inputData.getInt("inputInt", 0)
        var iteration = 0
        Log.i("TLO", "Worker is working...")
        return try {
            val ile = inputInt
            for (i in 0..ile) {
                inputInt ++
                iteration = i
                setProgress(workDataOf(
                    "inputProgress" to inputInt,
                    "itaretionProgress" to iteration)
                )
                delay(500)
            }
            Result.success(workDataOf(
                    "inputOut" to inputInt,
                    "iteration" to iteration
                )
            )
        }
        catch (e: Exception) {
            Result.failure()
        }
    }

}