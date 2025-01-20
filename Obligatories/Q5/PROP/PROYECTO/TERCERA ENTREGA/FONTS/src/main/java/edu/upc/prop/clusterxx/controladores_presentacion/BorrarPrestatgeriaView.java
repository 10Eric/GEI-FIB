package edu.upc.prop.clusterxx.controladores_presentacion;

import javax.swing.*;
import java.awt.*;

/**
 * Esta clase representa la vista para borrar una prestatgeria.
 */
class BorrarPrestatgeriaView {
    private JPanel panel;
    private Presentacion_Main controller;

    /**
     * Constructor de la vista BorrarPrestatgeriaView.
     *
     * @param controller el controlador principal de la aplicación
     */
    public BorrarPrestatgeriaView(Presentacion_Main controller) {
        this.controller = controller;
        panel = new JPanel(new GridLayout(1, 1, 10, 10));

        if (controller.getPrestatgerias().isEmpty()) {
            JOptionPane.showMessageDialog(controller, "No hay prestatgerías para borrar.");
            return;
        }

        String[] nombres = controller.getPrestatgerias().toArray(new String[0]);

        String seleccion = (String) JOptionPane.showInputDialog(
                controller,
                "Seleccione una prestatgeria para borrar:",
                "Borrar Prestatgeria",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nombres,
                nombres[0]
        );

        if (seleccion != null) {
            controller.removePrestatgeria_llista(seleccion);
            controller.eliminarPrestatgeria(seleccion);
            JOptionPane.showMessageDialog(controller, "Prestatgeria borrada.");
        }
        else {
            panel = null;
            controller.mostrarMenuPrincipalPrestatgeria();
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
