package com.nihalp01.moviesInfo.UI

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.nihalp01.moviesInfo.Network.API.Result
import com.nihalp01.moviesInfo.Network.API.TmdbEndpoints
import com.nihalp01.moviesInfo.Network.API.Trailer
import com.nihalp01.moviesInfo.Network.ServiceBuilder
import com.nihalp01.moviesInfo.R
import kotlinx.android.synthetic.main.youtube_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YoutubePlay : YouTubeBaseActivity() {

    lateinit var data: Result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.youtube_activity)

        val intent = intent
        data = intent.getSerializableExtra("movie_id") as Result

        val request = ServiceBuilder.buildService(TmdbEndpoints::class.java)
        val call = request.getTrailer(data.id, getString(R.string.api_key))

        call.enqueue(object : Callback<Trailer> {
            override fun onFailure(call: Call<Trailer>, t: Throwable) {
                Toast.makeText(this@YoutubePlay, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Trailer>, response: Response<Trailer>) {
                if (response.isSuccessful) {

                    val key = response.body()?.results

                        youtube_player_fragment.initialize("AIzaSyArVdtwrfe_B0xK6UZoDdzy4hTmhBlunnc",
                            object : YouTubePlayer.OnInitializedListener {
                                override fun onInitializationSuccess(
                                    p0: YouTubePlayer.Provider?,
                                    p1: YouTubePlayer?,
                                    p2: Boolean
                                ) {
                                    if (!p2) {
                                        p1!!.loadVideo(key?.get(0)?.key)
                                        p1.setFullscreen(true)
                                        p1.setPlayerStyle(
                                            YouTubePlayer.PlayerStyle.DEFAULT
                                        )
                                        progressBar.visibility = View.GONE
                                    }
                                }

                                override fun onInitializationFailure(
                                    p0: YouTubePlayer.Provider?,
                                    p1: YouTubeInitializationResult?
                                ) {
                                    Toast.makeText(
                                        this@YoutubePlay,
                                        p1.toString(),
                                        Toast.LENGTH_LONG
                                    )
                                        .show()
                                    progressBar.visibility = View.GONE
                                }

                            })
                    }
                }
        })
    }
}

