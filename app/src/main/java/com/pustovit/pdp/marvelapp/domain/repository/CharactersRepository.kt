package com.pustovit.pdp.marvelapp.domain.repository

import com.pustovit.pdp.marvelapp.domain.model.character.Character
import io.reactivex.Single

/**
 * Created by Pustovit V.V.
 * Date: 31.05.2022
 * Time: 18:46
 */
interface CharactersRepository {

     fun getCharacters(): Single<List<Character>>

}
