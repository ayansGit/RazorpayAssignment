package com.ayan.razorpayassignment.models

import android.os.Parcel
import android.os.Parcelable

data class DisplayData(
    val label: String?,
    val value: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(label)
        parcel.writeString(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DisplayData> {
        override fun createFromParcel(parcel: Parcel): DisplayData {
            return DisplayData(parcel)
        }

        override fun newArray(size: Int): Array<DisplayData?> {
            return arrayOfNulls(size)
        }
    }
}
