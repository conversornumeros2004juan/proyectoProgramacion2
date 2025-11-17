package demo;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ClienteAutenticacion {
    private final String apiKey;
    private final HttpClient http = HttpClient.newHttpClient();

    public ClienteAutenticacion(String apiKey) {
        this.apiKey = apiKey;
    }

    public String Registrarse(String email, String password) throws Exception {
        String url = "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + apiKey;
        String formato = String.format("{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}", email, password);
        HttpRequest req = HttpRequest.newBuilder(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(formato, StandardCharsets.UTF_8))
                .build();
        HttpResponse<String> res = http.send(req, HttpResponse.BodyHandlers.ofString());
        if (res.statusCode() == 200) return res.body();
        throw new RuntimeException("SignUp failed: HTTP " + res.statusCode() + " - " + res.body());
    }

    public String IniciarSesion(String email, String password) throws Exception {
    String url = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + apiKey;
    String formato = String.format("{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}", email, password);

    HttpRequest req = HttpRequest.newBuilder(URI.create(url))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(formato, StandardCharsets.UTF_8))
            .build();

    HttpResponse<String> res = http.send(req, HttpResponse.BodyHandlers.ofString());

    if (res.statusCode() == 200) return res.body();

  
    String responseBody = res.body();
    if (responseBody != null && responseBody.contains("INVALID_LOGIN_CREDENTIALS")) {
        throw new IllegalArgumentException("Usuario o contraseña incorrectos.");
    }

    throw new RuntimeException("Error al iniciar sesión: " + responseBody);
}
}
