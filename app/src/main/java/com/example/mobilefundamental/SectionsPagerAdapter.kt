package com.example.mobilefundamental

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        //  set position for tabs layout
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = PageOneFragment()
            1 -> fragment = PageTwoFragment()
        }
        return fragment as Fragment
    }
}