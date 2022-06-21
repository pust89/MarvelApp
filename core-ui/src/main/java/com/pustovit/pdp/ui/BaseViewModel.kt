package com.pustovit.pdp.ui

import androidx.lifecycle.ViewModel
import com.pustovit.pdp.marvelapp.common.mvi.ViewState
import com.pustovit.pdp.marvelapp.common.mvi.ViewStateError
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber

open class BaseViewModel<VS : ViewState>(protected val initialViewState: VS) : ViewModel() {

    private var isFirstLaunch = true
    protected val compositeDisposable = CompositeDisposable()

    private val _viewState = BehaviorSubject.createDefault(initialViewState)

    val currentViewState: VS
        get() = _viewState.value ?: initialViewState

    val viewState: Flowable<VS> =
        _viewState.toFlowable(BackpressureStrategy.LATEST)
            .observeOn(AndroidSchedulers.mainThread())

    fun onAttach() {
        if (isFirstLaunch) {
            isFirstLaunch = false
            onFirstViewAttach()
        }
    }

    protected open fun onFirstViewAttach() {}


    protected open fun onSuccess(newViewState: VS) {
        _viewState.onNext(newViewState)
    }

    protected open fun onError(ex: Throwable) {
        Timber.d("onError ex=${ex}")
        _viewState.value?.let { previousState ->

            val viewStateError = ViewStateError(error = ex)

            previousState.viewStateError = viewStateError
            previousState.loading = false

            _viewState.onNext(previousState)
        }
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun <T : Function<VS, VS>> Flowable<T>.scanPartialViewStates()
            : Flowable<VS> {
        return this.scan(initialViewState, BiFunction { state, partial ->
            partial.apply(state)
        })
    }

}
