package com.nihalp01.moviesInfo.UI

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.nihalp01.moviesInfo.Adapters.CastAdapter
import com.nihalp01.moviesInfo.Network.API.CastList
import com.nihalp01.moviesInfo.Network.API.Result
import com.nihalp01.moviesInfo.Network.API.TmdbEndpoints
import com.nihalp01.moviesInfo.Network.ServiceBuilder
import com.nihalp01.moviesInfo.R
import kotlinx.android.synthetic.main.movie_overview.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDescription : AppCompatActivity() {

    lateinit var data: Result

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_overview)

        val request = ServiceBuilder.buildService(TmdbEndpoints::class.java)
        val intent = intent
        data = intent.getSerializableExtra("result") as Result

        setSupportActionBar(toolbar)
        supportActionBar?.title = data.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btn_play.setOnClickListener {
             myIntent()
        }

        val photo: ImageView = findViewById(R.id.movie_poster)
        val poster = data.poster_path
        val call = request.getCast(data.id, getString(R.string.api_key))

        val popularity = " %.2f".format(data.popularity).toDouble()
        Log.d("myTag", popularity.toString())

        movie_overview.text = data.overview
        movie_popularity.text = "$popularity %"
        votes.text = data.vote_count.toString()+" ❤"
        rating.text = data.vote_average.toString()+" ★"

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

  fun myIntent(){
      val p = Intent(this, YoutubePlay::class.java)
      val bundle = Bundle()
      bundle.putSerializable("movie_id", data)
      p.putExtra("force_fullscreen", true)
      p.putExtras(bundle)
      startActivity(p)
  }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }
}