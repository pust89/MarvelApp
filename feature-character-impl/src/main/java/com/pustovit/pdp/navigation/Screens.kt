package com.pustovit.pdp.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.pustovit.pdp.models.domain.Items
import com.pustovit.pdp.summary.SummaryFragment
import com.pustovit.pdp.summary.SummaryTitle

object Screens {

    fun summaryScreen(title: SummaryTitle, series: Items) = FragmentScreen {
        SummaryFragment.newInstance(title, series)
    }

}