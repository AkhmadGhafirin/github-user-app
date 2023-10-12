package com.cascer.githubuserapp.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cascer.githubuserapp.data.model.UserModel
import com.cascer.githubuserapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _favorites = MutableLiveData<List<UserModel>>()
    val favorites: LiveData<List<UserModel>> = _favorites

    fun getFavorites() = viewModelScope.launch {
        _favorites.postValue(repository.getFavorites())
    }
}