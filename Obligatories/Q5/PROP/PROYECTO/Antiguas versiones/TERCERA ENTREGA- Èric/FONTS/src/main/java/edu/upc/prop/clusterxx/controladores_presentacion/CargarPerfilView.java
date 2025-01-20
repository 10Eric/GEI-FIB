package edu.upc.prop.clusterxx.controladores_presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CargarPerfilView extends JPanel {
    private JPanel panel;
    private JTextField usuarioField;
    private JPasswordField contraseñaField;
    private JButton cargarBtn;
    private JButton atrasBtn;
    private Presentacion_Main controller;

    public CargarPerfilView(Presentacion_Main controller) {
        this.controller = controller;
        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        usuarioField = new JTextField();
        usuarioField.setHorizontalAlignment(SwingConstants.CENTER);
        contraseñaField = new JPasswordField();
        contraseñaField.setHorizontalAlignment(SwingConstants.CENTER);
        cargarBtn = new JButton("Cargar");
        atrasBtn = new JButton("Atrás");

        panel.add(new JLabel("Usuario:", SwingConstants.CENTER));
        panel.add(usuarioField);
        panel.add(new JLabel("Contraseña:", SwingConstants.CENTER));
        panel.add(contraseñaField);
        panel.add(cargarBtn);
        panel.add(atrasBtn);

        cargarBtn.addActionListener(e -> {
            String usuario = getUsuario();
            String contraseña = getContraseña();
            if (controller.getPerfiles().containsKey(usuario) && controller.getPerfiles().get(usuario).getContrasenya().equals(contraseña)) {
                controller.setPerfilActual(controller.getPerfiles().get(usuario));
                JOptionPane.showMessageDialog(controller, "Perfil cargado correctamente.");
                controller.convertirYAsignarPrestatgerias();
                controller.mostrarMenuPrincipalPrestatgeria();
            } else {
                JOptionPane.showMessageDialog(controller, "Usuario o contraseña incorrectos.");
            }
        });

        atrasBtn.addActionListener(e -> controller.mostrarMenuPrincipal());


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
