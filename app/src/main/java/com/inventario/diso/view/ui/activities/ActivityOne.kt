package com.inventario.diso.view.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
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

class ActivityOne : AppCompatActivity() {

    lateinit var idbienedittxt : EditText
    lateinit var inventarioedittxt : EditText
    lateinit var inmuebleedittxt : EditText
    lateinit var nommateriaedittxt : EditText
    lateinit var marcaedittxt : EditText
    lateinit var nompisoedittxt : Spinner
    lateinit var equipoedittxt : EditText
    lateinit var nomubicacionedittxt : EditText
    lateinit var  modeloedittxt : EditText
    lateinit var  serieedittxt : EditText
    lateinit var   ncontratoedittxt : EditText
    lateinit var  garantiaedittxt : EditText
    lateinit var fechaadqedittxt : EditText
    lateinit var  fechavigedittxt : EditText
    lateinit var respbienedittxt : EditText
    lateinit var  folioresgedittxt : EditText
    lateinit var fecharegedittxt : EditText
    lateinit var  estatusedittxt : EditText
    lateinit var debajaedittxt : EditText
    lateinit var fechafolresgedittxt : EditText


    lateinit var  observacionedittxt : EditText
    lateinit var nominmuebleedittxt : EditText
    lateinit var  nombresedittxt : EditText
    lateinit var codinmuebleedittxt : EditText

    lateinit var idbien : Any
    lateinit var codinv  : Any
    lateinit var inmueble  : Any
    lateinit var materia : Any
    lateinit var marca : Any
    lateinit var piso : Any
    lateinit var equipo: Any
    lateinit var modelo: Any
    lateinit var serie: Any
    lateinit var ncontrato: Any
    lateinit var garantia: Any
    lateinit var fechadq: Any
    lateinit var fechavig: Any
    lateinit var responsable: Any
    lateinit var foliores: Any
    lateinit var fechres:  Any
    lateinit var estatus: Any

    val arrayListp = ArrayList<String>()





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


        mostrarItems()
        InicializarComponentes()
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.1.69:8887/webservices/ejemplo.php?idbien="+idScanner
        val stringRequest = StringRequest(
            Request.Method.GET,url, Response.Listener{ response ->


                if (response.toString()!="null"){  // si no encuentra registro


                    val jsonArray = JSONArray(response)

                    for (i in 0 until jsonArray.length()) {

                        val jsonObject = JSONObject(jsonArray.getString(i))
                        idbien = jsonObject.get("idbien")
                        codinv = jsonObject.get("codinv")
                        inmueble = jsonObject.get("nominmueble")
                        materia = jsonObject.get("nommateria")
                        marca = jsonObject.get("marca")
                        piso = jsonObject.get("nompiso")
                        equipo = jsonObject.get("marca")
                        modelo = jsonObject.get("modelo")
                        serie = jsonObject.get("serie")
                        ncontrato = jsonObject.get("ncontrato")
                        garantia = jsonObject.get("garantia")
                        fechadq = jsonObject.get("fechaadq")
                        fechavig = jsonObject.get("fechavig")
                        responsable = jsonObject.get("nombres")
                        foliores = jsonObject.get("folioresg")
                        fechres = jsonObject.get("fechafolresg")
                        estatus = jsonObject.get("estatus")

                        idbienedittxt.setText(idbien.toString())
                        inventarioedittxt.setText(codinv.toString())
                        inmuebleedittxt.setText(inmueble.toString())
                        nommateriaedittxt.setText(materia.toString())
                        marcaedittxt.setText(marca.toString())
                        spPiso(piso.toString())
                        equipoedittxt.setText(equipo.toString())
                        modeloedittxt.setText(modelo.toString())
                        serieedittxt.setText(serie.toString())
                        garantiaedittxt.setText(garantia.toString())
                        ncontratoedittxt.setText(ncontrato.toString())
                        fechaadqedittxt.setText(fechadq.toString())
                        fechavigedittxt.setText(fechavig.toString())
                        respbienedittxt.setText(responsable.toString())
                        fechafolresgedittxt.setText(fechres.toString())
                        folioresgedittxt.setText(foliores.toString())
                        estatusedittxt.setText(estatus.toString())


                        //Toast.makeText(applicationContext,text.toString(), Toast.LENGTH_LONG).show()
                    }

                }else {
                    limpiarCampos()
                    ocultarItems()
                    Toast.makeText(this, "No se encontro registro", Toast.LENGTH_LONG).show()
                }//else validar

        },
            Response.ErrorListener {
                idbienedittxt!!.setText("That didn't work!") })

        queue.add(stringRequest)


    }//metodo web

    fun spPiso(nombrePiso: String){
        var recuperaPiso = 0

        mostrarItems()
        InicializarComponentes()
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.1.69:8887/webservices/spnPiso.php"
        val stringRequest = StringRequest(
            Request.Method.GET,url, Response.Listener{ response ->
                if (response.toString()!="null"){  // si no encuentra registro
                    val jsonArray = JSONArray(response)
                    for (i in 0 until jsonArray.length()) {

                        val jsonObject = JSONObject(jsonArray.getString(i))

                        piso = jsonObject.get("nompiso")

                        arrayListp.add(piso.toString())

                       // nompisoedittxt.setText(piso.toString())


                    }//for

                    nompisoedittxt= findViewById(R.id.spnE_Piso) as Spinner
                    nompisoedittxt.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayListp)


                   //ValidacionSpinner
                    for(i in 0..arrayListp.size-1) {
                        //println("nombre piso recupe $nombrePiso")
                        //println("nombre del piso array "+arrayListp[i])

                        if(nombrePiso==arrayListp[i]){
                            recuperaPiso=i
                            nompisoedittxt.setSelection(recuperaPiso);
                            //println("recupero en el valor "+recuperaPiso)
                        }
                    }


                    nompisoedittxt.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            println("Please Select an Option")
                        }

                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            println(arrayListp.get(position))
                        }
                    }




                }else {
                    limpiarCampos()
                    ocultarItems()
                    Toast.makeText(this, "No se encontro registro", Toast.LENGTH_LONG).show()
                }//else validar

            },
            Response.ErrorListener {
                idbienedittxt!!.setText("That didn't work!") })

        queue.add(stringRequest)


    }

    fun InicializarComponentes (){


        idbienedittxt = findViewById(R.id.txtE_IdBien)
        inventarioedittxt = findViewById(R.id.txtE_Inventario)
        inmuebleedittxt = findViewById(R.id.txtE_In)
        nommateriaedittxt = findViewById(R.id.txtE_Materia)
        marcaedittxt= findViewById(R.id.txtE_Marca)
        nompisoedittxt= findViewById(R.id.spnE_Piso)
        equipoedittxt= findViewById(R.id.txtE_Equipo)
        modeloedittxt= findViewById(R.id.txtE_Modelo)
        serieedittxt= findViewById(R.id.txtE_Serie)
        garantiaedittxt= findViewById(R.id.txtE_Garantia)
        ncontratoedittxt=findViewById(R.id.txtE_Contrato)
        fechaadqedittxt=findViewById(R.id.txtE_Adquisicion)
        fechavigedittxt=findViewById(R.id.txtE_Vigencia)
        respbienedittxt=findViewById(R.id.txtE_Responsable)
        fechaadqedittxt=findViewById(R.id.txtE_Adquisicion)
        fechavigedittxt=findViewById(R.id.txtE_Vigencia)
        respbienedittxt=findViewById(R.id.txtE_Responsable)
        fechafolresgedittxt=findViewById(R.id.txtE_FecRes)
        folioresgedittxt=findViewById(R.id.txtE_Folio)
        estatusedittxt=findViewById(R.id.txtE_Estatus)



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



                    webService(result.contents)
                    txtE_Inventario.setText(result.contents)

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

    fun limpiarCampos(){
        idbienedittxt.setText("")
        inventarioedittxt.setText("")
        inmuebleedittxt.setText("")
        nommateriaedittxt.setText("")
        marcaedittxt.setText("")
        //nompisoedittxt.setText(piso.toString())
        equipoedittxt.setText("")
        modeloedittxt.setText("")
        serieedittxt.setText("")
        garantiaedittxt.setText("")
        ncontratoedittxt.setText("")
        fechaadqedittxt.setText("")
        fechavigedittxt.setText("")
        respbienedittxt.setText("")
        fechafolresgedittxt.setText("")
        folioresgedittxt.setText("")
        estatusedittxt.setText("")
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