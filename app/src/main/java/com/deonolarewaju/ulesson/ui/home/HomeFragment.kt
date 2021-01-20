package com.deonolarewaju.ulesson.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.deonolarewaju.ulesson.R
import com.deonolarewaju.ulesson.util.helper.EventObserver
import com.deonolarewaju.ulesson.util.helper.Resources.*
import com.deonolarewaju.ulesson.ui.BaseFragment
import com.deonolarewaju.ulesson.ui.home.adapters.RecentViewAdapter
import com.deonolarewaju.ulesson.ui.home.adapters.SubjectAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.dashboard_fragment.*

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.dashboard_fragment) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: SubjectAdapter
    private lateinit var recentViewAdapter: RecentViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SubjectAdapter(viewModel)
        recentViewAdapter = RecentViewAdapter()

        subjectList.adapter = adapter
        recentViewList.adapter = recentViewAdapter

        subjectList.layoutManager = GridLayoutManager(requireContext(), 2)
        viewModel.getSubjects()

        setupObservers()
        setUpToggleViewClickListener()
        viewModel.toggleButton(toggleViewText.text.toString())
    }

    private fun setUpToggleViewClickListener() {
        toggleBtn.setOnClickListener {
            viewModel.toggleButton(toggleViewText.text.toString())
        }
    }

    private fun setupObservers() {
        viewModel.fetchingSubject.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    loading.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    loading.visibility = View.GONE
                }
                Status.ERROR -> {
                    loading.visibility = View.GONE
                    onError(it.message)
                }
            }
        })

        viewModel.subjects.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                adapter.submitList(it)
            }
        })

        viewModel.openSubjectId.observe(viewLifecycleOwner, EventObserver {
            val action = HomeFragmentDirections.dashboardToSubjectFragment(it)
            findNavController().navigate(action)
        })

        viewModel.recentViews.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                emptyRecent.visibility = View.GONE
                toggleViewContainer.visibility = View.VISIBLE
            }

            recentViewAdapter.submitList(it)
        })

        viewModel.toggleText.observe(viewLifecycleOwner, Observer {
            toggleViewText.text = it
        })
    }

}