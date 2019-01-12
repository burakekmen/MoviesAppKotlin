package burakekmen.com.moviesappkotlin.adapters

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import burakekmen.com.moviesappkotlin.R
import burakekmen.com.moviesappkotlin.models.ResultModel
import burakekmen.com.moviesappkotlin.ui.fragments.DetailFragment
import com.squareup.picasso.Picasso

/****************************
 * Created by Burak EKMEN   |
 * 15.08.2018               |
 * ekmen.burak@hotmail.com  |
 ***************************/
class RcListAdapter(context: Context?, filmListesi:ArrayList<ResultModel>?): RecyclerView.Adapter<RcListViewHolder>() {

    private var context = context
    private var filmListesi = filmListesi

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RcListViewHolder {
        var inflater = LayoutInflater.from(parent?.context).inflate(R.layout.rclist_item, parent, false)

        return RcListViewHolder(inflater)
    }



    override fun onBindViewHolder(holder: RcListViewHolder, position: Int) {

        var film = getItem(position)

        Picasso.get().load(context!!.getString(R.string.poster_base_url) + film?.getPosterPath()).into(holder.moviePoster)
        holder.txtMovieTitle!!.text = film?.getTitle()
        holder.txtMoviewReleaseDate!!.text = film?.getReleaseDate()
        holder.txtMovieOverview!!.text = film?.getOverview()

        holder.txtMoreInfo!!.setOnClickListener {
            detaySayfasinaGit(film)
        }

    }


    private fun detaySayfasinaGit(movie: ResultModel?) {
        val fragmentManager = (context as AppCompatActivity).supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val detailFragment = DetailFragment()
        val bundle = Bundle()
        bundle.putParcelable("MovieDetail", movie)
        detailFragment.arguments = bundle

        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.replace(R.id.activity_base_container, detailFragment).commit()
    }


    override fun getItemCount(): Int {
        return filmListesi!!.size
    }

    private fun getItem(position:Int):ResultModel?{
        return filmListesi!![position]
    }

     fun addAll(yeniListe: ArrayList<ResultModel>) {

         if(filmListesi!!.isEmpty())
             filmListesi = yeniListe
         else
            filmListesi!!.addAll(yeniListe)
    }
}