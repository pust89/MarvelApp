package com.pustovit.pdp.marvelapp.domain.model.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Pustovit V.V.
 * Date: 31.05.2022
 * Time: 20:43
 */
@Parcelize
data class Series(
    val available: Int = 0,
    val collectionURI: String = "",
    val items: List<Summary> = emptyList(),
    val returned: Int = 0
) : Parcelable