package be.hub.jimmymiels.flaggame.countryScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import be.hub.jimmymiels.flaggame.apiCountry.CountryProperties
import be.hub.jimmymiels.flaggame.databinding.GridViewItemBinding
import be.hub.jimmymiels.flaggame.generated.callback.OnClickListener

class PhotoGridAdapter(val onClickListener: OnClickListener) : ListAdapter<CountryProperties, PhotoGridAdapter.CountryPropertiesViewHolder>(DiffCallback) {
    class CountryPropertiesViewHolder(private var binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root){
fun bind(countryProperties: CountryProperties) {
    binding.countries = countryProperties
    binding.executePendingBindings()
}
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CountryProperties>(){
        override fun areItemsTheSame(
            oldItem: CountryProperties,
            newItem: CountryProperties
        ): Boolean {
    return oldItem === newItem       }

        override fun areContentsTheSame(
            oldItem: CountryProperties,
            newItem: CountryProperties
        ): Boolean {
            return oldItem.name == newItem.name
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryPropertiesViewHolder {
return CountryPropertiesViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(
        holder: CountryPropertiesViewHolder,
        position: Int
    ) {
       val countryProperties = getItem(position)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(countryProperties)
        }
        holder.bind(countryProperties)
    }

    class OnClickListener(val clickListener: (countryProperties: CountryProperties) -> Unit) {
        fun onClick(countryProperties: CountryProperties) = clickListener(countryProperties)
    }

}