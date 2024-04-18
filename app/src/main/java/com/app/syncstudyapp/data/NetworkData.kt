package com.app.syncstudyapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkData @JvmOverloads constructor(
    var content: String = "",
    var name: String = "",
    var id: Int? = -1
) : Parcelable {
} // End of NetworkData class