package com.pustovit.pdp.marvelapp.domain.repository

import com.pustovit.pdp.marvelapp.domain.model.characters.Character
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by Pustovit V.V.
 * Date: 31.05.2022
 * Time: 18:46
 */
interface CharactersRepository {

     fun getCharacters(): Single<List<Character>>

}
