package com.uz.abu.movies.core.repository


import com.uz.abu.movies.core.model.now.FilmsNowPlayingResponse
import com.uz.abu.movies.core.model.popular.FilmsPopularResponse
import com.uz.abu.movies.core.network.ApiClient
import com.uz.abu.movies.core.util.API_KEY
import com.uz.abu.movies.core.util.ResultWrapper

class FilmsRepository {

    private val serviceNow = ApiClient.getFilmsNowPlaying()
    private val servicePopular = ApiClient.getFilmsNowPlaying()

    suspend fun getNowFilms(): ResultWrapper<FilmsNowPlayingResponse> {

        val response = serviceNow.getNowPlaying(API_KEY)

        if (response.isSuccessful){
            response.body()?.let { return ResultWrapper(it) }
        }

        return ResultWrapper(error = "Error")
    }




    suspend fun getPopularFilms(): ResultWrapper<FilmsPopularResponse> {
        val response = servicePopular.getPopular(API_KEY)

        if (response.isSuccessful){
            response.body()?.let { return ResultWrapper(it) }
        }
        return ResultWrapper(error = "Error")
    }

}