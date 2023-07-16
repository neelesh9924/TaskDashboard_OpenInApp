package com.hoest.networking

import com.hoest.pojos.DashboardDataPojo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface RetrofitInterface {

    companion object {
        const val BASE_URL = "https://api.inopenapp.com/"
    }

    @GET("api/v1/dashboardNew")
    fun getDashboardData(@Header("Authorization") authHeader: String): Call<DashboardDataPojo>
}