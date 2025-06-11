package com.training.hogaiat.screens

import android.os.Handler
import android.os.Looper
import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

fun sendDataToSheet(
    sheet: String,
    params: Map<String, String>,
    onSuccess: () -> Unit = {},
    onFailure: () -> Unit = {}
) {
    val url =
        "https://script.google.com/macros/s/AKfycbwQi_zyaPWiRPfyosNlinCc8zYWoWU0gTyVC-sJ4Coej2Sne9OrVuJDktmAjh_PcVARVg/exec"
    val formBody = FormBody.Builder().apply {
        add("sheet", sheet)
        for ((key, value) in params) {
            add(key, value)
        }
    }.build()

    val request = Request.Builder()
        .url(url)
        .post(formBody)
        .build()

    val client = OkHttpClient()
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.e("ERROR", "فشل في الإرسال: ${e.message}")
            Handler(Looper.getMainLooper()).post {
                onFailure()
            }
        }

        override fun onResponse(call: Call, response: Response) {
            Log.i("SUCCESS", "تم الإرسال: ${response.body?.string()}")
            Handler(Looper.getMainLooper()).post {
                onSuccess()
            }
        }
    })
}


//fun sendDataToSheet(
//    sheet: String, params: Map<String, String>, onSuccess: () -> Unit = {},
//    onFailure: () -> Unit = {}
//) {
//    val url =
//        "https://script.google.com/macros/s/AKfycbwQi_zyaPWiRPfyosNlinCc8zYWoWU0gTyVC-sJ4Coej2Sne9OrVuJDktmAjh_PcVARVg/exec"
//    val formBody = FormBody.Builder().apply {
//        add("sheet", sheet)
//        for ((key, value) in params) {
//            add(key, value)
//        }
//    }.build()
//
//    val request = Request.Builder()
//        .url(url)
//        .post(formBody)
//        .build()
//
//    val client = OkHttpClient()
//    client.newCall(request).enqueue(object : Callback {
//        override fun onFailure(call: Call, e: IOException) {
//            Log.e("ERROR", "فشل في الإرسال: ${e.message}")
//        }
//
//        override fun onResponse(call: Call, response: Response) {
//            Log.i("SUCCESS", "تم الإرسال: ${response.body?.string()}")
//            onSuccess()
//        }
//    })
//}
