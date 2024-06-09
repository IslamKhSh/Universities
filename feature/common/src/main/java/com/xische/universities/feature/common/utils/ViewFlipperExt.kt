package com.xische.universities.feature.common.utils

import android.view.View
import android.widget.ViewFlipper

fun ViewFlipper.displayView(view: View) {
    require(indexOfChild(view) != -1) { "This view is not a child of flipper" }
    displayedChild = indexOfChild(view)
}