package com.cascer.githubuserapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.cascer.githubuserapp.data.model.UserModel
import com.cascer.githubuserapp.data.repository.Repository
import com.cascer.githubuserapp.utils.SettingPreferences
import com.cascer.githubuserapp.utils.network.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    private val settingPreferences: SettingPreferences
) : ViewModel() {

    private val _users = MutableLiveData<List<UserModel>>()
    val users: LiveData<List<UserModel>> = _users

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isError = MutableLiveData<String>()
    val isError: LiveData<String> = _isError

    fun searchUser(query: String) = viewModelScope.launch {
        _isLoading.postValue(true)
        when (val result = repository.searchUser(query)) {
            is Results.Success -> {
                _isLoading.postValue(false)
                _users.postValue(result.data.toList())
            }

            is Results.Error -> {
                _isLoading.postValue(false)
                _isError.postValue(result.exception.message)
            }
        }
    }

    fun getThemeSettings(): LiveData<Boolean> {
        return settingPreferences.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkMode: Boolean) = viewModelScope.launch {
        settingPreferences.saveThemeSetting(isDarkMode)
    }
}