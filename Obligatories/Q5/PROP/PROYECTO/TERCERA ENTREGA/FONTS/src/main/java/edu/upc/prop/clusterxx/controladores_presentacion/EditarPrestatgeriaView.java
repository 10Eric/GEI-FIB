package edu.upc.prop.clusterxx.controladores_presentacion;

import javax.swing.*;
import java.awt.*;

/**
 * Esta clase representa la vista para editar una prestatgeria.
 */
class EditarPrestatgeriaView {
    private JPanel panel;
    private Presentacion_Main controller;

    /**
     * Constructor de la vista EditarPrestatgeriaView.
     *
     * @param controller el controlador principal de la aplicación
     */
    public EditarPrestatgeriaView(Presentacion_Main controller) {
        this.controller = controller;
        panel = new JPanel(new GridLayout(1, 1, 10, 10));

        if (controller.getPrestatgerias().isEmpty()) {
            JOptionPane.showMessageDialog(controller, "No hay prestatgerías para editar.");
            return;
        }

        String[] nombres = controller.getPrestatgerias().toArray(new String[0]);

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
            controller.buscarPrestatgerias(seleccion);
            controller.setPrestatgeriaActual(controller.getControladorPrestatgerias());

            JTextField nuevoNombreField = new JTextField(controller.getPrestatgeriaActual().getNom());
            JTextField nuevaAlturaField = new JTextField(String.valueOf(controller.getPrestatgeriaActual().getAltura()));
            String nombreActual = controller.getPrestatgeriaActual().getNom();

            Object[] mensaje = {
                    "Nuevo Nombre:", nuevoNombreField,
                    "Nueva Altura:", nuevaAlturaField
            };

            int opcion;
            do {
                opcion = JOptionPane.showConfirmDialog(controller, mensaje, "Editar Prestatgeria", JOptionPane.OK_CANCEL_OPTION);
                if (opcion == JOptionPane.OK_OPTION) {
                    String nuevoNombre = nuevoNombreField.getText();
                    String nuevaAlturaText = nuevaAlturaField.getText();

                    if (nuevoNombre.isEmpty() || nuevaAlturaText.isEmpty()) {
                        JOptionPane.showMessageDialog(controller, "Por favor, complete todos los campos.");
                    } else {
                        try {
                            int nuevaAltura = Integer.parseInt(nuevaAlturaField.getText());
                            if (nuevaAltura > 0) {
                                if (controller.getPrestatgerias().contains(nuevoNombreField.getText()) && !nombreActual.equals(nuevoNombreField.getText())) {
                                    JOptionPane.showMessageDialog(controller, "Ya existe una prestatgeria con ese nombre.");
                                } else {
                                    controller.eliminarPrestatgeria(controller.getPrestatgeriaActual().getNom());
                                    controller.getPrestatgeriaActual().setNom(nuevoNombreField.getText());
                                    controller.getPrestatgeriaActual().setAltura(nuevaAltura);
                                    controller.creaPrestatgeria2(controller.getPrestatgeriaActual().getNom());
                                    JOptionPane.showMessageDialog(controller, "Prestatgeria editada con éxito.");
                                    break;
                                }
                            } else {
                                JOptionPane.showMessageDialog(controller, "Por favor, ingrese una altura mayor a 0.");
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(controller, "Por favor, ingrese una altura válida.");
                        }
                    }
                }
            } while (opcion == JOptionPane.OK_OPTION);
        }

        else {
            panel = null;
            controller.mostrarMenuPrincipalPrestatgeria();
        }
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
