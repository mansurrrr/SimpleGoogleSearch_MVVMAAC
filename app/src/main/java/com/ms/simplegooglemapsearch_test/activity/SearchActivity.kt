package com.ms.simplegooglemapsearch_test.activity

import android.os.Bundle
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.ms.simplegooglemapsearch_test.base.BaseActivity
import com.ms.simplegooglemapsearch_test.R
import com.ms.simplegooglemapsearch_test.adapter.SearchAdapter
import com.ms.simplegooglemapsearch_test.databinding.ActivitySearchBinding
import com.ms.simplegooglemapsearch_test.model.BaseSearchModel
import com.ms.simplegooglemapsearch_test.viewmodel.SearchViewModel

class SearchActivity: BaseActivity() {
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        binding.view = this
        init()
    }

    private fun init() {
        val vm = ViewModelProvider(this).get(SearchViewModel::class.java)
        vm.getResult()
    }

    @BindingAdapter(value = ["data", "viewModel"])
    fun setLocationResults(view: RecyclerView, items: ArrayList<BaseSearchModel>, vm: SearchViewModel) {
        view.adapter?.run {
            if(this is SearchAdapter) {
                this.submitList(items)
            }
        } ?: run {
            SearchAdapter(vm).apply {
                view.adapter = this
                this.submitList(items)
            }
        }
    }
}