package com.inventario.diso.view.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.inventario.diso.R

class ActivityTow : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tow)
        val title = findViewById<View>(R.id.activityTitle2) as TextView
        title.text = "Tercera"

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