package com.rsk.stubretrofit3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.rsk.stubretrofit3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var postsAdapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()


    }

    private fun setUpRecyclerView() = binding.rvPosts.apply {
            postsAdapter = PostsAdapter()
            adapter = postsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
    }
}