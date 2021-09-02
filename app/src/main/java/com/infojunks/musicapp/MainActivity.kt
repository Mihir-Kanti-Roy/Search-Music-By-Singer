package com.infojunks.musicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun searchIt(view: android.view.View) {
        val singer=findViewById<EditText>(R.id.editText)
        val s=singer.editableText.toString()
        val new=URLEncoder.encode(s, "utf-8")
        val newUrl="https://itunes.apple.com/search?term=$new"
        val texts=findViewById<TextView>(R.id.textView)
        val queue = Volley.newRequestQueue(this)


        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, newUrl, null,
            { response ->
                val jsonArray=response.getJSONArray("results")
                for (i in 0 until jsonArray.length()) {
                    val item = jsonArray.getJSONObject(i)
                    texts.append(item.getString("trackName")+"-------")
                 }
                },
            { error ->
                texts.text="No internet!"
            }
        )
        queue.add(jsonObjectRequest)

    }

    fun clearIt(view: android.view.View) {
        val texts=findViewById<TextView>(R.id.textView)
        texts.text = ""
    }


}






