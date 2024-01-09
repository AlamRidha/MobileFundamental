package com.example.mobilefundamental.retrofitac.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
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

        //  setting layout manager for recycle view
        val layoutManager = LinearLayoutManager(this)
        binding.rvReview.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvReview.addItemDecoration(itemDecoration)

        //  call function find restaurant
        findRestaurant()

        binding.btnSend.setOnClickListener { view ->
            postReview(binding.edReview.text.toString())
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun postReview(review: String) {
        showLoading(true)
        val client = ApiConfig.getApiService().postReview(RESTAURANT_ID, "Dicoding", review)
        client.enqueue(object : Callback<PostReviewResponse> {
            override fun onResponse(
                call: Call<PostReviewResponse>,
                response: Response<PostReviewResponse>
            ) {
                showLoading(false)
                val responBody = response.body()
                if (response.isSuccessful && responBody != null) {
                    setReviewData(responBody.customerReviews)
                } else {
                    Log.d(TAG, "onFailure ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PostReviewResponse>, t: Throwable) {
                showLoading(false)
                Log.d(TAG, "onFailure ${t.message}")
            }

        })
    }

    private fun findRestaurant() {
        showLoading(true)
        //  call client to request API
        val client = ApiConfig.getApiService().getRestaurant(RESTAURANT_ID)
        client.enqueue(object : Callback<RestaurantResponse> {
            override fun onResponse(
                call: Call<RestaurantResponse>,
                response: Response<RestaurantResponse>
            ) {
                showLoading(false)
                //  cek response is successfull or not
                if (response.isSuccessful) {
                    val responBody = response.body()
                    if (responBody != null) {
                        //  get data restaurant, name, description and photo
                        setRestaurantData(responBody.restaurant)
                        //  get review data then put to recycle view
                        setReviewData(responBody.restaurant.customerReviews)
                    }
                } else {
                    Log.e(TAG, "onFailure ${response.message()}")
                }
            }

            override fun onFailure(call: Call<RestaurantResponse>, t: Throwable) {
                showLoading(false)
                Log.e(TAG, "onFailure ${t.message}")
            }

        })
    }

    private fun showLoading(isLoading: Boolean) {
        //  function for progress bar
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setReviewData(customerReviews: List<CustomerReviewsItem>) {
        //  function for set recycle view
        val adapter = ReviewAdapter()
        adapter.submitList(customerReviews)
        binding.rvReview.adapter = adapter
        binding.edReview.setText("")
    }

    private fun setRestaurantData(restaurant: Restaurant) {
        //  function for set the name for restaurant
        binding.tvTitle.text = restaurant.name
        binding.tvDescription.text = restaurant.description
        Glide.with(this@RestaurantActivity)
            .load("https://restaurant-api.dicoding.dev/images/large/${restaurant.pictureId}")
            .into(binding.ivPicture)
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val RESTAURANT_ID = "uewq1zg2zlskfw1e867"
    }
}