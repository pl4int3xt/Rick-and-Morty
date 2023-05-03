package com.example.rickandmorty.domain.use_case

import com.example.rickandmorty.common.Resource
import com.example.rickandmorty.data.mappers.toCharacterModel
import com.example.rickandmorty.domain.model.CharacterModel
import com.example.rickandmorty.domain.model.EpisodeModel
import com.example.rickandmorty.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetEpisodeDetailsUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(id: Int): Flow<Resource<EpisodeModel>> = flow {
        try {
            emit(Resource.Loading())
            val episodeDetails = repository.getEpisodeDetails(id)
            emit(Resource.Success(characterDetails))
        } catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage?:"unknown error occurred"))
        } catch (e: IOException){
            emit(Resource.Error("Oops something went wrong, check your internet connection"))
        }
    }
}