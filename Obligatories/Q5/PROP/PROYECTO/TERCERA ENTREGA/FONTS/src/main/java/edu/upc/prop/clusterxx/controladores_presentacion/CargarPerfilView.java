package edu.upc.prop.clusterxx.controladores_presentacion;

import javax.swing.*;
import java.awt.*;

/**
 * Esta clase representa la vista para cargar un perfil.
 */
public class CargarPerfilView extends JPanel {
    private JPanel panel;
    private JTextField usuarioField;
    private JPasswordField contraseñaField;
    private JButton cargarBtn;
    private JButton atrasBtn;
    private Presentacion_Main controller;

    /**
     * Constructor de la vista CargarPerfilView.
     *
     * @param controller el controlador principal de la aplicación
     */
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
            controller.buscarPerfiles(usuario);
            if (controller.existeix_controlador_perfil() && controller.get_contraseña_perfil().equals(contraseña)) {
                controller.setPerfilActual(controller.getControladorPerfiles());
                JOptionPane.showMessageDialog(controller, "Perfil cargado correctamente.");
                controller.cargarPrestatgerias();
                controller.mostrarMenuPrincipalPrestatgeria();
            } else {
                JOptionPane.showMessageDialog(controller, "Usuario o contraseña incorrectos.");
            }
        });

        atrasBtn.addActionListener(e -> controller.mostrarMenuPrincipal());


    }

    /**
     * Obtiene el nombre de usuario ingresado.
     *
     * @return el nombre de usuario
     */
    public String getUsuario() {
        return usuarioField.getText();
    }

    /**
     * Obtiene la contraseña ingresada.
     *
     * @return la contraseña
     */
    public String getContraseña() {
        return new String(contraseñaField.getPassword());
    }

    /**
     * Obtiene el panel de la vista.
     *
     * @return el panel de la vista
     */
    public JPanel getPanel() {
        return panel;
    }
}
