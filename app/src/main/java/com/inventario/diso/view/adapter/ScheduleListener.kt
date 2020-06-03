package com.inventario.diso.view.adapter

import com.inventario.diso.model.Conference

interface ScheduleListener {
    fun onConferenceClicked(conference: Conference, position: Int)
}