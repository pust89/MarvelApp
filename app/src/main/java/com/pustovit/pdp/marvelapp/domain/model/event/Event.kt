package com.pustovit.pdp.marvelapp.domain.model.event

import android.os.Parcelable
import com.pustovit.pdp.marvelapp.domain.model.common.*
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(
    val characters: Characters = Characters(),
    val comics: Comics = Comics(),
    val creators: Creators = Creators(),
    val description: String = "",
    val end: String = "",
    val id: Int = 0,
    val modified: String = "",
    val next: Summary = Summary(),
    val previous: Summary = Summary(),
    val resourceURI: String = "",
    val series: Series = Series(),
    val start: String = "",
    val stories: Stories = Stories(),
    val thumbnail: Thumbnail = Thumbnail(),
    val title: String = "",
    val urls: List<UrlModel> = emptyList()
) : Parcelable