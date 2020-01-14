package be.hub.jimmymiels.flaggame.countryScreen


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

import be.hub.jimmymiels.flaggame.databinding.FragmentCountryListBinding
import be.hub.jimmymiels.flaggame.gameScreen.GameViewModel

class CountryListFragment : Fragment() {

private val viewModel: GameViewModel by lazy {
    ViewModelProviders.of(this).get(GameViewModel::class.java)
}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
val binding = FragmentCountryListBinding.inflate(inflater)

        binding.setLifecycleOwner(this)
        binding.gameViewModel = viewModel
        binding.photosGrid.adapter = PhotoGridAdapter()

        return binding.root

    }


}
