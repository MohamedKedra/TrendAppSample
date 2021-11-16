              package com.kedra.trendappsample.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import android.widget.ExpandableListAdapter
import androidx.core.view.isVisible
import com.kedra.trendappsample.app.DataState
import com.kedra.trendappsample.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding

    private lateinit var adapter: CustomExpandableListAdapter

    private val viewModel by viewModels<MainViewModel>()

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
            lvItems.isVisible = false
            shimmerLayout.startShimmerAnimation()
            Thread.sleep(5000)
            adapter = CustomExpandableListAdapter(

                requireContext(),

                )
            this.lvItems.setAdapter(adapter)
            lvItems.isVisible = true
            shimmerLayout.stopShimmerAnimation()
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
                }

                DataState.DataStatus.SUCCESS -> {
                    Log.d("res",it.getData().toString())
                    it.getData()?.let { list ->
                        adapter.addList(list)
                    }

                }

                DataState.DataStatus.ERROR -> {
                    Log.d("res",it.getError().toString())
                }

                DataState.DataStatus.NO_INTERNET -> {

                }

            }
        })
    }
}