package burakekmen.com.moviesappkotlin.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

/****************************
 * Created by Burak EKMEN   |
 * 15.08.2018               |
 * ekmen.burak@hotmail.com  |
 ***************************/
class DiscoverModel {

    @SerializedName("page")
    @Expose
    private var page: Int? = null
    @SerializedName("total_results")
    @Expose
    private var totalResults: Int? = null
    @SerializedName("total_pages")
    @Expose
    private var totalPages: Int? = null
    @SerializedName("results")
    @Expose
    private var results: ArrayList<ResultModel>? = null

    fun getPage(): Int? {
        return page
    }

    fun setPage(page: Int?) {
        this.page = page
    }

    fun getTotalResults(): Int? {
        return totalResults
    }

    fun setTotalResults(totalResults: Int?) {
        this.totalResults = totalResults
    }

    fun getTotalPages(): Int? {
        return totalPages
    }

    fun setTotalPages(totalPages: Int?) {
        this.totalPages = totalPages
    }

    fun getResults(): ArrayList<ResultModel>? {
        return results
    }

    fun setResults(results: ArrayList<ResultModel>) {
        this.results = results
    }


    init {
        this.results = ArrayList()
    }


}