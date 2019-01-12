package burakekmen.com.moviesappkotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import burakekmen.com.moviesappkotlin.R

/****************************
 * Created by Burak EKMEN   |
 * 15.08.2018               |
 * ekmen.burak@hotmail.com  |
 ***************************/
class RcListViewHolder(itemView:View?) : RecyclerView.ViewHolder(itemView) {

    var moviePoster = itemView?.findViewById<ImageView>(R.id.rcList_item_Movie_Poster)
    var txtMovieTitle = itemView?.findViewById<TextView>(R.id.rcList_item_Movie_Title)
    var txtMoviewReleaseDate = itemView?.findViewById<TextView>(R.id.rcList_item_Movie_Release_Date)
    var txtMovieOverview = itemView?.findViewById<TextView>(R.id.rcList_item_Movie_Overview)
    var txtMoreInfo = itemView?.findViewById<TextView>(R.id.rcList_item_more_info)
}