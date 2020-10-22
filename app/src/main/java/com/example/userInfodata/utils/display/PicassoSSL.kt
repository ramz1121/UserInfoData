package com.example.userInfodata.utils.display

import android.content.Context
import android.net.Uri
import android.util.Log
import com.squareup.picasso.OkHttpDownloader
import com.squareup.picasso.Picasso
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * Picasso and OkHttpclient Implementaion to read images from different sources.
 */
class PicassoSSL private constructor(context: Context) {
    companion object {
        private val TAG = Picasso::class.java.simpleName
        private var mInstance: Picasso? = null
        fun getInstance(context: Context): Picasso? {
            if (mInstance == null) {
                PicassoSSL(context)
            }
            return mInstance
        }
    }

    init {
        val client = com.squareup.okhttp.OkHttpClient()
        client.hostnameVerifier = HostnameVerifier { s, sslSession -> true }
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            @Throws(CertificateException::class)
            override fun checkClientTrusted(
                    x509Certificates: Array<X509Certificate>,
                    s: String) {
            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(
                    x509Certificates: Array<X509Certificate>,
                    s: String) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        })
        try {
            val sc = SSLContext.getInstance("TLS")
            sc.init(null, trustAllCerts, SecureRandom())
            client.sslSocketFactory = sc.socketFactory
        } catch (e: Exception) {
            e.printStackTrace()
        }
        mInstance = Picasso.Builder(context)
                .downloader(OkHttpDownloader(client))
                .listener { picasso: Picasso?, uri: Uri?, exception: Exception -> Log.e(TAG, exception.message) }.build()
    }
}