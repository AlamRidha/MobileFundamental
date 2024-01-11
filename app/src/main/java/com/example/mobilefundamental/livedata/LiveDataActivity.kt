package com.example.mobilefundamental.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mobilefundamental.R
import com.example.mobilefundamental.databinding.ActivityLiveDataBinding

class LiveDataActivity : AppCompatActivity() {
    //  declaration for component
    private lateinit var binding: ActivityLiveDataBinding
    private lateinit var liveDataModel: LiveDataModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        liveDataModel = ViewModelProvider(this)[LiveDataModel::class.java]
        //  call function subscribe
        subscribe()
    }

    //  make function to call function from model
    private fun subscribe() {
        val elapsedTimeObserver = Observer<Long?> { aLong ->
            val newText = this@LiveDataActivity.resources.getString(R.string.seconds, aLong)
            binding.timerTextview.text = newText
        }

        liveDataModel.getElapsedTime().observe(this, elapsedTimeObserver)
    }
}