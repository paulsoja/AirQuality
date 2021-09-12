package com.paulsoia.airquality.presentation.ui.quality

import com.paulsoia.airquality.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QualityViewModel @Inject constructor(

) : BaseViewModel() {

    internal fun back() = flowRouter.finishFlow()

}