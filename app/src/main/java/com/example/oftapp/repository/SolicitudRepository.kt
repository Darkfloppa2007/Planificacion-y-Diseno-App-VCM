package com.example.oftapp.repository

import com.example.oftapp.model.Solicitud

class SolicitudRepository {

    private val solicitudes = mutableListOf(
        Solicitud(
            id = 1,
            titulo = "Solicitud de Pedido",
            descripcion = "Ejemplo de solicitud y su descripción"
        )
    )

    fun obtenerSolicitudes(): List<Solicitud> {
        return  solicitudes.toList()
    }

    fun agregar(Solicitud: Solicitud) {
        solicitudes.add(Solicitud)
    }

}
