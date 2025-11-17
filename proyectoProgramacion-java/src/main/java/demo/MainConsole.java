package demo;

import java.util.Scanner;

public class MainConsole {
    public static void main(String[] args) {
        String firebaseApiKey = "AIzaSyDPCTOMHkplN1w-zTYqK4Q1tE_-hMgYJ6c"; 

        ClienteAutenticacion auth = new ClienteAutenticacion(firebaseApiKey);
        Firebase admin = new Firebase();

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("== Demo Login/Register con Firebase (Consola) ==");
            System.out.print("¿Deseas Registrar ingresa(1) o  Iniciar Sesión (2)? [1/2]: ");
            String opt = sc.nextLine().trim();

            System.out.print("Email: ");
            String email = sc.nextLine().trim();
            System.out.print("contraseña: (mayor a 6 caracteres): ");
            String password = sc.nextLine().trim();

            if ("1".equals(opt)) {
    try {
        auth.Registrarse(email, password);
        System.out.println(" Usuario registrado correctamente.\n");
    } catch (Exception e) {
        System.err.println(" Error al registrar: " + e.getMessage());
    }
}

          try {
    String Json = auth.IniciarSesion(email, password);
    System.out.println("Inicio de sesión correcto.\n");

    admin.CuentaDeServicio();
    System.out.println("=== Usuarios registrados en Firebase ===");
    System.out.println(admin.ListadoUsuarios());

} catch (IllegalArgumentException e) {
    System.err.println(e.getMessage());
} catch (Exception e) {
    System.err.println("Error al iniciar sesión: " + e.getMessage());
}
    }
}
}