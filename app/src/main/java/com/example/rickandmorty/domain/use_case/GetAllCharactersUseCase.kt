package com.example.rickandmorty.domain.use_case

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.remote.dto.CharacterDto
import com.example.rickandmorty.domain.repository.Repository
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(
    private val repository: Repository
): PagingSource<Int, CharacterDto>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterDto>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDto> {
        return try {
            val currentPage = params.key ?: 1
            val response = repository.getAllCharacters(currentPage)
            val data = response.results
            LoadResult.Page(
                data = data,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = if(response.results.isNotEmpty()) currentPage.plus(1) else null
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}