package com.nihalp01.movies.UI

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.nihalp01.movies.R
import kotlinx.android.synthetic.main.movie_overview.*

class MovieDescription : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_overview)

        val intent = intent

        setSupportActionBar(toolbar)
        supportActionBar?.title = intent.getStringExtra("title")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val photo: ImageView = findViewById(R.id.movie_poster)
        val poster = intent.getStringExtra("movie_poster")

        movie_overview.text = intent.getStringExtra("movie_details")
        Glide.with(this).load("https://image.tmdb.org/t/p/w500$poster").into(photo)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }

}