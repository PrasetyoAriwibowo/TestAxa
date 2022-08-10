package com.android.entity

import com.google.gson.annotations.SerializedName


data class PlaceHolderResponse(
    @SerializedName("photos")
    val placeHolders: ArrayList<PlaceHolderResponseItem>
)
//: ArrayList<PlaceHolderResponseItem>() {

//}