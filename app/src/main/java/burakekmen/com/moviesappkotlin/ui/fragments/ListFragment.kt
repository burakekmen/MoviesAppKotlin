package burakekmen.com.moviesappkotlin.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import burakekmen.com.moviesappkotlin.R
import burakekmen.com.moviesappkotlin.adapters.RcListAdapter
import burakekmen.com.moviesappkotlin.models.DiscoverModel
import burakekmen.com.moviesappkotlin.network.ApiClient
import burakekmen.com.moviesappkotlin.network.ApiInterface
import com.kaopiz.kprogresshud.KProgressHUD
import kotlinx.android.synthetic.main.custom_action_bar_layout.*
import kotlinx.android.synthetic.main.fragment_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListFragment : Fragment() {

    private var fLayout: View? = null
    private var discoverList: DiscoverModel? = null
    private var apiInterface: ApiInterface? = null
    private var layoutManager: GridLayoutManager? = null
    private val COLUMN_NUM = 1
    private var isLoading = false
    private val dahaFazla = true
    private var adapter: RcListAdapter? = null
    private var pageList:ArrayList<Int>?=null

    private var requestDialog: KProgressHUD? = null
    private var ilkYuklemeMi = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (fLayout == null)
            fLayout = inflater.inflate(R.layout.fragment_list, container, false)

        init()
        getDiscoverMoview()

        return  fLayout
    }


    private fun init() {

        activity?.custom_action_bar_title?.text = getString(R.string.list_fragment_title)
        pageList = ArrayList()

        apiInterface = ApiClient.client?.create(ApiInterface::class.java)

        layoutManager = GridLayoutManager(activity, COLUMN_NUM)
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

        ilkYuklemeMi = false

        if (requestDialog != null) {
            if (requestDialog!!.isShowing) {
                requestDialog!!.dismiss()
                requestDialog = null
            }
        }
    }


    private fun listeyeGonder() {

        if(ilkYuklemeMi) {
            adapter = RcListAdapter(context, discoverList?.getResults())
            fragment_list_rcList?.adapter = adapter
            fragment_list_rcList?.setHasFixedSize(true)

            fragment_list_rcList?.layoutManager = layoutManager

            fragment_list_rcList?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    var toplamResim = layoutManager?.itemCount
                    var sonResimPosition = layoutManager?.findLastVisibleItemPosition()
                    if (dahaFazla && !isLoading && (toplamResim?.minus(1)) != sonResimPosition) {
                        getDiscoverMoview()
                    }
                }
            })
        }
        else {
            adapter!!.addAll(discoverList!!.getResults()!!)
            adapter!!.notifyDataSetChanged()
            fragment_list_rcList?.setHasFixedSize(true)
        }

        isLoading = false

    }

    private fun getDiscoverMoview(){

        if (ilkYuklemeMi)
            dialogGoster()

        isLoading = true
        var toplamResim = layoutManager!!.itemCount
        var page = toplamResim.div(20).plus(1)

        while(page <5){
            if(pageList!!.isEmpty()){
                pageList!!.add(page)
                break
            }else{
                for(i in 0 until pageList!!.size){
                    if(page == pageList!![i]){
                        page++
                        pageList!!.add(page)
                        break
                    }
                }
                break
            }
        }

        if(page<5){
            apiInterface?.getDiscoverLists(getString(R.string.api_key), page)?.enqueue(object : Callback<DiscoverModel> {

                override fun onResponse(call: Call<DiscoverModel>?, response: Response<DiscoverModel>?) {

                    if (response!!.isSuccessful) {
                        discoverList = DiscoverModel()
                        discoverList!!.getResults()?.addAll(response.body()!!.getResults()!!)
                        listeyeGonder()
                        dialogGosterme()
                    } else {
                        Toast.makeText(context, "Bir şeyler Ters Gitti", Toast.LENGTH_SHORT).show()
                        dialogGosterme()
                    }
                }

                override fun onFailure(call: Call<DiscoverModel>?, t: Throwable?) {

                    Log.e("BASARISIZ", t?.message)
                    dialogGosterme()
                }

            })
        }

    }

}
