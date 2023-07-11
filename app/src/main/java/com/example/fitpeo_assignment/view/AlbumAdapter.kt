package com.example.fitpeo_assignment.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpeo_assignment.databinding.AlbumListItemBinding
import com.example.fitpeo_assignment.model.Album
import java.lang.ref.WeakReference

class AlbumAdapter(val items:MutableList<Album>,val listener: WeakReference<ItemSelectionListener<Album>>): RecyclerView.Adapter<AlbumHolder>() {
    private lateinit var binding:AlbumListItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {
        binding = AlbumListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumHolder(binding,listener)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: AlbumHolder, position: Int) {
        val album = items[position]
        holder.bind(album)
    }

    fun addItems(items:List<Album>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}