package com.pustovit.pdp.characters.api.model

import android.os.Parcelable
import com.pustovit.pdp.models.domain.Items
import com.pustovit.pdp.models.domain.Thumbnail
import com.pustovit.pdp.models.domain.UrlModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val modified: String = "",
    val thumbnail: Thumbnail = Thumbnail(),
    val resourceURI: String = "",
    val comics: Items = Items(),
    val series: Items = Items(),
    val stories: Items = Items(),
    val events: Items = Items(),
    val urls: List<UrlModel> = emptyList()
):Parcelable


