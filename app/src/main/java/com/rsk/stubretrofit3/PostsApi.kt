package com.rsk.stubretrofit3

import retrofit2.Response
import retrofit2.http.GET

interface PostsApi {

    @GET("/posts")
    suspend fun getPosts(): Response<List<Posts>>
}