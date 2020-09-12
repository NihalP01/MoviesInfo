package com.nihalp01.moviesInfo.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nihalp01.moviesInfo.Network.API.Cast
import com.nihalp01.moviesInfo.R
import com.nihalp01.moviesInfo.UI.CastDescription

class CastAdapter(val context: Context, private val castList: List<Cast>) :
    RecyclerView.Adapter<CastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val view: View? =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cast, parent, false)
        return CastViewHolder(view!!)
    }

    override fun getItemCount() = castList.size

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val list: Cast = castList[position]
            val intent = Intent(context, CastDescription::class.java)
            val bundle = Bundle()
            bundle.putSerializable("cast_info", list)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
        return holder.bind(castList[position])
    }
}

class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val name: TextView = itemView.findViewById(R.id.cast_name)
    private val character: TextView = itemView.findViewById(R.id.cast_character)
    private val gender: TextView = itemView.findViewById(R.id.cast_gender)
    private val image: ImageView = itemView.findViewById(R.id.cast_image)

    @SuppressLint("SetTextI18n")
    fun bind(castlist: Cast) {
        if (castlist.gender == 1){
            gender.text = "Gender: Female"
        }
        else if(castlist.gender == 0){
            gender.text = "Gender: ---"
        }
        else{
            gender.text = "Gender: Male"
        }
        Glide.with(itemView.context)
            .load("https://image.tmdb.org/t/p/w500${castlist.profile_path}").into(image)
        name.text = "Name: ${castlist.name}"
        character.text = "Character: ${castlist.character}"
    }
}

