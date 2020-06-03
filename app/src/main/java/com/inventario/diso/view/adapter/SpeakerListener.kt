package com.inventario.diso.view.adapter

import com.inventario.diso.model.Speaker

interface SpeakerListener {
    fun onSpeakerClicked(speaker: Speaker, position: Int)
}