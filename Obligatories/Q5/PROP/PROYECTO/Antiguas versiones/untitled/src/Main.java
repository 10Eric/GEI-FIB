import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Main {
    // Archivo donde se almacenan los valores
    private static final String FILE_PATH = "productos.txt";

    // Campos de texto para los productos
    private static JTextField producto1Field;
    private static JTextField producto2Field;

    public static void main(String[] args) {
        // Crear la ventana principal
        JFrame frame = new JFrame("Productos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        // Crear el panel para los componentes
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        // Crear etiquetas y campos de texto
        JLabel labelProducto1 = new JLabel("Producto 1:");
        producto1Field = new JTextField(10);

        JLabel labelProducto2 = new JLabel("Producto 2:");
        producto2Field = new JTextField(10);

        // Botón para guardar los cambios
        JButton btnGuardar = new JButton("Guardar");

        // Añadir los componentes al panel
        panel.add(labelProducto1);
        panel.add(producto1Field);
        panel.add(labelProducto2);
        panel.add(producto2Field);
        panel.add(btnGuardar);

        // Leer los valores guardados en el archivo (si existen)
        cargarValores();

        // Añadir el panel al frame
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        // Acción del botón Guardar
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarValores();
            }
        });

        // Mostrar la ventana
        frame.setVisible(true);
    }

    // Método para cargar los valores del archivo
    private static void cargarValores() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String linea1 = reader.readLine();
                String linea2 = reader.readLine();

                // Si las líneas no son nulas, establecer los valores en los campos de texto
                if (linea1 != null) {
                    producto1Field.setText(linea1);
                }
                if (linea2 != null) {
                    producto2Field.setText(linea2);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Si el archivo no existe, establecer valores por defecto
            producto1Field.setText("12");
            producto2Field.setText("31");
        }
    }

    // Método para guardar los valores en el archivo
    private static void guardarValores() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            // Escribir los valores de los campos de texto en el archivo
            writer.println(producto1Field.getText());
            writer.println(producto2Field.getText());
            JOptionPane.showMessageDialog(null, "Valores guardados correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
