package com.example.rickandmorty.data.data_sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.remote.dto.EpisodeDto
import com.example.rickandmorty.domain.repository.Repository
import javax.inject.Inject

class GetAllEpisodesDataSource @Inject constructor(
    private val repository: Repository
): PagingSource<Int, EpisodeDto>(){
    override fun getRefreshKey(state: PagingState<Int, EpisodeDto>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodeDto> {
        return try {
            val currentPage = params.key ?: 1
            val response = repository.getAllEpisodes(currentPage)
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