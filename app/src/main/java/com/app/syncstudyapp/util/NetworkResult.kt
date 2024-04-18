package com.app.syncstudyapp.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response

sealed class NetworkResult<T>(var data: Any? = null, val message: String? = "") {
    data class Success<T> constructor(val value: T) : NetworkResult<T>(value)
    class Error<T> @JvmOverloads constructor(
        var code: Int? = null,
        var msg: String? = null,
        var exception: Throwable? = null

    ) : NetworkResult<T>(code, msg)

    class Loading<T> : NetworkResult<T>()

    fun <T> safeFlow(apiFunc: suspend () -> T): Flow<Response<T>> = flow {
        try {
            emit(Response.success(apiFunc.invoke()))
        } catch (e: HttpException) {
            emit(Response.error(e.code(), null))
        } catch (e: Exception) {
            // emit(Response.error(null, e.message))
        }
    }
} // End of NetworkResult class

sealed class SubNetworkResult<T>(var data: T? = null, val message: String? = null) {

} // End of SUbNetworkResult sealed class

sealed class ResourceState<T> {
    class Loading<T> : ResourceState<T>()
    data class Success<T>(val data: T) : ResourceState<T>()
    data class Error<T>(val error: Any) : ResourceState<T>()
} // End of ResourceState sealed class