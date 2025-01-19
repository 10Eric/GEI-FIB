package edu.upc.prop.clusterxx.controladores_presentacion;

import edu.upc.prop.clusterxx.clases_dominio.Perfil;
import edu.upc.prop.clusterxx.clases_dominio.Prestatgeria;
import edu.upc.prop.clusterxx.controladores.ControladorPrestatgeria;
import edu.upc.prop.clusterxx.controladores_persistencia.ControladorPersistenciaPrestatgeria;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;

public class PresentacionPrestatgeria extends JFrame {
    private static Perfil perfilActual = new Perfil("usuario", "contrasena");
    private static Map<String, ControladorPrestatgeria> prestatgeria = new HashMap<>();
    private static Prestatgeria prestatgeriaActual;
    private static ControladorPersistenciaPrestatgeria controladorPersistenciaPrestatgeria = new ControladorPersistenciaPrestatgeria();

    public PresentacionPrestatgeria() {
        setTitle("Gestión de Prestatgerias");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cargarDatos();
        mostrarMenuPrincipal();
    }

    private void mostrarMenuPrincipal() {
        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));

        JButton crearBtn = new JButton("Crear Prestatgeria");
        JButton cargarBtn = new JButton("Cargar Prestatgeria");
        JButton editarBtn = new JButton("Editar Prestatgeria");
        JButton borrarBtn = new JButton("Borrar Prestatgeria");
        JButton guardarBtn = new JButton("Guardar Cambios");
        JButton salirBtn = new JButton("Salir");

        crearBtn.addActionListener(e -> mostrarFormularioCrearPrestatgeria());
        cargarBtn.addActionListener(e -> mostrarFormularioCargarPrestatgeria());
        editarBtn.addActionListener(e -> mostrarFormularioEditarPrestatgeria());
        borrarBtn.addActionListener(e -> mostrarFormularioBorrarPrestatgeria());
        guardarBtn.addActionListener(e -> guardarDatos());
        salirBtn.addActionListener(e -> {
            guardarDatos();
            System.exit(0);
        });

        panel.add(crearBtn);
        panel.add(cargarBtn);
        panel.add(editarBtn);
        panel.add(borrarBtn);
        panel.add(guardarBtn);
        panel.add(salirBtn);

        setContentPane(panel);
        revalidate();
    }

    private void mostrarFormularioCrearPrestatgeria() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
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
                if (prestatgeria.containsKey(nombre)) {
                    JOptionPane.showMessageDialog(this, "La prestatgeria ya existe, elija otro nombre.");
                } else {
                    prestatgeriaActual = new Prestatgeria(nombre, altura);
                    prestatgeria.put(nombre, new ControladorPrestatgeria(prestatgeriaActual));
                    perfilActual.getPrestatgeria().add(prestatgeriaActual);
                    JOptionPane.showMessageDialog(this, "Prestatgeria creada con éxito.");
                    mostrarMenuPrincipal();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese una altura válida.");
            }
        });

        atrasBtn.addActionListener(e -> mostrarMenuPrincipal());

        setContentPane(panel);
        revalidate();
    }

    private void mostrarFormularioCargarPrestatgeria() {
        if (prestatgeria.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay prestatgerías disponibles.");
            return;
        }

        String[] nombres = prestatgeria.keySet().toArray(new String[0]);

        String seleccion = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione una prestatgeria para cargar:",
                "Cargar Prestatgeria",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nombres,
                nombres[0]
        );

        if (seleccion != null) {
            prestatgeriaActual = prestatgeria.get(seleccion).getPrestatgeria();
            JOptionPane.showMessageDialog(this, "Prestatgeria cargada.");
        }
    }

    private void mostrarFormularioEditarPrestatgeria() {
        if (prestatgeria.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay prestatgerías para editar.");
            return;
        }

        String[] nombres = prestatgeria.keySet().toArray(new String[0]);

        String seleccion = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione una prestatgeria para editar:",
                "Editar Prestatgeria",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nombres,
                nombres[0]
        );

        if (seleccion != null) {
            prestatgeriaActual = prestatgeria.get(seleccion).getPrestatgeria();

            JTextField nuevoNombreField = new JTextField(prestatgeriaActual.getNom());
            JTextField nuevaAlturaField = new JTextField(String.valueOf(prestatgeriaActual.getAltura()));

            Object[] mensaje = {
                    "Nuevo Nombre:", nuevoNombreField,
                    "Nueva Altura:", nuevaAlturaField
            };

            int opcion = JOptionPane.showConfirmDialog(this, mensaje, "Editar Prestatgeria", JOptionPane.OK_CANCEL_OPTION);
            if (opcion == JOptionPane.OK_OPTION) {
                prestatgeria.remove(prestatgeriaActual.getNom());
                prestatgeriaActual.setNom(nuevoNombreField.getText());
                prestatgeriaActual.setAltura(Integer.parseInt(nuevaAlturaField.getText()));
                prestatgeria.put(prestatgeriaActual.getNom(), new ControladorPrestatgeria(prestatgeriaActual));
                JOptionPane.showMessageDialog(this, "Prestatgeria editada con éxito.");
            }
        }
    }

    private void mostrarFormularioBorrarPrestatgeria() {
        if (prestatgeria.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay prestatgerías para borrar.");
            return;
        }

        String[] nombres = prestatgeria.keySet().toArray(new String[0]);

        String seleccion = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione una prestatgeria para borrar:",
                "Borrar Prestatgeria",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nombres,
                nombres[0]
        );

        if (seleccion != null) {
            prestatgeria.remove(seleccion);
            perfilActual.getPrestatgeria().removeIf(p -> p.getNom().equals(seleccion));
            JOptionPane.showMessageDialog(this, "Prestatgeria borrada.");
        }
    }

    private static void guardarDatos() {
        try {
            controladorPersistenciaPrestatgeria.guardarDatos(prestatgeria, "prestatgeries.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void cargarDatos() {
        try {
            prestatgeria = controladorPersistenciaPrestatgeria.cargarDatos("prestatgeries.dat");
            perfilActual = new Perfil("usuario", "contrasena");
        } catch (IOException | ClassNotFoundException e) {
            perfilActual = new Perfil("usuario", "contrasena");
            prestatgeria = new HashMap<>();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PresentacionPrestatgeria().setVisible(true));
    }
}
