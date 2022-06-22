package com.pustovit.pdp.character

import com.pustovit.pdp.character.mvi.CharacterPartialViewState
import com.pustovit.pdp.character.mvi.CharacterViewState
import com.pustovit.pdp.characters_api.api.model.Character
import com.pustovit.pdp.characters_api.api.repository.CharactersRepository
import com.pustovit.pdp.common_ui.ui.BaseViewModel
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class CharacterViewModel @Inject constructor(
    private val repository: CharactersRepository
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
            CharacterPartialViewState.loading(loading = true)
        }

        val characterPs = characterFlowable.map {
            CharacterPartialViewState.character(it)
        }
        Flowable.merge(loadingPs, characterPs)
            .scanPartialViewStates()
            .observeOn(AndroidSchedulers.mainThread())
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


}