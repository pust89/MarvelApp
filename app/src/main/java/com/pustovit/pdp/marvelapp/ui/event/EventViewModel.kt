package com.pustovit.pdp.marvelapp.ui.event

import com.github.terrakok.cicerone.Router
import com.pustovit.pdp.marvelapp.domain.model.event.Event
import com.pustovit.pdp.marvelapp.domain.repository.EventsRepository
import com.pustovit.pdp.marvelapp.ui.common.BaseViewModel
import com.pustovit.pdp.marvelapp.ui.event.mvi.EventPartialState
import com.pustovit.pdp.marvelapp.ui.event.mvi.EventViewState
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class EventViewModel @Inject constructor(
    private val repository: EventsRepository,
    private val router: Router
) : BaseViewModel<EventViewState>(EventViewState()) {

    private val eventIdIdSubject = PublishSubject.create<Int>()

    private val eventIdIdFlowable = eventIdIdSubject.toFlowable(BackpressureStrategy.LATEST)
        .filter { it != 0 }
        .distinctUntilChanged()

    private val eventFlowable: Flowable<Event> =
        eventIdIdFlowable.flatMap { id ->
            getEvent(id)
        }

    override fun onFirstViewAttach() {

        val loadingPs = eventIdIdFlowable.map {
            EventPartialState.loading(loading = true)
        }

        val eventPs = eventFlowable.map { event ->
            EventPartialState.event(event)
        }

        Flowable.merge(loadingPs, eventPs).scan(initialViewState) { state, partial ->
            partial.apply(state)
        }.observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .addTo(compositeDisposable)
    }

    private fun getEvent(eventId: Int): Flowable<Event> {
        return Flowable.fromPublisher {
            val singleRequest = Single.just(eventId)
            singleRequest.flatMap { id ->
                repository.getEvent(id)
            }.subscribeOn(Schedulers.io())
                .subscribe({ list ->
                    it.onNext(list)
                }, { ex ->
                    onError(ex)
                }).addTo(compositeDisposable)
        }
    }

    fun loadEvent(eventId: Int) {
        eventIdIdSubject.onNext(eventId)
    }

}