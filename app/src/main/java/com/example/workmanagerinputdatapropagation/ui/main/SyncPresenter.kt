package com.example.workmanagerinputdatapropagation.ui.main

import androidx.work.WorkStatus

class SyncPresenter(val s: WorkStatus) {
    val id
        get() = s.id.toString()

    val state
        get() = s.state.name

    val isFinished
        get() = s.state.isFinished.toString()

    val size
        get() = "[${s.outputData.keyValueMap.entries.joinToString { "${it.key}: ${it.value}" }}]"

}