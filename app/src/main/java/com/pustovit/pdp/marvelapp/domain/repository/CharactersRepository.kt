package com.pustovit.pdp.marvelapp.domain.repository

import com.pustovit.pdp.marvelapp.domain.model.Character
import io.reactivex.Flowable

/**
 * Created by Pustovit V.V.
 * Date: 31.05.2022
 * Time: 18:46
 */
interface CharactersRepository {

     fun getCharacters(): Flowable<Result<List<Character>>>

}
