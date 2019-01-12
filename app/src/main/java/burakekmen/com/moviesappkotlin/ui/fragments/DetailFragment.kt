package burakekmen.com.moviesappkotlin.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import burakekmen.com.moviesappkotlin.R
import burakekmen.com.moviesappkotlin.models.Genre
import burakekmen.com.moviesappkotlin.models.MovieDetailModel
import burakekmen.com.moviesappkotlin.models.ResultModel
import burakekmen.com.moviesappkotlin.network.ApiClient
import burakekmen.com.moviesappkotlin.network.ApiInterface
import com.kaopiz.kprogresshud.KProgressHUD
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_action_bar_layout.*
import kotlinx.android.synthetic.main.fragment_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.*


class DetailFragment : Fragment(){

    private var movie: ResultModel? = null
    private var fLayout: View? = null
    private var movieDetail: MovieDetailModel? = null
    private var apiInterface: ApiInterface? = null
    private var requestDialog: KProgressHUD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (fLayout == null)
            fLayout = inflater.inflate(R.layout.fragment_detail, container, false)


        val bundle = this.arguments
        if (bundle != null)
            movie = bundle.getParcelable("MovieDetail")

        init()
        dialogGoster()
        getMovieDetail()

        return  fLayout
    }


    private fun init() {
        apiInterface = ApiClient.client?.create(ApiInterface::class.java)

        setActionBarTitle(movie!!.getTitle())
    }


    private fun setActionBarTitle(movieTitle: String?) {

        activity?.custom_action_bar_title?.text = movieTitle
        activity?.custom_action_bar_back?.visibility = View.VISIBLE
        activity?.custom_action_bar_back?.isEnabled = true

        activity?.custom_action_bar_back?.setOnClickListener{
            activity?.custom_action_bar_back?.visibility = View.INVISIBLE
            activity?.custom_action_bar_back?.isEnabled = false
            fragmentManager!!.popBackStack()
        }
    }


    private fun dialogGoster() {

        if (requestDialog == null) {
            requestDialog = KProgressHUD.create(context!!)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel(getString(R.string.dialog_label))
                    .setDetailsLabel(getString(R.string.dialog_detail_label))
                    .setCancellable(true)
                    .setAnimationSpeed(2)
                    .setDimAmount(0.5f)
                    .show()
        }

    }

    private fun dialogGosterme() {
        if (requestDialog != null) {
            if (requestDialog!!.isShowing()) {
                requestDialog!!.dismiss()
                requestDialog = null
            }
        }
    }


    private fun getMovieDetail(){

        apiInterface?.getMovieDetail(movie?.getId(),getString(R.string.api_key))?.enqueue(object : Callback<MovieDetailModel> {

            override fun onResponse(call: Call<MovieDetailModel>?, response: Response<MovieDetailModel>?) {

                if (response!!.isSuccessful) {
                    movieDetail = MovieDetailModel()
                    movieDetail = response.body()

                    bilgileriDoldur()
                    dialogGosterme()
                } else {
                    Toast.makeText(context, "Bir şeyler Ters Gitti", Toast.LENGTH_SHORT).show()
                    dialogGosterme()
                }
            }

            override fun onFailure(call: Call<MovieDetailModel>?, t: Throwable?) {

                Log.e("BASARISIZ", t?.message)
                dialogGosterme()
            }

        })

    }


    private fun bilgileriDoldur() {
        Picasso.get().load(getString(R.string.poster_base_url) + movie!!.getPosterPath()).into(fragment_detail_imgPoster)
        fragment_detail_movie_release_date?.text = movie!!.getReleaseDate()
        fragment_detail_movie_overview?.text = movie!!.getOverview()

        genreCreate(movieDetail!!.getGenres())

        fragment_detail_movie_budget?.text = convertMoney(movieDetail?.getBudget()!!)
        fragment_detail_movie_revenue?.text = convertMoney(movieDetail?.getRevenue()!!)
        fragment_detail_movie_overrate_button?.text = movieDetail?.getVoteAverage().toString() + "%"
    }


    private fun convertMoney(money: Int): String {
        val n = NumberFormat.getCurrencyInstance(Locale.US)
        return n.format(money / 100.0)
    }

    private fun genreCreate(genreList: List<Genre>?) {

        for (i in genreList!!.indices) {
            val llp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            llp.marginEnd = 25

            val textView = TextView(context)
            textView.layoutParams = llp
            textView.setPadding(10, 5, 10, 5)
            textView.setBackgroundResource(R.drawable.textview_style_with_border)
            textView.text = genreList?.get(i)?.getName()

            fragment_detail_horizontal_layout?.addView(textView)

        }

    }





}
