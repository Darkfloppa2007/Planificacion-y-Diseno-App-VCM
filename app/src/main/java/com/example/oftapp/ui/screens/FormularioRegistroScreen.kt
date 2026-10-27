package com.example.oftapp.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.CameraAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import com.example.oftapp.ui.theme.OftAppTheme
import java.io.File

@Composable
fun FormularioRegistroScreen(
    onBackClick: () -> Unit = {},
    onSaveClick: (String, String, String, Uri?) -> Unit = { _, _, _, _ -> }
) {
    var titulo by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var tempImageUri by remember { mutableStateOf<Uri?>(null) }

    val context = LocalContext.current
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            imageUri = tempImageUri
        }
    }

    FormularioRegistroContent(
        titulo = titulo,
        onTituloChange = { titulo = it },
        categoria = categoria,
        onCategoriaChange = { categoria = it },
        descripcion = descripcion,
        onDescripcionChange = { descripcion = it },
        imageUri = imageUri,
        onTomarFotoClick = {
            val photoFile = File.createTempFile("formulario_", ".jpg", context.getExternalFilesDir("Pictures"))
            val uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider",
                photoFile
            )
            tempImageUri = uri
            cameraLauncher.launch(uri)
        },
        onBackClick = onBackClick,
        onSaveClick = {
            if (titulo.isNotBlank() && descripcion.isNotBlank()) {
                onSaveClick(titulo, categoria, descripcion, imageUri)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioRegistroContent(
    titulo: String,
    onTituloChange: (String) -> Unit,
    categoria: String,
    onCategoriaChange: (String) -> Unit,
    descripcion: String,
    onDescripcionChange: (String) -> Unit,
    imageUri: Uri?,
    onTomarFotoClick: () -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Nuevo Registro Clínico",
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
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = titulo,
                onValueChange = onTituloChange,
                label = { Text("Título (ej. Fondo de Ojo)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            OutlinedTextField(
                value = categoria,
                onValueChange = onCategoriaChange,
                label = { Text("Categoría (ej. Retina, Optometría)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            OutlinedTextField(
                value = descripcion,
                onValueChange = onDescripcionChange,
                label = { Text("Descripción o Diagnóstico preliminar") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                shape = RoundedCornerShape(12.dp),
                maxLines = 4
            )
            OutlinedButton(
                onClick = onTomarFotoClick,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(imageVector = Icons.Rounded.CameraAlt, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = if (imageUri == null) "Escanear/Tomar Foto del Formulario" else "Volver a tomar foto")
            }
            imageUri?.let { uri ->
                Image(
                    painter = rememberAsyncImagePainter(uri),
                    contentDescription = "Vista previa del formulario",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onSaveClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                enabled = titulo.isNotBlank() && descripcion.isNotBlank()
            ) {
                Text(
                    text = "Guardar Registro",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormularioRegistroScreenPreview() {
    OftAppTheme {
        FormularioRegistroContent(
            titulo = "",
            onTituloChange = {},
            categoria = "",
            onCategoriaChange = {},
            descripcion = "",
            onDescripcionChange = {},
            imageUri = null,
            onTomarFotoClick = {},
            onBackClick = {},
            onSaveClick = {}
        )
    }
}
