package burakekmen.com.moviesappkotlin.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/****************************
 * Created by Burak EKMEN   |
 * 15.08.2018               |
 * ekmen.burak@hotmail.com  |
 ***************************/
class ProductionCompany {

    @SerializedName("id")
    @Expose
    private var id: Int? = null
    @SerializedName("logo_path")
    @Expose
    private var logoPath: String? = null
    @SerializedName("name")
    @Expose
    private var name: String? = null
    @SerializedName("origin_country")
    @Expose
    private var originCountry: String? = null

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getLogoPath(): String? {
        return logoPath
    }

    fun setLogoPath(logoPath: String) {
        this.logoPath = logoPath
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getOriginCountry(): String? {
        return originCountry
    }

    fun setOriginCountry(originCountry: String) {
        this.originCountry = originCountry
    }
}