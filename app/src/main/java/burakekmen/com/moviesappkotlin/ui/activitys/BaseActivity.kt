package burakekmen.com.moviesappkotlin.ui.activitys

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.View
import burakekmen.com.moviesappkotlin.R
import burakekmen.com.moviesappkotlin.ui.fragments.ListFragment
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.custom_action_bar_layout.*

class BaseActivity : AppCompatActivity() {

    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        acilisAyarlariniYap()

    }


    private fun acilisAyarlariniYap(){

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setCustomView(R.layout.custom_action_bar_layout)

        var fragmentManager = supportFragmentManager
        var fragmentTransaction = fragmentManager.beginTransaction()
        var listFragment = ListFragment()
        fragmentTransaction.add(R.id.activity_base_container, listFragment)
        fragmentTransaction.commit()
    }


    override fun onBackPressed() {
        super.onBackPressed()

        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else if (!doubleBackToExitPressedOnce) {
            custom_action_bar_back?.isEnabled = false
            custom_action_bar_back?.visibility = View.INVISIBLE
            this.doubleBackToExitPressedOnce = true

            val snackbar = Snackbar.make(activity_base_layout!!, "Çıkış için tekrar Geri tuşuna basınız!", Snackbar.LENGTH_SHORT)
            snackbar.show()

            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
        } else {
            super.onBackPressed()
            return
        }
    }

}
