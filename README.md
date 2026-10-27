OftApp - Sistema de Gestión Oftalmológica Clínica

**OftApp** es una aplicación móvil desarrollada nativamente para Android en **Kotlin**, utilizando **Jetpack Compose** y el patrón arquitectónico **MVVM**. Su propósito principal es optimizar la gestión de consultas, historiales y solicitudes clínicas oftalmológicas, aportando una solución digital directa a las necesidades identificadas en el proyecto de Vinculación con el Medio (VcM).

## 🛠️ Características y Tecnologías Implementadas


✔️ **Arquitectura MVVM**: Organización modular del código separando responsabilidades en *Models*, *Views*, *ViewModels* y *Repositories*.

✔️ **Interfaz Moderna con Jetpack Compose y Material 3**: Pantallas estilizadas con un enfoque clínico real, uso de `TopAppBar`, componentes visuales reutilizables (`Cards`, `Badges`), y soporte para temas adaptativos.

✔️ **Navegación Funcional**: Integración fluida mediante `Navigation Compose` (`NavHost` y `NavController`) permitiendo un tránsito seguro y ordenado entre pantallas.

✔️ **Gestión de Estado**: Uso de estados reactivos en `ViewModels` para reflejar dinámicamente las métricas, contadores y listas de elementos en tiempo real.

✔️ **Formularios Validados**: Campos de entrada de datos obligatorios con validación interactiva que condiciona la habilitación de los botones de guardado.

✔️ **Listas, Búsquedas y Métricas**: Visualización de catálogos e historiales en `LazyColumn` con tarjetas interactivas y estadísticas en el panel principal (Totales, Pendientes y Completadas).

✔️ **Persistencia y Estructura de Datos**: Gestión de repositorios locales y modelos de datos escalables para exámenes y solicitudes médicas.

✔️ **Recursos Nativos de Android (Cámara)**: Integración con la cámara del dispositivo mediante `ActivityResultContracts.TakePicture()` y configuración segura de `FileProvider` para escanear y adjuntar fotografías directamente a los expedientes.

✔️ **Enfoque Práctico VcM**: Digitalización de procesos oftalmológicos para agilizar el registro y control de pacientes o solicitudes de insumos/exámenes.

---

## 📂 Estructura del Proyecto

El código fuente se encuentra organizado bajo el paquete base `com.example.oftapp`:

```text
com.example.oftapp/
│
├── model/                  # Modelos de datos (Solicitud, HomeItem, ExamenItem)
├── repository/             # Capa de datos y fuentes simuladas (SolicitudRepository)
├── viewmodel/              # Lógica de negocio y estados (HomeViewModel, SolicitudViewModel)
└── ui/
    ├── components/         # Componentes visuales (HomeCard, SolicitudCard, ExamenCard)
    ├── screens/            # Pantallas de la aplicación:
    │   ├── HomeScreen.kt              # Dashboard principal con métricas e historial
    │   ├── LoginScreen.kt             # Acceso clínico seguro
    │   ├── SolicitudesScreen.kt       # Gestión y visualización de peticiones
    │   ├── FormularioRegistroScreen.kt # Registro con soporte para cámara/fotos
    │   └── ListadoExámenesScreen.kt   # Catálogo de exámenes oftalmológicos
    └── theme/              # Configuración de Material 3 (Color, Typography, Shape)


# React + Vite

This template provides a minimal setup to get React working in Vite with HMR and some ESLint rules.

Currently, two official plugins are available:

- [@vitejs/plugin-react](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react) uses [Oxc](https://oxc.rs)
- [@vitejs/plugin-react-swc](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react-swc) uses [SWC](https://swc.rs/)

## React Compiler

The React Compiler is not enabled on this template because of its impact on dev & build performances. To add it, see [this documentation](https://react.dev/learn/react-compiler/installation).

## Expanding the ESLint configuration

If you are developing a production application, we recommend using TypeScript with type-aware lint rules enabled. Check out the [TS template](https://github.com/vitejs/vite/tree/main/packages/create-vite/template-react-ts) for information on how to integrate TypeScript and [`typescript-eslint`](https://typescript-eslint.io) in your project.
