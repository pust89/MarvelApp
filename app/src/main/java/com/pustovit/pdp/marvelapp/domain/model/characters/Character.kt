package com.pustovit.pdp.marvelapp.domain.model.characters

import android.os.Parcelable
import com.pustovit.pdp.marvelapp.domain.model.Thumbnail
import com.pustovit.pdp.marvelapp.domain.model.UrlModel
import kotlinx.parcelize.Parcelize

/**
 * Created by Pustovit V.V.
 * Date: 31.05.2022
 * Time: 20:25
 */
@Parcelize
data class Character(
    val id: Int = -1,
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


