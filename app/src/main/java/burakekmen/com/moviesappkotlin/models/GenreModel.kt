package burakekmen.com.moviesappkotlin.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/****************************
 * Created by Burak EKMEN   |
 * 15.08.2018               |
 * ekmen.burak@hotmail.com  |
 ***************************/
class GenreModel {

    @SerializedName("genres")
    @Expose
    private var genres: List<Genre>? = null

    fun getGenres(): List<Genre>? {
        return genres
    }

    fun setGenres(genres: List<Genre>) {
        this.genres = genres
    }

}