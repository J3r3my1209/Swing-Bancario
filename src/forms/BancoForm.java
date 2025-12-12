// Archivo: forms/BancoForm.java
package forms;

import model.Cliente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;

public class BancoForm extends JFrame {
    private Cliente cliente;
    private JLabel lbNombre;
    private JLabel lbSaldo;
    private JTextArea taHistorial;
    private final DecimalFormat df = new DecimalFormat("$#.00");

    public BancoForm(Cliente cliente) {
        this.cliente = cliente;
        initComponents();
        actualizarVista();
    }

    private void initComponents() {
        setTitle("Simulador Bancario - Operaciones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 420);
        setLocationRelativeTo(null);

        JPanel content = new JPanel();
        content.setBorder(new EmptyBorder(10,10,10,10));
        content.setLayout(new BorderLayout(10,10));

        // Arriba: nombre y saldo
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new GridLayout(2,1));
        lbNombre = new JLabel();
        lbNombre.setFont(lbNombre.getFont().deriveFont(Font.BOLD, 16f));
        lbSaldo = new JLabel();
        lbSaldo.setFont(lbSaldo.getFont().deriveFont(Font.PLAIN, 14f));
        panelTop.add(lbNombre);
        panelTop.add(lbSaldo);

        content.add(panelTop, BorderLayout.NORTH);

        // Centro: historial
        taHistorial = new JTextArea();
        taHistorial.setEditable(false);
        taHistorial.setLineWrap(true);
        taHistorial.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(taHistorial);
        content.add(scroll, BorderLayout.CENTER);

        // Derecha: botones de operaciones
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(4,1,8,8));

        JButton btnDepositar = new JButton("Depósito");
        btnDepositar.addActionListener(e -> accionDeposito());

        JButton btnRetirar = new JButton("Retiro");
        btnRetirar.addActionListener(e -> accionRetiro());

        JButton btnTransferir = new JButton("Transferencia");
        btnTransferir.addActionListener(e -> accionTransferencia());

        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(e -> System.exit(0));

        panelButtons.add(btnDepositar);
        panelButtons.add(btnRetirar);
        panelButtons.add(btnTransferir);
        panelButtons.add(btnSalir);

        content.add(panelButtons, BorderLayout.EAST);

        add(content);
    }

    private void actualizarVista() {
        lbNombre.setText("Cliente: " + cliente.getNombre());
        lbSaldo.setText(String.format("Saldo actual: $%.2f", cliente.getSaldo()));

        List<String> historial = cliente.getHistorial();
        StringBuilder sb = new StringBuilder();
        for (String entry : historial) {
            sb.append(entry).append("\n");
        }
        taHistorial.setText(sb.toString());
    }

    private void accionDeposito() {
        String input = JOptionPane.showInputDialog(this, "Ingrese el monto a depositar:", "Depósito", JOptionPane.PLAIN_MESSAGE);
        if (input == null) return; // usuario canceló
        input = input.trim();
        double monto;
        try {
            monto = Double.parseDouble(input);
            if (monto <= 0) {
                JOptionPane.showMessageDialog(this, "Ingrese un monto positivo", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            cliente.depositar(monto);
            JOptionPane.showMessageDialog(this, String.format("Depósito exitoso por $%.2f", monto), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            actualizarVista();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Monto inválido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void accionRetiro() {
        String input = JOptionPane.showInputDialog(this, "Ingrese el monto a retirar:", "Retiro", JOptionPane.PLAIN_MESSAGE);
        if (input == null) return;
        input = input.trim();
        double monto;
        try {
            monto = Double.parseDouble(input);
            if (monto <= 0) {
                JOptionPane.showMessageDialog(this, "Ingrese un monto positivo", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (cliente.retirar(monto)) {
                JOptionPane.showMessageDialog(this, String.format("Retiro exitoso por $%.2f", monto), "Éxito", JOptionPane.INFORMATION_MESSAGE);
                actualizarVista();
            } else {
                JOptionPane.showMessageDialog(this, "Saldo insuficiente", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Monto inválido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void accionTransferencia() {
        JPanel panel = new JPanel(new GridLayout(2,2,5,5));
        JTextField tfDest = new JTextField();
        JTextField tfMonto = new JTextField();
        panel.add(new JLabel("Destinatario:"));
        panel.add(tfDest);
        panel.add(new JLabel("Monto:"));
        panel.add(tfMonto);

        int result = JOptionPane.showConfirmDialog(this, panel, "Transferencia", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result != JOptionPane.OK_OPTION) return;

        String destinatario = tfDest.getText().trim();
        String montoStr = tfMonto.getText().trim();
        if (destinatario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre del destinatario", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double monto;
        try {
            monto = Double.parseDouble(montoStr);
            if (monto <= 0) {
                JOptionPane.showMessageDialog(this, "Ingrese un monto positivo", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (cliente.transferir(destinatario, monto)) {
                JOptionPane.showMessageDialog(this, String.format("Transferencia exitosa a %s por $%.2f", destinatario, monto), "Éxito", JOptionPane.INFORMATION_MESSAGE);
                actualizarVista();
            } else {
                JOptionPane.showMessageDialog(this, "Saldo insuficiente", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Monto inválido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}