package edu.upc.prop.clusterxx.controladores_presentacion;
import edu.upc.prop.clusterxx.clases_dominio.Prestatgeria;
import edu.upc.prop.clusterxx.controladores.ControladorPrestatgeria;

import javax.swing.*;
import java.awt.*;

class CargarPrestatgeriaView {
    private JPanel panel;
    private Presentacion_Main controller;

    public CargarPrestatgeriaView(Presentacion_Main controller) {
        this.controller = controller;
        panel = new JPanel(new GridLayout(1, 1, 10, 10));

        if (controller.getPrestatgerias().isEmpty()) {
            JOptionPane.showMessageDialog(controller, "No hay prestatgerías disponibles.");
            panel = null;
            return;
        }

        String[] nombres = controller.getPrestatgerias().toArray(new String[0]);

        String seleccion = (String) JOptionPane.showInputDialog(
                controller,
                "Seleccione una prestatgeria para cargar:",
                "Cargar Prestatgeria",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nombres,
                nombres[0]
        );

        if (seleccion != null) {
            controller.buscarPrestatgerias(seleccion);
            controller.setPrestatgeriaActual(controller.getControladorPrestatgerias());
            JOptionPane.showMessageDialog(controller, "Prestatgeria cargada.");
            controller.crearDistribucionInicial();
            panel = null;
            controller.mostrarMenuPrincipalDistribucio();
        }
        else {
            controller.mostrarMenuPrincipalPrestatgeria();
            panel = null;
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}
