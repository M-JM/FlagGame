package be.hub.jimmymiels.flaggame


import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()

GlideToVectorYou
    .init()
    .with(imgView.context)
    .setPlaceHolder(R.drawable.loading,R.drawable.error)
    .load(imgUri,imgView)
   /*     Glide.with(imgView.context)
        .load(imgUri)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
        )
        .into(imgView)*/

}


}