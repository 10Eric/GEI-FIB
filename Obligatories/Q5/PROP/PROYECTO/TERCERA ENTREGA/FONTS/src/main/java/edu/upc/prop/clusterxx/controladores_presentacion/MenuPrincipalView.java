package edu.upc.prop.clusterxx.controladores_presentacion;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.File;
import java.net.URL;
import java.io.InputStream;
import java.io.FileOutputStream;

/**
 * Esta clase representa la vista del menú principal.
 */
class MenuPrincipalView {
    private JPanel panel;
    private Presentacion_Main controller;

    /**
     * Constructor de la vista MenuPrincipalView.
     *
     * @param controller el controlador principal de la aplicación
     */
    public MenuPrincipalView(Presentacion_Main controller) {
        this.controller = controller;
        panel = new JPanel(new BorderLayout());

        // Panel superior para el logo y la ayuda
        JPanel topPanel = new JPanel(new BorderLayout());

        // Logo
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
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });
        topPanel.add(helpButton, BorderLayout.EAST);

        panel.add(topPanel, BorderLayout.NORTH);

        // Espacio de fondo en el centro
        JPanel fondoPanel = new JPanel(new BorderLayout());
        fondoPanel.setBackground(Color.LIGHT_GRAY);

        // Añadir espacio entre la foto y los botones
        fondoPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // Linea horizontal
        JSeparator separator = new JSeparator();
        fondoPanel.add(separator, BorderLayout.NORTH);

        // Panel para los botones
        JPanel botonesPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        JButton crearBtn = new JButton("Crear Perfil");
        JButton cargarBtn = new JButton("Cargar Perfil");
        JButton borrarBtn = new JButton("Borrar Perfil");
        JButton editarBtn = new JButton("Editar Perfil");

        crearBtn.addActionListener(e -> controller.mostrarFormularioCrearPerfil());
        cargarBtn.addActionListener(e -> controller.mostrarFormularioCargarPerfil());
        borrarBtn.addActionListener(e -> controller.mostrarFormularioBorrarPerfil());
        editarBtn.addActionListener(e -> {
            if (!controller.es_buit()) {
                controller.mostrarFormularioEditarPerfil();
            } else {
                JOptionPane.showMessageDialog(controller, "No hay perfiles disponibles para editar.");
            }
        });

        botonesPanel.add(crearBtn);
        botonesPanel.add(cargarBtn);
        botonesPanel.add(borrarBtn);
        botonesPanel.add(editarBtn);

        // Panel para los botones de guardar y salir
        JPanel guardarSalirPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        JButton guardarBtn = new JButton("Guardar Cambios");
        JButton salirBtn = new JButton("Salir");

        guardarBtn.addActionListener(e -> {
            Presentacion_Main.guardarDatos();
            JOptionPane.showMessageDialog(panel, "Datos guardados con éxito.");
        });
        salirBtn.addActionListener(e -> {
            Presentacion_Main.guardarDatos();
            System.exit(0);
        });

        guardarSalirPanel.add(guardarBtn);
        guardarSalirPanel.add(salirBtn);

        botonesPanel.add(guardarSalirPanel);

        fondoPanel.add(botonesPanel, BorderLayout.CENTER);

        panel.add(fondoPanel, BorderLayout.CENTER);

        controller.setTitle("Gestión de Perfiles");
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
