# CustomerService Hexa

**Sistema de gestión de clientes implementado con Arquitectura Hexagonal en Spring Boot**

## Tabla de Contenidos

- [Descripción](#descripción)
- [Arquitectura](#arquitectura)
- [Tecnologías](#tecnologías)
- [Prerrequisitos](#prerrequisitos)
- [Instalación](#instalación)
- [Configuración](#configuración)
- [Ejecución](#ejecución)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Endpoints API](#endpoints-api)
- [Pruebas](#pruebas)
- [Documentación Adicional](#documentación-adicional)

---

## Descripción

Este proyecto es una aplicación REST que implementa un sistema de gestión de clientes utilizando **Arquitectura Hexagonal** (también conocida como Ports and Adapters). La arquitectura hexagonal separa la lógica de negocio del dominio de los detalles técnicos de infraestructura, permitiendo que la aplicación sea independiente de frameworks, bases de datos y sistemas externos.

### Características Principales

- Arquitectura hexagonal completa con separación clara de capas
- CRUD completo de clientes
- Persistencia en MySQL usando JPA/Hibernate
- API REST documentada
- Pruebas automatizadas con Postman
- Configuración flexible mediante archivos de propiedades

---

## Arquitectura

El proyecto sigue los principios de la Arquitectura Hexagonal, dividiéndose en tres capas principales:

### 1. Domain (Dominio)
Contiene la lógica de negocio pura, independiente de frameworks y tecnologías:

- **Model**: Entidades del dominio (`Customer`)
- **Ports In**: Interfaces para casos de uso (UseCases)
  - `CreateCustomerUseCase`
  - `FindCustomersUseCase`
  - `UpdateCustomerUseCase`
  - `DeleteCustomerUseCase`
- **Ports Out**: Interfaces para repositorios (`CustomerRepositoryPort`)

### 2. Application (Aplicación)
Contiene la lógica de aplicación y orquestación:

- **Services**: Servicios de aplicación (`CustomerService`)
- **UseCases**: Implementaciones de los casos de uso
  - `CreateCustomerUseCaseImpl`
  - `FindCustomersUseCaseImpl`
  - `UpdateCustomerUseCaseImpl`
  - `DeleteCustomerUseCaseImpl`

### 3. Infrastructure (Infraestructura)
Contiene los detalles técnicos y adaptadores:

- **Controllers**: Controladores REST (`CustomerController`)
- **Entities**: Entidades JPA (`CustomerEntity`)
- **Repositories**: Adaptadores de repositorio (`JpaCustomerRepositoryAdapter`)
- **Config**: Configuración de Spring (`ApplicationConfig`)

### Flujo de Datos

```
Controller → Service → UseCase → RepositoryPort → Adapter → JPA Repository → Database
```

---

## Tecnologías

- **Java 21**: Lenguaje de programación
- **Spring Boot 3.2.12**: Framework de aplicación
- **Spring Data JPA**: Abstracción para acceso a datos
- **Hibernate**: ORM para persistencia
- **MySQL**: Base de datos relacional
- **Maven**: Gestor de dependencias
- **Lombok**: Reducción de código boilerplate

---

## Prerrequisitos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

1. **JDK 17 o superior**
   ```bash
   java -version
   ```

2. **Maven 3.6 o superior**
   ```bash
   mvn -version
   ```

3. **MySQL 8.0 o superior**
   - MySQL debe estar corriendo
   - Debe tener una base de datos creada (ver configuración)

4. **IDE (Opcional pero recomendado)**
   - IntelliJ IDEA, Eclipse, o VS Code con extensiones Java

---

## Instalación

### 1. Clonar o Descargar el Proyecto

```bash
# Si tienes Git
git clone <url-del-repositorio>

# O descarga el proyecto y descomprímelo
```

### 2. Navegar al Directorio del Proyecto

```bash
cd customerservicehexa
```

### 3. Compilar el Proyecto

```bash
mvn clean install
```

---

## Configuración

### 1. Crear Base de Datos en MySQL

Conéctate a MySQL y ejecuta:

```sql
CREATE DATABASE customerDB;
```

### 2. Configurar application.properties

Edita el archivo `src/main/resources/application.properties`:

```properties
# Base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/customerDB
spring.datasource.username=root
spring.datasource.password=tu_contraseña_aqui

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Puerto del servidor
server.port=8080
```

**Importante:**
- Reemplaza `tu_contraseña_aqui` con tu contraseña de MySQL
- Ajusta el nombre de la base de datos si es diferente
- Verifica que el puerto de MySQL sea `3306`

---

## Ejecución

### Opción 1: Ejecutar con Maven

```bash
mvn spring-boot:run
```

### Opción 2: Ejecutar el JAR

Primero compila:
```bash
mvn clean package
```

Luego ejecuta:
```bash
java -jar target/customerservicehexa-0.0.1-SNAPSHOT.jar
```

### Verificar que la Aplicación Está Corriendo

La aplicación debería iniciar en `http://localhost:8080`. Verás en la consola:

```
Started CustomerservicehexaApplication in X.XXX seconds
```

---

## Estructura del Proyecto

```
customerservicehexa/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── edu/uptc/swii/customerservicehexa/
│   │   │       ├── domain/                    # Capa de Dominio
│   │   │       │   ├── model/                 # Entidades del dominio
│   │   │       │   │   └── Customer.java
│   │   │       │   └── ports/                 # Puertos (interfaces)
│   │   │       │       ├── in/                 # Puertos de entrada (UseCases)
│   │   │       │       │   ├── CreateCustomerUseCase.java
│   │   │       │       │   ├── FindCustomersUseCase.java
│   │   │       │       │   ├── UpdateCustomerUseCase.java
│   │   │       │       │   └── DeleteCustomerUseCase.java
│   │   │       │       └── out/                # Puertos de salida (Repositorios)
│   │   │       │           └── CustomerRepositoryPort.java
│   │   │       ├── application/               # Capa de Aplicación
│   │   │       │   ├── services/              # Servicios de aplicación
│   │   │       │   │   └── CustomerService.java
│   │   │       │   └── usecases/              # Implementaciones de UseCases
│   │   │       │       ├── CreateCustomerUseCaseImpl.java
│   │   │       │       ├── FindCustomersUseCaseImpl.java
│   │   │       │       ├── UpdateCustomerUseCaseImpl.java
│   │   │       │       └── DeleteCustomerUseCaseImpl.java
│   │   │       └── infraestructure/           # Capa de Infraestructura
│   │   │           ├── config/                # Configuración
│   │   │           │   └── ApplicationConfig.java
│   │   │           ├── controllers/           # Controladores REST
│   │   │           │   └── CustomerController.java
│   │   │           ├── entities/              # Entidades JPA
│   │   │           │   └── CustomerEntity.java
│   │   │           └── repositories/           # Adaptadores de repositorio
│   │   │               ├── JpaCustomerRepository.java
│   │   │               └── JpaCustomerRepositoryAdapter.java
│   │   └── resources/
│   │       └── application.properties        # Configuración de la aplicación
│   └── test/                                  # Tests (opcional)
├── pom.xml                                    # Configuración de Maven
├── README.md                                  # Este archivo
├── README_POSTMAN.md                         # Documentación de Postman
└── Postman_CustomerService_Collection.json   # Colección de Postman
```

---

## Endpoints API

### Base URL
```
http://localhost:8080/customer
```

### Endpoints Disponibles

#### 1. Crear Cliente
```http
POST /customer/addcustomer
Content-Type: application/json

{
    "firstName": "Juan",
    "lastName": "Pérez",
    "address": "Calle 123, Ciudad"
}
```

**Respuesta:** `201 Created`
```json
{
    "id": 1,
    "firstName": "Juan",
    "lastName": "Pérez",
    "address": "Calle 123, Ciudad"
}
```

#### 2. Obtener Cliente por ID
```http
GET /customer/{customerId}
```

**Respuesta:** `200 OK`
```json
{
    "id": 1,
    "firstName": "Juan",
    "lastName": "Pérez",
    "address": "Calle 123, Ciudad"
}
```

#### 3. Listar Todos los Clientes
```http
GET /customer/allcustomers
```

**Respuesta:** `200 OK`
```json
[
    {
        "id": 1,
        "firstName": "Juan",
        "lastName": "Pérez",
        "address": "Calle 123, Ciudad"
    }
]
```

#### 4. Actualizar Cliente
```http
PUT /customer/update/{customerId}
Content-Type: application/json

{
    "firstName": "María",
    "lastName": "González",
    "address": "Avenida 456, Ciudad Nueva"
}
```

**Respuesta:** `200 OK`

#### 5. Eliminar Cliente
```http
DELETE /customer/delete/{customerId}
```

**Respuesta:** `204 No Content`

---

## Pruebas

### Pruebas con Postman

El proyecto incluye una colección completa de Postman para probar todos los endpoints.

1. **Importar la colección:**
   - Abre Postman
   - Importa el archivo `Postman_CustomerService_Collection.json`

2. **Configurar variables:**
   - La colección usa `baseUrl = http://localhost:8080` por defecto
   - Ajusta si es necesario

3. **Ejecutar las pruebas:**
   - Ejecuta los requests en orden
   - Cada request incluye tests automáticos

Para más detalles, consulta el archivo `README_POSTMAN.md`.

### Orden de Ejecución Recomendado

1. Crear Cliente
2. Obtener Cliente por ID
3. Listar Todos los Clientes
4. Actualizar Cliente
5. Obtener Cliente por ID (verificar cambios)
6. Eliminar Cliente

---

## Documentación Adicional

- **README_POSTMAN.md**: Guía completa para usar la colección de Postman
- **Postman_CustomerService_Collection.json**: Colección de pruebas para Postman

---

## Solución de Problemas

### Error de Conexión a MySQL

**Problema:** `Access denied for user 'root'@'localhost'`

**Solución:**
- Verifica que la contraseña en `application.properties` sea correcta
- Asegúrate de que MySQL esté corriendo
- Verifica que el usuario tenga permisos para acceder a la base de datos

### Error: Base de Datos No Encontrada

**Problema:** `Unknown database 'customerDB'`

**Solución:**
- Crea la base de datos: `CREATE DATABASE customerDB;`
- O cambia el nombre en `application.properties`

### Error: Puerto en Uso

**Problema:** `Port 8080 is already in use`

**Solución:**
- Cambia el puerto en `application.properties`: `server.port=8081`
- O cierra la aplicación que está usando el puerto 8080

### Error de Compilación

**Problema:** Errores al compilar con Maven

**Solución:**
- Verifica que tienes JDK 17+ instalado
- Ejecuta: `mvn clean install`
- Revisa que todas las dependencias estén descargadas

---

## Estructura de Datos

### Modelo Customer

```java
{
    "id": Integer,           // ID único (generado automáticamente)
    "firstName": String,     // Nombre del cliente
    "lastName": String,      // Apellido del cliente
    "address": String        // Dirección del cliente
}
```

### Tabla en Base de Datos

La tabla `customers` se crea automáticamente con:

- `id`: INT PRIMARY KEY AUTO_INCREMENT
- `first_name`: VARCHAR
- `last_name`: VARCHAR
- `address`: VARCHAR

---

## Desarrollo

### Agregar Nuevos Casos de Uso

1. Crea la interfaz en `domain/ports/in/`
2. Crea la implementación en `application/usecases/`
3. Agrega el método en `CustomerService`
4. Expón el endpoint en `CustomerController`
5. Configura el bean en `ApplicationConfig`

### Modificar el Modelo de Dominio

1. Actualiza `Customer.java` en `domain/model/`
2. Actualiza `CustomerEntity.java` en `infraestructure/entities/`
3. Actualiza los métodos de conversión en `JpaCustomerRepositoryAdapter`

---

## Licencia

Este proyecto fue desarrollado como parte de un taller de arquitectura hexagonal.

---

## Autor

Desarrollado para el taller de Arquitectura Hexagonal - Universidad Pedagógica y Tecnológica de Colombia

---

## Contacto

Para preguntas o problemas, revisa la sección de [Solución de Problemas](#solución-de-problemas) o consulta la documentación adicional.
