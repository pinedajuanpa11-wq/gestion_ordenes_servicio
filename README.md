# Sistema de gestión de órdenes de servicio

Proyecto académico para Lenguaje de Programación Avanzado II. Java 17 + Spring Boot + Thymeleaf + Spring Data JPA (ORM) + H2.

## Requisitos
- JDK 17 instalado.
- IntelliJ IDEA.
- Conexión a internet la primera vez para que Maven descargue dependencias.

## Ejecutar en IntelliJ IDEA
1. Descomprime el ZIP.
2. Abre IntelliJ IDEA y elige **Open**; selecciona la carpeta `gestion_ordenes_servicio` donde está `pom.xml`.
3. Acepta importar el proyecto como Maven y espera que termine de descargar dependencias.
4. Verifica en **File > Project Structure > Project** que el SDK sea Java 17.
5. Abre `src/main/java/co/edu/uniremington/ordenes/GestionOrdenesApplication.java`.
6. Pulsa el triángulo verde junto a `main` y selecciona **Run**.
7. Abre `http://localhost:8080`.
8. Registra un cliente, un equipo y una orden. Usa datos ficticios.

La base de datos H2 se guarda en la carpeta `data`. No se requieren instalaciones externas de MySQL.

## API REST (prueba desde navegador/Postman/cURL)
Base URL: `http://localhost:8080/api`

### Listar clientes
`GET /api/clientes`
Respuesta: `200 OK`, arreglo JSON.

### Crear cliente
`POST /api/clientes`
Header: `Content-Type: application/json`
```json
{"name":"Laura Gómez","email":"laura@ejemplo.com","phone":"3000000000"}
```
Respuesta: `201 Created`, objeto JSON del cliente con `id`.

### Listar equipos
`GET /api/equipos`

### Crear equipo
`POST /api/equipos`
```json
{"description":"Portátil de prueba","serialNumber":"EQ-001","clientId":1}
```
`clientId` debe existir.

### Listar órdenes
`GET /api/ordenes`

### Crear orden
`POST /api/ordenes`
```json
{"description":"Diagnóstico y mantenimiento","equipmentId":1}
```
`equipmentId` debe existir.

### Cambiar estado de una orden
`PATCH /api/ordenes/1/estado`
```json
{"status":"CERRADA"}
```
Esta operación usa `@Transactional`: actualiza el estado de la orden y del equipo asociado en una misma transacción.

## Modelo de datos
- `clientes`: id (BIGINT, PK), name (VARCHAR 100), email (VARCHAR 120), phone (VARCHAR 25).
- `equipos`: id (BIGINT, PK), description (VARCHAR 100), serial_number (VARCHAR 60), status (VARCHAR 30), client_id (BIGINT, FK).
- `ordenes_servicio`: id (BIGINT, PK), description (VARCHAR 250), status (VARCHAR 30), created_at (DATE), equipment_id (BIGINT, FK).
- Relación cliente 1:N equipos. Relación equipo 1:N órdenes.

## Evidencias que debes tomar tú al ejecutar
1. Página principal con tablas y formularios.
2. Cliente, equipo y orden creados en la interfaz.
3. Respuesta JSON de `GET /api/ordenes`.
4. Respuesta de `PATCH /api/ordenes/1/estado` y la tabla mostrando la orden cerrada.
5. Opcional: consola H2 en `http://localhost:8080/h2-console`; JDBC URL `jdbc:h2:file:./data/ordenesdb`, usuario `sa`, contraseña vacía.

## REST y SOAP
REST organiza operaciones alrededor de recursos y utiliza métodos HTTP como GET, POST y PATCH; esta aplicación entrega y recibe JSON. SOAP es un protocolo de mensajería estructurado que suele utilizar XML y contratos formales (por ejemplo WSDL). Para este alcance se selecciona REST por su sencillez para una interfaz web y pruebas HTTP. No se implementa un endpoint SOAP: se explica la diferencia solicitada en el informe.

## Alcance y limitaciones
- Prototipo académico; no incluye autenticación ni autorización.
- Validación básica; no debe usarse con datos reales o en producción.
- La evidencia de ejecución debe capturarse localmente después de correr el proyecto.
- No se configura un servicio SOAP porque el alcance implementado es REST. El gestor de servicios web de NetBeans no aplica al entorno principal elegido (IntelliJ); se demuestra la API con navegador o Postman.

## Uso de IA
Declarar en el informe el uso real: ChatGPT se utilizó como apoyo para estructurar el prototipo, generar un borrador de código y organizar la documentación. El estudiante debe ejecutar, revisar, comprender y modificar el código, y tomar las capturas auténticas de las pruebas.
