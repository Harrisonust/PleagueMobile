package com.example.gamechangermobile.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Record(val wins: Float = 0F,
                  val loses: Float = 0F,
                  var records: ArrayList<String> = ArrayList<String>()
) : Parcelable {

    fun getRecord(): String {
        return "${wins.toInt()} - ${loses.toInt()}"
    }

    fun setRecord(r: ArrayList<String>) {
        records = r
    }

}