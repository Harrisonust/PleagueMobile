package com.example.gamechangermobile.network

import android.content.Context
import org.chromium.net.CronetEngine
import org.chromium.net.UrlRequest
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class Network {
    companion object {
        fun loadData(context: Context, path: String, queryParams: Map<String, String>, urlRequestCallback: UrlRequestCallback) {
            val myBuilder = CronetEngine.Builder(context)
            val cronetEngine: CronetEngine = myBuilder.build()
            val executor: Executor = Executors.newSingleThreadExecutor()

            val requestBuilder =
                cronetEngine.newUrlRequestBuilder(
                    Api.url(path, queryParams),
                    urlRequestCallback,
                    executor
                )
            val request: UrlRequest = requestBuilder.build()
            request.start()
        }
    }

}