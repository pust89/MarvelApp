package com.pustovit.pdp.marvelapp.ui.characters

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pustovit.pdp.marvelapp.domain.repository.CharactersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

@SuppressLint("LogNotTimber")
class CharactersViewModel(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    val TAG = "CharactersViewModel"

    private val compositeDisposable = CompositeDisposable()

    private val _text = MutableLiveData<String>().apply {
        value = "This is Characters Fragment"
    }
    val text: LiveData<String> = _text

    fun loadCharacters() {
        charactersRepository.getCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Log.d(TAG, "loadCharacters: doOnNext result = $result")
            },
                { error ->
                    Log.d(TAG, "loadCharacters: doOnError error = $error")
                }
            ).addTo(compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}