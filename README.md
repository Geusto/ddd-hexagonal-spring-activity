# Arquitectura Hexagonal en Spring Boot

## 📋 Descripción

Proyecto que implementa la gestión de dos agregados entidades utilizando arquitectura hexagonal con Spring Boot y persistencia en memoria.

## 🛠️ Requisitos del Sistema

### Java
- **Versión requerida**: Java 21 (LTS)
- **Versión recomendada**: Java 21
- **Nota**: Java 21 es la versión LTS más reciente y estable

### Gradle
- **Versión**: 8.0+ (incluida en el wrapper)

## 🚀 Instalación y Ejecución

### 1. Clonar el repositorio
```bash
git clone <url-del-repositorio>
cd ddd-hexagonal-spring
```

### 2. Verificar versión de Java
```bash
java -version
# Debe mostrar Java 21
```

### 3. Compilar el proyecto (OBLIGATORIO)
```bash
# Compilar y descargar dependencias
./gradlew build
```

### 4. Ejecutar la aplicación
```bash
# Opción 1: Usando Gradle Wrapper (recomendado)
./gradlew bootRun

# Opción 2: Ejecutar JAR compilado
java -jar build/libs/ddd-hexagonal-spring-0.0.1-SNAPSHOT.jar
```

### 5. Verificar que funciona
```bash
# Salud de la aplicación
curl http://localhost:8080/actuator/health

# Información de la aplicación
curl http://localhost:8080/actuator/info
```

## 🔧 Configuración de Java

### Si tienes Java 21
- El proyecto se compilará y ejecutará perfectamente
- Podrás usar todas las características modernas de Java

### Si tienes Java 17, 20, u otra versión
- **NO es compatible** - necesitas actualizar a Java 21
- Java 21 es LTS y la versión más estable actualmente

## 📁 Estructura del Proyecto

```
src/
├── main/
│   ├── java/
│   │   └── co/edu/udec/ingsw/activity/
│   │       ├── domain/           # Capa de dominio
│   │       ├── application/      # Capa de aplicación
│   │       └── infrastructure/   # Capa de infraestructura
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── co/edu/udec/ingsw/activity/
```

## 🧪 Testing

```bash
# Ejecutar todos los tests
./gradlew test

# Ejecutar tests con reporte
./gradlew test jacocoTestReport
```

## 🤝 Contribución

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -m 'Agrega nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request

## 📝 Notas

- El proyecto usa persistencia en memoria (Mapas/Collections)
- No requiere base de datos externa
- Los datos se pierden al reiniciar la aplicación
