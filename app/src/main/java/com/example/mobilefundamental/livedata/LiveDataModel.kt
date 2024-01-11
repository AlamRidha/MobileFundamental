package com.example.mobilefundamental.livedata

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Timer
import java.util.TimerTask

class LiveDataModel : ViewModel() {

    //  declaration for component
    private val mInitialTime = SystemClock.elapsedRealtime()

    //  MutableLiveData can change the value
    private val mElapsedTime = MutableLiveData<Long?>()

    init {
        val timer = Timer()
        timer.scheduleAtFixedRate(
            object : TimerTask() {
                override fun run() {
                    val newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000
                    //  meaning of change values is this line
                    mElapsedTime.postValue(newValue)
                }
            }, ONE_SECOND.toLong(), ONE_SECOND.toLong()
        )
    }

    //  LiveData can't change the value
    fun getElapsedTime(): LiveData<Long?> {
        return mElapsedTime
    }

    //  make companion object
    companion object {
        private const val ONE_SECOND = 1000
    }
}