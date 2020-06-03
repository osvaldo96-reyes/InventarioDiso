package com.inventario.diso.view.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.inventario.diso.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBar(findViewById(R.id.toolbarMain))

        val title = findViewById<View>(R.id.homeTitle1) as TextView
        title.text = "Principal"

        val bnv_menu =
            findViewById<View>(R.id.bnvMenu) as BottomNavigationView
        bnv_menu.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navScheduleFragment -> {
                }
                R.id.navHomeFragment -> {
                    val a = Intent(this@MainActivity, ActivityOne::class.java)
                    startActivity(a)
                }
                R.id.navSpeakersFragment -> {
                    val b = Intent(this@MainActivity, ActivityTow::class.java)
                    startActivity(b)
                }
            }
            false
        }


    }






}
