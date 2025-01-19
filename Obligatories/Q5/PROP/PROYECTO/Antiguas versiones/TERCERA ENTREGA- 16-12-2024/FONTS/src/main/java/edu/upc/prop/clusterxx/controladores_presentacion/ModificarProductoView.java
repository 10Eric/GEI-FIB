package edu.upc.prop.clusterxx.controladores_presentacion;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class ModificarProductoView {
    private JPanel panel;
    private Presentacion_Main controller;

    public ModificarProductoView(Presentacion_Main controller) {
        this.controller = controller;
        panel = new JPanel(new GridLayout(1, 1, 10, 10));
        ArrayList<String> productos = controller.getControladorDistribucio().getProductes();
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(controller, "No hay productos para modificar.");
            panel = null;
            return;
        }

        String seleccion = (String) JOptionPane.showInputDialog(
                controller,
                "Seleccione el producto a modificar:",
                "Modificar Producto",
                JOptionPane.QUESTION_MESSAGE,
                null,
                productos.toArray(),
                productos.get(0)
        );

        if (seleccion != null) {
            ArrayList<String> info = controller.getControladorDistribucio().get_info_producte(seleccion);

            panel = new JPanel(new GridLayout(5, 2, 10, 10));
            JTextField precioActualField = new JTextField(info.get(2));
            precioActualField.setHorizontalAlignment(SwingConstants.CENTER);
            JTextField cantidadActualField = new JTextField(info.get(3));
            cantidadActualField.setHorizontalAlignment(SwingConstants.CENTER);
            JTextField nuevoPrecioField = new JTextField();
            nuevoPrecioField.setHorizontalAlignment(SwingConstants.CENTER);
            JTextField nuevaCantidadField = new JTextField();
            nuevaCantidadField.setHorizontalAlignment(SwingConstants.CENTER);
            JButton guardarBtn = new JButton("Guardar");
            JButton atrasBtn = new JButton("Atrás");

            precioActualField.setEditable(false);
            cantidadActualField.setEditable(false);

            panel.add(createCenteredLabel("Precio Actual:"));
            panel.add(precioActualField);
            panel.add(createCenteredLabel("Cantidad Actual:"));
            panel.add(cantidadActualField);
            panel.add(createCenteredLabel("Nuevo Precio:"));
            panel.add(nuevoPrecioField);
            panel.add(createCenteredLabel("Nueva Cantidad:"));
            panel.add(nuevaCantidadField);
            panel.add(guardarBtn);
            panel.add(atrasBtn);

            guardarBtn.addActionListener(e -> {
                try {
                    double nuevoPrecio = Double.parseDouble(nuevoPrecioField.getText());
                    int nuevaCantidad = Integer.parseInt(nuevaCantidadField.getText());

                    controller.getControladorDistribucio().set_precio_producto(seleccion, nuevoPrecio);
                    controller.getControladorDistribucio().set_cantidad_producto(seleccion, nuevaCantidad);
                    JOptionPane.showMessageDialog(controller, "Producto modificado con éxito.");
                    controller.mostrarMenuPrincipalDistribucio();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(controller, "Por favor, ingrese valores válidos para el precio y la cantidad.");
                }
            });

            atrasBtn.addActionListener(e -> controller.mostrarMenuPrincipalDistribucio());
        }
        else {
            panel = null;
            controller.mostrarMenuPrincipalDistribucio();
        }
    }

    private JLabel createCenteredLabel(String text) {
        return new JLabel(text, SwingConstants.CENTER);
    }

    public JPanel getPanel() {
        return panel;
    }
}
