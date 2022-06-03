package com.pustovit.pdp.marvelapp.ui.comics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ComicsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Comics Fragment"
    }
    val text: LiveData<String> = _text
}