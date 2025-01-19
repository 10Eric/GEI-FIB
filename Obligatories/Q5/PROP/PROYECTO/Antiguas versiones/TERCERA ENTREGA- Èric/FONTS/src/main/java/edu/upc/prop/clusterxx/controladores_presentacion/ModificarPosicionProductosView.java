package edu.upc.prop.clusterxx.controladores_presentacion;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ModificarPosicionProductosView {
    JPanel panel;
    private Presentacion_Main controller;

    public ModificarPosicionProductosView(Presentacion_Main controller) {
        this.controller = controller;
        ArrayList<String> productos = controller.getControladorDistribucio().getProductes();
        panel = new JPanel(new GridLayout(2, 2, 10, 10));
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(controller, "No hay productos para modificar.");
            panel = null;
            return;
        }

        JComboBox<String> producto1Combo = new JComboBox<>(productos.toArray(new String[0]));
        JComboBox<String> producto2Combo = new JComboBox<>(productos.toArray(new String[0]));

        panel.add(new JLabel("Seleccione el primer producto:"));
        panel.add(producto1Combo);
        panel.add(new JLabel("Seleccione el segundo producto:"));
        panel.add(producto2Combo);

        int result = JOptionPane.showConfirmDialog(panel, panel, "Modificar Posición de Productos", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String producto1 = (String) producto1Combo.getSelectedItem();
            String producto2 = (String) producto2Combo.getSelectedItem();

            if (producto1.equals(producto2)) {
                JOptionPane.showMessageDialog(controller, "Seleccione dos productos diferentes.");
                controller.ModificarPosicionProductos();
            }

            int index1 = productos.indexOf(producto1);
            int index2 = productos.indexOf(producto2);

            controller.getControladorDistribucio().intercambiar_productes(index1, index2);
        }
        else {
            panel = null;
        }
    }

    JPanel getPanel() {
        return panel;
    }
}
