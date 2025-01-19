package edu.upc.prop.clusterxx.controladores_presentacion;
import javax.swing.*;
import java.awt.*;

class MenuPrincipalView {
    private JPanel panel;
    private Presentacion_Main controller;

    public MenuPrincipalView(Presentacion_Main controller) {
        this.controller = controller;
        panel = new JPanel(new GridLayout(6, 1, 10, 10));

        JButton crearBtn = new JButton("Crear Perfil");
        JButton cargarBtn = new JButton("Cargar Perfil");
        JButton borrarBtn = new JButton("Borrar Perfil");
        JButton editarBtn = new JButton("Editar Perfil");
        JButton guardarBtn = new JButton("Guardar Cambios");
        JButton salirBtn = new JButton("Salir");

        crearBtn.addActionListener(e -> controller.mostrarFormularioCrearPerfil());
        cargarBtn.addActionListener(e -> controller.mostrarFormularioCargarPerfil());
        borrarBtn.addActionListener(e -> controller.mostrarFormularioBorrarPerfil());
        editarBtn.addActionListener(e -> {
            if (controller.getPerfiles() != null) {
                controller.mostrarFormularioEditarPerfil();
            } else {
                JOptionPane.showMessageDialog(controller, "No hay perfiles disponibles para editar.");            }
        });
        guardarBtn.addActionListener(e -> {
            Presentacion_Main.guardarDatos();
            JOptionPane.showMessageDialog(panel, "Datos guardados con éxito.");
        });
        salirBtn.addActionListener(e -> {
            Presentacion_Main.guardarDatos();
            System.exit(0);
        });

        panel.add(crearBtn);
        panel.add(cargarBtn);
        panel.add(borrarBtn);
        panel.add(editarBtn);
        panel.add(guardarBtn);
        panel.add(salirBtn);
    }

    public JPanel getPanel() {
        return panel;
    }
}
