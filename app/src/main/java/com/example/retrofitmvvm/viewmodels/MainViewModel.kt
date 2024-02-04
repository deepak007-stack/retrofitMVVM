package com.example.retrofitmvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitmvvm.model.Post
import com.example.retrofitmvvm.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: UserRepository) : ViewModel() {  // parameterized view model

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPost()
        }
    }

    val post: LiveData<Post>
        get() = repository.post
}