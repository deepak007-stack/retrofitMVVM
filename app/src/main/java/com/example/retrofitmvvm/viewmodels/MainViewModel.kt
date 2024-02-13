package com.example.retrofitmvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitmvvm.model.Post
import com.example.retrofitmvvm.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel(private val repository: UserRepository) : ViewModel() {  // parameterized view model

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPost()
        }
    }

    val post: MutableLiveData<Post?>
        get() = repository.post
}