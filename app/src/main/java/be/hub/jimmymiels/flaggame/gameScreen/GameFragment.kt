package be.hub.jimmymiels.flaggame.gameScreen


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
import androidx.navigation.fragment.findNavController
import be.hub.jimmymiels.flaggame.R
import be.hub.jimmymiels.flaggame.databinding.FragmentGameBinding



class GameFragment : Fragment() {

    private lateinit var viewModel: GameViewModel
    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game,
            container,
            false
        )
        // Get the viewmodel
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        // Set the viewmodel for databinding - this allows the bound layout access to all of the
        // data in the VieWModel
        binding.gameViewModel = viewModel

        binding.setLifecycleOwner(this)

        viewModel.eventGameFinish.observe(this, Observer { isFinished ->   if (isFinished) {
            val action = GameFragmentDirections.actionGameFragmentToEndFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }
        })

        binding.endButton.setOnClickListener {
            v: View ->
            v.findNavController().navigate(GameFragmentDirections.actionGameFragmentToEndFragment())
        }

        return binding.root
    }




}
