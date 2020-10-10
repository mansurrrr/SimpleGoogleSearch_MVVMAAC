package com.ms.simplegooglemapsearch_test.network

import com.ms.simplegooglemapsearch_test.model.AutoCompleteModel
import com.ms.simplegooglemapsearch_test.model.SearchApiResult
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiListInterface {
//    @GET("/maps/api/place/nearbysearch/json")
//    fun getSearchAutoComplete(@QueryMap params: HashMap<String, String>?): Observable<Response<SearchApiResult>>

    @GET("/maps/api/place/nearbysearch/json")
    fun getSearchAutoComplete(@QueryMap params: HashMap<String, String>?): Observable<Response<ResponseBody>>
}