package com.example.mobilefundamental

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.mobilefundamental.livedata.LiveDataActivity
import com.example.mobilefundamental.navigation.NavigationActivity
import com.example.mobilefundamental.retrofitac.ui.RestaurantActivity
import com.example.mobilefundamental.viewmodel.ViewModelActivity


class HomeFragment : Fragment(), View.OnClickListener {

    // declare any component for fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnCategory: Button = view.findViewById(R.id.btn_category)
        btnCategory.setOnClickListener(this)

        val btnVm: Button = view.findViewById(R.id.btn_view_model)
        btnVm.setOnClickListener(this)

        val btnNa: Button = view.findViewById(R.id.btn_navigation)
        btnNa.setOnClickListener(this)

        val btnRa: Button = view.findViewById(R.id.btn_retrofit)
        btnRa.setOnClickListener(this)

        val btnLa: Button = view.findViewById(R.id.btn_livedata)
        btnLa.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        //  make navigation to move category fragment use replace
        if (v.id == R.id.btn_category) {
            val categoryFragment = CategoryFragment()
            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction().apply {
                replace(
                    R.id.frame_container,
                    categoryFragment,
                    CategoryFragment::class.java.simpleName
                )
                addToBackStack(null)
                commit()
            }
        } else if (v.id == R.id.btn_view_model) {
            val intentVm = Intent(requireActivity(), ViewModelActivity::class.java)
            startActivity(intentVm)
        } else if (v.id == R.id.btn_navigation) {
            val intentNa = Intent(requireActivity(), NavigationActivity::class.java)
            startActivity(intentNa)
        } else if (v.id == R.id.btn_retrofit) {
            val intentRa = Intent(requireActivity(), RestaurantActivity::class.java)
            startActivity(intentRa)
        } else if (v.id == R.id.btn_livedata) {
            val intentLa = Intent(requireActivity(), LiveDataActivity::class.java)
            startActivity(intentLa)
        }
    }

}