package com.pustovit.pdp.marvelapp.ui.characters

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pustovit.pdp.marvelapp.domain.repository.CharactersRepository
import com.pustovit.pdp.marvelapp.ui.characters.mvi.CharactersPartialState
import com.pustovit.pdp.marvelapp.ui.characters.mvi.CharactersViewState
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
import javax.inject.Inject

class CharactersViewModel(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _viewState = BehaviorSubject.createDefault(CharactersViewState())

    @MainThread
    fun viewState(): Flowable<CharactersViewState> {
        return _viewState.toFlowable(BackpressureStrategy.LATEST)
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadCharacters() {
        charactersRepository.getCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                val currentState = _viewState.value!!
                 currentState.copy(characters = it)
            }
            .subscribe({
                _viewState.onNext(it)
            }, { error ->
                Timber.d("loadCharacters: doOnError error = $error")
            }
            ).addTo(compositeDisposable)
    }


    fun handleUserInput(query: String?) {

    }

    fun clearSearchResult() {

    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    class Factory @Inject constructor(private val charactersRepository: CharactersRepository) :
        ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CharactersViewModel::class.java)) {
                return CharactersViewModel(charactersRepository) as T
            } else {
                throw RuntimeException("Unknown viewModel ${modelClass::class.java.canonicalName}")
            }
        }

    }
}