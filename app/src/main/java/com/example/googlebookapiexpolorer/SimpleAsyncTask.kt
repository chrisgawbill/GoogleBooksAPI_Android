package com.example.googlebookapiexpolorer

import android.net.Network
import android.os.AsyncTask
import android.util.Log
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONObject
import java.lang.ref.WeakReference

class SimpleAsyncTask(titleText:TextView, authorText:TextView):AsyncTask<String, Void, String>() {
    var titleText:WeakReference<TextView> = WeakReference(titleText)
    var authorText:WeakReference<TextView> = WeakReference(authorText)

    override fun doInBackground(vararg p0: String?): String {
        var network:NetworkUtils = NetworkUtils()
        return network.getBookInfo(p0[0].toString()).toString()
    }

    override fun onPostExecute(result: String?) {
        try{
            var jsonObject:JSONObject = JSONObject(result)
            var jsonItems:JSONArray = jsonObject.getJSONArray("items")

            var i:Int = 0
            lateinit var title:String
            lateinit var authors:String
            while(i < jsonItems.length()){
                var json = jsonItems.getJSONObject(i)
                Log.d("HEELLO THERE", json.toString())
            }
        }catch(ex:Exception){
            ex.printStackTrace()
        }
    }
}