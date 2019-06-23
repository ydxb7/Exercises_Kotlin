package com.example.android.navigation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.android.navigation.databinding.FragmentTitleBinding

// TODO (01) Create the new TitleFragment
// Select File->New->Fragment->Fragment (Blank)

// TODO (02) Clean up the new TitleFragment
// In our new TitleFragment

/**
 * A simple [Fragment] subclass.
 *
 */
class TitleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // TODO (03) Use DataBindingUtil.inflate to inflate and return the titleFragment in onCreateView
        // In our new TitleFragment
        // R.layout.fragment_title
        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_title, container, false)
        return binding.root
    }


}
