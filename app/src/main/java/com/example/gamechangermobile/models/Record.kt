package com.example.gamechangermobile.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Record(var wins: Int = 0,
                  var loses: Int = 0,
) : Parcelable {

    fun getRecord(): String {
        return "${wins.toInt()} - ${loses.toInt()}"
    }

//    fun setTotalRecord(r: ArrayList<String>) {
//        records = r
//    }

}