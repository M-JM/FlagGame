package be.hub.jimmymiels.flaggame.countryScreen


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

import be.hub.jimmymiels.flaggame.R
import be.hub.jimmymiels.flaggame.databinding.FragmentCountryListBinding
import be.hub.jimmymiels.flaggame.gameScreen.GameViewModel

/**
 * A simple [Fragment] subclass.
 */
class CountryListFragment : Fragment() {

    private lateinit var viewModel: GameViewModel
    private lateinit var binding: FragmentCountryListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
binding = DataBindingUtil.inflate(
    inflater,
    R.layout.fragment_country_list,
    container,
    false
)
    viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        binding.gameviewModel = viewModel
        binding.setLifecycleOwner(this)

        return binding.root

    }


}
