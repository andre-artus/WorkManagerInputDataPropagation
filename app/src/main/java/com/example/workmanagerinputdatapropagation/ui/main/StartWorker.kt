package com.example.workmanagerinputdatapropagation.ui.main

import androidx.work.Worker
import androidx.work.toWorkData

import com.example.workmanagerinputdatapropagation.constants.KEY_SYNC_START_TIME
import com.example.workmanagerinputdatapropagation.constants.KEY_WORKER_NAME
import org.threeten.bp.Instant
import timber.log.Timber

class StartWorker : Worker() {
    override fun doWork(): WorkerResult {
        val startTime = Instant.now().toString()
        outputData = mapOf(
            KEY_WORKER_NAME to this.javaClass.simpleName,
            KEY_SYNC_START_TIME to startTime)
            .toWorkData()

        return WorkerResult.SUCCESS
    }

}

open class BaseWorker : Worker() {
    override fun doWork(): WorkerResult {
        val startTime = inputData.getString(KEY_SYNC_START_TIME, "")
        // startTime == value set by Instant.now().toString()

        val mapOf = mapOf(
            KEY_WORKER_NAME to this.javaClass.simpleName)

        val map = inputData.keyValueMap + mapOf

        outputData = map.toWorkData()

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

class Worker7 : BaseWorker() {
    override fun doWork(): WorkerResult {
//        outputData = inputData

        val workerResult = super.doWork()
        outputData = mapOf(
            KEY_WORKER_NAME to "Overwrite 7")
            .toWorkData()

        return workerResult
    }
}

class FinishWorker : Worker() {
    override fun doWork(): WorkerResult {
        val startTime = inputData.getString(KEY_SYNC_START_TIME, "")
        outputData = mapOf(
            KEY_SYNC_START_TIME to startTime,
            KEY_WORKER_NAME to this.javaClass.simpleName)
            .toWorkData()
        // startTime == ""
        Timber.d(startTime)
        return when {
            startTime.isNullOrBlank() -> WorkerResult.FAILURE
            else -> WorkerResult.SUCCESS
        }
    }
}
