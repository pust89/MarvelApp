package com.pustovit.pdp.character.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.pustovit.pdp.common_models.domain.Items
import com.pustovit.pdp.character.summary.SummaryFragment
import com.pustovit.pdp.character.summary.SummaryTitle

object Screens {

    fun summaryScreen(title: SummaryTitle, series: Items) = FragmentScreen {
        SummaryFragment.newInstance(title, series)
    }

}