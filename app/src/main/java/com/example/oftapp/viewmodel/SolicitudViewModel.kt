package com.example.oftapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.oftapp.repository.SolicitudRepository
import com.example.oftapp.model.Solicitud


class SolicitudViewModel : ViewModel() {

    private val repository = SolicitudRepository()

    var solicitud by mutableStateOf(repository.obtenerSolicitudes())
        private set

    fun agregarSolicitud() {
        val nuevaSolicitud = Solicitud(
            id = solicitud.size + 1,
            titulo = "Solucitud ${solicitud.size + 1}",
            descripcion = "Agregado desde el ViewModel"
        )

        repository.agregar(nuevaSolicitud)
        solicitud = repository.obtenerSolicitudes()
    }

}

