package com.example.rickandmorty.domain.use_case

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.example.rickandmorty.data.mappers.toCharacterModel
import com.example.rickandmorty.data.remote.dto.CharacterDto
import com.example.rickandmorty.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(
    private val pager: Pager<Int, CharacterDto>
) {
    operator fun invoke(): Flow<PagingData<CharacterModel>>{
        return pager
            .flow
            .map { pagingData ->
                pagingData.map { it.toCharacterModel() }
            }
    }
}