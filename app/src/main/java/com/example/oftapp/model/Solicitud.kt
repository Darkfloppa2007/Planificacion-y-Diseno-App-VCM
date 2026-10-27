package com.example.oftapp.model
data class Solicitud(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val estado: String = "Pendiente",
    val imageUri: String? = null
)