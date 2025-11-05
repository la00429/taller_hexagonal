# CustomerService Hexa - Colección de Postman

<div align="center">

**Colección completa de pruebas API para el servicio de clientes con arquitectura hexagonal**

[Características](#características) • [Instalación](#instalación-rápida) • [Uso](#cómo-usar) • [Endpoints](#endpoints)

</div>

---

## Características

- **6 endpoints** completamente configurados
- **Pruebas automáticas** integradas en cada request
- **Variables dinámicas** para facilitar el testing
- **Documentación completa** de cada endpoint
- **Flujo de trabajo** optimizado (CRUD completo)

---

## Instalación Rápida

### Paso 1: Importar la Colección

1. Abre **Postman** en tu computador
2. Haz clic en el botón **Import** (esquina superior izquierda)
   - O usa el atajo: `Ctrl + O` (Windows) / `Cmd + O` (Mac)
3. Selecciona el archivo `Postman_CustomerService_Collection.json`
4. ¡Listo! La colección aparecerá en tu panel lateral

### Paso 2: Configurar Variables (Opcional)

Si tu aplicación corre en un puerto diferente a `8080`:

1. En Postman, haz clic en la colección **CustomerService Hexa**
2. Ve a la pestaña **Variables**
3. Edita el valor de `baseUrl` según tu configuración
   - Ejemplo: `http://localhost:9090`

---

## Cómo Usar

### Configuración Inicial

Antes de ejecutar las pruebas, asegúrate de:

- La aplicación Spring Boot esté **corriendo**
- MySQL esté **activo** y la base de datos `customerDB` esté creada
- La conexión a la base de datos esté configurada correctamente

### Ejecutar las Pruebas

#### Opción 1: Ejecución Individual
1. Abre cada request en la colección
2. Haz clic en **Send**
3. Revisa los resultados en la pestaña **Test Results**

#### Opción 2: Ejecución en Lote
1. Haz clic derecho en la colección
2. Selecciona **Run collection**
3. Revisa el resumen de todas las pruebas

---

## Endpoints

### 1. Crear Cliente

| Método | Endpoint | Código |
|--------|----------|--------|
| `POST` | `/customer/addcustomer` | `201 Created` |

**Request Body:**
```json
{
    "firstName": "Juan",
    "lastName": "Pérez",
    "address": "Calle 123, Ciudad"
}
```

**Respuesta Exitosa:**
```json
{
    "id": 1,
    "firstName": "Juan",
    "lastName": "Pérez",
    "address": "Calle 123, Ciudad"
}
```

> **Nota:** El `customerId` se guarda automáticamente en una variable para usar en otros requests.

---

### 2. Obtener Cliente por ID

| Método | Endpoint | Código |
|--------|----------|--------|
| `GET` | `/customer/{customerId}` | `200 OK` |

**Respuesta Exitosa:**
```json
{
    "id": 1,
    "firstName": "Juan",
    "lastName": "Pérez",
    "address": "Calle 123, Ciudad"
}
```

---

### 3. Listar Todos los Clientes

| Método | Endpoint | Código |
|--------|----------|--------|
| `GET` | `/customer/allcustomers` | `200 OK` |

**Respuesta Exitosa:**
```json
[
    {
        "id": 1,
        "firstName": "Juan",
        "lastName": "Pérez",
        "address": "Calle 123, Ciudad"
    },
    {
        "id": 2,
        "firstName": "María",
        "lastName": "González",
        "address": "Avenida 456, Ciudad"
    }
]
```

---

### 4. Actualizar Cliente

| Método | Endpoint | Código |
|--------|----------|--------|
| `PUT` | `/customer/update/{customerId}` | `200 OK` |

**Request Body:**
```json
{
    "firstName": "María",
    "lastName": "González",
    "address": "Avenida 456, Ciudad Nueva"
}
```

**Respuesta Exitosa:**
```json
{
    "id": 1,
    "firstName": "María",
    "lastName": "González",
    "address": "Avenida 456, Ciudad Nueva"
}
```

---

### 5. Eliminar Cliente

| Método | Endpoint | Código |
|--------|----------|--------|
| `DELETE` | `/customer/delete/{customerId}` | `204 No Content` |

---

### 6. Obtener Cliente (Prueba de Error 404)

| Método | Endpoint | Código |
|--------|----------|--------|
| `GET` | `/customer/99999` | `404 Not Found` |

> Esta prueba verifica el manejo de errores cuando un cliente no existe.

---

## Pruebas Automáticas

Cada request incluye **tests automáticos** que verifican:

| Verificación | Descripción |
|--------------|-------------|
| **Status Code** | Valida que el código HTTP sea el correcto |
| **Response Structure** | Verifica la estructura del JSON |
| **Required Fields** | Comprueba que existan los campos necesarios |
| **Data Integrity** | Valida que los datos sean correctos |

### Ejemplo de Test

```javascript
pm.test("Status code is 201", function () {
    pm.response.to.have.status(201);
});

pm.test("Response tiene customer con id", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData).to.have.property('id');
    pm.expect(jsonData).to.have.property('firstName');
});
```

---

## Flujo de Trabajo Recomendado

Sigue este orden para probar el flujo completo:

```
1. Crear Cliente → 2. Obtener por ID → 3. Listar Todos → 4. Actualizar → 5. Obtener por ID → 6. Eliminar → 7. Prueba 404
```

### Secuencia de Ejecución

1. **Crear Cliente** → Crea un nuevo cliente y guarda su ID
2. **Obtener Cliente por ID** → Verifica que el cliente fue creado correctamente
3. **Listar Todos los Clientes** → Confirma que aparece en la lista
4. **Actualizar Cliente** → Modifica los datos del cliente
5. **Obtener Cliente por ID** → Verifica que los cambios se aplicaron
6. **Eliminar Cliente** → Elimina el cliente del sistema
7. **Obtener Cliente (404)** → Verifica el manejo de errores

---

## Solución de Problemas

### Error de Conexión

**Síntomas:** No se puede conectar al servidor

**Soluciones:**
- Verifica que la aplicación Spring Boot esté corriendo
- Confirma que el puerto sea `8080` (o el configurado)
- Revisa que no haya un firewall bloqueando la conexión

### Error 404

**Síntomas:** Cliente no encontrado

**Soluciones:**
- Asegúrate de que el `customerId` esté establecido correctamente
- Verifica que el cliente exista en la base de datos
- Ejecuta primero el request "Crear Cliente"

### Error 500

**Síntomas:** Error interno del servidor

**Soluciones:**
- Revisa los logs de la aplicación Spring Boot
- Verifica la conexión a MySQL
- Confirma que la base de datos `customerDB` exista
- Revisa las credenciales en `application.properties`

### Error de Base de Datos

**Síntomas:** No se puede conectar a MySQL

**Soluciones:**
- Verifica que MySQL esté corriendo
- Confirma que la base de datos `customerDB` esté creada
- Revisa usuario y contraseña en `application.properties`
- Verifica que el puerto de MySQL sea `3306`

---

## Variables de la Colección

| Variable | Valor por Defecto | Descripción |
|----------|-------------------|-------------|
| `baseUrl` | `http://localhost:8080` | URL base de la API |
| `customerId` | `""` | ID del cliente (se establece automáticamente) |

---

## Tips y Trucos

### Personalizar Datos

Puedes modificar los datos de ejemplo en los bodies de los requests:

```json
{
    "firstName": "Tu Nombre",
    "lastName": "Tu Apellido",
    "address": "Tu Dirección"
}
```

### Reutilizar Variables

El `customerId` se guarda automáticamente después de crear un cliente y se usa en:
- Obtener Cliente por ID
- Actualizar Cliente
- Eliminar Cliente

### Exportar Resultados

1. Ejecuta la colección completa
2. Haz clic en **Export Results**
3. Guarda el archivo para revisión posterior

---

## Recursos Adicionales

- [Documentación de Postman](https://learning.postman.com/docs/)
- [Guía de Testing en Postman](https://learning.postman.com/docs/writing-scripts/test-scripts/)
- [Variables en Postman](https://learning.postman.com/docs/sending-requests/variables/)

---

<div align="center">

**Hecho con dedicación para CustomerService Hexa**

¿Encontraste un problema? Revisa la sección de [Solución de Problemas](#solución-de-problemas)

</div>
