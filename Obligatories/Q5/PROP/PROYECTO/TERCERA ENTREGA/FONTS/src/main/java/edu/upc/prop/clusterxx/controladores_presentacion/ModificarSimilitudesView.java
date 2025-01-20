package edu.upc.prop.clusterxx.controladores_presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Esta clase representa la vista para modificar las similitudes entre productos.
 */
class ModificarSimilitudesView {
    private JPanel panel;
    private Presentacion_Main controller;

    /**
     * Constructor de la vista ModificarSimilitudesView.
     *
     * @param controller el controlador principal de la aplicación
     */
    public ModificarSimilitudesView(Presentacion_Main controller) {
        this.controller = controller;
        panel = new JPanel(new GridLayout(4, 2, 10, 10));
        ArrayList<String> productos = controller.getControladorDistribucio().getProductes();
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(controller, "No hay productos para modificar similitudes.");
            panel = null;
            return;
        }

        JComboBox<String> producto1Combo = new JComboBox<>(productos.toArray(new String[0]));
        JComboBox<String> producto2Combo = new JComboBox<>(productos.toArray(new String[0]));
        JTextField similitudField = new JTextField();
        similitudField.setHorizontalAlignment(SwingConstants.CENTER);
        JButton actualizarBtn = new JButton("Actualizar");
        JButton atrasBtn = new JButton("Atrás");

        panel.add(createCenteredLabel("Seleccione el primer producto:"));
        panel.add(producto1Combo);
        panel.add(createCenteredLabel("Seleccione el segundo producto:"));
        panel.add(producto2Combo);
        panel.add(createCenteredLabel("Valor de similitud (0-100):"));
        panel.add(similitudField);
        panel.add(actualizarBtn);
        panel.add(atrasBtn);

        ActionListener updateFieldAction = e -> {
            String producto1 = (String) producto1Combo.getSelectedItem();
            String producto2 = (String) producto2Combo.getSelectedItem();

            if (!producto1.equals(producto2)) {
                int valorActual = controller.getControladorDistribucio().getSimilitud(producto1, producto2);
                similitudField.setText(String.valueOf(valorActual));
            } else {
                similitudField.setText("");
            }
        };

        producto1Combo.addActionListener(updateFieldAction);
        producto2Combo.addActionListener(updateFieldAction);

        actualizarBtn.addActionListener(e -> {
            String producto1 = (String) producto1Combo.getSelectedItem();
            String producto2 = (String) producto2Combo.getSelectedItem();

            if (producto1.equals(producto2)) {
                JOptionPane.showMessageDialog(controller, "Por favor, seleccione dos productos diferentes.");
                return;
            }

            try {
                int valorSimilitud = Integer.parseInt(similitudField.getText());
                if (valorSimilitud < 0 || valorSimilitud > 100) {
                    JOptionPane.showMessageDialog(controller, "El valor de similitud debe estar entre 0 y 100.");
                    return;
                }

                controller.getControladorDistribucio().set_similitud(producto1, producto2, valorSimilitud);
                controller.getControladorDistribucio().set_similitud(producto2, producto1, valorSimilitud);

                JOptionPane.showMessageDialog(controller, "Similitud actualizada con éxito.");
                controller.mostrarMenuPrincipalDistribucio(); // Regresa al menú principal
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(controller, "Por favor, ingrese un valor numérico válido para la similitud.");
            }
        });

        atrasBtn.addActionListener(e -> controller.mostrarMenuPrincipalDistribucio());
    }

    /**
     * Crea una etiqueta centrada.
     *
     * @param text el texto de la etiqueta
     * @return la etiqueta centrada
     */
    private JLabel createCenteredLabel(String text) {
        return new JLabel(text, SwingConstants.CENTER);
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
