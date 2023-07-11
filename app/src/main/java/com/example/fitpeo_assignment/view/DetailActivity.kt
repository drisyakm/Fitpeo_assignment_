package com.example.fitpeo_assignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.fitpeo_assignment.R
import com.example.fitpeo_assignment.constants.AppConstants
import com.example.fitpeo_assignment.databinding.ActivityDetailBinding
import com.example.fitpeo_assignment.model.Album

class DetailActivity : AppCompatActivity() {
    private var selectedAlbum :Album?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding:ActivityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        selectedAlbum = intent.getParcelableExtra(AppConstants.SELECTED_ALBUM)
        binding.album = selectedAlbum


    }
}