## 📋 Descripción

Spring gRPC es un proyecto que implementa servicios gRPC utilizando Spring Boot. Este proyecto combina la potencia de
Spring Boot con la eficiencia de gRPC (Google Remote Procedure Call) para crear servicios de alta performance y fácil
mantenimiento.

## 🚀 Características

- **Spring Boot 3.5.6**: Framework moderno de Java
- **gRPC 1.74.0**: Protocolo de comunicación de alto rendimiento
- **Java 21**: Versión moderna de Java con las últimas características
- **Lombok**: Para reducir el código boilerplate
- **Protocol Buffers 4.31.1**: Serialización eficiente de datos
- **Spring gRPC 0.11.0**: Integración nativa de gRPC con Spring Boot

## 🛠️ Tecnologías Utilizadas

| Tecnología       | Versión | Propósito                       |
|------------------|---------|---------------------------------|
| Spring Boot      | 3.5.6   | Framework principal             |
| Java             | 21      | Lenguaje de programación        |
| gRPC             | 1.74.0  | Protocolo de comunicación       |
| Protocol Buffers | 4.31.1  | Serialización de datos          |
| Spring gRPC      | 0.11.0  | Integración Spring-gRPC         |
| Lombok           | -       | Reducción de código boilerplate |
| Maven            | -       | Gestión de dependencias         |

## 📁 Estructura del Proyecto

``` 
spring-grpc/
├── src/
│   ├── main/
│   │   ├── java/          # Código fuente Java
│   │   ├── proto/         # Archivos Protocol Buffer
│   │   └── resources/     # Recursos de la aplicación
│   └── test/              # Pruebas unitarias
├── pom.xml               # Configuración Maven
└── README.md             # Documentación
```

## ⚙️ Configuración

### Prerrequisitos

- **Java 21** o superior
- **Maven 3.6+**
- IDE compatible (IntelliJ IDEA recomendado)

### Dependencias Principales

- : Starter de Spring gRPC `spring-grpc-spring-boot-starter`
- `grpc-services`: Servicios gRPC
- : Anotaciones para reducir código `lombok`
- `spring-boot-starter-test`: Dependencias para testing
- : Utilidades de testing para gRPC `spring-grpc-test`

## 🔧 Plugins de Maven

### Plugin de Protocol Buffers

- **protobuf-maven-plugin**: Genera clases Java desde archivos .proto
- Configurado para usar protoc versión 4.31.1
- Incluye generador de servicios gRPC

### Plugin de Spring Boot

- Configuración para exclusión de Lombok en el JAR final
- Soporte para aplicaciones ejecutables

## 🏃‍♂️ Ejecución

### Compilar el proyecto

``` bash
./mvnw clean compile
```

### Ejecutar tests

``` bash
./mvnw test
```

### Ejecutar la aplicación

``` bash
./mvnw spring-boot:run
```

### Generar JAR ejecutable

``` bash
./mvnw clean package
java -jar target/spring-grpc-0.0.1-SNAPSHOT.jar
```

## 📚 Uso

Este proyecto está configurado para:

1. **Definir servicios gRPC** en archivos `.proto`
2. **Generar automáticamente** clases Java desde los archivos proto
3. **Implementar servicios** usando anotaciones de Spring
4. **Configurar fácilmente** servidores y clientes gRPC

## 🧪 Testing

El proyecto incluye:

- **Spring Boot Test**: Para testing de integración
- **Spring gRPC Test**: Utilidades específicas para testing de servicios gRPC
- Configuración automática para entornos de testing

## 📝 Notas de Desarrollo

- Los archivos Protocol Buffer deben ubicarse en `src/main/proto/`
- Las clases generadas se colocan automáticamente en el classpath
- Lombok está configurado para procesamiento de anotaciones
- El proyecto usa Jakarta EE con imports jakarta

## 🔍 Próximos Pasos

Para usar este proyecto:

1. Define tus servicios gRPC en archivos `.proto`
2. Implementa los servicios usando `@GrpcService`
3. Configura clientes gRPC si es necesario
4. Añade lógica de negocio en los servicios
5. Implementa tests para validar funcionalidad

## 📋 Licencia

Este proyecto está bajo licencia MIT (o la que corresponda según tu configuración).
**Información del Proyecto:**

- GroupId: `com.davinchicoder`
- ArtifactId: `spring-grpc`
- Versión: `0.0.1-SNAPSHOT`
