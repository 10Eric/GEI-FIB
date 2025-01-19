package edu.upc.prop.clusterxx.controladores_presentacion;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class EliminarProductoView {
    private JPanel panel;
    private Presentacion_Main controller;

    public EliminarProductoView(Presentacion_Main controller) {
        this.controller = controller;
        panel = new JPanel(new GridLayout(1, 1, 10, 10));
        ArrayList<String> productos = controller.getControladorDistribucio().getProductes();
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(controller, "No hay productos para eliminar.");
            panel = null;
            return;
        }

        String seleccion = (String) JOptionPane.showInputDialog(
                controller,
                "Seleccione el producto a eliminar:",
                "Eliminar Producto",
                JOptionPane.QUESTION_MESSAGE,
                null,
                productos.toArray(),
                productos.get(0)
        );

        if (seleccion != null) {
            controller.getControladorDistribucio().eliminaProducte(seleccion);
            JOptionPane.showMessageDialog(controller, "Producto eliminado.");
        }
        else {
            panel = null;
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}
