package com.example.roomsamplekolokvij.main

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.roomsamplekolokvij.databinding.ItemAlbumBinding
import com.example.roomsamplekolokvij.model.WeatherBaseInfo

class WeatherAdapter: RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private val items: MutableList<WeatherBaseInfo> = mutableListOf()
    var listener: WeatherInteractionListener? = null

    fun setWeather(weather: List<WeatherBaseInfo>){
        items.clear()
        items.addAll(weather)
        notifyDataSetChanged()
    }

    inner class WeatherViewHolder(private val itemBinding: ItemAlbumBinding): RecyclerView.ViewHolder(itemBinding.root){

        init {
            itemBinding.root.setOnClickListener {
                listener?.onWeatherDeleted(items[layoutPosition])
            }
        }

        fun bind(album: WeatherBaseInfo){
            itemBinding.tvBandName.text = album.name
            itemBinding.tvAlbumTitle.text = ((album.tempMax + album.tempMin) / 2).toString()
        }
    }

    interface WeatherInteractionListener{
        fun onWeatherDeleted(weather: WeatherBaseInfo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val itemBinding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }


}