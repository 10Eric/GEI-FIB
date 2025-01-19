package edu.upc.prop.clusterxx.controladores_presentacion;

import javax.swing.*;
import java.awt.*;

class MenuDistribucionView {
    private JPanel panel;
    private Presentacion_Main controller;

    public MenuDistribucionView(Presentacion_Main controller) {
        this.controller = controller;
        panel = new JPanel(new GridBagLayout());
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
            String title = "Distribución"; // Initialize with appropriate title
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
        panel.add(agregarBtn, gbc);

        gbc.gridx = 1;
        panel.add(eliminarBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(modificarBtn, gbc);

        gbc.gridx = 1;
        panel.add(modificarSimBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(consultarSimBtn, gbc);

        gbc.gridx = 1;
        panel.add(consultarProdBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(calcularBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(mostrarDistribucionBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        panel.add(guardarBtn, gbc);

        gbc.gridx = 1;
        panel.add(atrasBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panel.add(salirBtn, gbc);
    }

    public JPanel getPanel() {
        return panel;
    }
}
