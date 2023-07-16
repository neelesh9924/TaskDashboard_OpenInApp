package com.hoest.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.hoest.networking.RetrofitClient
import com.hoest.networking.RetrofitInterface
import com.hoest.pojos.DashboardDataPojo
import com.hoest.utils.MyApp
import com.hoest.utils.UserAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Repository {

    lateinit var mutableData: MutableLiveData<DashboardDataPojo?>

    private val apiInterface = RetrofitClient().getClient().create(RetrofitInterface::class.java)

    fun getHomePageData(): MutableLiveData<DashboardDataPojo?> {

        mutableData = MutableLiveData()

        apiInterface.getDashboardData("Bearer ${UserAuth(MyApp.applicationContext()).getToken()}")
            .enqueue(object : Callback<DashboardDataPojo> {
                override fun onResponse(
                    call: Call<DashboardDataPojo>,
                    response: Response<DashboardDataPojo>
                ) {
                    if (response.isSuccessful) {
                        mutableData.value = response.body()
                    } else {
                        Log.i("APICall", "onResponse: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<DashboardDataPojo>, t: Throwable) {
                    Log.i("APICall", "onFailure: ${t.message}")
                }
            })

        return mutableData
    }

}