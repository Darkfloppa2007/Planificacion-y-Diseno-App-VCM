package com.example.oftapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.MedicalServices
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.oftapp.ui.theme.OftAppTheme

data class ExamenItem(
    val id: Int,
    val nombreExamen: String,
    val categoria: String,
    val duracionAprox: String,
    val descripcion: String
)

@Composable
fun ListadoExámenesScreen(
    onBackClick: () -> Unit = {},
    onExamenClick: (ExamenItem) -> Unit = {}
) {
    val examenes = listOf(
        ExamenItem(1, "Agudeza Visual", "Optometría", "15 min", "Evaluación de la nitidez de la visión de lejos y cerca."),
        ExamenItem(2, "Fondo de Ojo", "Retina", "20 min", "Inspección de la retina, el nervio óptico y los vasos sanguíneos."),
        ExamenItem(3, "Tonometría", "Glaucoma", "10 min", "Medición de la presión intraocular para prevención de patologías."),
        ExamenItem(4, "Campimetría Computarizada", "Neurología Visual", "30 min", "Evaluación del campo visual central y periférico."),
        ExamenItem(5, "Paquimetría Corneal", "Córnea", "10 min", "Medición del grosor de la córnea para estudios refractivos.")
    )

    val listState = rememberLazyListState()

    ListadoExámenesContent(
        examenes = examenes,
        listState = listState,
        onBackClick = onBackClick,
        onExamenClick = onExamenClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListadoExámenesContent(
    examenes: List<ExamenItem>,
    listState: androidx.compose.foundation.lazy.LazyListState,
    onBackClick: () -> Unit,
    onExamenClick: (ExamenItem) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Catálogo de Exámenes",
                        fontWeight = FontWeight.Bold
                    )
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
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Seleccione un examen para ver detalles o agendar",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.outline
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (examenes.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No hay exámenes disponibles",
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
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(examenes) { examen ->
                        ExamenCard(
                            examen = examen,
                            onClick = { onExamenClick(examen) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ExamenCard(
    examen: ExamenItem,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icono representativo médico en un contenedor circular
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.MedicalServices,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = examen.nombreExamen,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.secondaryContainer
                    ) {
                        Text(
                            text = examen.duracionAprox,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Categoría: ${examen.categoria}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = examen.descripcion,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListadoExámenesScreenPreview() {
    OftAppTheme {
        ListadoExámenesContent(
            examenes = listOf(
                ExamenItem(1, "Agudeza Visual", "Optometría", "15 min", "Evaluación de la nitidez de la visión de lejos y cerca."),
                ExamenItem(2, "Fondo de Ojo", "Retina", "20 min", "Inspección de la retina y el nervio óptico.")
            ),
            listState = rememberLazyListState(),
            onBackClick = {},
            onExamenClick = {}
        )
    }
}