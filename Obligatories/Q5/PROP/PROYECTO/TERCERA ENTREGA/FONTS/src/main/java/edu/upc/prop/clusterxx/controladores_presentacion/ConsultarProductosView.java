package edu.upc.prop.clusterxx.controladores_presentacion;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Esta clase representa la vista para consultar productos.
 */
class ConsultarProductosView {
    private JPanel panel;
    private Presentacion_Main controller;

    /**
     * Constructor de la vista ConsultarProductosView.
     *
     * @param controller el controlador principal de la aplicación
     */
    public ConsultarProductosView(Presentacion_Main controller) {
        this.controller = controller;
        panel = new JPanel(new GridLayout(1, 1, 10, 10));
        ArrayList<String> productos = controller.get_productes();
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(controller, "No hay productos para consultar.");
            panel = null;
            return;
        }

        JOptionPane.showMessageDialog(controller, "Productos actuales:\n" + String.join("\n", productos));
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
