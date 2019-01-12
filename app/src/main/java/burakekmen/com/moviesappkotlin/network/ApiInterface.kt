package burakekmen.com.moviesappkotlin.network

import burakekmen.com.moviesappkotlin.models.DiscoverModel
import burakekmen.com.moviesappkotlin.models.MovieDetailModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/****************************
 * Created by Burak EKMEN   |
 * 15.08.2018               |
 * ekmen.burak@hotmail.com  |
 ***************************/
interface ApiInterface {

    @GET("discover/movie?&language=tr-TR&sort_by=popularity.desc&include_adult=false&include_video=false")
    fun getDiscoverLists(@Query("api_key") apiKey: String, @Query("page") page: Int?): Call<DiscoverModel>

    @GET("movie/{movie_id}?&language=tr-TR")
    fun getMovieDetail(@Path("movie_id") moviId: Int?, @Query("api_key") apiKey: String): Call<MovieDetailModel>

}