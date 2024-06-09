package com.xische.universities.feature.universitydetails.presentation.universityDetails

import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.xische.universities.feature.common.presentation.base.BaseFragment
import com.xische.universities.feature.common.utils.Constants.IS_REFRESH_REQUIRED_KEY
import com.xische.universities.feature.common.utils.Constants.LISTEN_TO_REFRESH_KEY
import com.xische.universities.feature.universitydetails.R
import com.xische.universities.feature.universitydetails.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    private val args: DetailsFragmentArgs by navArgs()

    override val layoutRes = R.layout.fragment_details

    override fun init() {
        args.university.let { university ->
            binding.university = university
            binding.tvState.isVisible = university.state != null
        }

        binding.imgRefresh.setOnClickListener {
            setFragmentResult(LISTEN_TO_REFRESH_KEY, bundleOf(IS_REFRESH_REQUIRED_KEY to true))
            findNavController().popBackStack()
        }
    }

}