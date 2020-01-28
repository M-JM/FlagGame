package be.hub.jimmymiels.flaggame.endScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.hub.jimmymiels.flaggame.R
import be.hub.jimmymiels.flaggame.TextItemViewHolder
import be.hub.jimmymiels.flaggame.database.FinalScore

class EndScoreAdaptor: RecyclerView.Adapter<TextItemViewHolder>(){
    var data = listOf<FinalScore>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var top10 = listOf(1..10)
    override fun getItemCount()= data.size
    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        var item = data[position]
        var count = data.indexOf(item) + 1
        holder.textView.text = """${count}.  ${item.finalScorevalue}"""
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.text_view_item, parent  , false) as TextView
        return TextItemViewHolder(view)


    }

}