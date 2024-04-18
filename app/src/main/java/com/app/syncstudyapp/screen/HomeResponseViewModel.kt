package com.app.syncstudyapp.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.syncstudyapp.data.NetworkData
import com.app.syncstudyapp.domain.Repository.NetworkDataRepository
import com.app.syncstudyapp.util.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "HomeResponseViewModel_창영"


@HiltViewModel
class HomeResponseViewModel @Inject constructor(
    private val networkDataRepo: NetworkDataRepository
) :
    ViewModel() {

    // ========================================== getNetworkData ==========================================
    private val _getNetworkDataResponseStateFlow: MutableStateFlow<ResourceState<NetworkData>> =
        MutableStateFlow(ResourceState.Loading())

    var getNetworkDataResponseStateFlow: StateFlow<ResourceState<NetworkData>> =
        _getNetworkDataResponseStateFlow
        private set


    fun getNetworkData() {
        viewModelScope.launch(Dispatchers.IO) {
            networkDataRepo.getNetworkData()
                .collectLatest { response ->
                    _getNetworkDataResponseStateFlow.value = response

                }
        }
    } // End of getNetworkData()


//    fun getNetworkData() {
//        viewModelScope.launch {
//            networkDataRepo.getNetworkData().onStart {
//                _getNetworkDataResponseSharedFlow.emit(NetworkResult.Loading())
//            }.onCompletion {
//                Log.d(TAG, "getNetworkData: ")
//            }.catch {
//                // 에러
//            }.retryWhen { cause, attempt ->
//                when (cause) {
//                    is UnknownHostException -> {
//                        false
//                    }
//
//                    is HttpException -> {
//
//                    }
//
//                    else -> {}
//                }
//                true
//            }.collectLatest { result ->
//                Log.d(TAG, "getNetworkData: ${result.message()}")
//                Log.d(TAG, "getNetworkData: ${result.body()}")
//
//                when {
//                    result.isSuccessful -> {
//                        _getNetworkDataResponseSharedFlow.emit(NetworkResult.Success(result.body()!!))
//                    }
//
//                    result.errorBody() != null -> {
//                        _getNetworkDataResponseSharedFlow.emit(
//                            NetworkResult.Error(
//                                result.code(),
//                                result.message()
//                            )
//                        )
//                    }
//                }
//            }
//        }
//    } // End of getNetworkData()

} // End of HomeResponseViewModel class