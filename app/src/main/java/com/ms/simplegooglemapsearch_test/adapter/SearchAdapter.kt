package com.ms.simplegooglemapsearch_test.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ms.simplegooglemapsearch_test.database.RecentResultEntity
import com.ms.simplegooglemapsearch_test.databinding.RecentKeywordRowviewBinding
import com.ms.simplegooglemapsearch_test.databinding.ResultKeywordRowviewBinding
import com.ms.simplegooglemapsearch_test.model.BaseSearchModel
import com.ms.simplegooglemapsearch_test.model.SearchApiResult
import com.ms.simplegooglemapsearch_test.viewmodel.SearchViewModel

class SearchAdapter(var vm: SearchViewModel) :
    ListAdapter<BaseSearchModel, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        // View Type
        const val TYPE_RECENT: Int = 0
        const val TYPE_RESULT: Int = 1

        // Diff Callback
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BaseSearchModel>() {
            override fun areItemsTheSame(oldItem: BaseSearchModel, newItem: BaseSearchModel) =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: BaseSearchModel, newItem: BaseSearchModel) =
                oldItem.hashCode() == newItem.hashCode()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_RECENT -> {
                val inflater =
                    parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val binding = RecentKeywordRowviewBinding.inflate(inflater)
                RecentKeywordHolder(binding)
            }
            TYPE_RESULT -> {
                val inflater =
                    parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val binding = ResultKeywordRowviewBinding.inflate(inflater)
                ResultKeywordHolder(binding)
            }
            else -> {
                val inflater =
                    parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val binding = ResultKeywordRowviewBinding.inflate(inflater)
                ResultKeywordHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        item?.run {
            when (holder) {
                is RecentKeywordHolder -> {
                    holder.binding.vm = vm
                    holder.binding.data = item as RecentResultEntity
                }
                is ResultKeywordHolder -> {
                    holder.binding.vm = vm
                    holder.binding.data = item as SearchApiResult
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (vm.searchList.value?.get(position)) {
            is RecentResultEntity -> TYPE_RECENT
            is SearchApiResult -> TYPE_RESULT
            else -> 0
        }

    class RecentKeywordHolder(val binding: RecentKeywordRowviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    class ResultKeywordHolder(val binding: ResultKeywordRowviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}