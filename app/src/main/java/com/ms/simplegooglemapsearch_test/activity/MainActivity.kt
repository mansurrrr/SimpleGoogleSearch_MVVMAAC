package com.ms.simplegooglemapsearch_test.activity

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.ms.simplegooglemapsearch_test.base.BaseActivity
import com.ms.simplegooglemapsearch_test.R
import com.ms.simplegooglemapsearch_test.databinding.ActivityMainBinding
import com.ms.simplegooglemapsearch_test.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.view = this
        init()
    }

    fun init() {

    }

    fun onClickInputText() {
        val params = HashMap<String, String>()
        params["location"] = "-33.8670522,151.1957362"
        params["radius"] = "500"
        params["types"] = "food"
        params["name"] = "harbour"
        params["key"] = "AIzaSyCX-soK_0wGvWCU5UcQBx9ssK89DxmYYks"
        RetrofitClient().getClient()
            .getSearchAutoComplete(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.body()?.string()?.let { it1 -> Log.d("abcd", it1) }
            }, {
//                Log.d("abcd", it.message.toString())
            })
//            .observeOn(AndroidSchedulers.mainThread())

    }
}