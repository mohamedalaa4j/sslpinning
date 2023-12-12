package com.example.sslpinning

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var tv: TextView
    private lateinit var tv2: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv = findViewById<TextView>(R.id.tv)
        tv2 = findViewById<TextView>(R.id.tv2)
        progressBar = findViewById<ProgressBar>(R.id.progressBar)

        callJsonPlaceHolder()
//        callAmtalek()

    }

    private fun callJsonPlaceHolder() {
        progressBar.visibility = View.VISIBLE

        val certificatePinner = CertificatePinner.Builder()
            .add("jsonplaceholder.typicode.com", "sha256/JCmeBpzLgXemYfoqqEoVJlU/givddwcfIXpwyaBk52I=")
            .build()

        val okHttpClient = OkHttpClient.Builder()
//            .certificatePinner(certificatePinner)
            // Add any other OkHttpClient configurations as needed
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        GlobalScope.launch {
            val call = apiService.fetchData()

            try {
                Log.e("network", call.body()?.body.toString())
                withContext(Dispatchers.Main) {
                    tv.text = "Response\n" +  call.body()?.body.toString()
                    tv2.text = "Connection Success"
                    progressBar.visibility = View.GONE
                }

            } catch (e: Exception) {
                Log.e("network", e.toString())
                withContext(Dispatchers.Main) {
                    tv.text =  "Response\n" + e.toString()
                    tv2.text = "Connection Failed"
                    progressBar.visibility = View.GONE
                }
            }
        }

    }

    private fun callAmtalek() {
        val certificatePinner = CertificatePinner.Builder()
            .add("amtalek.com", "sha256/KZz5PR4GnwfX9vpcizpjR+LgwK/eGu6dQJHR0lXlN+k=")
            .build()

        val okHttpClient = OkHttpClient.Builder()
//            .certificatePinner(certificatePinner)
            // Add any other OkHttpClient configurations as needed
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://amtalek.com/amtalekadmin/public/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        GlobalScope.launch {
            val call = apiService.amtalek()

            try {
                Log.e("network", call.body()?.toString().toString())
                withContext(Dispatchers.Main) {
                    tv.text = call.body()?.toString().toString()
                }

            } catch (e: Exception) {
                Log.e("network", e.toString())
                withContext(Dispatchers.Main) {
                    tv.text = e.toString()
                }
            }
        }

    }
}