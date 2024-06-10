package com.geeks.rickandmortyy.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.geeks.rickandmortyy.data.api.ApiService
import com.geeks.rickandmortyy.data.model.characters.Character
import retrofit2.HttpException

const val START_INDEX = 1

class CharacterPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val currentPage = params.key ?: START_INDEX
            val previousKey = if (currentPage == START_INDEX) null else currentPage.minus(1)
            val response = (apiService.getCharacters(currentPage).body()?.results) ?: emptyList()
            LoadResult.Page(
                data = response,
                prevKey = previousKey,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)

        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition.let {anchorPosition ->
            state.closestPageToPosition(anchorPosition!!)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}