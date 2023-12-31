package com.pustovit.pdp.character.summary.model

import com.pustovit.pdp.common_models.domain.Summary

sealed class SummaryItem {
    abstract val uid: String
    abstract val type: Int

    data class Header(val title: String) : SummaryItem() {
        override val uid = "header"
        override val type: Int
            get() = 0
    }

    data class Item(val summary: Summary) : SummaryItem() {
        override val uid: String
            get() = summary.resourceURI
        override val type: Int
            get() = 1
    }

}
