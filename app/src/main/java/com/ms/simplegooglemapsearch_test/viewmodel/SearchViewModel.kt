package com.ms.simplegooglemapsearch_test.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ms.simplegooglemapsearch_test.database.LocationDatabase
import com.ms.simplegooglemapsearch_test.database.RecentResultEntity
import com.ms.simplegooglemapsearch_test.model.AutoCompleteModel
import com.ms.simplegooglemapsearch_test.model.BaseSearchModel
import com.ms.simplegooglemapsearch_test.model.SearchApiResult
import com.ms.simplegooglemapsearch_test.repository.SearchRepository

class SearchViewModel(private val context: Context) : ViewModel() {
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

    fun onClickResultKeyword(data: SearchApiResult?) {
        if (data == null) {
            return
        }
        Toast.makeText(context, "onClickResultKeyword", Toast.LENGTH_LONG).show()
    }

    fun onClickRecentKeyword(data: RecentResultEntity?) {
        if (data == null) {
            return
        }
        Toast.makeText(context, "onClickRecentKeyword", Toast.LENGTH_LONG).show()
    }

    fun onClickDeleteRecentKeyword(data: RecentResultEntity?) {
        if (data == null) {
            return
        }
        LocationDatabase.getInstance(context)?.getDao()?.deleteLocation(data)
    }
}