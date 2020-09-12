package com.nihalp01.moviesInfo.UI.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nihalp01.moviesInfo.Network.API.Movies
import com.nihalp01.moviesInfo.Network.API.TmdbEndpoints
import com.nihalp01.moviesInfo.Network.ServiceBuilder
import com.nihalp01.moviesInfo.R
import com.nihalp01.moviesInfo.Adapters.MoviesAdapter
import kotlinx.android.synthetic.main.fragment_upcoming.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentUpcoming: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_upcoming, container, false)
        val request = ServiceBuilder.buildService(TmdbEndpoints::class.java)
        val call = request.getUpcoming(getString(R.string.api_key))

        call.enqueue(object : Callback<Movies> {
            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.isSuccessful){
                    my_progressbar_upcoming?.visibility = View.GONE
                    recyclerView_upcoming?.apply {
                        setHasFixedSize(true)
                        adapter = MoviesAdapter(
                            context,
                            response.body()!!.results
                        )
                        layoutManager = LinearLayoutManager(context)
                    }
                }
            }
        })
        return view
    }
}