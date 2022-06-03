package com.pustovit.pdp.marvelapp.ui.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Pustovit V.V.
 * Date: 30.05.2022
 * Time: 19:59
 */
class EventViewModel: ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Event Fragment"
    }
    val text: LiveData<String> = _text
}