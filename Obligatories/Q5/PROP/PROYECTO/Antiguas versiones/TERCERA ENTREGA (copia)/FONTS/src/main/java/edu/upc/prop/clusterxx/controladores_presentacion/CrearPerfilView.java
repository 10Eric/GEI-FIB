package edu.upc.prop.clusterxx.controladores_presentacion;

import edu.upc.prop.clusterxx.clases_dominio.Perfil;
import edu.upc.prop.clusterxx.controladores.ControladorPerfil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CrearPerfilView extends JPanel {
    private JPanel panel;
    private JTextField usuarioField;
    private JPasswordField contraseñaField;
    private JButton crearBtn;
    private JButton atrasBtn;
    private Presentacion_Main controller;

    public CrearPerfilView(Presentacion_Main controller) {
        this.controller = controller;
        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        usuarioField = new JTextField();
        usuarioField.setHorizontalAlignment(SwingConstants.CENTER);
        contraseñaField = new JPasswordField();
        contraseñaField.setHorizontalAlignment(SwingConstants.CENTER);
        crearBtn = new JButton("Crear");
        atrasBtn = new JButton("Atrás");

        crearBtn.addActionListener(e -> {
            String usuario = getUsuario();
            String contraseña = getContraseña();
            if (controller.getUsuarios().contains(usuario)) {
                JOptionPane.showMessageDialog(controller, "El usuario ya existe, elija otro.");
            } else {
                controller.creaPerfil(usuario,contraseña);
                JOptionPane.showMessageDialog(controller, "Perfil creado con éxito.");
                controller.mostrarMenuPrincipal();
            }
        });

        atrasBtn.addActionListener(e -> controller.mostrarMenuPrincipal());

        panel.add(new JLabel("Usuario:", SwingConstants.CENTER));
        panel.add(usuarioField);
        panel.add(new JLabel("Contraseña:", SwingConstants.CENTER));
        panel.add(contraseñaField);
        panel.add(crearBtn);
        panel.add(atrasBtn);
    }

    public String getUsuario() {
        return usuarioField.getText();
    }

    public String getContraseña() {
        return new String(contraseñaField.getPassword());
    }

    public JPanel getPanel() {
        return panel;
    }
}
