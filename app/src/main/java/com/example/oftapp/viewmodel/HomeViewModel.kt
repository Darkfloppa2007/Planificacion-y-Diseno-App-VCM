package com.example.oftapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.oftapp.model.HomeItem

class HomeViewModel : ViewModel() {

    private val _items = mutableStateListOf<HomeItem>()
    val items: List<HomeItem> get() = _items

    init {
        _items.addAll(
            listOf(
            )
        )
    }

    fun agregarItem() {
        val nuevoId = (_items.maxOfOrNull { it.id } ?: 0) + 1
        val nuevoItem = HomeItem(
            id = nuevoId,
            titulo = "Evaluación Oftalmológica #$nuevoId",
            descripcion = "Control rutinario de rutina y evaluación de córnea.",
            fecha = "Hoy"
        )
        _items.add(nuevoItem)
    }
}