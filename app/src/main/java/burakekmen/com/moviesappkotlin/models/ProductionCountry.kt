package burakekmen.com.moviesappkotlin.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/****************************
 * Created by Burak EKMEN   |
 * 15.08.2018               |
 * ekmen.burak@hotmail.com  |
 ***************************/
class ProductionCountry {

    @SerializedName("iso_3166_1")
    @Expose
    private var iso31661: String? = null
    @SerializedName("name")
    @Expose
    private var name: String? = null

    fun getIso31661(): String? {
        return iso31661
    }

    fun setIso31661(iso31661: String) {
        this.iso31661 = iso31661
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }
}