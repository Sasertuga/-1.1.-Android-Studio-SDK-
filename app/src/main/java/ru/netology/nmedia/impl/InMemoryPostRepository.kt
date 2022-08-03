package ru.netology.nmedia.impl

import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.Post
import ru.netology.nmedia.PostRepository


class InMemoryPostRepository : PostRepository {

    private val posts
        get() = checkNotNull(data.value) { "Data value should not be null" }

    override val data = MutableLiveData(
        List(10) { index ->
            Post(
                id = index + 1L,
                author = "Netology",
                content = "Some whe are over the rainbow... $index",
                published = "21.$index.2022",
                likes = 999,
                likedByMe = false,
                countShare = 0
            )
        }
    )

    override fun like(postId: Long) {
        data.value = posts.map {
            if (it.id == postId) it.copy(
                likedByMe = !it.likedByMe,
                likes = it.likes + if (it.likedByMe) -1 else +1
            ) else it
        }
    }

    override fun share(postId: Long) {
        data.value = posts.map {
            if (it.id == postId) it.copy(countShare = it.countShare + 100) else it
        }
    }

//    override fun share() {
//        val currentPost = checkNotNull(data.value)
//        val clickShare = currentPost.copy(countShare = currentPost.countShare + 100)
//        data.value = clickShare
//    }
}







