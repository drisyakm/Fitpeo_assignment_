package com.example.fitpeo_assignment.view

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.fitpeo_assignment.R
import com.example.fitpeo_assignment.constants.AppConstants
import com.example.fitpeo_assignment.databinding.ActivityMainBinding
import com.example.fitpeo_assignment.model.Album
import com.example.fitpeo_assignment.viewmodel.MainViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ref.WeakReference

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),ItemSelectionListener<Album> {
    lateinit var mainViewModel:  MainViewModel
    private var albumAdapter:AlbumAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        albumAdapter = AlbumAdapter(mutableListOf(), WeakReference(this))
        binding.rvAlbum.adapter = albumAdapter
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.albumLiveData.observe(this) {
            binding.loading = false
            if (it != null) {
                albumAdapter?.addItems(it)

            } else {
                //show error
            }
        }

        binding.loading = true
        mainViewModel.getPhotos()
    }



    override fun onItemSelected(item: Album?, position: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(AppConstants.SELECTED_ALBUM,item)
        startActivity(intent)
    }
}

@BindingAdapter("thumbnailUrl")
fun ImageView.loadImage(url: String) {
    Picasso.get().load(url)
        .placeholder(ColorDrawable(Color.BLACK))
        .into(this)
}

@BindingAdapter("android:visibility")
fun View.setVisibility(visible: Boolean) {
    visibility = if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

