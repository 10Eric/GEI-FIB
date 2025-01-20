package edu.upc.prop.clusterxx.controladores_presentacion;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * Esta clase representa la vista del menú de prestatgería.
 */
class MenuPrestatgeriaView {
    private JPanel panel;
    private Presentacion_Main controller;

    /**
     * Constructor de la vista MenuPrestatgeriaView.
     *
     * @param controller el controlador principal de la aplicación
     */
    public MenuPrestatgeriaView(Presentacion_Main controller) {
        this.controller = controller;
        panel = new JPanel(new BorderLayout());

        // Panel superior para el logo y la ayuda
        JPanel topPanel = new JPanel(new BorderLayout());

        URL logoURL = getClass().getClassLoader().getResource("DATOS/LOGO.png");
        if (logoURL != null) {
            ImageIcon originalIcon = new ImageIcon(logoURL);
            Image scaledImage = originalIcon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);
            JLabel fotoLabel = new JLabel(new ImageIcon(scaledImage));
            fotoLabel.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 0));
            topPanel.add(fotoLabel, BorderLayout.CENTER);
        } else {
            System.err.println("Logo image not found.");
        }

        // Texto de ayuda
        JButton helpButton = new JButton("Ayuda");
        helpButton.setBorderPainted(false);
        helpButton.setContentAreaFilled(false);
        helpButton.setFocusPainted(false);
        helpButton.setOpaque(false);
        helpButton.setForeground(Color.BLUE);
        helpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        helpButton.addActionListener(e -> {
            try {
                URL pdfURL = getClass().getClassLoader().getResource("DATOS/ayuda.pdf");
                if (pdfURL != null) {
                    File tempFile = File.createTempFile("ayuda", ".pdf");
                    tempFile.deleteOnExit();
                    try (InputStream in = pdfURL.openStream(); FileOutputStream out = new FileOutputStream(tempFile)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = in.read(buffer)) != -1) {
                            out.write(buffer, 0, bytesRead);
                        }
                    }
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(tempFile);
                    } else {
                        JOptionPane.showMessageDialog(panel, "Apertura de PDF no soportada en este sistema.");
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Archivo PDF no encontrado.");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        topPanel.add(helpButton, BorderLayout.EAST);

        panel.add(topPanel, BorderLayout.NORTH);

        // Espacio de fondo en el centro
        JPanel fondoPanel = new JPanel(new BorderLayout());
        fondoPanel.setBackground(Color.LIGHT_GRAY);


        // Panel para el texto del perfil actual
        JPanel perfilPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        perfilPanel.setBackground(Color.LIGHT_GRAY);
        perfilPanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
        JLabel perfilLabel = new JLabel("<html>PERFIL ACTUAL: <span style='color:red;'>" + controller.getPerfilActual().getUsuari() + "</span></html>");        perfilLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 3, 0)); // Add space below the profile label
        perfilPanel.add(perfilLabel);
        fondoPanel.add(perfilPanel, BorderLayout.NORTH);

        // Panel para los botones
        JPanel botonesPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        JButton crearBtn = new JButton("Crear Prestatgeria");
        JButton cargarBtn = new JButton("Cargar Prestatgeria");
        JButton editarBtn = new JButton("Editar Prestatgeria");
        JButton borrarBtn = new JButton("Borrar Prestatgeria");
        JButton atrasBtn = new JButton("Atrás");

        crearBtn.addActionListener(e -> controller.mostrarFormularioCrearPrestatgeria());
        cargarBtn.addActionListener(e -> controller.mostrarFormularioCargarPrestatgeria());
        editarBtn.addActionListener(e -> controller.mostrarFormularioEditarPrestatgeria());
        borrarBtn.addActionListener(e -> controller.mostrarFormularioBorrarPrestatgeria());
        atrasBtn.addActionListener(e -> controller.mostrarMenuPrincipal());

        botonesPanel.add(crearBtn);
        botonesPanel.add(cargarBtn);
        botonesPanel.add(editarBtn);
        botonesPanel.add(borrarBtn);
        botonesPanel.add(atrasBtn);

        // Panel para los botones de guardar y salir
        JPanel guardarSalirPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        JButton guardarBtn = new JButton("Guardar Cambios");
        JButton salirBtn = new JButton("Salir");

        guardarBtn.addActionListener(e -> {
            Presentacion_Main.guardarDatos();
            JOptionPane.showMessageDialog(controller, "Datos guardados con éxito.");
        });
        salirBtn.addActionListener(e -> {
            controller.guardarDatos();
            System.exit(0);
        });

        guardarSalirPanel.add(guardarBtn);
        guardarSalirPanel.add(salirBtn);

        botonesPanel.add(guardarSalirPanel);

        fondoPanel.add(botonesPanel, BorderLayout.CENTER);

        panel.add(fondoPanel, BorderLayout.CENTER);

        // Set the title of the interface
        controller.setTitle("Gestión de Prestatgerías");
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
