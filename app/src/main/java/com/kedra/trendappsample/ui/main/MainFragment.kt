package com.kedra.trendappsample.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import com.kedra.trendappsample.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding

    private lateinit var adapter: ExpandableListAdapter

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
            adapter = CustomExpandableListAdapter(
                listOf("Hello", "Word", "WER", "ERT", "EFT", "EGT", "EHT", "EJT"),
                mapOf(
                    Pair("Hello", listOf("")),
                    Pair("Word", listOf("")),
                    Pair("WER", listOf("")),
                    Pair("ERT", listOf("")),
                    Pair("EFT", listOf("")),
                    Pair("EGT", listOf("")),
                    Pair("EHT", listOf("")),
                    Pair("EJT", listOf("")),
                ),
                requireContext()
            )
            this.lvItems.setAdapter(adapter)
        }
    }
}