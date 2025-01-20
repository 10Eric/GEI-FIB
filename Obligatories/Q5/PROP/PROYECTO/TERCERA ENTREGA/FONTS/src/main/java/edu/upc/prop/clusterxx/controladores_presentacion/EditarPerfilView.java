package edu.upc.prop.clusterxx.controladores_presentacion;

import javax.swing.*;
import java.awt.*;

/**
 * Esta clase representa la vista para editar un perfil.
 */
class EditarPerfilView {
    private JPanel panel;
    private Presentacion_Main controller;

    /**
     * Constructor de la vista EditarPerfilView.
     *
     * @param controller el controlador principal de la aplicación
     */
    public EditarPerfilView(Presentacion_Main controller) {
        this.controller = controller;
        if (controller.es_buit()) {
            JOptionPane.showMessageDialog(controller, "No hay Perfiles para editar.");
            return;
        }
        panel = new JPanel(new GridLayout(1, 1, 10, 10));
        String[] nombres = controller.getUsuarios().toArray(new String[0]);
        String seleccion = (String) JOptionPane.showInputDialog(
                controller,
                "Seleccione el perfil a editar:",
                "Editar Perfil",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nombres,
                nombres[0]
        );

        if (seleccion != null) {
            panel = new JPanel(new GridLayout(2, 2, 10, 10));
            JPasswordField contraseñaField = new JPasswordField();
            contraseñaField.setHorizontalAlignment(SwingConstants.CENTER);
            JButton loginBtn = new JButton("Login");
            JButton atrasBtn = new JButton("Atrás");

            panel.add(new JLabel("Contraseña:", SwingConstants.CENTER));
            panel.add(contraseñaField);
            panel.add(loginBtn);
            panel.add(atrasBtn);

            loginBtn.addActionListener(e -> {
                String contraseña = new String(contraseñaField.getPassword());
                controller.buscarPerfiles(seleccion);
                if (controller.get_contraseña_perfil().equals(contraseña)) {
                    JOptionPane.showMessageDialog(controller, "Contraseña correcta.");
                    controller.mostrarEditarPerfil();

                } else {
                    JOptionPane.showMessageDialog(controller, "Contraseña incorrecta.");
                }
            });

            atrasBtn.addActionListener(e -> controller.mostrarMenuPrincipal());
        }
        else {
            controller.mostrarMenuPrincipal();
            panel = null;
        }



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
