package edu.upc.prop.clusterxx.controladores_presentacion;
import edu.upc.prop.clusterxx.clases_dominio.Prestatgeria;
import edu.upc.prop.clusterxx.controladores.ControladorPrestatgeria;

import javax.swing.*;
import java.awt.*;

class CrearPrestatgeriaView {
    private JPanel panel;
    private Presentacion_Main controller;

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
            try {
                int altura = Integer.parseInt(alturaField.getText());
                if (controller.exists_prestatgeria(nombre)) {
                    JOptionPane.showMessageDialog(controller, "La prestatgeria ya existe, elija otro nombre.");
                } else {
                    controller.creaPrestatgeria(nombre,altura);
                    JOptionPane.showMessageDialog(controller, "Prestatgeria creada con éxito.");
                    controller.mostrarMenuPrincipalPrestatgeria();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(controller, "Por favor, ingrese una altura válida.");
            }
        });

        atrasBtn.addActionListener(e -> controller.mostrarMenuPrincipalPrestatgeria());
    }

    public JPanel getPanel() {
        return panel;
    }
}
