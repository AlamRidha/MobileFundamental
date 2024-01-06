package com.example.mobilefundamental

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mobilefundamental.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //  declaration fragment to overwrite mainactivity
        val fragmentManager = supportFragmentManager
        val homeFragment = HomeFragment()
        val fragment = fragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName)

        if (fragment !is HomeFragment) {
            Log.d(TAG, "Fragment name : " + HomeFragment::class.java.simpleName)
            fragmentManager.beginTransaction()
                .add(R.id.frame_container, homeFragment, HomeFragment::class.java.simpleName)
                .commit()
        }

    }

    companion object {
        const val TAG = "MainActivity"
    }
}