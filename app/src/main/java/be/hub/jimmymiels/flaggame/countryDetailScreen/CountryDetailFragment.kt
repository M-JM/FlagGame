package be.hub.jimmymiels.flaggame.countryDetailScreen

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import be.hub.jimmymiels.flaggame.R
import be.hub.jimmymiels.flaggame.databinding.CountryDetailFragmentBinding

class CountryDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val binding = CountryDetailFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        val countryproperties = CountryDetailFragmentArgs.fromBundle(arguments!!).countryProperties
        val viewModelFactory = CountryDetailViewModelFactory(countryproperties,application)
        binding.viewModel = ViewModelProviders.of(
            this, viewModelFactory).get(CountryDetailViewModel::class.java)
        return binding.root
    }

}
