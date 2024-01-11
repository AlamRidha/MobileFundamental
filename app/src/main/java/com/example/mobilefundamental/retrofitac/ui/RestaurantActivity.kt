package com.example.mobilefundamental.retrofitac.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.bumptech.glide.Glide
import com.example.mobilefundamental.R
import com.example.mobilefundamental.databinding.ActivityRestaurantBinding
import com.example.mobilefundamental.retrofitac.response.CustomerReviewsItem
import com.example.mobilefundamental.retrofitac.response.PostReviewResponse
import com.example.mobilefundamental.retrofitac.response.Restaurant
import com.example.mobilefundamental.retrofitac.response.RestaurantResponse
import com.example.mobilefundamental.retrofitac.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRestaurantBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        //  initialization for viewModel
        val restaurantViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[RestaurantViewModel::class.java]

        //  call restairaunt data from viewModel
        restaurantViewModel.restaurant.observe(this) { restaurant ->
            setRestaurantData(restaurant)
        }

        //  setting layout manager for recycle view
        val layoutManager = LinearLayoutManager(this)
        binding.rvReview.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvReview.addItemDecoration(itemDecoration)

        //  call listReview data from viewModel
        restaurantViewModel.listReview.observe(this) { customerReview ->
            setReviewData(customerReview)
        }

        //  call isLoading data from viewModel
        restaurantViewModel.isLoading.observe(this) { it ->
            showLoading(it)
        }


        binding.btnSend.setOnClickListener { view ->
            //  call function postReview from viewModel
            restaurantViewModel.postReview(binding.edReview.text.toString())
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun setReviewData(customerReviews: List<CustomerReviewsItem>) {
        //  function for set recycle view
        val adapter = ReviewAdapter()
        adapter.submitList(customerReviews)
        binding.rvReview.adapter = adapter
        binding.edReview.setText("")
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun setRestaurantData(restaurant: Restaurant) {
        //  function for set the name for restaurant
        binding.tvTitle.text = restaurant.name
        binding.tvDescription.text = restaurant.description
        Glide.with(this@RestaurantActivity)
            .load("https://restaurant-api.dicoding.dev/images/large/${restaurant.pictureId}")
            .into(binding.ivPicture)
    }
}