package com.example.oftapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Assignment
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.HourglassTop
import androidx.compose.material.icons.rounded.LocalHospital
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.oftapp.model.HomeItem
import com.example.oftapp.ui.components.HomeCard
import com.example.oftapp.ui.theme.OftAppTheme
import com.example.oftapp.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onNavigateToLogin: () -> Unit = {},
    onNavigateToSolicitudes: () -> Unit = {}
) {
    val items = viewModel.items
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    HomeContent(
        items = items,
        listState = listState,
        onAgregarClick = {
            viewModel.agregarItem()
            if (items.isNotEmpty()) {
                coroutineScope.launch {
                    listState.animateScrollToItem(items.size - 1)
                }
            }
        },
        onLoginClick = onNavigateToLogin,
        onSolicitudesClick = onNavigateToSolicitudes
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    items: List<HomeItem>,
    listState: androidx.compose.foundation.lazy.LazyListState,
    onAgregarClick: () -> Unit,
    onLoginClick: () -> Unit,
    onSolicitudesClick: () -> Unit
) {
    val totalCreadas = items.size
    val pendientes = items.count { it.estado.equals("Pendiente", ignoreCase = true) }
    val completadas = items.count { it.estado.equals("Completado", ignoreCase = true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Rounded.LocalHospital,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "OftApp Clínica",
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onLoginClick) {
                        Icon(
                            imageVector = Icons.Rounded.Logout,
                            contentDescription = "Ir a Login",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
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
                text = { Text(text = "Nueva Evaluación") },
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StatCard(
                    title = "Totales",
                    count = totalCreadas,
                    icon = Icons.Rounded.Assignment,
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    title = "Pendientes",
                    count = pendientes,
                    icon = Icons.Rounded.HourglassTop,
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    title = "Completadas",
                    count = completadas,
                    icon = Icons.Rounded.CheckCircle,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onSolicitudesClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Assignment,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Ir a Gestión de Solicitudes",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Historial de Evaluaciones y Consultas",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.outline
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (items.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No hay registros recientes",
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
                    items(items) { item ->
                        HomeCard(item = item)
                    }
                }
            }
        }
    }
}
@Composable
fun StatCard(
    title: String,
    count: Int,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = count.toString(),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.outline
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    OftAppTheme {
        HomeContent(
            items = listOf(
                HomeItem(1, "Agudeza Visual", "Resultado OD: 20/20", "19 Oct 2026", "Pendiente"),
                HomeItem(2, "Fondo de Ojo", "Retina aplicada", "18 Oct 2026", "Completado")
            ),
            listState = rememberLazyListState(),
            onAgregarClick = {},
            onLoginClick = {},
            onSolicitudesClick = {}
        )
    }
}