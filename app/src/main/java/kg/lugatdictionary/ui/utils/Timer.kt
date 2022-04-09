package kg.lugatdictionary.ui.utils

import kotlinx.coroutines.*

class Timer(private val scope: CoroutineScope, private val doOnEndTime: () -> Unit) {

    private var job: Job? = null

    fun startTimer() {
        job?.cancel()
        job = scope.launch {
            delay(800)
            doOnEndTime.invoke()
        }
    }

    fun cancelTimer() {
        job?.cancel()
    }
}