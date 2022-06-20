package com.pustovit.pdp.marvelapp.ui.characters

import com.pustovit.pdp.marvelapp.domain.model.character.Character
import com.pustovit.pdp.marvelapp.domain.repository.CharactersRepository
import com.pustovit.pdp.marvelapp.ui.characters.mvi.CharactersPartialViewState
import com.pustovit.pdp.marvelapp.ui.characters.mvi.CharactersViewState
import com.pustovit.pdp.marvelapp.ui.common.BaseViewModel
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val repository: CharactersRepository
) : BaseViewModel<CharactersViewState>(CharactersViewState()) {
    init {
        Timber.d("init CharactersViewModel ${hashCode()}")
    }
    private val userInputSubject = BehaviorSubject.createDefault<String>("")

    private val queryFlowable: Flowable<String> =
        userInputSubject.toFlowable(BackpressureStrategy.LATEST)
            .debounce(1, TimeUnit.SECONDS)
            .distinctUntilChanged()

    private val charactersFlowable: Flowable<List<Character>> =
        queryFlowable.flatMap {
            executeQuery(it)
        }

    private fun executeQuery(query: String): Flowable<List<Character>> {
        return Flowable.fromPublisher<List<Character>> {
            val singleRequest = Single.just(query)
            singleRequest.flatMap {
                repository.getCharacters(query)
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
        val queryPs = queryFlowable.map {
            CharactersPartialViewState.query(it)
        }

        val charactersPs = charactersFlowable.map {
            CharactersPartialViewState.characters(it)
        }

        Flowable.merge(queryPs, charactersPs)
            .scanPartialViewStates()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .addTo(compositeDisposable)
    }


    fun handleUserInput(input: String?) {
        Timber.d("handleUserInput input=${input}")
        input?.let(userInputSubject::onNext)
    }

}