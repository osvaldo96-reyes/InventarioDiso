package com.inventario.diso.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inventario.diso.model.Conference


class ScheduleViewModel: ViewModel() {
    var listSchedule: MutableLiveData<List<Conference>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()




    fun processFinished() {
        isLoading.value = true
    }
}