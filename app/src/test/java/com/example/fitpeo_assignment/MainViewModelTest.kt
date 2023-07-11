package com.example.fitpeo_assignment

import androidx.lifecycle.Observer
import com.example.fitpeo_assignment.model.Album
import com.example.fitpeo_assignment.retrofit.ApiInterface
import com.example.fitpeo_assignment.viewmodel.MainViewModel
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.json.JSONObject
import org.junit.After

//
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    private lateinit var viewModel: MainViewModel
    @Mock
    private lateinit var albumObserver :Observer<List<Album>>
    lateinit var apiService:ApiInterface
    lateinit var gson: Gson
    private lateinit var mockWebServer: MockWebServer
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        gson = Gson()
        apiService = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ApiInterface::class.java)
        viewModel = MainViewModel(apiService)
        viewModel.albumLiveData.observeForever(albumObserver)
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @Test
    fun `read sample success json file`(){
        val reader = MockResponseFileReader("success_response.json")
        assertNotNull(reader.content)
    }

    @Test
    fun `fetch details and check response Code 200 returned`(){
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("success_response.json").content)
        mockWebServer.enqueue(response)
        // Act
        val  actualResponse = apiService.getPhotos().execute()
        // Assert
        assertEquals(response.toString().contains("200"),actualResponse.code().toString().contains("200"))
    }


    @After
    fun tearDown() {
        viewModel.albumLiveData.removeObserver(albumObserver)
        mockWebServer.shutdown()
    }

}