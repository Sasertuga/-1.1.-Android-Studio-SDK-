package ru.netology.nmedia.impl

import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.Post
import ru.netology.nmedia.PostRepository


class InMemoryPostRepository : PostRepository {
    override val data = MutableLiveData(
        Post(
            id = 1,
            author = "Maksim",
            content = "Bla bla bla bla bla bla...",
            published = "21.07.2022",
            likes = 999,
            likedByMe = false,
            countShare = 0
        )
    )

    override fun like() {
        val currentPost = checkNotNull(data.value) {
            "Data value should not be null"
        }
        val likedPost = currentPost.copy(likedByMe = !currentPost.likedByMe)
        likedPost.likes = if (likedPost.likedByMe) likedPost.likes + 1
        else (likedPost.likes - 1)
        data.value = likedPost
    }

    override fun share() {
        val currentPost = checkNotNull(data.value)
        val clickShare = currentPost.copy(countShare = currentPost.countShare + 100)
        data.value = clickShare
    }
}




