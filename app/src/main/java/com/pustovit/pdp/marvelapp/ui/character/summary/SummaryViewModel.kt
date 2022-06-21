package com.pustovit.pdp.marvelapp.ui.character.summary

import com.pustovit.pdp.marvelapp.domain.model.common.Summary
import com.pustovit.pdp.summary.model.SummaryItem
import com.pustovit.pdp.summary.mvi.SummaryPartialViewState
import com.pustovit.pdp.summary.mvi.SummaryViewState
import com.pustovit.pdp.marvelapp.ui.common.BaseViewModel
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class SummaryViewModel @Inject constructor() : BaseViewModel<SummaryViewState>(SummaryViewState()) {

    private val summariesBehaviorSubject =
        BehaviorSubject.createDefault<List<SummaryItem>>(emptyList())

    override fun onFirstViewAttach() {

        val summariesPs = summariesBehaviorSubject.toFlowable(BackpressureStrategy.LATEST)
            .distinctUntilChanged()
            .map {
                SummaryPartialViewState.summaries(summaries = it)
            }

        summariesPs.scanPartialViewStates()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .addTo(compositeDisposable)
    }

    fun setContent(title: String?, summaryList: List<Summary>?) {
        summaryList?.let {
            val summariesItem: MutableList<SummaryItem> = it.map { summary ->
                SummaryItem.Item(summary)
            }.toMutableList()
            val header = SummaryItem.Header(title.orEmpty())
            summariesItem.add(0, header)
            summariesBehaviorSubject.onNext(summariesItem)
        }
    }
}