package com.example.workmanagerinputdatapropagation.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.workmanagerinputdatapropagation.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.setLifecycleOwner(this)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val localAdapter = SyncListAdapter()
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.model = viewModel
        binding.list.adapter = localAdapter
        binding.list.addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        viewModel.status?.observe(this, Observer(localAdapter::submitList))
        viewModel.inProgress.observe(this, Observer {
            it?.let {
                binding.inProgress = it
            }
        })
    }

}
