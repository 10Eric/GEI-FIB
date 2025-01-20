package edu.upc.prop.clusterxx.controladores_presentacion;

import edu.upc.prop.clusterxx.controladores.ControladorDistribucio;
import edu.upc.prop.clusterxx.controladores_persistencia.ControladorPersistenciaDistribucio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class PresentacionDistribucio extends JFrame {
    private static ControladorDistribucio controladorDistribucio = new ControladorDistribucio();
    private static ControladorPersistenciaDistribucio controladorPersistenciaDistribucio = new ControladorPersistenciaDistribucio();
    public PresentacionDistribucio() {
        setTitle("Gestión de Distribuciones");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cargarDistribuciones();
        mostrarFormularioCrearDistribucionInicial();
    }

    private void mostrarFormularioCrearDistribucionInicial() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JTextField alturaField = new JTextField();
        alturaField.setHorizontalAlignment(SwingConstants.CENTER);
        JButton crearBtn = new JButton("Crear");
        JButton salirBtn = new JButton("Salir");

        panel.add(new JLabel("Altura del Estante:",SwingConstants.CENTER));
        panel.add(alturaField);
        panel.add(crearBtn);
        panel.add(salirBtn);

        crearBtn.addActionListener(e -> {
            String nombre = "Distribucion Predeterminada";
            try {
                int altura = Integer.parseInt(alturaField.getText());
                if (controladorDistribucio.getProductes().isEmpty()) {
                    controladorDistribucio.crea_distribucio_inicial(nombre, altura);
                }
                else controladorDistribucio.get_Prestatgeria().setAltura(altura);
                JOptionPane.showMessageDialog(this, "Altura insertada con éxito.");
                mostrarMenuPrincipal();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese una altura válida.");
            }
        });

        salirBtn.addActionListener(e -> System.exit(0));

        setContentPane(panel);
        revalidate();
    }

    private void mostrarMenuPrincipal() {
        JPanel panel = new JPanel(new GridBagLayout());
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
        JButton salirBtn = new JButton("Salir");

        agregarBtn.addActionListener(e -> mostrarFormularioAgregarProducto());
        eliminarBtn.addActionListener(e -> mostrarFormularioEliminarProducto());
        modificarBtn.addActionListener(e -> mostrarFormularioModificarProducto());
        modificarSimBtn.addActionListener(e -> mostrarFormularioModificarSimilitudes());
        consultarSimBtn.addActionListener(e -> mostrarFormularioConsultarSimilitudes());
        consultarProdBtn.addActionListener(e -> mostrarFormularioConsultarProductos());
        calcularBtn.addActionListener(e -> mostrarFormularioCalcularDistribucion());
        mostrarDistribucionBtn.addActionListener(e -> mostrarDistribucion());
        guardarBtn.addActionListener(e -> guardarDistribuciones());
        salirBtn.addActionListener(e -> {
            guardarDistribuciones();
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
        panel.add(salirBtn, gbc);

        setContentPane(panel);
        revalidate();
    }

    private void mostrarFormularioAgregarProducto() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        JTextField nombreField = new JTextField();
        nombreField.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField marcaField = new JTextField();
        marcaField.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField precioField = new JTextField();
        precioField.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField cantidadField = new JTextField();
        cantidadField.setHorizontalAlignment(SwingConstants.CENTER);
        JButton agregarBtn = new JButton("Añadir");
        JButton atrasBtn = new JButton("Atrás");

        panel.add(new JLabel("Nombre:", SwingConstants.CENTER));
        panel.add(nombreField);
        panel.add(new JLabel("Marca:", SwingConstants.CENTER));
        panel.add(marcaField);
        panel.add(new JLabel("Precio:", SwingConstants.CENTER));
        panel.add(precioField);
        panel.add(new JLabel("Cantidad:",SwingConstants.CENTER));
        panel.add(cantidadField);
        panel.add(agregarBtn);
        panel.add(atrasBtn);

        agregarBtn.addActionListener(e -> {
            String nombre = nombreField.getText();
            String marca = marcaField.getText();
            double precio = 0;
            int cantidad = 0;
            boolean precioValido = true;
            boolean cantidadValida = true;

            try {
                precio = Double.parseDouble(precioField.getText());
            } catch (NumberFormatException ex) {
                precioValido = false;
            }

            try {
                cantidad = Integer.parseInt(cantidadField.getText());
            } catch (NumberFormatException ex) {
                cantidadValida = false;
            }

            if (!precioValido && !cantidadValida) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese valores válidos para el precio y la cantidad.");
                return;
            } else if (!precioValido) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor válido para el precio.");
                return;
            } else if (!cantidadValida) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor válido para la cantidad.");
                return;
            }

            if (controladorDistribucio.existeix_producte(nombre)) {
                JOptionPane.showMessageDialog(this, "El producto con ese nombre ya existe. No se puede agregar.");
                return;
            }

            int pos;
            if (controladorDistribucio.getProductesColocats().isEmpty()){
                pos = 1;
            }
            else pos = controladorDistribucio.getProductesColocats().get(controladorDistribucio.getProductesColocats().size() - 1).getPos() + 1;            controladorDistribucio.afegeix_producte(nombre, marca, precio, cantidad, pos, cantidad);
            JOptionPane.showMessageDialog(this, "Producto añadido con éxito.");
            mostrarMenuPrincipal();
        });

        atrasBtn.addActionListener(e -> mostrarMenuPrincipal());

        setContentPane(panel);
        revalidate();
    }

    private void mostrarFormularioEliminarProducto() {
        ArrayList<String> productos = controladorDistribucio.getProductes();
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos para eliminar.");
            return;
        }

        String seleccion = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione el producto a eliminar:",
                "Eliminar Producto",
                JOptionPane.QUESTION_MESSAGE,
                null,
                productos.toArray(),
                productos.get(0)
        );

        if (seleccion != null) {
            controladorDistribucio.eliminaProducte(seleccion);
            JOptionPane.showMessageDialog(this, "Producto eliminado.");
        }
    }

    private void mostrarFormularioModificarProducto() {
        ArrayList<String> productos = controladorDistribucio.getProductes();
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos para modificar.");
            return;
        }

        String seleccion = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione el producto a modificar:",
                "Modificar Producto",
                JOptionPane.QUESTION_MESSAGE,
                null,
                productos.toArray(),
                productos.get(0)
        );

        if (seleccion != null) {
            ArrayList<String> info = controladorDistribucio.get_info_producte(seleccion);

            JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
            JTextField precioActualField = new JTextField(info.get(2));
            precioActualField.setHorizontalAlignment(SwingConstants.CENTER);
            JTextField cantidadActualField = new JTextField(info.get(3));
            cantidadActualField.setHorizontalAlignment(SwingConstants.CENTER);
            JTextField nuevoPrecioField = new JTextField();
            nuevoPrecioField.setHorizontalAlignment(SwingConstants.CENTER);
            JTextField nuevaCantidadField = new JTextField();
            nuevaCantidadField.setHorizontalAlignment(SwingConstants.CENTER);
            JButton guardarBtn = new JButton("Guardar");
            JButton atrasBtn = new JButton("Atrás");

            precioActualField.setEditable(false);
            cantidadActualField.setEditable(false);

            panel.add(createCenteredLabel("Precio Actual:"));
            panel.add(precioActualField);
            panel.add(createCenteredLabel("Cantidad Actual:"));
            panel.add(cantidadActualField);
            panel.add(createCenteredLabel("Nuevo Precio:"));
            panel.add(nuevoPrecioField);
            panel.add(createCenteredLabel("Nueva Cantidad:"));
            panel.add(nuevaCantidadField);
            panel.add(guardarBtn);
            panel.add(atrasBtn);

            guardarBtn.addActionListener(e -> {
                try {
                    double nuevoPrecio = Double.parseDouble(nuevoPrecioField.getText());
                    int nuevaCantidad = Integer.parseInt(nuevaCantidadField.getText());

                        controladorDistribucio.set_precio_producto(seleccion, nuevoPrecio);
                        controladorDistribucio.set_cantidad_producto(seleccion, nuevaCantidad);
                        JOptionPane.showMessageDialog(this, "Producto modificado con éxito.");
                        mostrarMenuPrincipal();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese valores válidos para el precio y la cantidad.");
                }
            });

            atrasBtn.addActionListener(e -> mostrarMenuPrincipal());

            setContentPane(panel);
            revalidate();
        }
    }

    private void mostrarFormularioModificarSimilitudes() {
        ArrayList<String> productos = controladorDistribucio.getProductes();
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos para modificar similitudes.");
            return;
        }

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        JComboBox<String> producto1Combo = new JComboBox<>(productos.toArray(new String[0]));
        JComboBox<String> producto2Combo = new JComboBox<>(productos.toArray(new String[0]));
        JTextField similitudField = new JTextField();
        similitudField.setHorizontalAlignment(SwingConstants.CENTER);
        JButton actualizarBtn = new JButton("Actualizar");
        JButton atrasBtn = new JButton("Atrás");

        panel.add(createCenteredLabel("Seleccione el primer producto:"));
        panel.add(producto1Combo);
        panel.add(createCenteredLabel("Seleccione el segundo producto:"));
        panel.add(producto2Combo);
        panel.add(createCenteredLabel("Valor de similitud (0-100):"));
        panel.add(similitudField);
        panel.add(actualizarBtn);
        panel.add(atrasBtn);

        ActionListener updateFieldAction = e -> {
            String producto1 = (String) producto1Combo.getSelectedItem();
            String producto2 = (String) producto2Combo.getSelectedItem();

            if (!producto1.equals(producto2)) {
                int valorActual = controladorDistribucio.getSimilitud(producto1, producto2);
                similitudField.setText(String.valueOf(valorActual));
            } else {
                similitudField.setText("");
            }
        };

        producto1Combo.addActionListener(updateFieldAction);
        producto2Combo.addActionListener(updateFieldAction);

        actualizarBtn.addActionListener(e -> {
            String producto1 = (String) producto1Combo.getSelectedItem();
            String producto2 = (String) producto2Combo.getSelectedItem();

            if (producto1.equals(producto2)) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione dos productos diferentes.");
                return;
            }

            try {
                int valorSimilitud = Integer.parseInt(similitudField.getText());
                if (valorSimilitud < 0 || valorSimilitud > 100) {
                    JOptionPane.showMessageDialog(this, "El valor de similitud debe estar entre 0 y 100.");
                    return;
                }

                controladorDistribucio.set_similitud(producto1, producto2, valorSimilitud);
                controladorDistribucio.set_similitud(producto2, producto1, valorSimilitud);

                JOptionPane.showMessageDialog(this, "Similitud actualizada con éxito.");
                mostrarMenuPrincipal(); // Regresa al menú principal
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor numérico válido para la similitud.");
            }
        });

        atrasBtn.addActionListener(e -> mostrarMenuPrincipal());

        setContentPane(panel);
        revalidate();
    }

    private void mostrarFormularioConsultarSimilitudes() {
        ArrayList<String> productos = controladorDistribucio.getProductes();
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos para consultar similitudes.");
            return;
        }

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

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
            ArrayList<String> similitudes = controladorDistribucio.getSimilitudsProducte(producto);

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

        atrasBtn.addActionListener(e -> mostrarMenuPrincipal());

        setContentPane(panel);
        revalidate();
    }

    private void mostrarFormularioConsultarProductos() {
        ArrayList<String> productos = controladorDistribucio.getProductes();
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos para consultar.");
            return;
        }

        JOptionPane.showMessageDialog(this, "Productos actuales:\n" + String.join("\n", productos));
    }

    private void mostrarDistribucion() {
        ArrayList<String> productos = controladorDistribucio.getProductes();
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos para calcular.");
            return;
        }
        String[][] matriz = controladorDistribucio.getMatrizDistribucion();
        mostrarMatrizResultadosEditable(matriz, "Distribución de Productos");
    }

    private void mostrarFormularioCalcularDistribucion() {
        ArrayList<String> productos = controladorDistribucio.getProductes();
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos para calcular.");
            return;
        }
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton backtrackingBtn = new JButton("Backtracking");
        JButton tspBtn = new JButton("TSP");
        JButton atrasBtn = new JButton("Atrás");

        backtrackingBtn.addActionListener(e -> {
            controladorDistribucio.calcula_distribucio(1);

            // Mostrar los resultados en una interfaz gráfica
            String[][] matriz = controladorDistribucio.getMatrizDistribucion(); // Obtener la matriz calculada
            mostrarMatrizResultados(matriz, "Resultados con Backtracking");
        });

        tspBtn.addActionListener(e -> {
            if (controladorDistribucio.getProductesColocats().size() == 1) controladorDistribucio.calcula_distribucio(1);
            else controladorDistribucio.calcula_distribucio(2);

            // Mostrar los resultados en una interfaz gráfica
            String[][] matriz = controladorDistribucio.getMatrizDistribucion(); // Obtener la matriz calculada
            mostrarMatrizResultados(matriz, "Resultados con TSP");
        });

        atrasBtn.addActionListener(e -> mostrarMenuPrincipal());

        panel.add(backtrackingBtn);
        panel.add(tspBtn);
        panel.add(atrasBtn);

        setContentPane(panel);
        revalidate();
    }

    private void mostrarMatrizResultadosEditable(String[][] matriz, String titulo) {
        if (matriz == null || matriz.length == 0) {
            JOptionPane.showMessageDialog(this, "No hay datos para mostrar.");
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

        // Centrar la tabla dentro del JScrollPane
        JViewport viewport = new JViewport() {
            @Override
            public Dimension getPreferredSize() {
                Dimension viewSize = super.getPreferredSize();
                Dimension extentSize = getExtentSize();
                int width = Math.max(viewSize.width, extentSize.width);
                int height = Math.max(viewSize.height, extentSize.height);
                return new Dimension(width, height);
            }
        };
        viewport.setView(tabla);
        viewport.setViewPosition(new Point(
                Math.max(0, (viewport.getViewSize().width - scrollPane.getWidth()) / 2),
                Math.max(0, (viewport.getViewSize().height - scrollPane.getHeight()) / 2)
        ));
        scrollPane.setViewport(viewport);

        JButton modificarPosicionBtn = new JButton("Modificar Posición");
        modificarPosicionBtn.addActionListener(e -> modificarPosicionProductos());

        JButton atrasBtn = new JButton("Atrás");
        atrasBtn.addActionListener(e -> mostrarMenuPrincipal());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(modificarPosicionBtn);
        buttonPanel.add(atrasBtn);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        mainPanel.add(scrollPane, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.PAGE_END;
        mainPanel.add(buttonPanel, gbc);

        setContentPane(mainPanel);
        revalidate();
        repaint();
    }


    private void modificarPosicionProductos() {
        ArrayList<String> productos = controladorDistribucio.getProductes();
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos para modificar.");
            return;
        }

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        JComboBox<String> producto1Combo = new JComboBox<>(productos.toArray(new String[0]));
        JComboBox<String> producto2Combo = new JComboBox<>(productos.toArray(new String[0]));

        panel.add(new JLabel("Seleccione el primer producto:"));
        panel.add(producto1Combo);
        panel.add(new JLabel("Seleccione el segundo producto:"));
        panel.add(producto2Combo);

        int result = JOptionPane.showConfirmDialog(this, panel, "Modificar Posición de Productos", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String producto1 = (String) producto1Combo.getSelectedItem();
            String producto2 = (String) producto2Combo.getSelectedItem();

            if (producto1.equals(producto2)) {
                JOptionPane.showMessageDialog(this, "Seleccione dos productos diferentes.");
                modificarPosicionProductos();
            }

            int index1 = productos.indexOf(producto1);
            int index2 = productos.indexOf(producto2);

            controladorDistribucio.intercambiar_productes(index1, index2);
            mostrarDistribucion();
        }
    }

    private void mostrarMatrizResultados(String[][] matriz, String titulo) {
        if (matriz == null || matriz.length == 0) {
            JOptionPane.showMessageDialog(this, "No hay datos para mostrar.");
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

        // Centrar la tabla dentro del JScrollPane
        JViewport viewport = new JViewport() {
            @Override
            public Dimension getPreferredSize() {
                Dimension viewSize = super.getPreferredSize();
                Dimension extentSize = getExtentSize();
                int width = Math.max(viewSize.width, extentSize.width);
                int height = Math.max(viewSize.height, extentSize.height);
                return new Dimension(width, height);
            }
        };
        viewport.setView(tabla);
        viewport.setViewPosition(new Point(
                Math.max(0, (viewport.getViewSize().width - scrollPane.getWidth()) / 2),
                Math.max(0, (viewport.getViewSize().height - scrollPane.getHeight()) / 2)
        ));
        scrollPane.setViewport(viewport);

        JButton atrasBtn = new JButton("Atrás");
        atrasBtn.addActionListener(e -> mostrarMenuPrincipal());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(atrasBtn);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        mainPanel.add(scrollPane, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.PAGE_END;
        mainPanel.add(buttonPanel, gbc);

        setContentPane(mainPanel);
        revalidate();
        repaint();
    }

    private void cargarDistribuciones() {
        try {
            controladorDistribucio = controladorPersistenciaDistribucio.cargarDistribucio("distribuciones.dat");
        } catch (IOException | ClassNotFoundException e) {
            controladorDistribucio = new ControladorDistribucio();
        }
    }

    private void guardarDistribuciones() {
        try {
            controladorPersistenciaDistribucio.guardarDistribucio(controladorDistribucio, "distribuciones.dat");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar distribuciones.");
        }
    }

    private JLabel createCenteredLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        return label;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PresentacionDistribucio().setVisible(true));
    }
}
