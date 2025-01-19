package edu.upc.prop.clusterxx.controladores_presentacion;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class ConsultaSimilitudesView {
    private JPanel panel;
    private Presentacion_Main controller;

    public ConsultaSimilitudesView(Presentacion_Main controller) {
        this.controller = controller;
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        ArrayList<String> productos = controller.getControladorDistribucio().getProductes();
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(controller, "No hay productos para consultar similitudes.");
            panel = null;
            return;
        }

        JLabel label = new JLabel("Seleccione el producto:");
        JComboBox<String> productoCombo = new JComboBox<>(productos.toArray(new String[0]));
        JTextArea similitudesArea = new JTextArea(15, 50);
        similitudesArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(similitudesArea);
        JButton consultarBtn = new JButton("Consultar");
        JButton atrasBtn = new JButton("Atrás");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panel.add(productoCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        panel.add(consultarBtn, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(atrasBtn, gbc);

        consultarBtn.addActionListener(e -> {
            String producto = (String) productoCombo.getSelectedItem();
            ArrayList<String>similitudes = controller.get_similituds_distribucio(producto);

            if (similitudes.isEmpty()) {
                similitudesArea.setText("No hay similitudes registradas para este producto.");
            } else {
                StringBuilder resultado = new StringBuilder();
                resultado.append("\n");
                for (int i = 0; i < similitudes.size(); i += 2) {
                    resultado.append(" - " + similitudes.get(i))
                            .append(" - Similitud: ")
                            .append(similitudes.get(i + 1))
                            .append("\n");
                }
                similitudesArea.setText(resultado.toString());
            }
        });

        atrasBtn.addActionListener(e -> controller.mostrarMenuPrincipalDistribucio());
    }

    public JPanel getPanel() {
        return panel;
    }
}
