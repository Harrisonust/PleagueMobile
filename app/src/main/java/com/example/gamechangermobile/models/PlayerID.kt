package com.example.gamechangermobile.models

import android.os.Parcelable
import com.example.gamechangermobile.Utils.sha256
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlayerID(
    val ID: String = "".sha256()
) : Parcelable {

}
