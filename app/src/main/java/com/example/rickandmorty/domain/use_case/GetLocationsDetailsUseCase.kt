package com.example.rickandmorty.domain.use_case

import com.example.rickandmorty.common.Resource
import com.example.rickandmorty.data.mappers.toCharacterModel
import com.example.rickandmorty.data.mappers.toLocationModel
import com.example.rickandmorty.domain.model.CharacterModel
import com.example.rickandmorty.domain.model.LocationModel
import com.example.rickandmorty.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetLocationsDetailsUseCase @Inject constructor(
    private val repository: Repository
){
    operator fun invoke(id: Int): Flow<Resource<LocationModel>> = flow {
        try {
            emit(Resource.Loading())
            val locationDetails = repository.getLocationDetails(id).toLocationModel()
            emit(Resource.Success(locationDetails))
        } catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage?:"unknown error occurred"))
        } catch (e: IOException){
            emit(Resource.Error("Oops something went wrong, check your internet connection"))
        }
    }
}