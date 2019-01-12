package burakekmen.com.moviesappkotlin.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/****************************
 * Created by Burak EKMEN   |
 * 15.08.2018               |
 * ekmen.burak@hotmail.com  |
 ***************************/
class SpokenLanguage {

    @SerializedName("iso_639_1")
    @Expose
    private var iso6391: String? = null
    @SerializedName("name")
    @Expose
    private var name: String? = null

    fun getIso6391(): String? {
        return iso6391
    }

    fun setIso6391(iso6391: String) {
        this.iso6391 = iso6391
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }
}