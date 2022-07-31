package ru.netology.nmedia

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.impl.InMemoryPostRepository

class PostViewModel : ViewModel() {
    private val repository: PostRepository = InMemoryPostRepository()
    val data by repository::data


    fun onLikeClicked() = repository.like()
    fun onShareClicked() = repository.share()
}
