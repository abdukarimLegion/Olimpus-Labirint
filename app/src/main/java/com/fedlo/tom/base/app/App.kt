package com.fedlo.tom.base.app

import android.app.Application
import android.util.Log
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.appsflyer.AppsFlyerLibCore
import com.fedlo.tom.base.utils.PrefManager
import com.onesignal.OneSignal

import com.fedlo.tom.base.data.NetworkModule
import com.fedlo.tom.base.data.Prefs


class App : Application() {


    override fun onCreate() {
        super.onCreate()
        NetworkModule.init()
        Prefs.init(applicationContext)
        PrefManager.init(this)
        Prefs.init(applicationContext)
        initAppsFlyer()
        initOneSignal()
    }
    val ONESIGNAL_APP_ID = "3d9371e6-ed76-45a4-9fd9-505b29087af6"

    fun initOneSignal(){
        // Logging set to help debug issues, remove before releasing your app.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
//        OneSignal.sendTag("af_id", AppsFlyerLib.getInstance().getAppsFlyerUID(this))
    }

    private fun initAppsFlyer() {
        val conversionDataListener = object : AppsFlyerConversionListener {
            override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {
                data?.let { cvData ->
                    cvData.map {
                        Log.i(AppsFlyerLibCore.LOG_TAG, "conversion_attribute:  ${it.key} = ${it.value}")
                    }
                }
            }

            override fun onConversionDataFail(error: String?) {
                Log.e(AppsFlyerLibCore.LOG_TAG, "error onAttributionFailure :  $error")
            }

            override fun onAppOpenAttribution(data: MutableMap<String, String>?) {
                data?.map {
                    Log.d(AppsFlyerLibCore.LOG_TAG, "onAppOpen_attribute: ${it.key} = ${it.value}")
                }
            }

            override fun onAttributionFailure(error: String?) {
                Log.e(AppsFlyerLibCore.LOG_TAG, "error onAttributionFailure :  $error")
            }
        }

        AppsFlyerLib.getInstance().init(AF_DEV_KEY, conversionDataListener, this)
        AppsFlyerLib.getInstance().start(this)
    }

    private companion object {
        const val AF_DEV_KEY = "oE6HaRmct5nbBEo9HfXFnL"
    }
}