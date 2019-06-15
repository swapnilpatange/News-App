package com.example.swapnil.verticalviewpager.service.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class Articles() :Parcelable{
    val source: Source? = null
    val author: String = ""
    val title: String = ""
    val description: String = ""
    val url: String = ""
    val urlToImage: String = ""
    val publishedAt: String = ""
    val content: String = ""

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Articles> {
        override fun createFromParcel(parcel: Parcel): Articles {
            return Articles(parcel)
        }

        override fun newArray(size: Int): Array<Articles?> {
            return arrayOfNulls(size)
        }
    }
}
