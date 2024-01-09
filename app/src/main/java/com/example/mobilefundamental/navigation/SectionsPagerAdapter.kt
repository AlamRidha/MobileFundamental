package com.example.mobilefundamental.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mobilefundamental.navigation.PageOneFragment
import com.example.mobilefundamental.navigation.PageTwoFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    // to set how many tabs want to show
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