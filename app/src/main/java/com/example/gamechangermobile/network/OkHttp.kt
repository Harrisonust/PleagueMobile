package com.example.gamechangermobile.network

import okhttp3.*
import java.io.IOException

class OkHttp {
    private val client = OkHttpClient()
    var delegate: OnSuccessResponse? = null

    constructor(onSuccessResponse: OnSuccessResponse) {
        delegate = onSuccessResponse
    }

    interface OnSuccessResponse {
        fun action(result: String?)
    }

    fun getRequest(path: String, queryParams: Map<String, String>, source: String) {
        val request = Request.Builder()
            .url(Api.url(path, queryParams, source))
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    delegate?.action(response.body?.string())
                }
            }
        })
    }
}