package com.example.workmanagerinputdatapropagation.ui.main

import androidx.work.Worker
import androidx.work.toWorkData

import com.example.workmanagerinputdatapropagation.constants.KEY_SYNC_START_TIME
import org.threeten.bp.Instant
import timber.log.Timber

class StartWorker : Worker() {
    override fun doWork(): WorkerResult {
        val startTime = Instant.now().toString()
        outputData = mapOf(
            KEY_SYNC_START_TIME to startTime)
            .toWorkData()

        return WorkerResult.SUCCESS
    }

}

open class BaseWorker : Worker() {
    override fun doWork(): WorkerResult {
        val startTime = inputData.getString(KEY_SYNC_START_TIME, "")
        // startTime == value set by Instant.now().toString()
        Timber.d(startTime)
        Thread.sleep(500)
        return WorkerResult.SUCCESS
    }
}

class Worker1 : BaseWorker()
class Worker2 : BaseWorker()
class Worker3 : BaseWorker()
class Worker4 : BaseWorker()
class Worker5 : BaseWorker()
class Worker6 : BaseWorker()
class Worker7 : BaseWorker()

class FinishWorker : Worker() {
    override fun doWork(): WorkerResult {
        val startTime = inputData.getString(KEY_SYNC_START_TIME, "")
        // startTime == ""
        Timber.d(startTime)
        return WorkerResult.SUCCESS
    }
}
