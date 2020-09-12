package com.nihalp01.moviesInfo.UI.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.nihalp01.moviesInfo.Adapters.MoviesAdapter
import com.nihalp01.moviesInfo.Network.API.Movies
import com.nihalp01.moviesInfo.Network.API.TmdbEndpoints
import com.nihalp01.moviesInfo.Network.ServiceBuilder
import com.nihalp01.moviesInfo.R
import kotlinx.android.synthetic.main.fragment_latest.*
import retrofit2.Callback
import retrofit2.Response

class FragmentLatest : androidx.fragment.app.Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View? = inflater.inflate(R.layout.fragment_latest, container, false)
        val request = ServiceBuilder.buildService(TmdbEndpoints::class.java)
        val call = request.getLatest(getString(R.string.api_key))

        call.enqueue(object : Callback<Movies> {
            override fun onFailure(call: retrofit2.Call<Movies>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: retrofit2.Call<Movies>, response: Response<Movies>) {
                if (response.isSuccessful) {
                    my_progressbar_latest?.visibility = View.GONE
                    recyclerView_latest?.apply {
                        setHasFixedSize(true)
                        adapter = response.body()?.results?.let {
                            MoviesAdapter(
                                context,
                                it
                            )
                        }
                        layoutManager = LinearLayoutManager(context)
                    }
                }
            }

        })
        return view
    }

}