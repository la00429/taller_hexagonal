# Colección de Postman para CustomerService Hexa

## 📋 Descripción

Esta colección de Postman contiene todas las pruebas para los endpoints del servicio de clientes con arquitectura hexagonal.

## 🚀 Instalación

1. Abre Postman
2. Haz clic en **Import** (botón en la esquina superior izquierda)
3. Selecciona el archivo `Postman_CustomerService_Collection.json`
4. La colección se importará automáticamente

## ⚙️ Configuración

### Variables de Entorno

La colección usa las siguientes variables:

- **baseUrl**: `http://localhost:8080` (por defecto)
- **customerId**: Se establece automáticamente después de crear un cliente

### Configurar la URL base

Si tu aplicación corre en un puerto diferente, puedes:

1. Editar la variable `baseUrl` en la colección
2. O crear un Environment en Postman con:
   - Variable: `baseUrl`
   - Valor: `http://localhost:8080` (o tu puerto)

## 📝 Endpoints Incluidos

### 1. Crear Cliente
- **Método**: `POST`
- **URL**: `/customer/addcustomer`
- **Body**: 
```json
{
    "firstName": "Juan",
    "lastName": "Pérez",
    "address": "Calle 123, Ciudad"
}
```
- **Respuesta esperada**: 201 Created

### 2. Obtener Cliente por ID
- **Método**: `GET`
- **URL**: `/customer/{customerId}`
- **Respuesta esperada**: 200 OK con los datos del cliente

### 3. Listar Todos los Clientes
- **Método**: `GET`
- **URL**: `/customer/allcustomers`
- **Respuesta esperada**: 200 OK con array de clientes

### 4. Actualizar Cliente
- **Método**: `PUT`
- **URL**: `/customer/update/{customerId}`
- **Body**:
```json
{
    "firstName": "María",
    "lastName": "González",
    "address": "Avenida 456, Ciudad Nueva"
}
```
- **Respuesta esperada**: 200 OK con el cliente actualizado

### 5. Eliminar Cliente
- **Método**: `DELETE`
- **URL**: `/customer/delete/{customerId}`
- **Respuesta esperada**: 204 No Content

### 6. Obtener Cliente (No existe - 404)
- **Método**: `GET`
- **URL**: `/customer/99999`
- **Respuesta esperada**: 404 Not Found

## 🧪 Pruebas Automáticas

Cada request incluye pruebas automáticas que verifican:

- ✅ Códigos de estado HTTP correctos
- ✅ Estructura de la respuesta JSON
- ✅ Presencia de campos requeridos
- ✅ Valores esperados en las respuestas

## 📋 Orden Recomendado de Ejecución

1. **Crear Cliente** - Crea un cliente y guarda su ID automáticamente
2. **Obtener Cliente por ID** - Verifica que el cliente fue creado
3. **Listar Todos los Clientes** - Verifica que aparece en la lista
4. **Actualizar Cliente** - Modifica los datos del cliente
5. **Obtener Cliente por ID** - Verifica que los cambios se aplicaron
6. **Eliminar Cliente** - Elimina el cliente
7. **Obtener Cliente (No existe - 404)** - Verifica que ya no existe

## 💡 Notas

- El `customerId` se guarda automáticamente después de crear un cliente
- Puedes modificar los datos en los bodies de los requests según tus necesidades
- Asegúrate de que la aplicación Spring Boot esté corriendo antes de ejecutar las pruebas

## 🔧 Troubleshooting

### Error de conexión
- Verifica que la aplicación esté corriendo en `http://localhost:8080`
- Verifica que MySQL esté corriendo y la base de datos esté creada
- Revisa la configuración en `application.properties`

### Error 404
- Asegúrate de que el `customerId` esté establecido correctamente
- Verifica que el cliente exista en la base de datos

### Error 500
- Revisa los logs de la aplicación Spring Boot
- Verifica la conexión a la base de datos MySQL

