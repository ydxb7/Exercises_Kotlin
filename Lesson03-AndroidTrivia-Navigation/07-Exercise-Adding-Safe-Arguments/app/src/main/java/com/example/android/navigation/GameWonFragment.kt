package com.example.android.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_game_won, container, false)
        binding.nextMatchButton.setOnClickListener { view: View ->
            // TODO (10) Replace action ID with actionGameWonFragmentToGameFragment
            // From GameWonFragmentDirections
//            view.findNavController().navigate(R.id.action_gameWonFragment_to_gameFragment)
            view.findNavController().navigate(GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
        }
        // TODO (08) Add and show toast to get the GameWonFragmentArgs from the arguments Bundle
        // "NumCorrect: ${args.numCorrect}, NumQuestions: ${args.numQuestions}"
        var args = GameWonFragmentArgs.fromBundle(arguments!!)
        Toast.makeText(context,
                "NumCorrect: ${args.numCorrect}, NumQuestions: ${args.numQuestions}",
                Toast.LENGTH_LONG).show()

        return binding.root
    }
}