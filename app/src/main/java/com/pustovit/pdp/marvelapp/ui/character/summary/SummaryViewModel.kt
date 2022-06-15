package com.pustovit.pdp.marvelapp.ui.character.summary

import com.github.terrakok.cicerone.Router
import com.pustovit.pdp.marvelapp.domain.model.common.Summary
import com.pustovit.pdp.marvelapp.ui.character.summary.model.SummaryItem
import com.pustovit.pdp.marvelapp.ui.character.summary.mvi.SummaryPartialState
import com.pustovit.pdp.marvelapp.ui.common.BaseViewModel
import com.pustovit.pdp.marvelapp.ui.character.summary.mvi.SummaryViewState
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class SummaryViewModel @Inject constructor(
    private val router: Router
) : BaseViewModel<SummaryViewState>(SummaryViewState()) {

    private val summariesBehaviorSubject =
        BehaviorSubject.createDefault<List<SummaryItem>>(emptyList())

    override fun onFirstViewAttach() {

        val summariesPs = summariesBehaviorSubject.toFlowable(BackpressureStrategy.LATEST)
            .distinctUntilChanged()
            .map {
                SummaryPartialState.summaries(summaries = it)
            }

        summariesPs.scan(initialViewState) { state, partial ->
            partial.apply(state)
        }.observeOn(AndroidSchedulers.mainThread())
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