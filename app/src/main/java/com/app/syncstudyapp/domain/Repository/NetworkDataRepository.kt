package com.app.syncstudyapp.domain.Repository

import com.app.syncstudyapp.data.NetworkData
import com.app.syncstudyapp.domain.datasource.NetworkDataSource
import com.app.syncstudyapp.util.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class NetworkDataRepository @Inject constructor(private val networkDataSource: NetworkDataSource) {
    // ========================================= getNetworkData ======================================

    private val _networkData = MutableStateFlow<NetworkData>(NetworkData())
    val networkData: StateFlow<NetworkData>
        get() = _networkData


    suspend fun getNetworkData(): Flow<ResourceState<NetworkData>> {
        return flow {
            emit(ResourceState.Loading())
            val response = networkDataSource.getNetworkData()

            if (response.isSuccessful && response.body() != null) {
                emit(ResourceState.Success(response.body()!!))
            } else {
                emit(ResourceState.Error("Error get NetworkData"))
            }
        }.catch { e ->
            emit(ResourceState.Error(e.localizedMessage ?: "Some error in flow"))
        }
    }

//    suspend fun getNetworkData(): Flow<Response<NetworkData>> = flow {
//        emit(networkDataSource.getNetworkData())
//    }.flowOn(Dispatchers.IO)
} // End of NetworkDataRepository class
