package edu.upc.prop.clusterxx.controladores_presentacion;

import javax.swing.*;
import java.awt.*;

/**
 * Esta clase representa la vista para crear una prestatgeria.
 */
class CrearPrestatgeriaView {
    private JPanel panel;
    private Presentacion_Main controller;

    /**
     * Constructor de la vista CrearPrestatgeriaView.
     *
     * @param controller el controlador principal de la aplicación
     */
    public CrearPrestatgeriaView(Presentacion_Main controller) {
        this.controller = controller;
        panel = new JPanel(new GridLayout(3, 2, 10, 10));

        JTextField nombreField = new JTextField();
        nombreField.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField alturaField = new JTextField();
        alturaField.setHorizontalAlignment(SwingConstants.CENTER);
        JButton crearBtn = new JButton("Crear");
        JButton atrasBtn = new JButton("Atrás");

        panel.add(new JLabel("Nombre de la Prestatgeria:", SwingConstants.CENTER));
        panel.add(nombreField);
        panel.add(new JLabel("Altura de la Prestatgeria:", SwingConstants.CENTER));
        panel.add(alturaField);
        panel.add(crearBtn);
        panel.add(atrasBtn);

        crearBtn.addActionListener(e -> {
            String nombre = nombreField.getText();
            String alturaText = alturaField.getText();

            if (nombre.isEmpty() || alturaText.isEmpty()) {
                JOptionPane.showMessageDialog(controller, "Por favor, complete todos los campos.");
            } else {
                try {
                    int altura = Integer.parseInt(alturaText);
                    if (altura <= 0) {
                        JOptionPane.showMessageDialog(controller, "Por favor, ingrese una altura mayor a 0.");
                    } else if (controller.exists_prestatgeria(nombre)) {
                        JOptionPane.showMessageDialog(controller, "La prestatgeria ya existe, elija otro nombre.");
                    } else {
                        controller.creaPrestatgeria(nombre, altura);
                        JOptionPane.showMessageDialog(controller, "Prestatgeria creada con éxito.");
                        controller.mostrarMenuPrincipalPrestatgeria();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(controller, "Por favor, ingrese una altura válida.");
                }
            }
        });

        atrasBtn.addActionListener(e -> controller.mostrarMenuPrincipalPrestatgeria());
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
