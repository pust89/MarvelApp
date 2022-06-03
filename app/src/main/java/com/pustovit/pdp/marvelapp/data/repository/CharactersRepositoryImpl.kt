package com.pustovit.pdp.marvelapp.data.repository

import com.pustovit.pdp.marvelapp.data.source.remote.CharactersRemoteDataSource
import com.pustovit.pdp.marvelapp.domain.model.Character
import com.pustovit.pdp.marvelapp.domain.repository.CharactersRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Pustovit V.V.
 * Date: 02.06.2022
 * Time: 20:00
 */
class CharactersRepositoryImpl @Inject constructor(
    private val charactersRemoteDataSource: CharactersRemoteDataSource
) : CharactersRepository {

    override fun getCharacters(): Flowable<Result<List<Character>>> {
        return charactersRemoteDataSource.getCharacters()
    }
}