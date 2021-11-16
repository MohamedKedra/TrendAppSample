package com.kedra.trendappsample.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import android.widget.ExpandableListView
import androidx.core.view.isVisible
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kedra.trendappsample.app.DataState
import com.kedra.trendappsample.databinding.MainFragmentBinding
import com.kedra.trendappsample.remote.TrendingResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding

    private lateinit var adapter: CustomExpandableListAdapter

    private val viewModel by viewModels<MainViewModel>()

    private lateinit var dataList: List<TrendingResponse>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(binding) {
            adapter = CustomExpandableListAdapter(requireContext())
            this.lvItems.setAdapter(adapter)
            refreshItem.setOnRefreshListener {
                initObserveData()
                refreshItem.isRefreshing = false
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserveData()
    }

    private fun initObserveData() {
        viewModel.refreshHomeList().observe(viewLifecycleOwner, {
            when (it.getStatus()) {

                DataState.DataStatus.LOADING -> {
                    handleState(isLoading = true)
                }

                DataState.DataStatus.SUCCESS -> {
                    handleState(isLoading = false)
                    Log.d("res", it.getData().toString())
                    it.getData()?.let { list ->
                        dataList = list
                        adapter.addList(list)
                    }

                }

                DataState.DataStatus.ERROR -> {
                    handleState(isLoading = false, hasError = true)
                    Log.d("res", it.getError().toString())
                }

                DataState.DataStatus.NO_INTERNET -> {
                    handleState(isLoading = false, hasError = true)
                }

            }
        })
    }


    private fun handleState(isLoading: Boolean, hasError: Boolean = false) {
        with(binding) {
            error.progress.isVisible = isLoading && !hasError
            error.errorContainer.isVisible = hasError && !isLoading
            lvItems.isVisible = !(isLoading || hasError)

            error.btnRetry.setOnClickListener {
                initObserveData()
            }

            handleExpandedState(this.lvItems)
        }
    }

    private fun handleExpandedState(expandableListView: ExpandableListView) {

        expandableListView.setOnGroupClickListener { parent, v, groupPosition, id ->

            for (i in 0..dataList.size) {
                parent.collapseGroup(i)

            }
            val item = dataList[groupPosition]
            if (item.isExpanded) {
                item.isExpanded = false
                parent.collapseGroup(groupPosition)
            } else {
                for (i in 0..dataList.size) {
                    parent.collapseGroup(i)
                }
                item.isExpanded = true
                parent.expandGroup(
                    groupPosition
                )
            }

        }
    }
}