package be.hub.jimmymiels.flaggame.endScreen


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment

import be.hub.jimmymiels.flaggame.R
import be.hub.jimmymiels.flaggame.database.ScoreDatabase
import be.hub.jimmymiels.flaggame.databinding.FragmentEndBinding


class EndFragment : Fragment() {

    //  private lateinit var viewModel: GameViewModel
    private lateinit var binding: FragmentEndBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_end,
            container,
            false
        )
        // Get the viewmodel
        //  viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        // Set the viewmodel for databinding - this allows the bound layout access to all of the
        // data in the VieWModel

        val application = requireNotNull(this.activity).application
        val dataSource = ScoreDatabase.getInstance(application).scoreDatabaseDao
        val viewModelFactory = EndViewModelFactory(dataSource, application)
        val endViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(EndViewModel::class.java)

        val adaptor = EndScoreAdaptor()
        binding.scoreList.adapter = adaptor

       endViewModel.top10score?.observe(viewLifecycleOwner, Observer {
           it?.let { adaptor.data = it  }
       })

        val args = EndFragmentArgs.fromBundle(arguments!!)
        endViewModel.endscore = args.endScore
        binding.endViewModel = endViewModel
        //binding.gameViewModel = viewModel
        binding.setLifecycleOwner(this)

        // navigate on clicklistener
        binding.backtitleButton.setOnClickListener { v: View ->
            v.findNavController()
                .navigate(EndFragmentDirections.actionEndFragmentToTitleFragment())
        }

        // navigate by observing by change in value in Endviewmodel, bind the function to execute on click
        // conditional statement if , value is True then Navhost navigates.

    endViewModel.eventStartNewGame.observe(this, Observer { isFinished -> if(isFinished)
    {
        val action = EndFragmentDirections.actionEndFragmentToGameFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

    })

        return binding.root


    }


}
