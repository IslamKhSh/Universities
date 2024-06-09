package com.xische.universities.feature.universitieslist.presentation.list

import android.os.Bundle
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.xische.universities.feature.common.presentation.base.BaseFragment
import com.xische.universities.feature.common.utils.Constants.IS_REFRESH_REQUIRED_KEY
import com.xische.universities.feature.common.utils.Constants.LISTEN_TO_REFRESH_KEY
import com.xische.universities.feature.common.utils.displayView
import com.xische.universities.feature.universitieslist.R
import com.xische.universities.feature.universitieslist.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>() {

    val viewModel: ListViewModel by viewModels()
    private lateinit var universitiesAdapter: UniversitiesAdapter

    override val layoutRes = R.layout.fragment_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(LISTEN_TO_REFRESH_KEY) { _, bundle ->
            if (bundle.getBoolean(IS_REFRESH_REQUIRED_KEY)) {
                viewModel.getUniversities()
            }
        }
    }

    override fun init() {
        initErrorView()
        initUniversitiesRecycler()
        initResultListener()
    }

    private fun initErrorView() {
        binding.errorView.btnRetry.setOnClickListener { viewModel.getUniversities() }
    }

    private fun initUniversitiesRecycler() {
        universitiesAdapter = UniversitiesAdapter { university ->
            val action = ListFragmentDirections.actionFragmentListToFragmentDetails(university)
            findNavController().navigate(action)
        }

        binding.recyUniversities.adapter = universitiesAdapter
    }

    private fun initResultListener() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UniversitiesListUiState.Error -> binding.flipperContent.displayView(binding.errorView.root)
                UniversitiesListUiState.Loading -> binding.flipperContent.displayView(binding.loading)
                is UniversitiesListUiState.Success -> {
                    binding.flipperContent.displayView(binding.recyUniversities)
                    universitiesAdapter.addToList(uiState.data)
                }
            }
        }
    }

}