package com.pustovit.pdp.common_models.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Summary(
    val resourceURI: String = "",
    val name: String = ""
) : Parcelable

fun List<Summary>.asString(): String {
   return buildString {
        this@asString.forEach {
           append(it.name).append("\n")
        }
    }
}