# proyectoProgramacion (Java + Firebase)

Proyecto Maven listo para abrir en NetBeans:
- Login/Register con Firebase Auth (REST)
- Listado de usuarios con Firebase Admin SDK (requiere clave de servicio)

## Pasos
1) En `src/main/resources/` coloca tu `firebase-service-account.json` (descargado desde Firebase → Configuración del proyecto → Cuentas de servicio → Generar nueva clave privada).
2) Abre `demo/MainConsole.java` **o** `demo/LoginFrame.java` y reemplaza:
   ```java
   String apiKey = "PON_TU_API_KEY_AQUI";
   ```
   por tu **Clave de API web** que ves en Firebase → Configuración del proyecto → General.
3) Ejecuta:
   - **Consola:** `MainConsole`
   - **Interfaz gráfica:** `LoginFrame`

> Nota: El Admin SDK es para entornos de servidor. Aquí se usa solo con fines académicos.
