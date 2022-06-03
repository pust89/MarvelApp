package com.pustovit.pdp.marvelapp.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Pustovit V.V.
 * Date: 30.05.2022
 * Time: 17:10
 */
class CharacterViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Character Fragment"
    }
    val text: LiveData<String> = _text
}