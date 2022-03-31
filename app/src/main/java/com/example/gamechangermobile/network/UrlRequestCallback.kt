package com.example.gamechangermobile.network

import android.util.Log
import org.chromium.net.CronetException
import org.chromium.net.UrlRequest
import org.chromium.net.UrlResponseInfo
import java.nio.ByteBuffer
import java.nio.charset.Charset



class UrlRequestCallback(): UrlRequest.Callback() {
    private val TAG = "Network Debug"
    private val buffer = ByteBuffer.allocateDirect(102400)
    private var response = ""
    var delegate: OnFinishRequest? = null

    interface OnFinishRequest {
        fun onFinishRequest(result: String?)
    }

    constructor (onFinishRequest: OnFinishRequest?): this() {
        //You should create a MyUrlRequestCallback.OnFinishRequest() and
        //override onFinishRequest.
        //We will send JSON String response to this interface and you can then
        //perform actions on the UI or otherwise based on the result.

        //All MyUrlRequestCallback functions send response to this.delegate
        //which provides it to the interface onFinishRequest which you use in
        //your activity or fragment.
        delegate = onFinishRequest
    }

    override fun onRedirectReceived(
        request: UrlRequest?,
        info: UrlResponseInfo?,
        newLocationUrl: String?
    ) {
        Log.i(TAG, "onRedirectReceived method called.")
        // You should call the request.followRedirect() method to continue
        // processing the request.
        request?.followRedirect()
    }

    override fun onResponseStarted(request: UrlRequest?, info: UrlResponseInfo?) {
        Log.i(TAG, "onResponseStarted method called.")
        // You should call the request.read() method before the request can be
        // further processed. The following instruction provides a ByteBuffer object
        // with a capacity of 102400 bytes to the read() method.
        request?.read(buffer)
    }

    override fun onReadCompleted(
        request: UrlRequest?,
        info: UrlResponseInfo?,
        byteBuffer: ByteBuffer?
    ) {
        Log.i(TAG, "onReadCompleted method called.")

        buffer?.flip()
        // Convert the byte buffer to a string
        buffer?.let {
            val byteArray = ByteArray(it.remaining())
            it.get(byteArray)
            String(byteArray, Charset.forName("UTF-8"))
        }.apply {
            Log.i(TAG, "Response: $this")
            response += this
        }

        // Clear the buffer
        buffer?.clear()
        request?.read(buffer)
    }

    override fun onSucceeded(request: UrlRequest?, info: UrlResponseInfo?) {
        Log.i(TAG, "onSucceeded method called.")
        Log.i(TAG, response)
        delegate?.onFinishRequest(response)
    }

    override fun onFailed(request: UrlRequest?, info: UrlResponseInfo?, error: CronetException?) {
        Log.i(TAG, "onFailed method called.")
    }

}