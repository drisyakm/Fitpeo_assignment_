package com.example.fitpeo_assignment.view

import androidx.recyclerview.widget.RecyclerView
import com.example.fitpeo_assignment.databinding.AlbumListItemBinding
import com.example.fitpeo_assignment.model.Album
import java.lang.ref.WeakReference

class AlbumHolder(private var binding:AlbumListItemBinding,val listener: WeakReference<ItemSelectionListener<Album>>): RecyclerView.ViewHolder(binding.root) {
    init {
        itemView.setOnClickListener {
            listener.get()?.onItemSelected(binding.album,layoutPosition)
        }
    }
    fun bind(album: Album){
        binding.album = album
    }
}