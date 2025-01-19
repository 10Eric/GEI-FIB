package edu.upc.prop.clusterxx.controladores_presentacion;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Esta clase representa la vista para mostrar una matriz de resultados.
 */
class MostrarMatrizResultadosView {
    private JPanel panel;
    private Presentacion_Main controller;

    /**
     * Constructor de la vista MostrarMatrizResultadosView.
     * @param controller el controlador de la aplicación
     * @param matriz la matriz de resultados a mostrar
     * @param titulo el título de la vista
     */
    public MostrarMatrizResultadosView(Presentacion_Main controller, String[][] matriz, String titulo) {
        this.controller = controller;
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        if (matriz == null || matriz.length == 0) {
            JOptionPane.showMessageDialog(controller, "No hay datos para mostrar.");
            panel = null;
            return;
        }

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == null || matriz[i][j].isEmpty()) {
                    matriz[i][j] = "Vacio";
                }
            }
        }

        int columnas = matriz[0].length;
        String[] encabezados = new String[columnas];
        for (int i = 0; i < columnas; i++) {
            encabezados[i] = "Columna " + (i + 1);
        }

        DefaultTableModel tableModel = new DefaultTableModel(matriz, encabezados) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable tabla = new JTable(tableModel);
        tabla.setRowHeight(25);
        tabla.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tabla.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton atrasBtn = new JButton("Atrás");
        atrasBtn.addActionListener(e -> controller.mostrarMenuPrincipalDistribucio());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(atrasBtn);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(scrollPane, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.PAGE_END;
        panel.add(buttonPanel, gbc);

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