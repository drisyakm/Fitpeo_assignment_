package com.example.fitpeo_assignment.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
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

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = resources.getString(R.string.details_screen_title)
        selectedAlbum = intent.getParcelableExtra(AppConstants.SELECTED_ALBUM)
        binding.album = selectedAlbum


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)

    }
}