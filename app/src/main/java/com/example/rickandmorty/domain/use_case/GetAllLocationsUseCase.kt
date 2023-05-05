package com.example.rickandmorty.domain.use_case

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.remote.dto.LocationDto
import com.example.rickandmorty.domain.repository.Repository
import javax.inject.Inject

class GetAllLocationsUseCase @Inject constructor(
    private val repository: Repository
): PagingSource<Int, LocationDto>() {
    override fun getRefreshKey(state: PagingState<Int, LocationDto>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationDto> {
        return try {
            val currentPage = params.key?:1
            val response = repository.getAllLocations(currentPage)
            LoadResult.Page(
                data = response.results,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}