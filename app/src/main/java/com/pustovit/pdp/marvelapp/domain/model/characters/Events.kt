package com.pustovit.pdp.marvelapp.domain.model.characters

import android.os.Parcelable
import com.pustovit.pdp.marvelapp.domain.model.Item
import kotlinx.parcelize.Parcelize

/**
 * Created by Pustovit V.V.
 * Date: 31.05.2022
 * Time: 20:44
 */
@Parcelize
data class Events(
    val available: Int = 0,
    val collectionURI: String = "",
    val items: List<Item> = emptyList(),
    val returned: Int = 0
) : Parcelable
