package com.paulsoia.airquality.presentation.base

import android.util.MalformedJsonException
import androidx.lifecycle.ViewModel
import com.accessercise.android.data.model.ServerErrorApiModel
import com.accessercise.android.data.model.ServerErrorDetailApiModel
import com.paulsoia.airquality.data.net.tools.exceptions.RetrofitException
import com.paulsoia.airquality.presentation.navigation.router.FlowRouter
import timber.log.Timber
import java.net.SocketTimeoutException
import javax.inject.Inject

open class BaseViewModel : ViewModel() {

    @Inject lateinit var flowRouter: FlowRouter

    protected fun handleRetrofitError(e: Throwable) {
        Timber.d("AppHandleRetrofitError: $e")
        if (e is RetrofitException) {
            try {
                val err = e.getErrorBodyAs(ServerErrorApiModel::class.java)
                when (e.serverErrorType) {
                    RetrofitException.ServerErrorType.HTTP -> {
                        Timber.d("http")
                        handleServerError(e.response?.code() ?: 500, err, err?.errors.orEmpty())
                    }
                    RetrofitException.ServerErrorType.NETWORK -> {
                        Timber.d("NETWORK")
                        if (e.exception is SocketTimeoutException) {
                            Timber.d("NETWORK = SocketTimeoutException")
                            handleNetworkError()
                        } else {
                            handleNetworkError()
                        }
                    }
                    RetrofitException.ServerErrorType.UNEXPECTED -> {
                        Timber.d("NETWORK = showUnexpectedError")
                        showUnexpectedError()
                    }
                }
            } catch (e: MalformedJsonException) {
                Timber.d("MalformedJsonException $e")
                showUnexpectedError()
            }
        }
    }

    open fun handleServerError(
        code: Int,
        error: ServerErrorApiModel?,
        details: List<ServerErrorDetailApiModel>
    ) {
        Timber.w("handleServerError: http error code=$code")
        if (code == 401) {
            Timber.w("handleServerError: code == 401")
        } else {
            Timber.w("handleServerError: error.code=${error?.errorCode}, error.details=${error?.message}")
            // parse error - example
            error?.run {
                Timber.e("handleServerError:")
            } ?: Timber.e("handleServerError: `error` == null")
        }
    }

    open fun handleNetworkError() {
        Timber.d("NETWORK = showNetworkError")
    }

    open fun showUnexpectedError() {
        Timber.d("UNEXPECTED = showUnexpectedError")
    }

}