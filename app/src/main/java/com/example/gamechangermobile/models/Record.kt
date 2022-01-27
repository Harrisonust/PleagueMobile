package com.example.gamechangermobile.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Record(private val win: Float = 0F,
             private val lose: Float = 0F,
             var records: ArrayList<String> = ArrayList<String>()
) : Parcelable {

    fun getRecord(): String {
        return "${win.toInt()} - ${lose.toInt()}"
    }

    fun setRecord(r: ArrayList<String>) {
        records = r
    }

    fun getLast10(): String {
        if (records.isEmpty()) return "0"
        var first: String = ""

        first = records[0]
        if (records.size == 1) return first + "1"

        var acc = 1
        for (i in 0 until records.size - 1) {
            if (records[i] == records[i + 1]) acc += 1
            else break
        }
        return first + acc.toString()
    }

}