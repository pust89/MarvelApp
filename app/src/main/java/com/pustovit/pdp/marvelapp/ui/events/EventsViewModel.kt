package com.pustovit.pdp.marvelapp.ui.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Router
import com.pustovit.pdp.marvelapp.domain.model.character.Character
import com.pustovit.pdp.marvelapp.domain.model.event.Event
import com.pustovit.pdp.marvelapp.domain.repository.EventsRepository
import com.pustovit.pdp.marvelapp.navigation.Screens
import com.pustovit.pdp.marvelapp.navigation.TabNavigationEvents
import com.pustovit.pdp.marvelapp.ui.characters.mvi.CharactersPartialState
import com.pustovit.pdp.marvelapp.ui.common.BaseViewModel
import com.pustovit.pdp.marvelapp.ui.events.mvi.EventsPartialState
import com.pustovit.pdp.marvelapp.ui.events.mvi.EventsViewState
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
import javax.inject.Inject


class EventsViewModel(
    private val repository: EventsRepository,
    private val router: Router
) : BaseViewModel<EventsViewState>(EventsViewState()) {

    private val loadingSubject = BehaviorSubject.createDefault(Unit)

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
            EventsPartialState.loading(true)
        }

        val eventsPs = eventsFlowable.map {
            EventsPartialState.events(it)
        }

        Flowable.merge(loadingPs, eventsPs)
            .scan(initialViewState) { state, partial ->
                partial.apply(state)
            }.observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .addTo(compositeDisposable)
    }

    fun onEventClick(event: Event) {
        router.navigateTo(Screens.eventScreen(event))
    }

    fun onRefresh() {
        loadingSubject.onNext(Unit)
    }

    class Factory @Inject constructor(
        private val repository: EventsRepository,
        @TabNavigationEvents private val router: Router
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EventsViewModel::class.java)) {
                return EventsViewModel(repository, router) as T
            } else {
                throw RuntimeException("Unknown viewModel ${modelClass::class.java.canonicalName}")
            }
        }

    }
}