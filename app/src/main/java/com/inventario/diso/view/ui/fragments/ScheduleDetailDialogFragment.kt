package com.inventario.diso.view.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment

import androidx.fragment.app.DialogFragment

import com.inventario.diso.R

/**
 * A simple [Fragment] subclass.
 */
class ScheduleDetailDialogFragment : DialogFragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }



}
