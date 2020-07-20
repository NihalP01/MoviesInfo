package com.nihalp01.movies.UI

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.nihalp01.movies.Network.API.Cast
import com.nihalp01.movies.Network.API.CastDetails
import com.nihalp01.movies.Network.API.TmdbEndpoints
import com.nihalp01.movies.Network.ServiceBuilder
import com.nihalp01.movies.R
import kotlinx.android.synthetic.main.cast_description.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

class CastDescription : AppCompatActivity() {

    lateinit var data: Cast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cast_description)

        val intent = intent
        data = intent.getSerializableExtra("cast_info") as Cast

        val request = ServiceBuilder.buildService(TmdbEndpoints::class.java)

        setSupportActionBar(toolbar_cast)
        supportActionBar?.title = data.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val call = request.getCastDetails(data.id, getString(R.string.api_key))
        call.enqueue(object : Callback<CastDetails> {
            override fun onFailure(call: Call<CastDetails>, t: Throwable) {
                Toast.makeText(this@CastDescription, t.message, Toast.LENGTH_SHORT).show()
            }

            @RequiresApi(Build.VERSION_CODES.O)
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<CastDetails>, response: Response<CastDetails>) {
                if (response.isSuccessful) {
                    info_cast_name.text = "${response.body()?.name}"
                    info_birth_place.text =
                        "Birth Place: ${response.body()?.place_of_birth.toString()}"
                    info_birthday.text = "Birth date: ${response.body()?.birthday.toString()}"
                    info_knownfor.text = "Known for: ${response.body()?.known_for_department}"
                    info_popularity.text = "Popularity: ${response.body()?.popularity.toString()}"
                    Glide.with(this@CastDescription)
                        .load("https://image.tmdb.org/t/p/w500${response.body()?.profile_path}")
                        .into(info_cast_image)
                    if(response.body()?.deathday !== null){
                        info_deathday.text = "Death date: ${response.body()?.deathday.toString()}"
                    }
                    else{
                        info_deathday.visibility = View.GONE
                    }
                    if (response.body()?.biography != null){
                        info_biography.text = response.body()?.biography
                        Log.d("myTag", "${response.body()?.biography}")
                    }
                    else{
                        info_biography.text = "Bio details are not available"
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

