package com.nihalp01.movies.UI

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nihalp01.movies.Network.API.Result
import com.nihalp01.movies.R

class MoviesAdapter(private val context: Context, private val movies: List<Result>) :
    RecyclerView.Adapter<MoviesViewHolder>() {

    private val onItemClicked: ((Result) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val list: Result = movies[position]
        holder.itemView.setOnClickListener {
            onItemClicked?.invoke(movies[position])
            val intent = Intent(context, MovieDescription::class.java)
            intent.putExtra("movie_details", list.overview)
            intent.putExtra("title", list.title)
            intent.putExtra("movie_poster", list.poster_path)
            context.startActivity(intent)
        }
        return holder.bind(movies[position])
    }
}

class MoviesViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
    private val photo: ImageView = itemView.findViewById(R.id.movie_photo)
    private val title: TextView = itemView.findViewById(R.id.movie_title)
    private val rating: TextView = itemView.findViewById(R.id.movie_rating)
    private val release: TextView = itemview.findViewById(R.id.movie_release)
    private val vote: TextView = itemview.findViewById(R.id.movie_vote)
    private val language: TextView = itemview.findViewById(R.id.movie_language)

    @SuppressLint("SetTextI18n")
    fun bind(movie: Result) {
        Glide.with(itemView.context).load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
            .into(photo)
        title.text = movie.title
        release.text = "Released on: ${movie.release_date}"
        rating.text = "Rating: ${movie.vote_average}"
        vote.text = "${movie.vote_count} Votes"
        language.text = "Language: ${movie.original_language}"
    }
}
