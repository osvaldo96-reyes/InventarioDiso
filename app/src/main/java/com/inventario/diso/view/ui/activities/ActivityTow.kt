package com.inventario.diso.view.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.inventario.diso.R
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class ActivityTow : AppCompatActivity(){


    var urlConnection: HttpURLConnection? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tow)
        //MenuBar
        val title = findViewById<View>(R.id.activityTitle2) as TextView
        title.text = ""

        val bnv_menu = findViewById<View>(R.id.bnvMenu) as BottomNavigationView
        bnv_menu.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navScheduleFragment -> {
                    val a = Intent(this@ActivityTow, MainActivity::class.java)
                    startActivity(a)
                }
                R.id.navHomeFragment -> {
                    val b = Intent(this@ActivityTow, ActivityOne::class.java)
                    startActivity(b)
                }
                R.id.navSpeakersFragment -> {
                }
            }
            false
        }




    }


    }



