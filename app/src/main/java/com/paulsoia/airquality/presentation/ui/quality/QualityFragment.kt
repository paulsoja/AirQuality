package com.paulsoia.airquality.presentation.ui.quality

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.viewModels
import com.paulsoia.airquality.R
import com.paulsoia.airquality.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QualityFragment : BaseFragment(R.layout.fragment_quality) {

    companion object {
        fun newInstance() = QualityFragment()
    }

    private val viewModel: QualityViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<AppCompatTextView>(R.id.tvQuality).text = "test quality"
    }

    override fun onBackPressed() = viewModel.back()

}