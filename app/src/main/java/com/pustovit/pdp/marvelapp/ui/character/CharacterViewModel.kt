package com.pustovit.pdp.marvelapp.ui.character

import com.github.terrakok.cicerone.Router
import com.pustovit.pdp.marvelapp.domain.model.character.Character
import com.pustovit.pdp.marvelapp.domain.repository.CharactersRepository
import com.pustovit.pdp.marvelapp.navigation.Screens
import com.pustovit.pdp.marvelapp.ui.character.mvi.CharacterPartialState
import com.pustovit.pdp.marvelapp.ui.character.mvi.CharacterViewState
import com.pustovit.pdp.marvelapp.ui.common.BaseViewModel
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class CharacterViewModel @Inject constructor(
    private val repository: CharactersRepository,
    private val router: Router
) : BaseViewModel<CharacterViewState>(CharacterViewState()) {

    private val characterIdSubject = PublishSubject.create<Int>()

    private val characterIdFlowable: Flowable<Int> =
        characterIdSubject.toFlowable(BackpressureStrategy.LATEST)
            .filter { it != 0 }
            .distinctUntilChanged()

    private val characterFlowable: Flowable<Character> =
        characterIdFlowable.flatMap {
            getCharacter(it)
        }

    override fun onFirstViewAttach() {

        val loadingPs = characterIdFlowable.map {
            CharacterPartialState.loading(loading = true)
        }

        val characterPs = characterFlowable.map {
            CharacterPartialState.character(it)
        }

        Flowable.merge(loadingPs, characterPs)
            .scan(initialViewState) { state, partial ->
                partial.apply(state)
            }.observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .addTo(compositeDisposable)
    }

    private fun getCharacter(characterId: Int): Flowable<Character> {
        return Flowable.fromPublisher<Character> {
            val singleRequest = Single.just(characterId)
            singleRequest.flatMap {
                repository.getCharacter(characterId)
            }.subscribeOn(Schedulers.io())
                .subscribe({ list ->
                    it.onNext(list)
                }, { ex ->
                    onError(ex)
                }).addTo(compositeDisposable)
        }
    }

    fun loadCharacter(characterId: Int) {
        characterIdSubject.onNext(characterId)
    }

    fun onComicsButtonClick() {
        val comics = currentViewState.character.comics
        router.navigateTo(Screens.summaryScreen(comics))
    }

    fun onSeriesButtonClick() {
        val series = currentViewState.character.series
        router.navigateTo(Screens.summaryScreen(series))
    }

    fun onStoriesButtonClick() {
        val stories = currentViewState.character.stories
        router.navigateTo(Screens.summaryScreen(stories))
    }

}