package com.example.rickandmorty.data.data_sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.remote.dto.CharacterDto
import com.example.rickandmorty.domain.repository.Repository
import javax.inject.Inject

class GetAllCharactersDataSource @Inject constructor(
    private val repository: Repository
): PagingSource<Int, CharacterDto>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterDto>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDto> {
        return try {
            val currentPage = params.key ?: 1
            val response = repository.getAllCharacters(currentPage)
            val data = response.results
            LoadResult.Page(
                data = data,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}