package com.example.movies.ViewModel

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashScreenViewModel : ViewModel() {

    val isFinished = MutableLiveData<Boolean>()

    init {
        object : CountDownTimer(2500L, 1000) {

            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                isFinished.postValue(true)
            }

        }.start()
    }
}