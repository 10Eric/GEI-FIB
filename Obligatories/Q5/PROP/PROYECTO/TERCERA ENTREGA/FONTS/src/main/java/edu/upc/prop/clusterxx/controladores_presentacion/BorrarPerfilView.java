package edu.upc.prop.clusterxx.controladores_presentacion;

import javax.swing.*;
import java.awt.*;

/**
 * Esta clase representa la vista para borrar un perfil.
 */
class BorrarPerfilView {
    private JPanel panel;
    private Presentacion_Main controller;

    /**
     * Constructor de la vista BorrarPerfilView.
     *
     * @param controller el controlador principal de la aplicación
     */
    public BorrarPerfilView(Presentacion_Main controller) {
        this.controller = controller;
        panel = new JPanel(new GridLayout(1, 1, 10, 10));
            if (!controller.getUsuarios().isEmpty()) {
                String[] nombres = controller.getUsuarios().toArray(new String[0]);
                String seleccion = (String) JOptionPane.showInputDialog(
                        controller,
                        "Seleccione el perfil a borrar:",
                        "Borrar Perfil",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        nombres,
                        nombres[0]
                );
                if (seleccion != null) {
                    JLabel passwordLabel = new JLabel("Ingrese la Contraseña:");
                    passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    JPasswordField passwordField = new JPasswordField();
                    passwordField.setHorizontalAlignment(SwingConstants.CENTER);
                    panel.add(passwordLabel);
                    panel.add(passwordField);
                    int option = JOptionPane.showConfirmDialog(
                            controller,
                            panel,
                            "Borrar Perfil",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE
                    );
                    if (option == JOptionPane.OK_OPTION) {
                        controller.buscarPerfiles(seleccion);
                        String password = new String(passwordField.getPassword());
                        if (controller.get_contraseña_perfil().equals(password)) {
                            controller.borrarPrestatgerias(seleccion);
                            controller.eliminarPerfil(seleccion);
                            JOptionPane.showMessageDialog(controller, "Perfil eliminado.");
                        } else {
                            JOptionPane.showMessageDialog(controller, "Contraseña incorrecta. No se pudo eliminar el perfil.");
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(controller, "No hay perfiles para borrar.");
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
