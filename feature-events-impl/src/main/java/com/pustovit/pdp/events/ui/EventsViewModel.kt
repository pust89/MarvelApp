package com.pustovit.pdp.events.ui

import com.pustoivt.pdp.events_api.model.Event
import com.pustoivt.pdp.events_api.repository.EventsRepository
import com.pustovit.pdp.events.ui.mvi.EventsPartialViewState
import com.pustovit.pdp.events.ui.mvi.EventsViewState
import com.pustovit.pdp.common_ui.ui.BaseViewModel
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
import javax.inject.Inject


class EventsViewModel @Inject constructor(
    private val repository: EventsRepository
) : BaseViewModel<EventsViewState>(EventsViewState()) {

    private val loadingSubject = BehaviorSubject.createDefault(Any())

    private val loadingFlowable = loadingSubject.toFlowable(BackpressureStrategy.LATEST)

    private val eventsFlowable = loadingFlowable
        .flatMap { getEventsFlowable() }

    private fun getEventsFlowable(): Flowable<List<Event>> {
        return Flowable.fromPublisher<List<Event>> {
            val singleRequest = Single.just(Unit)
            singleRequest.flatMap {
                repository.getEvents()
            }.subscribeOn(Schedulers.io())
                .subscribe({ list ->
                    it.onNext(list)
                }, { ex ->
                    onError(ex)
                }).addTo(compositeDisposable)
        }
    }

    override fun onFirstViewAttach() {
        Timber.d("onFirstViewAttach called")
        val loadingPs = loadingFlowable.map {
            EventsPartialViewState.loading(true)
        }

        val eventsPs = eventsFlowable.map {
            EventsPartialViewState.events(it)
        }

        Flowable.merge(loadingPs, eventsPs)
            .scanPartialViewStates()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .addTo(compositeDisposable)
    }

    fun onRefresh() {
        Timber.d("onRefresh called")
        loadingSubject.onNext(Any())
    }

}