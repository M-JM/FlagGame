package be.hub.jimmymiels.flaggame.titleScreen


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import be.hub.jimmymiels.flaggame.R
import be.hub.jimmymiels.flaggame.databinding.FragmentTitleBinding
import be.hub.jimmymiels.flaggame.gameScreen.GameViewModel


class TitleFragment : Fragment() {

    private  lateinit var viewModel: GameViewModel
    private lateinit var binding: FragmentTitleBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_title,
            container,
            false
        )
        // Get the viewmodel
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        // Set the viewmodel for databinding - this allows the bound layout access to all of the
        // data in the VieWModel
        binding.gameViewModel = viewModel

        binding.setLifecycleOwner(this)

        return binding.root
    }


}
