package com.pustovit.pdp.marvelapp.domain.model.character

import android.os.Parcelable
import com.pustovit.pdp.marvelapp.domain.model.common.*
import kotlinx.parcelize.Parcelize

/**
 * Created by Pustovit V.V.
 * Date: 31.05.2022
 * Time: 20:25
 */
@Parcelize
data class Character(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val modified: String = "",
    val thumbnail: Thumbnail = Thumbnail(),
    val resourceURI: String = "",
    val comics: Comics = Comics(),
    val series: Series = Series(),
    val stories: Stories = Stories(),
    val events: Events = Events(),
    val urls: List<UrlModel> = emptyList()
):Parcelable


