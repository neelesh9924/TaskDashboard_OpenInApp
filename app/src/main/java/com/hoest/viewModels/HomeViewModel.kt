package com.hoest.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hoest.repository.Repository
import com.hoest.pojos.DashboardDataPojo

class HomeViewModel : ViewModel() {

    private val repository = Repository

    fun getHomePageData(): MutableLiveData<DashboardDataPojo?> {
        return repository.getHomePageData()
    }

}