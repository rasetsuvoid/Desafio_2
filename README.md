## Proyecto de Automatización de Pruebas

Este repositorio contiene un conjunto de pruebas automatizadas para validar el comportamiento de formularios web usando Selenium WebDriver, Cucumber y TestNG.
## Herramientas y versiones utilizadas

Java: 17
JUnit Jupiter: 5.9.3
TestNG: 7.11.0
Selenium Java: 4.21.0
WebDriverManager: 5.7.0
Cucumber Java: 7.22.0
Cucumber JUnit: 7.22.0
Gradle: 8.8

## Manejador de dependencias

Este proyecto usa Gradle como sistema de construcción y manejador de dependencias.

## Comando para ejecutar los tests

Para ejecutar los escenarios de prueba, usa el siguiente comando desde la raíz del proyecto:
./gradlew test
Asegúrate de tener configurado Java 17 y Gradle (o usar el wrapper gradlew) antes de ejecutar.

## Estructura esperada

src/
├── main/
│   └── java/          # Código fuente
├── test/
│   └── java/          # Archivos de prueba con Cucumber + TestNG
│   └── resources/
│       └── features/  # Archivos .feature
│       └── pantallazos/ # Capturas de pantalla

