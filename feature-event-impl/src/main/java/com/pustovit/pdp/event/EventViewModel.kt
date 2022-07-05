package com.pustovit.pdp.event

import com.pustoivt.pdp.events_api.model.Event
import com.pustoivt.pdp.events_api.repository.EventsRepository
import com.pustovit.pdp.characters_api.api.model.Character
import com.pustovit.pdp.characters_api.api.repository.CharactersRepository
import com.pustovit.pdp.event.mvi.EventPartialViewState
import com.pustovit.pdp.event.mvi.EventViewState
import com.pustovit.pdp.common_ui.ui.BaseViewModel
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject

class EventViewModel @Inject constructor(
    private val eventsRepository: EventsRepository,
    private val charactersRepository: CharactersRepository
) : BaseViewModel<EventViewState>(EventViewState()) {

    private val eventIdIdSubject = PublishSubject.create<Int>()

    private val eventIdIdFlowable = eventIdIdSubject.toFlowable(BackpressureStrategy.LATEST)
        .filter { it != 0 }
        .distinctUntilChanged()

    private val eventFlowable: Flowable<Event> =
        eventIdIdFlowable.flatMap { id ->
            getEvent(id)
        }

    private val charactersFlowable: Flowable<List<Character>> =
        eventIdIdFlowable.flatMap { id ->
            getCharactersByEventId(id)
        }

    override fun onFirstViewAttach() {

        val loadingPs = eventIdIdFlowable.map {
            EventPartialViewState.loading(loading = true)
        }

        val eventPs = eventFlowable.map { event ->
            EventPartialViewState.event(event)
        }
        val charactersPs = charactersFlowable.map { event ->
            EventPartialViewState.characters(event)
        }

        Flowable.merge(loadingPs, eventPs, charactersPs)
            .scanPartialViewStates()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .addTo(compositeDisposable)
    }

    private fun getEvent(eventId: Int): Flowable<Event> {
        return Flowable.fromPublisher {
            val singleRequest = Single.just(eventId)
            singleRequest.flatMap { id ->
                eventsRepository.getEvent(id)
            }.subscribeOn(Schedulers.io())
                .subscribe({ list ->
                    it.onNext(list)
                }, { ex ->
                    onError(ex)
                }).addTo(compositeDisposable)
        }
    }

    private fun getCharactersByEventId(eventId: Int): Flowable<List<Character>> {
        return Flowable.fromPublisher {
            val singleRequest = Single.just(eventId)
            singleRequest.flatMap { id ->
                charactersRepository.getCharactersByEvent(id)
            }.subscribeOn(Schedulers.io())
                .subscribe({ list ->
                    it.onNext(list)
                }, { ex ->
                    onError(ex)
                }).addTo(compositeDisposable)
        }
    }

    fun loadEvent(eventId: Int) {
        Timber.d("eventId=$eventId")
        eventIdIdSubject.onNext(eventId)
    }

}