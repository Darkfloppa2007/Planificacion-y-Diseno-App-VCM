package com.example.oftapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Assignment
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.oftapp.model.Solicitud
import com.example.oftapp.ui.components.SolicitudCard
import com.example.oftapp.ui.theme.OftAppTheme
import com.example.oftapp.viewmodel.SolicitudViewModel
import kotlinx.coroutines.launch

@Composable
fun SolicitudesScreen(
    viewModel: SolicitudViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    onNavigateToForm: () -> Unit = {}
) {
    val solicitudes = viewModel.solicitud
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    SolicitudesContent(
        solicitudes = solicitudes,
        listState = listState,
        onBackClick = onBackClick,
        onAgregarClick = onNavigateToForm
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SolicitudesContent(
    solicitudes: List<Solicitud>,
    listState: androidx.compose.foundation.lazy.LazyListState,
    onBackClick: () -> Unit,
    onAgregarClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Rounded.Assignment,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Gestión de Solicitudes",
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onAgregarClick,
                icon = { Icon(Icons.Rounded.Add, contentDescription = null) },
                text = { Text(text = "Nueva Solicitud") },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        }
    ) { innerPadding: PaddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Listado de peticiones médicas con descripción y fotografía",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.outline
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (solicitudes.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No hay solicitudes registradas",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            } else {
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(bottom = 80.dp)
                ) {
                    items(solicitudes) { solicitud ->
                        SolicitudCard(solicitud = solicitud)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SolicitudesScreenPreview() {
    OftAppTheme {
        SolicitudesContent(
            solicitudes = listOf(
                Solicitud(1, "Solicitud de Lentes", "Lentes ópticos con medida especial.", "Pendiente", null)
            ),
            listState = rememberLazyListState(),
            onBackClick = {},
            onAgregarClick = {}
        )
    }
}