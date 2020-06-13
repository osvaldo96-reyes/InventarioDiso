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

        //Metodos

        webService()


    }

    fun webService() {
        val idbienedittxt = findViewById<TextView>(R.id.txtE_IdBien)
        val inventarioedittxt = findViewById<TextView>(R.id.txtE_Inventario)

        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.1.70:8888/webservices/ejemplo.php"

        val data = "Idbien=" + URLEncoder.encode("5651000300000043-1", "UTF-8")


        val stringRequest = StringRequest(Request.Method.POST,url, Response.Listener { response ->
            val jsonArray = JSONArray(response)
            for(i in 0 until jsonArray.length()){
                val jsonObject = JSONObject(jsonArray.getString(i))
                var idbien = jsonObject.get("idbien")
                var codinv = jsonObject.get("codinv")



                idbienedittxt.text = idbien.toString()
                inventarioedittxt.text = codinv.toString()

                //Toast.makeText(applicationContext,text.toString(), Toast.LENGTH_LONG).show()
            }
        },
            Response.ErrorListener {
                idbienedittxt!!.text = "That didn't work!" })

        queue.add(stringRequest)
    }



}