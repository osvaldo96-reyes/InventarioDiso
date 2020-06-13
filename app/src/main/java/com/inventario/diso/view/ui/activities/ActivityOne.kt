package com.inventario.diso.view.ui.activities

import android.app.DownloadManager
import android.content.Intent
import android.os.Bundle
import android.view.View

import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.zxing.integration.android.IntentIntegrator
import com.inventario.diso.R
import kotlinx.android.synthetic.main.activity_one.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.URLEncoder
import kotlin.properties.Delegates

class ActivityOne : AppCompatActivity() {

    //private lateinit var progressBar: ProgressDialog

    private var txtEIdBien by Delegates.notNull<String>()
    private var txtEInventario by Delegates.notNull<String>()
    private var txtESerie by Delegates.notNull<String>()
    private var txtEContrato by Delegates.notNull<String>()
    private var txtEMarca by Delegates.notNull<String>()
    private var txtEModelo by Delegates.notNull<String>()
    private var txtERes by Delegates.notNull<String>()
    private var txtEMateria by Delegates.notNull<String>()
    private var txtEGarantia by Delegates.notNull<String>()
    private var txtEEquip by Delegates.notNull<String>()
    private var txtEAdquisicion by Delegates.notNull<String>()
    private var txtEVigencia by Delegates.notNull<String>()
    private var txtEFolio by Delegates.notNull<String>()
    private var txtEFecRes by Delegates.notNull<String>()
    private var txtEEstatus by Delegates.notNull<String>()
    private var txtEFecReg by Delegates.notNull<String>()
    private var txtEUsReg by Delegates.notNull<String>()
    private var btG by Delegates.notNull<String>()
    private var txtEInmueble by Delegates.notNull<String>()
    private var btF by Delegates.notNull<String>()
    private var btP by Delegates.notNull<String>()
    private var btI by Delegates.notNull<String>()
    private var btS by Delegates.notNull<String>()
    private var txtEPiso by Delegates.notNull<String>()
    private var txtEUbicacion by Delegates.notNull<String>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)
        ocultarItems ()

       //Menu

        val title = findViewById<View>(R.id.activityTitle1) as TextView
        title.text = "Escanear"
        val bnv_menu =
            findViewById<View>(R.id.bnvMenu) as BottomNavigationView
        bnv_menu.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navScheduleFragment -> {
                    val a = Intent(this@ActivityOne, MainActivity::class.java)
                    startActivity(a)
                }
                R.id.navHomeFragment -> {
                }
                R.id.navSpeakersFragment -> {
                    val b = Intent(this@ActivityOne, ActivityTow::class.java)
                    startActivity(b)
                }
            }
            false
        }






        //BARDCODE

        floting.setOnClickListener {
            val scanner = IntentIntegrator (this)
            scanner.initiateScan()
            scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            scanner.setBeepEnabled(false)




        }
    }


    fun webService(idScanner: String){
        Toast.makeText(this, "Resultado: " + idScanner, Toast.LENGTH_LONG).show()
        mostrarItems()

        val idbienedittxt = findViewById<TextView>(R.id.txtE_IdBien)
        val inventarioedittxt = findViewById<TextView>(R.id.txtE_Inventario)

        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.1.70:8888/webservices/ejemplo.php?idbien="+idScanner

        val stringRequest = StringRequest(
            Request.Method.GET,url, Response.Listener{ response ->
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





    //ACTION BARDCODE



    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        val result =
            IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "No se escaneo nada", Toast.LENGTH_LONG).show()
                ocultarItems ()
            } else {
                Toast.makeText(this, "Resultado: " + result.contents, Toast.LENGTH_LONG).show()
                txtE_Inventario.setText(result.contents)
                webService(result.contents)

                if (result.contents ==""){




                }



            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }





    //Funcion mostrar formulario

    fun mostrarItems (){

        txtE_IdBien.visibility=View.VISIBLE
        txtE_Inventario.visibility=View.VISIBLE
        txtE_Serie.visibility=View.VISIBLE
        txtE_Contrato.visibility=View.VISIBLE
        txtE_Marca.visibility=View.VISIBLE
        txtE_Modelo.visibility=View.VISIBLE
        txtE_Responsable.visibility=View.VISIBLE
        txtE_Materia.visibility=View.VISIBLE
        txtE_Equipo.visibility=View.VISIBLE
        txtE_Garantia.visibility=View.VISIBLE
        txtE_Adquisicion.visibility=View.VISIBLE
        txtE_Vigencia.visibility=View.VISIBLE
        txtE_Folio.visibility=View.VISIBLE
        txtE_FecRes.visibility=View.VISIBLE
        txtE_Estatus.visibility=View.VISIBLE
        txtE_FecReg.visibility=View.VISIBLE
        txtE_UsReg.visibility=View.VISIBLE
        txtE_In.visibility=View.VISIBLE
        btnE_Guardar.visibility=View.VISIBLE
        btnE_Frente.visibility=View.VISIBLE
        btnE_Perfil.visibility=View.VISIBLE
        btnE_Inven.visibility=View.VISIBLE
        btnE_Serie.visibility=View.VISIBLE
        spnE_Piso.visibility=View.VISIBLE
        spnE_Ubicacion.visibility=View.VISIBLE


        textView45.visibility=View.VISIBLE
        textView46.visibility=View.VISIBLE
        textView47.visibility=View.VISIBLE
        textView48.visibility=View.VISIBLE
        textView49.visibility=View.VISIBLE
        textView51.visibility=View.VISIBLE
        textView52.visibility=View.VISIBLE
        textView53.visibility=View.VISIBLE
        textView54.visibility=View.VISIBLE
        textView55.visibility=View.VISIBLE
        textView56.visibility=View.VISIBLE
        textView57.visibility=View.VISIBLE
        textView58.visibility=View.VISIBLE
        textView59.visibility=View.VISIBLE
        textView83.visibility=View.VISIBLE
        textView84.visibility=View.VISIBLE
        textView85.visibility=View.VISIBLE
        textView90.visibility=View.VISIBLE
        textView91.visibility=View.VISIBLE
        chkVer.visibility=View.VISIBLE
        textView96.visibility=View.VISIBLE






    }
    //Funcion ocultar formulario

    fun ocultarItems (){

        txtE_IdBien.visibility=View.GONE
        txtE_Inventario.visibility=View.GONE
        txtE_Serie.visibility=View.GONE
        txtE_Contrato.visibility=View.GONE
        txtE_Marca.visibility=View.GONE
        txtE_Modelo.visibility=View.GONE
        txtE_Responsable.visibility=View.GONE
        txtE_Materia.visibility=View.GONE
        txtE_Equipo.visibility=View.GONE
        txtE_Garantia.visibility=View.GONE
        txtE_Adquisicion.visibility=View.GONE
        txtE_Vigencia.visibility=View.GONE
        txtE_Folio.visibility=View.GONE
        txtE_FecRes.visibility=View.GONE
        txtE_Estatus.visibility=View.GONE
        txtE_FecReg.visibility=View.GONE
        txtE_UsReg.visibility=View.GONE
        txtE_In.visibility=View.GONE
        btnE_Guardar.visibility=View.GONE
        btnE_Frente.visibility=View.GONE
        btnE_Perfil.visibility=View.GONE
        btnE_Inven.visibility=View.GONE
        btnE_Serie.visibility=View.GONE
        spnE_Piso.visibility=View.GONE
        spnE_Ubicacion.visibility=View.GONE

        textView45.visibility=View.GONE
        textView46.visibility=View.GONE
        textView47.visibility=View.GONE
        textView48.visibility=View.GONE
        textView49.visibility=View.GONE
        textView51.visibility=View.GONE
        textView52.visibility=View.GONE
        textView53.visibility=View.GONE
        textView54.visibility=View.GONE
        textView55.visibility=View.GONE
        textView56.visibility=View.GONE
        textView57.visibility=View.GONE
        textView58.visibility=View.GONE
        textView59.visibility=View.GONE
        textView83.visibility=View.GONE
        textView84.visibility=View.GONE
        textView85.visibility=View.GONE
        textView90.visibility=View.GONE
        textView91.visibility=View.GONE
        chkVer.visibility=View.GONE
        textView96.visibility=View.GONE
    }

    //Coclo de vida de la Actividad

    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}