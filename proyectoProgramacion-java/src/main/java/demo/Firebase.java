package demo;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ListUsersPage;
import com.google.firebase.auth.ExportedUserRecord;

import java.io.InputStream;

public class Firebase {

    public void CuentaDeServicio() throws Exception {
        try (InputStream serviceAccount = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("firebase-service-account.json")) {

            if (serviceAccount == null) {
                throw new IllegalStateException("No se encontró firebase-service-account.json en resources/");
            }

            FirebaseOptions opcion = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(opcion);
            }
        }
    }

    public String ListadoUsuarios() throws Exception {
        StringBuilder sb = new StringBuilder();
        ListUsersPage page = FirebaseAuth.getInstance().listUsers(null);
        int count = 0;
        while (page != null) {
            for (ExportedUserRecord user : page.getValues()) {
                String email = user.getEmail() != null ? user.getEmail() : "(sin email)";
                sb.append("• UID: ").append(user.getUid()).append(" | email: ").append(email).append("\n");
                count++;
            }
            page = page.getNextPage();
        }
        sb.append("Total usuarios: ").append(count);
        return sb.toString();
    }
}
