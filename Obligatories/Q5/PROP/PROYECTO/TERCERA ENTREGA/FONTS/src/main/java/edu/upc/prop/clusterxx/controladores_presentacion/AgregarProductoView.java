package edu.upc.prop.clusterxx.controladores_presentacion;

import javax.swing.*;
import java.awt.*;

/**
 * Esta clase representa la vista para agregar un producto.
 */
class AgregarProductoView {
    private JPanel panel;
    private Presentacion_Main controller;

    /**
     * Constructor de la vista AgregarProductoView.
     *
     * @param controller el controlador principal de la aplicación
     */
    public AgregarProductoView(Presentacion_Main controller) {
        this.controller = controller;
        panel = new JPanel(new GridLayout(5, 2, 10, 10));

        JTextField nombreField = new JTextField();
        nombreField.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField marcaField = new JTextField();
        marcaField.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField precioField = new JTextField();
        precioField.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField cantidadField = new JTextField();
        cantidadField.setHorizontalAlignment(SwingConstants.CENTER);
        JButton agregarBtn = new JButton("Añadir");
        JButton atrasBtn = new JButton("Atrás");

        panel.add(new JLabel("Nombre:", SwingConstants.CENTER));
        panel.add(nombreField);
        panel.add(new JLabel("Marca:", SwingConstants.CENTER));
        panel.add(marcaField);
        panel.add(new JLabel("Precio:", SwingConstants.CENTER));
        panel.add(precioField);
        panel.add(new JLabel("Cantidad:", SwingConstants.CENTER));
        panel.add(cantidadField);
        panel.add(agregarBtn);
        panel.add(atrasBtn);

        agregarBtn.addActionListener(e -> {
            String nombre = nombreField.getText();
            String marca = marcaField.getText();
            double precio = 0;
            int cantidad = 0;
            boolean precioValido = true;
            boolean cantidadValida = true;

            if (nombre.isEmpty() || marca.isEmpty()) {
                JOptionPane.showMessageDialog(controller, "Por favor, complete todos los campos.");
                return;
            }

            try {
                precio = Double.parseDouble(precioField.getText());
                if (precio <= 0) {
                    precioValido = false;
                }
            } catch (NumberFormatException ex) {
                precioValido = false;
            }

            try {
                cantidad = Integer.parseInt(cantidadField.getText());
                if (cantidad < 1) {
                    cantidadValida = false;
                }
            } catch (NumberFormatException ex) {
                cantidadValida = false;
            }

            if (!precioValido && !cantidadValida) {
                JOptionPane.showMessageDialog(controller, "Por favor, ingrese valores válidos para el precio y la cantidad.");
                return;
            } else if (!precioValido) {
                JOptionPane.showMessageDialog(controller, "Por favor, ingrese un valor válido para el precio.");
                return;
            } else if (!cantidadValida) {
                JOptionPane.showMessageDialog(controller, "Por favor, ingrese un valor válido para la cantidad.");
                return;
            }

            if (controller.exists_producte(nombre)) {
                JOptionPane.showMessageDialog(controller, "El producto con ese nombre ya existe. No se puede agregar.");
                return;
            }
            int pos;
            if (controller.teProductesColocats()){
                pos = 1;
            }
            else pos = controller.get_posicio_final();
            controller.afegeix_producte(nombre, marca, precio, cantidad,pos, cantidad);
            JOptionPane.showMessageDialog(controller, "Producto añadido con éxito.");
            controller.mostrarMenuPrincipalDistribucio();
        });

        atrasBtn.addActionListener(e -> controller.mostrarMenuPrincipalDistribucio());

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
