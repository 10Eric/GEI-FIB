package edu.upc.prop.clusterxx.controladores_presentacion;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * Esta clase representa la vista del menú de distribución.
 */
class MenuDistribucionView {
    private JPanel panel;
    private Presentacion_Main controller;

    /**
     * Constructor de la vista MenuDistribucionView.
     *
     * @param controller el controlador principal de la aplicación
     */
    public MenuDistribucionView(Presentacion_Main controller) {
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

        // Panel para el texto del perfil y prestatgeria actual
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setBackground(Color.LIGHT_GRAY);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 0));
        JLabel perfilLabel = new JLabel("<html>PERFIL ACTUAL: <span style='color:red;'>" + controller.getPerfilActual().getUsuari() + "</span></html>");        perfilLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 3, 0)); // Add space below the profile label
        JLabel prestatgeriaLabel = new JLabel("<html>PRESTATGERIA ACTUAL: <span style='color:green;'>" + controller.getPrestatgeriaActual().getNom() + "</span></html>");
        infoPanel.add(perfilLabel);
        infoPanel.add(prestatgeriaLabel);
        fondoPanel.add(infoPanel, BorderLayout.NORTH);

        // Panel para los botones
        JPanel botonesPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        JButton agregarBtn = new JButton("Añadir Producto");
        JButton eliminarBtn = new JButton("Eliminar Producto");
        JButton modificarBtn = new JButton("Modificar Producto");
        JButton modificarSimBtn = new JButton("Modificar Similitudes");
        JButton consultarSimBtn = new JButton("Consultar Similitudes");
        JButton consultarProdBtn = new JButton("Consultar Productos");
        JButton calcularBtn = new JButton("Calcular Distribución");
        JButton mostrarDistribucionBtn = new JButton("Mostrar Distribución");
        JButton guardarBtn = new JButton("Guardar");
        JButton atrasBtn = new JButton("Atrás");
        JButton salirBtn = new JButton("Salir");

        agregarBtn.addActionListener(e -> controller.mostrarFormularioAgregarProducto());
        eliminarBtn.addActionListener(e -> controller.mostrarFormularioEliminarProducto());
        modificarBtn.addActionListener(e -> controller.mostrarFormularioModificarProducto());
        modificarSimBtn.addActionListener(e -> controller.mostrarFormularioModificarSimilitudes());
        consultarSimBtn.addActionListener(e -> controller.mostrarFormularioConsultarSimilitudes());
        consultarProdBtn.addActionListener(e -> controller.mostrarFormularioConsultarProductos());
        calcularBtn.addActionListener(e -> controller.mostrarFormularioCalcularDistribucion());
        mostrarDistribucionBtn.addActionListener(e -> {
            String[][] matriz = controller.getControladorDistribucio().getMatrizDistribucion();
            String title = "Distribución";
            controller.MostrarMatrizResultadosEditable(matriz, title);
        });
        guardarBtn.addActionListener(e -> {
            Presentacion_Main.guardarDatos();
            JOptionPane.showMessageDialog(controller, "Datos guardados con éxito.");
        });
        atrasBtn.addActionListener(e -> controller.mostrarMenuPrincipalPrestatgeria());
        salirBtn.addActionListener(e -> {
            controller.guardarDatos();
            System.exit(0);
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        botonesPanel.add(agregarBtn, gbc);

        gbc.gridx = 1;
        botonesPanel.add(eliminarBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        botonesPanel.add(modificarBtn, gbc);

        gbc.gridx = 1;
        botonesPanel.add(modificarSimBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        botonesPanel.add(consultarSimBtn, gbc);

        gbc.gridx = 1;
        botonesPanel.add(consultarProdBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        botonesPanel.add(calcularBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        botonesPanel.add(mostrarDistribucionBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        botonesPanel.add(guardarBtn, gbc);

        gbc.gridx = 1;
        botonesPanel.add(atrasBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        botonesPanel.add(salirBtn, gbc);

        fondoPanel.add(botonesPanel, BorderLayout.CENTER);

        panel.add(fondoPanel, BorderLayout.CENTER);

        // Set the title of the interface
        controller.setTitle("Gestión de Distribución");
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
