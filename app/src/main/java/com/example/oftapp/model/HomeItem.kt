package com.example.oftapp.model

data class HomeItem(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val fecha: String,
    val estado: String = "Pendiente"
)