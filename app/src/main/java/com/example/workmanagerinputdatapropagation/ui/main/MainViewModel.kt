package com.example.workmanagerinputdatapropagation.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import androidx.work.*
import com.example.workmanagerinputdatapropagation.constants.SYNC_WORK_NAME

class MainViewModel : ViewModel() {
    val inProgress: LiveData<Boolean>
        get() = Transformations.map(
            WorkManager.getInstance().getStatusesForUniqueWork(SYNC_WORK_NAME)) {
            if (it == null || it.isEmpty()) return@map false
            else {
                return@map it.any { !it.state.isFinished }
            }
        }
    private val constraints = Constraints.Builder()
        .setRequiresCharging(true)
        .setRequiresStorageNotLow(true)
        .setRequiresBatteryNotLow(true)
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    fun reset() {
        performSync()
    }

    fun cancel() {
        WorkManager.getInstance().cancelUniqueWork(SYNC_WORK_NAME)
    }

    private fun performSync() {
        val build = OneTimeWorkRequestBuilder<StartWorker>()
            .setConstraints(constraints)

        val startWorker = build.build()

        WorkManager.getInstance()
            .beginUniqueWork(SYNC_WORK_NAME, ExistingWorkPolicy.KEEP, startWorker)
            .then(
                OneTimeWorkRequest.from(
                    Worker1::class.java,
                    Worker2::class.java,
                    Worker3::class.java,
                    Worker4::class.java,
                    Worker5::class.java,
                    Worker6::class.java,
                    Worker7::class.java))
            .then(OneTimeWorkRequest.from(FinishWorker::class.java))
            .enqueue()
    }

}

