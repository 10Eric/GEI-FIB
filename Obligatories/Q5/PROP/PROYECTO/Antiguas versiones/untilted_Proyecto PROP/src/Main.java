import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {

    public static void main(String[] args) {
        // Crear la ventana principal
        JFrame ventana = new JFrame("Ejemplo Completo de Swing");
        ventana.setSize(600, 500);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null); // Centrar la ventana

        // Crear un panel principal con un layout GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Añadir espaciado entre componentes

        // 1. Etiquetas (JLabel)
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel etiqueta = new JLabel("Escribe tu nombre:");
        panel.add(etiqueta, gbc);

        // 2. Campo de texto (JTextField)
        gbc.gridx = 1; gbc.gridy = 0;
        JTextField campoTexto = new JTextField(15);
        panel.add(campoTexto, gbc);

        // 3. Área de texto (JTextArea) con barra de scroll
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        JTextArea areaTexto = new JTextArea(5, 20);
        JScrollPane scrollAreaTexto = new JScrollPane(areaTexto);
        scrollAreaTexto.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollAreaTexto, gbc);

        // 4. Casillas de verificación (JCheckBox)
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        JCheckBox casilla1 = new JCheckBox("Opción 1");
        JCheckBox casilla2 = new JCheckBox("Opción 2");
        panel.add(casilla1, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(casilla2, gbc);

        // 5. Botones de radio (JRadioButton) y grupo de botones
        gbc.gridx = 0; gbc.gridy = 3;
        JRadioButton radio1 = new JRadioButton("Opción A");
        JRadioButton radio2 = new JRadioButton("Opción B");
        ButtonGroup grupoRadios = new ButtonGroup();
        grupoRadios.add(radio1);
        grupoRadios.add(radio2);
        panel.add(radio1, gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(radio2, gbc);

        // 6. ComboBox (JComboBox)
        gbc.gridx = 0; gbc.gridy = 4;
        JLabel etiquetaCombo = new JLabel("Elige una opción:");
        panel.add(etiquetaCombo, gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        String[] opcionesCombo = { "Opción 1", "Opción 2", "Opción 3" };
        JComboBox<String> comboBox = new JComboBox<>(opcionesCombo);
        panel.add(comboBox, gbc);

        // 7. Lista (JList) con barra de scroll
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        JLabel etiquetaLista = new JLabel("Elige un elemento de la lista:");
        panel.add(etiquetaLista, gbc);
        gbc.gridy = 6;
        String[] elementosLista = { "Elemento 1", "Elemento 2", "Elemento 3", "Elemento 4" };
        JList<String> lista = new JList<>(elementosLista);
        JScrollPane scrollLista = new JScrollPane(lista);
        scrollLista.setPreferredSize(new Dimension(100, 80));
        panel.add(scrollLista, gbc);

        // 8. Tabla (JTable)
        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        String[] columnas = { "Nombre", "Edad", "Ciudad" };
        Object[][] datos = {
                { "Juan", 25, "Madrid" },
                { "Ana", 30, "Barcelona" },
                { "Luis", 22, "Valencia" }
        };
        JTable tabla = new JTable(datos, columnas);
        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.setPreferredSize(new Dimension(400, 80));
        panel.add(scrollTabla, gbc);

        // 9. Botón (JButton)
        gbc.gridx = 0; gbc.gridy = 8; gbc.gridwidth = 2;
        JButton boton = new JButton("Mostrar Datos");
        panel.add(boton, gbc);

        // Acción del botón
        boton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = campoTexto.getText();
                String opcionSeleccionada = (String) comboBox.getSelectedItem();
                String listaSeleccionada = lista.getSelectedValue();
                String mensaje = "Nombre: " + nombre + "\nOpción ComboBox: " + opcionSeleccionada +
                        "\nElemento de la lista: " + listaSeleccionada;
                JOptionPane.showMessageDialog(null, mensaje);
            }
        });

        // Añadir el panel a la ventana
        ventana.add(panel);
        ventana.setVisible(true);
    }
}