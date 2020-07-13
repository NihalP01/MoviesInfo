package com.nihalp01.movies.UI

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.nihalp01.movies.Network.API.Movies
import com.nihalp01.movies.Network.API.TmdbEndpoints
import com.nihalp01.movies.Network.ServiceBuilder
import com.nihalp01.movies.R
import kotlinx.android.synthetic.main.movie_overview.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDescription : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_overview)

        val request = ServiceBuilder.buildService(TmdbEndpoints::class.java)
        val call = request.getCast(getString(R.string.api_key))

        val intent = intent

        setSupportActionBar(toolbar)
        supportActionBar?.title = intent.getStringExtra("title")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val photo: ImageView = findViewById(R.id.movie_poster)
        val poster = intent.getStringExtra("movie_poster")

        movie_overview.text = intent.getStringExtra("movie_details")
        Glide.with(this).load("https://image.tmdb.org/t/p/w500$poster").into(photo)

        call.enqueue(object: Callback<Movies>{
            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Toast.makeText(this@MovieDescription, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.isSuccessful){
                    my_recycler?.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(context, 2)
                        adapter = CastAdapter(response.body()!!.MovieCastList)
                    }
                }
            }

        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }
}