package com.pustovit.pdp.marvelapp.ui.events

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Router
import com.pustovit.pdp.marvelapp.domain.model.event.Event
import com.pustovit.pdp.marvelapp.domain.repository.EventsRepository
import com.pustovit.pdp.marvelapp.navigation.Screens
import com.pustovit.pdp.marvelapp.navigation.TabNavigationEvents
import com.pustovit.pdp.marvelapp.ui.events.mvi.EventsViewState
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
import javax.inject.Inject


class EventsViewModel(
    private val eventsRepository: EventsRepository,
    private val router: Router
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _viewState = BehaviorSubject.createDefault(EventsViewState())

    @MainThread
    fun viewState(): Flowable<EventsViewState> {
        return _viewState.toFlowable(BackpressureStrategy.LATEST)
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadEvents() {
        eventsRepository.getEvents()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                val currentState = _viewState.value!!
                currentState.copy(events = it)
            }
            .subscribe({
                _viewState.onNext(it)
            }, { error ->
                Timber.d("loadEvents: doOnError error = $error")
            }
            ).addTo(compositeDisposable)
    }


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun onEventClick(event: Event) {
       router.navigateTo(Screens.eventScreen(event))
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