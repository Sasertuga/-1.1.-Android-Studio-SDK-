package ru.netology.nmedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import kotlin.math.ln
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.likes.setOnClickListener {
            binding.likes.setImageResource(R.drawable.ic_is_like)
        }

        val post = Post(
            id = 1,
            author = "Maksim",
            content = "Bla bla bla bla bla bla...",
            published = "21.07.2022",
            likes = 999,
            likedByMe = false
        )

        with(binding) {
            textPost.text = post.content
            authorText.text = post.published
            authorName.text = post.author
            countLikes.text = post.likes.toString()
            if (post.likedByMe) binding.likes.setImageResource(R.drawable.ic_is_like)

        }



        binding.likes.setOnClickListener {
            post.likedByMe = !post.likedByMe
            val imageResId =
                if (!post.likedByMe) R.drawable.ic_baseline_favorite_border_24
                else R.drawable.ic_is_like
            binding.likes.setImageResource(imageResId)

            if (post.likedByMe) binding.countLikes.text = getFormattedNumber(post.likes + 1)
            else binding.countLikes.text = (post.likes).toString()
        }

        var countShare = 0

        binding.share.setOnClickListener {
            countShare += 1

            binding.countShare.text = getFormattedNumber(countShare)
        }
    }
}

fun getFormattedNumber(count: Int): String {
    if (count < 1000) return "" + count
    val exp = (ln(count.toDouble()) / ln(1000.0)).toInt()
    return String.format("%.1f %c", count / 1000.0.pow(exp.toDouble()), "kMISTYPE"[exp - 1])
}








