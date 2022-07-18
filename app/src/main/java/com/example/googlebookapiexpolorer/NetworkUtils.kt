package com.example.googlebookapiexpolorer

import android.net.Uri
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection


class NetworkUtils() {
    private val BOOK_BASE_URL = "https://api.weatherapi.com/v1/forecast.json?"
    private val QUERY_PARAM = "q"
    private val MAX_RESULTS = "maxResults"
    private val PRINT_TYPE = "printType"

    var LOG_TAG:String = NetworkUtils::class.java.simpleName

    fun getBookInfo(queryString:String):String?{
        lateinit var urlConnection: HttpsURLConnection
        lateinit var read:BufferedReader
        lateinit var bookJSON:String

        try{
            var uri:Uri = Uri.parse(BOOK_BASE_URL).buildUpon()
                .appendQueryParameter("key","6091e99104ab428da5c205439221507")
                .appendQueryParameter("q","Dallas")
                .appendQueryParameter("days","5")
                .appendQueryParameter("aqi","no")
                .appendQueryParameter("alerts","no")
                .build()
            var url:URL = URL(uri.toString())

            urlConnection = url.openConnection() as HttpsURLConnection
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
            urlConnection.setRequestProperty("Accept-Charset", "UTF-8")
            urlConnection.doInput = true
            urlConnection.requestMethod = "GET"
            urlConnection.connect()
            val inputStream = urlConnection.inputStream
            read = BufferedReader(InputStreamReader(inputStream))
            val builder = StringBuilder()
            var line: String?
            while (read.readLine().also { line = it } != null) {
                builder.append(line)
                builder.append("\n")
            }
            if (builder.length == 0) {
                return null;
            }
            bookJSON = builder.toString()


        }catch(ex:Exception){
            ex.printStackTrace()
        }finally{
            if(urlConnection != null){
                urlConnection.disconnect()
            }
            if(read != null){
                try{
                    read.close()
                }catch(ex:IOException){
                    ex.printStackTrace()
                }
            }
        }
        Log.d(LOG_TAG, bookJSON);
        return bookJSON
    }
}