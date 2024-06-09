package com.xische.universities.feature.universitieslist.presentation.list

import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.xische.universities.feature.common.model.University
import com.xische.universities.feature.common.presentation.base.BaseRecyclerAdapter
import com.xische.universities.feature.universitieslist.R
import com.xische.universities.feature.universitieslist.databinding.ItemUniversityBinding

class UniversitiesAdapter(val onItemClick: (University) -> Unit) :
    BaseRecyclerAdapter<University, ItemUniversityBinding>(areItemsTheSame = { old, new -> old.name == new.name }) {

    override val itemLayoutRes: Int
        get() = R.layout.item_university

    override fun bind(binding: ItemUniversityBinding, item: University, position: Int) {
        binding.university = item
        binding.tvState.isVisible = item.state != null
        binding.root.setOnClickListener {
            onItemClick(item)
        }
    }
}