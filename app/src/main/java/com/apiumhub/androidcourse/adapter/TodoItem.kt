package com.apiumhub.androidcourse.adapter

import android.os.Parcel
import android.os.Parcelable

data class TodoItem(
    val title: String?,
    val description: String?,
    val date: Long
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readLong()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeLong(date)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<TodoItem> {
        override fun createFromParcel(parcel: Parcel): TodoItem = TodoItem(parcel)

        override fun newArray(size: Int): Array<TodoItem?> = arrayOfNulls(size)
    }
}