package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import kotlin.math.ln
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<PostViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.data.observe(this) { post ->
            with(binding) {
                textPost.text = post.content
                authorText.text = post.published
                authorName.text = post.author
                countLikes.text = numberCalculation(post.likes)
                countShare.text = numberCalculation(post.countShare)
                if (post.likedByMe) likes.setImageResource(R.drawable.ic_is_like) else likes.setImageResource(
                    R.drawable.ic_baseline_favorite_border_24
                )
            }
        }

        binding.likes.setOnClickListener {
            viewModel.onLikeClicked()
        }

        binding.share.setOnClickListener {
            viewModel.onShareClicked()
        }
    }
}

private fun numberCalculation(number: Int): String {
    if (number < 1000) return "" + number
    val exp = (ln(number.toDouble()) / ln(1000.0)).toInt()
    return String.format(
        "%.1f %c", number / 1000.0.pow(exp.toDouble()),
        "kMISTYPE"[exp - 1]
    )
}













