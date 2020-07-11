package com.nihalp01.movies.UI.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nihalp01.movies.Network.API.PopularMovies
import com.nihalp01.movies.Network.API.TmdbEndpoints
import com.nihalp01.movies.Network.ServiceBuilder
import com.nihalp01.movies.R
import com.nihalp01.movies.UI.MoviesAdapter
import kotlinx.android.synthetic.main.fragment_popular.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentTopRated: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_top_rated, container, false)
        val request = ServiceBuilder.buildService(TmdbEndpoints::class.java)
        val call = request.getTopRated(getString(R.string.api_key))

        call.enqueue(object : Callback<PopularMovies> {
            override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
                if (response.isSuccessful) {
                    myProgressbar?.visibility = View.GONE
                    recyclerView?.layoutManager = LinearLayoutManager(context)
                    recyclerView?.adapter =
                        context?.let { MoviesAdapter(it, response.body()!!.results) }
                }
            }
        })
        return view
        //return inflater.inflate(R.layout.fragment_top_rated, container, false)
    }
}