package com.ms.simplegooglemapsearch_test.repository

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.ms.simplegooglemapsearch_test.database.LocationDatabase
import com.ms.simplegooglemapsearch_test.database.RecentResultEntity
import com.ms.simplegooglemapsearch_test.model.BaseSearchModel
import com.ms.simplegooglemapsearch_test.model.SearchApiResult
import com.ms.simplegooglemapsearch_test.network.RetrofitClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Response

class SearchRepository {
    public interface OnGetResultListener {
        fun onResult(results: ArrayList<BaseSearchModel>)

        fun onError(throwable: String?)
    }

    @SuppressLint("CheckResult")
    fun getResultList(context: Context, listener: OnGetResultListener?) {
        Observable.zip(
            getSearchApiResult(),
            getSearchRecentResult(context),
            BiFunction { searchList: ArrayList<SearchApiResult>?, recentList: List<RecentResultEntity>? ->
                val resultList = ArrayList<BaseSearchModel>()
                if (recentList != null) resultList.addAll(recentList)
                if (searchList != null) resultList.addAll(searchList)
                resultList
            })
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it == null) listener?.onResult(it)
                else listener?.onError("NULL")
            }, {
                listener?.onError(it.message)
            }, {

            })
    }

    fun getSearchApiResult(): Observable<ArrayList<SearchApiResult>>? {
        val params = HashMap<String, String>()
        params["location"] = "-33.8670522,151.1957362"
        params["radius"] = "500"
        params["types"] = "food"
        params["name"] = "harbour"
        params["key"] = "AIzaSyCX-soK_0wGvWCU5UcQBx9ssK89DxmYYks"
        return RetrofitClient().getClient()
            .getSearchAutoComplete(params)
            .subscribeOn(Schedulers.io())
            .map {
                val resultList = ArrayList<SearchApiResult>()
                return@map resultList
            }
    }

    fun getSearchRecentResult(context: Context): Observable<List<RecentResultEntity>>? {
        /* Observable.create 사용 필요 없이, Room에서 Observable로 반환 */
        return LocationDatabase.getInstance(context)?.getDao()?.getLocations()
    }

    fun test() {

    }
}