package com.fedlo.tom.base.data

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

val BASE_URL = "http://jadosa.info/"

interface APIInterface {
    @GET("pokte.php")
    fun doGetListResources():Call<String>

    @GET("pokte.php")
     fun loadData(): Call<ResponseBody>
}