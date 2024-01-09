package com.example.mobilefundamental.retrofitac.retrofit

import com.example.mobilefundamental.retrofitac.response.PostReviewResponse
import com.example.mobilefundamental.retrofitac.response.RestaurantResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    //  configure endpoint for api
    //  add anotation for any endpoint to get or post request
    @GET("detail/{id}")
    fun getRestaurant(
        @Path("id") id: String
    ): Call<RestaurantResponse>

    @FormUrlEncoded
    @Headers("Authorization: token 12345")
    @POST("review")
    fun postReview(
        @Field("id") id: String,
        @Field("name") name: String,
        @Field("review") review: String
    ): Call<PostReviewResponse>
}