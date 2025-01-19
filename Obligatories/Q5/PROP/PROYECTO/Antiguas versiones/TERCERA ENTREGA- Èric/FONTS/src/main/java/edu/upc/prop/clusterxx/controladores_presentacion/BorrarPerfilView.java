package edu.upc.prop.clusterxx.controladores_presentacion;

import javax.swing.*;
import java.awt.*;

// Vista para borrar perfil
class BorrarPerfilView {
    private JPanel panel;
    private Presentacion_Main controller;

    public BorrarPerfilView(Presentacion_Main controller) {
        this.controller = controller;
        panel = new JPanel(new GridLayout(1, 1, 10, 10));
            if (!controller.getPerfiles().isEmpty()) {
                String[] nombres = controller.getPerfiles().keySet().toArray(new String[0]);
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
                    controller.getPerfiles().remove(seleccion);
                    JOptionPane.showMessageDialog(controller, "Perfil eliminado.");
                }
            } else {
                JOptionPane.showMessageDialog(controller, "No hay perfiles para borrar.");
            }


    }

    public JPanel getPanel() {
        return panel;
    }
}
