package be.hub.jimmymiels.flaggame.gameScreen


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
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

                val test = viewModel.score.value!!.toInt()
            val action = GameFragmentDirections.actionGameFragmentToEndFragment(test)


            NavHostFragment.findNavController(this).navigate(action)
        }
        })


        return binding.root
    }




}
