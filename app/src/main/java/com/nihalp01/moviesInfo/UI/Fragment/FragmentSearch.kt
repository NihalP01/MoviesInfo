package com.nihalp01.moviesInfo.UI.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nihalp01.moviesInfo.R

class FragmentSearch: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

}