package com.example.firebaseproject

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseproject.databinding.ImageItemBinding
class ImageAdapter (val context : Context,val imageList : ArrayList<imageModel>)
    :RecyclerView.Adapter<ImageAdapter.myViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return myViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return imageList.count()
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.binding.imageView3.setImageResource(imageList[position].image)
    }
    inner class myViewHolder(val binding:ImageItemBinding) : RecyclerView.ViewHolder(binding.root)

}