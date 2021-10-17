package com.example.newspaper.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.newspaper.R
import kotlinx.android.synthetic.main.activity_article.*

class ArticleActivity : AppCompatActivity() {

    private var myUrl: String? = null
    private var title: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        val intent = intent
        myUrl = intent.getStringExtra("url")
        title = intent.getStringExtra("title")

        supportActionBar?.title =title

        webViewSetup()


    }


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewSetup(){
        webView.webViewClient=WebViewClient()
        webView.apply {
             myUrl?.let { loadUrl(it) }
            settings.javaScriptEnabled=true
            settings.safeBrowsingEnabled=true
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}