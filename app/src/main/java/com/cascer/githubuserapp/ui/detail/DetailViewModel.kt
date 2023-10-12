package com.cascer.githubuserapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cascer.githubuserapp.data.model.UserModel
import com.cascer.githubuserapp.data.model.mapper.Mapper.toEntity
import com.cascer.githubuserapp.data.repository.Repository
import com.cascer.githubuserapp.utils.network.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _user = MutableLiveData<UserModel?>()
    val user: LiveData<UserModel?> = _user

    private val _favorite = MutableLiveData<UserModel>()
    var favorite: LiveData<UserModel> = _favorite

    private val _followers = MutableLiveData<List<UserModel>>()
    val followers: LiveData<List<UserModel>> = _followers

    private val _following = MutableLiveData<List<UserModel>>()
    val following: LiveData<List<UserModel>> = _following

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isError = MutableLiveData<String>()
    val isError: LiveData<String> = _isError

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    fun getUserDetail(username: String) = viewModelScope.launch {
        _isLoading.postValue(true)
        when (val result = repository.userDetail(username)) {
            is Results.Success -> {
                _isLoading.postValue(false)
                _user.postValue(result.data)
            }

            is Results.Error -> {
                _isLoading.postValue(false)
                _isError.postValue(result.exception.message)
            }
        }
    }

    fun getUserFollowers(username: String) = viewModelScope.launch {
        _isLoading.postValue(true)
        when (val result = repository.userFollowers(username)) {
            is Results.Success -> {
                _isLoading.postValue(false)
                _followers.postValue(result.data.toList())
            }

            is Results.Error -> {
                _isLoading.postValue(false)
                _isError.postValue(result.exception.message)
            }
        }
    }

    fun getUserFollowing(username: String) = viewModelScope.launch {
        _isLoading.postValue(true)
        when (val result = repository.userFollowing(username)) {
            is Results.Success -> {
                _isLoading.postValue(false)
                _following.postValue(result.data.toList())
            }

            is Results.Error -> {
                _isLoading.postValue(false)
                _isError.postValue(result.exception.message)
            }
        }
    }

    fun insertFavorite(user: UserModel) = viewModelScope.launch {
        _isFavorite.postValue(true)
        repository.insertFavorite(user.toEntity())
    }

    fun getFavorite(login: String) = viewModelScope.launch {
        _favorite.postValue(repository.getFavoriteByUsername(login))
    }

    fun deleteFavorite(id: Int) = viewModelScope.launch {
        _isFavorite.postValue(false)
        repository.deleteFavorite(id)
    }
}