package com.uz.abu.movies.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uz.abu.movies.core.model.now.FilmsNowPlayingResponse
import com.uz.abu.movies.core.model.popular.FilmsPopularResponse
import com.uz.abu.movies.core.repository.FilmsRepository
import kotlinx.coroutines.launch



class HomeScreenViewModel : ViewModel() {

    private val repository = FilmsRepository()

    val filmsNowLiveData: MutableLiveData<FilmsNowPlayingResponse?> = MutableLiveData()
    val filmsPopularLiveData: MutableLiveData<FilmsPopularResponse?> = MutableLiveData()
    val errorLiveData: MutableLiveData<String?> = MutableLiveData()

    fun getFilmsData() {

        viewModelScope.launch {
            val resultNow = repository.getNowFilms()
            val resultPopular = repository.getPopularFilms()

            if (resultNow != null) {
                filmsNowLiveData.value = resultNow.data!!
            } else {
                errorLiveData.value = resultNow.error
            }
            if (resultPopular!= null){
                filmsPopularLiveData.value = resultPopular.data!!
            }
        }

    }

}