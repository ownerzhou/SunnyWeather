package com.owner.sunnyweather.ui.place

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.owner.sunnyweather.R
import com.owner.sunnyweather.logic.model.Place
import com.owner.sunnyweather.ui.weather.WeatherActivity

class PlaceAdapter(private val fragment: PlaceFragment, private val placeList: List<Place>) :
    RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val placeName: TextView = view.findViewById(R.id.placeName)
        val placeAddress: TextView = view.findViewById(R.id.placeAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(fragment.context).inflate(R.layout.item_place, parent, false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            val place = placeList[holder.adapterPosition]
            fragment.viewModel.savePlace(place)
            val activity = fragment.activity
            if (activity is WeatherActivity) {
                activity.closeDrawer(place)
            } else {
                WeatherActivity.startActivity(fragment, place)
                fragment.activity?.finish()
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = placeList[position]
        holder.apply {
            placeName.text = place.name
            placeAddress.text = place.address
        }
    }

    override fun getItemCount() = placeList.size
}