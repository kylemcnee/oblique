package com.example.kylemcnee.oblique

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchCard()
    }

    fun fetchCard() {
        println("Attempting to fetch card")

        val url: String = resources.getString(R.string.URL)

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {

            override fun onResponse(call: Call?, response: Response?) {
                val jsonBody = response?.body()?.string()
                println(jsonBody)
            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to fetch card")
            }

        })
    }
}
