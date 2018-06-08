package com.example.workmanagerinputdatapropagation.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import androidx.work.*
import com.example.workmanagerinputdatapropagation.constants.SYNC_LAST_ITEM_TAG
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

    val status: LiveData<List<SyncPresenter>>?
        get() = Transformations.map(WorkManager.getInstance().getStatusesByTag(SYNC_LAST_ITEM_TAG)) {
            val toList = it.toList().map { SyncPresenter(it) }
            toList
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
        val startWorker = OneTimeWorkRequestBuilder<StartWorker>()
            .setConstraints(constraints).addTag(SYNC_LAST_ITEM_TAG).build()

        val workRequest1 = OneTimeWorkRequestBuilder<Worker1>().addTag(SYNC_LAST_ITEM_TAG).build()
        val workRequest2 = OneTimeWorkRequestBuilder<Worker2>().addTag(SYNC_LAST_ITEM_TAG).build()
        val workRequest3 = OneTimeWorkRequestBuilder<Worker3>().addTag(SYNC_LAST_ITEM_TAG).build()
        val workRequest4 = OneTimeWorkRequestBuilder<Worker4>().addTag(SYNC_LAST_ITEM_TAG).build()
        val workRequest5 = OneTimeWorkRequestBuilder<Worker5>().addTag(SYNC_LAST_ITEM_TAG).build()
        val workRequest6 = OneTimeWorkRequestBuilder<Worker6>().addTag(SYNC_LAST_ITEM_TAG).build()
        val workRequest7 = OneTimeWorkRequestBuilder<Worker7>().addTag(SYNC_LAST_ITEM_TAG).build()

        WorkManager.getInstance()
            .beginUniqueWork(SYNC_WORK_NAME, ExistingWorkPolicy.KEEP, startWorker)
            .then(
                workRequest1,
//                workRequest2,
//                workRequest3,
//                workRequest4,
//                workRequest5,
                workRequest6,
                workRequest7)
            .then(
                OneTimeWorkRequestBuilder<FinishWorker>().addTag(SYNC_LAST_ITEM_TAG).build())
            .enqueue()
    }

}

