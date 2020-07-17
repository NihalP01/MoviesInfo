package com.nihalp01.movies.UI

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.util.Log
import com.nihalp01.movies.Adapters.CastAdapter
import com.nihalp01.movies.Network.API.CastList
import com.nihalp01.movies.Network.API.Result
import com.nihalp01.movies.Network.API.TmdbEndpoints
import com.nihalp01.movies.Network.ServiceBuilder
import com.nihalp01.movies.R
import com.nihalp01.movies.UI.Fragment.YoutubePlay
import kotlinx.android.synthetic.main.movie_overview.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDescription : AppCompatActivity() {

    lateinit var data: Result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_overview)

        val request = ServiceBuilder.buildService(TmdbEndpoints::class.java)
        //val call = request.getCast(getString(R.string.api_key))
        val intent = intent
        data = intent.getSerializableExtra("result") as Result

        setSupportActionBar(toolbar)
        supportActionBar?.title = data.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btn_play.setOnClickListener {
            val p = Intent(this, YoutubePlay::class.java)
            p.putExtra("movieId", data.id)
            startActivity(p)
        }


        val photo: ImageView = findViewById(R.id.movie_poster)
        val poster = data.poster_path
        //val id: String? = intent.getStringExtra("movie_id")
        val call = request.getCast(data.id, getString(R.string.api_key))

        movie_overview.text = data.overview

        Glide.with(this).load("https://image.tmdb.org/t/p/w500$poster").into(photo)

        call.enqueue(object : Callback<CastList> {
            override fun onFailure(call: Call<CastList>, t: Throwable) {
                Toast.makeText(this@MovieDescription, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<CastList>, response: Response<CastList>) {
                if (response.isSuccessful) {
                    cast_progressbar?.visibility = View.GONE
                    my_recycler?.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(context, 2)
                        adapter = CastAdapter(
                            context,
                            response.body()!!.cast
                        )
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