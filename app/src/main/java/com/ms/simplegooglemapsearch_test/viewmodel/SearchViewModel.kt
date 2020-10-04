package com.ms.simplegooglemapsearch_test.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ms.simplegooglemapsearch_test.model.BaseSearchModel
import com.ms.simplegooglemapsearch_test.repository.SearchRepository

class SearchViewModel(val context: Context): ViewModel() {
    val searchList: MutableLiveData<ArrayList<BaseSearchModel>> = MutableLiveData(arrayListOf())

    fun getResult() {
        SearchRepository().getResultList(context, object : SearchRepository.OnGetResultListener {
            override fun onResult(results: ArrayList<BaseSearchModel>) {
                searchList.value = results
            }

            override fun onError(throwable: String?) {
                // nothing
            }
        })

    }
}