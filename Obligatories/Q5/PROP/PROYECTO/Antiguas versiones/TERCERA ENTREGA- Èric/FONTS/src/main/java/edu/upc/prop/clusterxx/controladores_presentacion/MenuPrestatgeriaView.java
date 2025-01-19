package edu.upc.prop.clusterxx.controladores_presentacion;

import javax.swing.*;
import java.awt.*;

class MenuPrestatgeriaView {
    private JPanel panel;
    private Presentacion_Main controller;

    public MenuPrestatgeriaView(Presentacion_Main controller) {
        this.controller = controller;
        panel = new JPanel(new GridLayout(7, 1, 10, 10));

        JButton crearBtn = new JButton("Crear Prestatgeria");
        JButton cargarBtn = new JButton("Cargar Prestatgeria");
        JButton editarBtn = new JButton("Editar Prestatgeria");
        JButton borrarBtn = new JButton("Borrar Prestatgeria");
        JButton guardarBtn = new JButton("Guardar Cambios");
        JButton atrasBtn = new JButton("Atrás");
        JButton salirBtn = new JButton("Salir");

        crearBtn.addActionListener(e -> controller.mostrarFormularioCrearPrestatgeria());
        cargarBtn.addActionListener(e -> controller.mostrarFormularioCargarPrestatgeria());
        editarBtn.addActionListener(e -> controller.mostrarFormularioEditarPrestatgeria());
        borrarBtn.addActionListener(e -> controller.mostrarFormularioBorrarPrestatgeria());
        guardarBtn.addActionListener(e -> {
            Presentacion_Main.guardarDatos();
            JOptionPane.showMessageDialog(controller, "Datos guardados con éxito.");
        });
        atrasBtn.addActionListener(e -> controller.mostrarMenuPrincipal());
        salirBtn.addActionListener(e -> {
            controller.guardarDatos();
            System.exit(0);
        });

        panel.add(crearBtn);
        panel.add(cargarBtn);
        panel.add(editarBtn);
        panel.add(borrarBtn);
        panel.add(guardarBtn);
        panel.add(atrasBtn);
        panel.add(salirBtn);
    }

    public JPanel getPanel() {
        return panel;
    }
}
