package com.example.fitpeo_assignment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitpeo_assignment.model.Album
import com.example.fitpeo_assignment.retrofit.ApiInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject constructor(private val apiInterface: ApiInterface):ViewModel() {

    val albumLiveData:  MutableLiveData<List<Album>>  by lazy {
        MutableLiveData<List<Album>>()
    }

    fun getPhotos(){
        apiInterface.getPhotos().enqueue(object: Callback<List<Album>>{
            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                if(response.isSuccessful && response.body()!=null){
                    albumLiveData.postValue(response.body())
                }else{
                    albumLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                albumLiveData.postValue(null)
            }

        })
    }
}