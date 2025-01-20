package edu.upc.prop.clusterxx.controladores_presentacion;

import javax.swing.*;
import java.awt.*;

class CalculaDistribucionView {
    private JPanel panel;
    private Presentacion_Main controller;

    public CalculaDistribucionView(Presentacion_Main controller) {
        this.controller = controller;
        panel = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton backtrackingBtn = new JButton("Backtracking");
        JButton tspBtn = new JButton("TSP");
        JButton atrasBtn = new JButton("Atrás");

        backtrackingBtn.addActionListener(e -> {
            controller.getControladorDistribucio().calcula_distribucio(1);
            String[][] matriz = controller.getControladorDistribucio().getMatrizDistribucion();
            controller.mostrarDistribucion(matriz, "Resultados con Backtracking");
        });

        tspBtn.addActionListener(e -> {
            if (controller.getControladorDistribucio().getProductesColocats().size() == 1) {
                controller.getControladorDistribucio().calcula_distribucio(1);
            } else {
                controller.getControladorDistribucio().calcula_distribucio(2);
            }
            String[][] matriz = controller.getControladorDistribucio().getMatrizDistribucion();
            controller.mostrarDistribucion(matriz, "Resultados con TSP");
        });

        atrasBtn.addActionListener(e -> controller.mostrarMenuPrincipalDistribucio());

        panel.add(backtrackingBtn);
        panel.add(tspBtn);
        panel.add(atrasBtn);
    }

    public JPanel getPanel() {
        return panel;
    }
}
