package com.example.withingstest.data.domain

import android.os.Parcel
import android.os.Parcelable


data class ImageResult(
    val previewURL: String,
    val previewWidth : Int,
    val previewHeight : Int,
    val largeImageURL : String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(previewURL)
        parcel.writeInt(previewWidth)
        parcel.writeInt(previewHeight)
        parcel.writeString(largeImageURL)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ImageResult> {
        override fun createFromParcel(parcel: Parcel): ImageResult {
            return ImageResult(parcel)
        }

        override fun newArray(size: Int): Array<ImageResult?> {
            return arrayOfNulls(size)
        }
    }
}