customerservicehexa
===================

Proyecto esqueleto (arquitectura hexagonal) generado para el taller.

Instrucciones rápidas:
1. Ajusta `src/main/resources/application.properties` con tu usuario/clave MySQL.
2. Compilar: mvn clean package
3. Ejecutar: mvn spring-boot:run
4. Endpoints:
   - POST /customers
   - GET /customers
   - GET /customers/{id}
   - DELETE /customers/{id}

Este esqueleto incluye clases mínimas para que empieces a implementar la lógica de dominio y casos de uso.
