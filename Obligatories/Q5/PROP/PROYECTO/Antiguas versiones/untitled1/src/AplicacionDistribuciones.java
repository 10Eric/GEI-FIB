import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AplicacionDistribuciones extends JFrame {

    // Constructor
    public AplicacionDistribuciones() {
        setTitle("Aplicación de Gestión");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mostrarMenuPrincipal();
    }

    // Método para mostrar el menú principal (Gestión de Perfiles)
    private void mostrarMenuPrincipal() {
        JPanel panel = new JPanel(new GridLayout(4, 1));
        JButton crearPerfilBtn = new JButton("Crear Perfil");
        JButton cargarPerfilBtn = new JButton("Cargar Perfil");
        JButton borrarPerfilBtn = new JButton("Borrar Perfil");
        JButton editarPerfilBtn = new JButton("Editar Perfil");

        crearPerfilBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Perfil creado"));
        cargarPerfilBtn.addActionListener(e -> mostrarMenuDistribuciones());
        borrarPerfilBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Perfil borrado"));
        editarPerfilBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Perfil editado"));

        panel.add(crearPerfilBtn);
        panel.add(cargarPerfilBtn);
        panel.add(borrarPerfilBtn);
        panel.add(editarPerfilBtn);

        setContentPane(panel);
        revalidate();
    }

    // Método para mostrar el menú de distribuciones
    private void mostrarMenuDistribuciones() {
        JPanel panel = new JPanel(new GridLayout(5, 1));
        JButton crearDistBtn = new JButton("Crear Distribución");
        JButton cargarDistBtn = new JButton("Cargar Distribución");
        JButton borrarDistBtn = new JButton("Borrar Distribución");
        JButton editarDistBtn = new JButton("Editar Distribución");
        JButton guardarDistBtn = new JButton("Guardar Distribución");

        crearDistBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Distribución creada"));
        cargarDistBtn.addActionListener(e -> mostrarMenuProductos());
        borrarDistBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Distribución borrada"));
        editarDistBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Distribución editada"));
        guardarDistBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Distribución guardada"));

        panel.add(crearDistBtn);
        panel.add(cargarDistBtn);
        panel.add(borrarDistBtn);
        panel.add(editarDistBtn);
        panel.add(guardarDistBtn);

        setContentPane(panel);
        revalidate();
    }

    // Método para mostrar el menú de productos
    private void mostrarMenuProductos() {
        JPanel panel = new JPanel(new GridLayout(5, 1));
        JButton añadirProdBtn = new JButton("Añadir Producto");
        JButton eliminarProdBtn = new JButton("Eliminar Producto");
        JButton modificarProdBtn = new JButton("Modificar Producto");
        JButton consultarProdBtn = new JButton("Consultar Producto");
        JButton consultarSimilitudesBtn = new JButton("Consultar Similitudes");

        añadirProdBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Producto añadido"));
        eliminarProdBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Producto eliminado"));
        modificarProdBtn.addActionListener(e -> mostrarMenuModificacionProducto());
        consultarProdBtn.addActionListener(e -> mostrarMenuConsultaProducto());
        consultarSimilitudesBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Similitudes consultadas"));

        panel.add(añadirProdBtn);
        panel.add(eliminarProdBtn);
        panel.add(modificarProdBtn);
        panel.add(consultarProdBtn);
        panel.add(consultarSimilitudesBtn);

        setContentPane(panel);
        revalidate();
    }

    // Método para mostrar el menú de modificación de un producto
    private void mostrarMenuModificacionProducto() {
        JPanel panel = new JPanel(new GridLayout(4, 1));
        JButton modificarCantidadBtn = new JButton("Modificar Cantidad");
        JButton modificarPrecioBtn = new JButton("Modificar Precio");

        modificarCantidadBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Cantidad modificada"));
        modificarPrecioBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Precio modificado"));

        panel.add(modificarCantidadBtn);
        panel.add(modificarPrecioBtn);

        setContentPane(panel);
        revalidate();
    }

    // Método para mostrar el menú de consulta de un producto
    private void mostrarMenuConsultaProducto() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        JButton consultarCantidadBtn = new JButton("Consultar Cantidad");
        JButton consultarPrecioBtn = new JButton("Consultar Precio");

        consultarCantidadBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Cantidad consultada"));
        consultarPrecioBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Precio consultado"));

        panel.add(consultarCantidadBtn);
        panel.add(consultarPrecioBtn);

        setContentPane(panel);
        revalidate();
    }

    // Método main para iniciar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AplicacionDistribuciones app = new AplicacionDistribuciones();
            app.setVisible(true);
        });
    }
}
