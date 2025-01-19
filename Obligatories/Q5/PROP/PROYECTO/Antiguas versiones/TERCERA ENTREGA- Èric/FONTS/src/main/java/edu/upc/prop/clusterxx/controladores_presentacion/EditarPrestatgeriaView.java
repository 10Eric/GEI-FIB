package edu.upc.prop.clusterxx.controladores_presentacion;

import javax.swing.*;
import java.awt.*;
import edu.upc.prop.clusterxx.controladores.ControladorPrestatgeria;

class EditarPrestatgeriaView {
    private JPanel panel;
    private Presentacion_Main controller;

    public EditarPrestatgeriaView(Presentacion_Main controller) {
        this.controller = controller;
        panel = new JPanel(new GridLayout(1, 1, 10, 10));

        if (controller.getPrestatgeria().isEmpty()) {
            JOptionPane.showMessageDialog(controller, "No hay prestatgerías para editar.");
            return;
        }

        String[] nombres = controller.getPrestatgeria().keySet().toArray(new String[0]);

        String seleccion = (String) JOptionPane.showInputDialog(
                controller,
                "Seleccione una prestatgeria para editar:",
                "Editar Prestatgeria",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nombres,
                nombres[0]
        );

        if (seleccion != null) {
            controller.setPrestatgeriaActual(controller.getPrestatgeria().get(seleccion).getPrestatgeria());

            JTextField nuevoNombreField = new JTextField(controller.getPrestatgeriaActual().getNom());
            JTextField nuevaAlturaField = new JTextField(String.valueOf(controller.getPrestatgeriaActual().getAltura()));

            Object[] mensaje = {
                    "Nuevo Nombre:", nuevoNombreField,
                    "Nueva Altura:", nuevaAlturaField
            };

            int opcion = JOptionPane.showConfirmDialog(controller, mensaje, "Editar Prestatgeria", JOptionPane.OK_CANCEL_OPTION);
            if (opcion == JOptionPane.OK_OPTION) {
                controller.getPrestatgeria().remove(controller.getPrestatgeriaActual().getNom());
                controller.getPrestatgeriaActual().setNom(nuevoNombreField.getText());
                controller.getPrestatgeriaActual().setAltura(Integer.parseInt(nuevaAlturaField.getText()));
                controller.getPrestatgeria().put(controller.getPrestatgeriaActual().getNom(), new ControladorPrestatgeria(controller.getPrestatgeriaActual()));
                JOptionPane.showMessageDialog(controller, "Prestatgeria editada con éxito.");
            }
            else {
                panel = null;
                controller.mostrarMenuPrincipalPrestatgeria();
            }
        }
        else {
            panel = null;
            controller.mostrarMenuPrincipalPrestatgeria();
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}
