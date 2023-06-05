package com.fedlo.tom.base.start

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.facebook.FacebookSdk
import com.facebook.applinks.AppLinkData
import com.onesignal.OneSignal
import com.fedlo.tom.base.data.Prefs
import com.fedlo.tom.olimpuslabirint.R
import com.fedlo.tom.olimpuslabirint.ui.MainActivity


val EXTRA_TASK_URL = "extra_task_url"

class ThinkActivity : AppCompatActivity() {

    private var queryString = ""
    lateinit var pref: Prefs
    private val progressBar: View by lazy { findViewById(R.id.progress_bar) }
    private val noInternet: View by lazy { findViewById(R.id.no_internet_container) }
    private val retryBtn: Button by lazy { findViewById(R.id.reload_btn) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_think)
        Prefs.init(this)
        pref = Prefs
        supportActionBar?.hide()

        initListeners()
//        runOnUiThread { loadData() }
        initFb()
    }

    private fun initListeners() {
        retryBtn.setOnClickListener { loadData() }
    }

    private fun initFb() {
        FacebookSdk.setAutoInitEnabled(true)
        FacebookSdk.fullyInitialize()
        loadFbDeepLink()
    }

    private fun loadFbDeepLink() {
        AppLinkData.fetchDeferredAppLinkData(this) {
            fetchLinkData(it?.targetUri?.toString())
        }
    }

    private fun fetchLinkData(link: String?) {
        if (link == null) {
            runOnUiThread { loadData() }
            return
        }
        val data = link.split("//")
        queryString = data[1]
        runOnUiThread { loadData() }
    }

    private fun loadData() {
        var afid: String = ""
        try {
            afid = com.appsflyer.AppsFlyerLib.getInstance().getAppsFlyerUID(applicationContext)
            OneSignal.sendTag("af_id", afid)
            OneSignal.setExternalUserId(afid)
        } catch (e: Exception) {
            //do nothing
        }
        progressBar.isVisible = true
        noInternet.isVisible = false
        startActivity(Intent(this@ThinkActivity, MainActivity::class.java))
        finish()
    }
}