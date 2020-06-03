package com.inventario.diso.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inventario.diso.model.Speaker
import com.inventario.diso.network.Callback

class SpeakersViewModel : ViewModel() {

    var listSpeakers : MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()






    private fun processFinished() {
        isLoading.value = true
    }

}
