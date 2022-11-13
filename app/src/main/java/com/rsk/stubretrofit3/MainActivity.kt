package com.rsk.stubretrofit3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.rsk.stubretrofit3.databinding.ActivityMainBinding
import retrofit2.HttpException
import java.io.IOException

const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var postsAdapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true

            val response = try {
                RetrofitInstance.api.getPosts()
            }catch (e: IOException){
                Log.e(TAG, "IOException caught")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }catch (e: HttpException){
                Log.e(TAG, "HttpException caught")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }

            if (response.isSuccessful && response.body() != null){
                postsAdapter.posts = response.body()!!
            }else{
                Snackbar.make(binding.root, "Error reaching remote host", Snackbar.LENGTH_LONG).show()
                Log.e(TAG, "Response not successful")
            }

            binding.progressBar.isVisible = false
        }

    }

    private fun setUpRecyclerView() = binding.rvPosts.apply {
            postsAdapter = PostsAdapter()
            adapter = postsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
    }
}