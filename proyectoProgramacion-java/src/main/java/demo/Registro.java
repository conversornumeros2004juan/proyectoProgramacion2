package demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Registro extends JFrame {

    private final JTextField emailField = new JTextField();
    private final JPasswordField passField = new JPasswordField();
    private final JTextArea outputArea = new JTextArea(12, 60);

    private final ClienteAutenticacion auth;
    private final Firebase admin;

    public Registro(String apiKey) {
        super("Login/Register Firebase (Java Swing)");
        this.auth = new ClienteAutenticacion(apiKey);
        this.admin = new Firebase();
        buildUI();
    }

    private void buildUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(2, 2, 8, 8));
        form.add(new JLabel("Email:"));
        form.add(emailField);
        form.add(new JLabel("Password:"));
        form.add(passField);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        JButton btnRegister = new JButton(new AbstractAction("Registrar") {
            @Override public void actionPerformed(ActionEvent e) { RegistroExitoso(); }
        });
        JButton btnLogin = new JButton(new AbstractAction("Iniciar sesión") {
            @Override public void actionPerformed(ActionEvent e) { SesionIniciada(); }
        });
        buttons.add(btnRegister);
        buttons.add(btnLogin);

        outputArea.setEditable(false);
        outputArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(outputArea);

        JPanel top = new JPanel(new BorderLayout(8,8));
        top.add(form, BorderLayout.CENTER);
        top.add(buttons, BorderLayout.SOUTH);

        add(top, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    private void RegistroExitoso() {
        String email = emailField.getText().trim();
        String pass = new String(passField.getPassword());
        try {
            String res = auth.Registrarse(email, pass);
            outputArea.append("Registro OK:\n" + res + "\n\n");
        } catch (Exception ex) {
            outputArea.append("Error al registrar: " + ex.getMessage() + "\n\n");
        }
    }

    private void SesionIniciada() {
        String email = emailField.getText().trim();
        String pass = new String(passField.getPassword());
        try {
            String res = auth.IniciarSesion(email, pass);
            outputArea.append("Login OK:\n" + res + "\n\n");

            admin.CuentaDeServicio();
            String Usuarios = admin.ListadoUsuarios();
            outputArea.append("=== Usuarios en Firebase Auth ===\n" + Usuarios + "\n\n");
        } catch (Exception ex) {
            outputArea.append("No autorizado: " + ex.getMessage() + "\n\n");
        }
    }

    public static void main(String[] args) {
        String apiKey = "AIzaSyDPCTOMHkplN1w-zTYqK4Q1tE_-hMgYJ6c"; 
        SwingUtilities.invokeLater(() -> new Registro(apiKey).setVisible(true));
    }
}
