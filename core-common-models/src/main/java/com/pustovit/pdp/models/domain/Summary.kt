package com.pustovit.pdp.models.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.lang.StringBuilder

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