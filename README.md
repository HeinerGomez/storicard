# Prueba STORI CARD

Se presenta una aplicación la cual permite el registro de un usuario, almacenar foto y login por medio de firebase,
aparte permite visualizar una tarjeta de credito y sus movimientos asociados

## Tecnologias y Arquitecturas implementadas

- Jetpack Compose
- Clean Architecture
- Coroutines
- MutableState
- MVVM
- Retrofit
- Coil
- Dagger Hilt
- Lottie
- Project Modularization
- Gradle KTS
- Firebase

## Video Demostrativo


## Instalación

- *PASO 1:* Clonar el proyecto y tenerlo de manera local
- *PASO 2:* Abrir el proyecto en android studio y dejar que se descarguen las dependencias
- *PASO 3:* Ir a Android Studio > Settings > En el apartado de Gradle, verificar que se este usando la versión de java 18.0.2 o superior
- *PASO 4:* Ir a File > Project Structure, Verificar en el apartado de project que se este usando la versión 8.4 de gradle.
- *PASO 5:* Sincronizar el proyecto y ya se podría ejecutar.

## Notas

- procesos en segundo plano se manejaron con coroutines
- el proyecto quedó modularizado creando los siguientes módulos: app, shared, presentation, buildSrc(para configuración del proyecto), data, domain
- se crearon componentes reutilizables en jetpack compose
- se firebase para la creacion de usuario, almacenar imagenes, autenticar y para mostrar una tarjeta de credito y sus movimientos
