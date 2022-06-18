package com.project.loginfirebase.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.loginfirebase.R

class CustomAdapter(private val dataSet: List<OnBoardingItem>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView
        var description: TextView
        var image: ImageView

        init {
            title = view.findViewById(R.id.textTile)
            description = view.findViewById(R.id.textdescription)
            image = view.findViewById(R.id.imageOnBoarding)
        }

        fun bind(item: OnBoardingItem) {
            title.text = item.title
            description.text = item.description
            image.setImageResource(item.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_container, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet.get(position)
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}