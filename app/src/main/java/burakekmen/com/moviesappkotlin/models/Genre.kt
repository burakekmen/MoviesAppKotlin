package burakekmen.com.moviesappkotlin.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/****************************
 * Created by Burak EKMEN   |
 * 15.08.2018               |
 * ekmen.burak@hotmail.com  |
 ***************************/
class Genre {

    @SerializedName("id")
    @Expose
    private var id: Int? = null
    @SerializedName("name")
    @Expose
    private var name: String? = null

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

}