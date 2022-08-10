package com.android.common

import okhttp3.ResponseBody

sealed class AppResponse<T : Any?>(val state: Int = 2, val data: (T)?) {
    companion object {
        fun <T> success(t : T) = SuccessResponse(t)
        fun <T> error(error : Exception?) = ErrorResponse<T>(error, null, 0)

        fun <T> error(error : Exception?, body: ResponseBody?, code: Int) = ErrorResponse<T>(error, body, code)
        fun <T> loading() = LoadingResponse<T>()
        const val SUCCESS = 0
        const val ERROR = 1
        const val LOADING = 2
    }
}
class SuccessResponse<T>(data: T) : AppResponse<T>(0, data)
data class ErrorResponse<T>(val error : Exception?, val body: ResponseBody?, val code: Int) : AppResponse<T>(1,null)
class LoadingResponse<T> : AppResponse<T>(2,null)
