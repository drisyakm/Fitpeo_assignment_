package com.example.fitpeo_assignment.retrofit

import com.example.fitpeo_assignment.model.Album
import retrofit2.Call
import retrofit2.http.GET


interface ApiInterface {
    @GET("photos")
    fun getPhotos() : Call<List<Album>>
}