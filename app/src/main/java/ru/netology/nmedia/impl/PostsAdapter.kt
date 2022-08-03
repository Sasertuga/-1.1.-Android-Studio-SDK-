package ru.netology.nmedia.impl

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.Post
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.PostBinding
import kotlin.math.ln
import kotlin.math.pow


internal class PostsAdapter(
    private val onLikeClicked: (Post) -> Unit,
    private val onShareClicked: (Post) -> Unit
) : ListAdapter<Post, PostsAdapter.ViewHolder>(DiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: PostBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) = with(binding) {
            textPost.text = post.content
            authorText.text = post.published
            authorName.text = post.author
            countLikes.text = numberCalculation(post.likes)
            countShare.text = numberCalculation(post.countShare)
            likes.setImageResource(getLikeIconResId(post.likedByMe))
            likes.setOnClickListener { onLikeClicked(post) }
            share.setOnClickListener { onShareClicked(post) }
        }


        @DrawableRes
        private fun getLikeIconResId(liked: Boolean) =
            if (liked) R.drawable.ic_is_like else R.drawable.ic_baseline_favorite_border_24
    }


    private fun numberCalculation(number: Int): String {
        if (number < 1000) return "" + number
        val exp = (ln(number.toDouble()) / ln(1000.0)).toInt()
        return String.format(
            "%.1f %c", number / 1000.0.pow(exp.toDouble()),
            "kMISTYPE"[exp - 1]
        )
    }

    private object DiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Post, newItem: Post) =
            oldItem == newItem
    }
}