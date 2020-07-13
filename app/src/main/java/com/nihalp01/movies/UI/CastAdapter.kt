package com.nihalp01.movies.UI

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nihalp01.movies.Network.API.Cast
import com.nihalp01.movies.R

class CastAdapter(private val context: Context, private val castList: List<Cast>): RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cast, parent, false)
        return CastViewHolder(view)
    }

    override fun getItemCount() = castList.size

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        return holder.bind(castList[position])
    }

    class CastViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.cast_name)
        private val character: TextView = itemView.findViewById(R.id.cast_character)
        private val gender: TextView = itemView.findViewById(R.id.cast_gender)
        private val image: ImageView = itemView.findViewById(R.id.cast_image)

        @SuppressLint("SetTextI18n")
        fun bind(castlist: Cast) {
            Glide.with(itemView.context).load("https://image.tmdb.org/t/p/w500${castlist.profile_path}").into(image)
            name.text = "Name: ${castlist.name}"
            character.text = "Character: ${castlist.character}"
            gender.text = "Gender: ${castlist.gender}"
        }
    }

}