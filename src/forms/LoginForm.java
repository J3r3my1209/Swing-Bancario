// Archivo: forms/LoginForm.java
package forms;

import model.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginForm extends JFrame {
    private JTextField tfUsuario;
    private JPasswordField pfContrasena;

    // Credenciales predefinidas
    private final String USUARIO_VALIDO = "cliente123";
    private final String CONTRASENA_VALIDA = "clave456";

    public LoginForm() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Login - Simulador Bancario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(360, 200);
        setLocationRelativeTo(null);

        JLabel lbUsuario = new JLabel("Usuario:");
        JLabel lbContrasena = new JLabel("Contraseña:");

        tfUsuario = new JTextField();
        pfContrasena = new JPasswordField();

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticar();
            }
        });

        // Layout simple con GroupLayout
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lbUsuario)
                                .addComponent(lbContrasena))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(tfUsuario)
                                .addComponent(pfContrasena)))
                .addComponent(btnIngresar)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lbUsuario)
                        .addComponent(tfUsuario))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lbContrasena)
                        .addComponent(pfContrasena))
                .addComponent(btnIngresar)
        );

        add(panel);
    }

    private void autenticar() {
        String usuario = tfUsuario.getText().trim();
        String contrasena = new String(pfContrasena.getPassword());

        if (USUARIO_VALIDO.equals(usuario) && CONTRASENA_VALIDA.equals(contrasena)) {
            // Credenciales correctas -> abrir BancoForm
            Cliente cliente = new Cliente("Cliente", 1000.00);
            SwingUtilities.invokeLater(() -> {
                BancoForm bancoForm = new BancoForm(cliente);
                bancoForm.setVisible(true);
            });
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new LoginForm().setVisible(true);
        });
    }
}