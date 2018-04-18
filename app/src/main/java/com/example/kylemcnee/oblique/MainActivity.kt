package com.example.kylemcnee.oblique

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val text: TextView = findViewById(R.id.textView)
        val button = findViewById<Button>(R.id.button)

        fetchCard()

        button.setOnClickListener{
            fetchCard()
        }



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

                val gson = GsonBuilder().create()
                val obliqueCard = gson.fromJson(jsonBody, ObliqueCard::class.java)

                val text: TextView = findViewById(R.id.textView)


                this@MainActivity.runOnUiThread(java.lang.Runnable {
                    text.text = obliqueCard.strategy
                })


            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to fetch card")
            }

        })
    }

}


class ObliqueCard(val strategy: String)


