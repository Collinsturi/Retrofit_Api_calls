package com.rsk.stubretrofit3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rsk.stubretrofit3.databinding.ItemPostsBinding

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {
    inner class PostsViewHolder(val binding: ItemPostsBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Posts>(){
        override fun areItemsTheSame(oldItem: Posts, newItem: Posts): Boolean = (oldItem.userId == oldItem.userId)

        override fun areContentsTheSame(oldItem: Posts, newItem: Posts): Boolean = (oldItem == newItem)
    }

    private val differ = AsyncListDiffer<Posts>(this, diffCallback)

    var posts: List<Posts>
        get() = differ.currentList
        set(value) { differ.submitList(value)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(ItemPostsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.binding.apply {
            val item = posts[position]

            tvTitle.text = item.title
            tvBody.text = item.body
        }
    }
}