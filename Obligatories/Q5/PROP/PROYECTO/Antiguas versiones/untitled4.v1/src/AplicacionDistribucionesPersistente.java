import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Perfil implements Serializable {
    String usuario;
    String contraseña;
    List<Distribucion> distribuciones;

    public Perfil(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.distribuciones = new ArrayList<>();
    }

    public String getUsuario() {
        return usuario;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public List<Distribucion> getDistribuciones() {
        return distribuciones;
    }

    public void añadirDistribucion(Distribucion distribucion) {
        distribuciones.add(distribucion);
    }

    public void eliminarDistribucion(Distribucion distribucion) {
        distribuciones.remove(distribucion);
    }
}

class Producto implements Serializable {
    String nombre;
    String marca;
    double precio;
    int cantidad;

    public Producto(String nombre, String marca, double precio, int cantidad) {
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

class Distribucion implements Serializable {
    String nombre;
    List<Producto> productos;

    public Distribucion(String nombre) {
        this.nombre = nombre;
        this.productos = new ArrayList<>();
    }

    public void añadirProducto(Producto producto) {
        productos.add(producto);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public String getNombre() {
        return nombre;
    }
}

public class AplicacionDistribucionesPersistente extends JFrame {

    private Map<String, Perfil> perfiles = new HashMap<>();
    private Perfil perfilActual;
    private Distribucion distribucionActual;

    private static final String ARCHIVO_PERFILES = "perfiles.dat";

    public AplicacionDistribucionesPersistente() {
        setTitle("Aplicación de Gestión de Distribuciones");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cargarDatos();
        mostrarMenuPrincipal();
    }

    private void mostrarMenuPrincipal() {
        JPanel panel = new JPanel(new GridLayout(5, 1));
        JButton crearPerfilBtn = new JButton("Crear Perfil");
        JButton cargarPerfilBtn = new JButton("Cargar Perfil");
        JButton borrarPerfilBtn = new JButton("Borrar Perfil");
        JButton editarPerfilBtn = new JButton("Editar Perfil");
        JButton salirBtn = new JButton("Salir");

        crearPerfilBtn.addActionListener(e -> mostrarFormularioCrearPerfil());
        cargarPerfilBtn.addActionListener(e -> mostrarFormularioCargarPerfil());
        borrarPerfilBtn.addActionListener(e -> mostrarFormularioBorrarPerfil());
        editarPerfilBtn.addActionListener(e -> mostrarFormularioEditarPerfil());
        salirBtn.addActionListener(e -> {
            guardarDatos();
            System.exit(0);
        });

        panel.add(crearPerfilBtn);
        panel.add(cargarPerfilBtn);
        panel.add(borrarPerfilBtn);
        panel.add(editarPerfilBtn);
        panel.add(salirBtn);

        setContentPane(panel);
        revalidate();
    }

    private void mostrarFormularioCrearPerfil() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        JTextField usuarioField = new JTextField();
        JPasswordField contraseñaField = new JPasswordField();
        JButton crearBtn = new JButton("Crear");
        JButton atrasBtn = new JButton("Atrás");

        panel.add(new JLabel("Usuario:"));
        panel.add(usuarioField);
        panel.add(new JLabel("Contraseña:"));
        panel.add(contraseñaField);
        panel.add(crearBtn);
        panel.add(atrasBtn);

        crearBtn.addActionListener(e -> {
            String usuario = usuarioField.getText();
            String contraseña = new String(contraseñaField.getPassword());
            if (perfiles.containsKey(usuario)) {
                JOptionPane.showMessageDialog(this, "El usuario ya existe, elija otro.");
            } else {
                perfiles.put(usuario, new Perfil(usuario, contraseña));
                JOptionPane.showMessageDialog(this, "Perfil creado con éxito.");
                guardarDatos();
                mostrarMenuPrincipal();
            }
        });

        atrasBtn.addActionListener(e -> mostrarMenuPrincipal());

        setContentPane(panel);
        revalidate();
    }

    private void mostrarFormularioCargarPerfil() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField usuarioField = new JTextField();
        JPasswordField contraseñaField = new JPasswordField();
        JButton cargarBtn = new JButton("Cargar");
        JButton atrasBtn = new JButton("Atrás");

        panel.add(new JLabel("Usuario:"));
        panel.add(usuarioField);
        panel.add(new JLabel("Contraseña:"));
        panel.add(contraseñaField);
        panel.add(cargarBtn);
        panel.add(atrasBtn);

        cargarBtn.addActionListener(e -> {
            String usuario = usuarioField.getText();
            String contraseña = new String(contraseñaField.getPassword());

            if (perfiles.containsKey(usuario) && perfiles.get(usuario).contraseña.equals(contraseña)) {
                perfilActual = perfiles.get(usuario);
                JOptionPane.showMessageDialog(this, "Perfil cargado correctamente.");
                mostrarMenuDistribuciones();
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
            }
        });

        atrasBtn.addActionListener(e -> mostrarMenuPrincipal());

        setContentPane(panel);
        revalidate();
    }

    private void mostrarFormularioBorrarPerfil() {
        JPanel panel = new JPanel(new GridLayout(3, 2));

        if (perfiles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay perfiles para borrar.");
            return;
        }

        String[] nombresUsuarios = perfiles.keySet().toArray(new String[0]);
        JComboBox<String> comboBoxUsuarios = new JComboBox<>(nombresUsuarios);
        JButton borrarBtn = new JButton("Borrar");
        JButton atrasBtn = new JButton("Atrás");

        panel.add(new JLabel("Seleccionar usuario a borrar:"));
        panel.add(comboBoxUsuarios);
        panel.add(borrarBtn);
        panel.add(atrasBtn);

        borrarBtn.addActionListener(e -> {
            String usuarioSeleccionado = (String) comboBoxUsuarios.getSelectedItem();
            if (usuarioSeleccionado != null) {
                int confirmacion = JOptionPane.showConfirmDialog(this,
                        "¿Estás seguro de que deseas borrar el perfil de " + usuarioSeleccionado + "?",
                        "Confirmación", JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    perfiles.remove(usuarioSeleccionado);
                    guardarDatos();
                    JOptionPane.showMessageDialog(this, "Perfil eliminado.");
                    mostrarMenuPrincipal();
                }
            }
        });

        atrasBtn.addActionListener(e -> mostrarMenuPrincipal());

        setContentPane(panel);
        revalidate();
    }

    private void mostrarMenuDistribuciones() {
        JPanel panel = new JPanel(new GridLayout(6, 1));
        JButton crearDistBtn = new JButton("Crear Distribución");
        JButton cargarDistBtn = new JButton("Cargar Distribución");
        JButton borrarDistBtn = new JButton("Borrar Distribución");
        JButton editarDistBtn = new JButton("Editar Distribución");
        JButton guardarDistBtn = new JButton("Guardar Distribución");
        JButton atrasBtn = new JButton("Atrás");

        crearDistBtn.addActionListener(e -> mostrarFormularioCrearDistribucion());
        cargarDistBtn.addActionListener(e -> mostrarFormularioCargarDistribucion());
        borrarDistBtn.addActionListener(e -> mostrarFormularioBorrarDistribucion());
        editarDistBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Distribución editada"));
        guardarDistBtn.addActionListener(e -> {
            guardarDatos();
            JOptionPane.showMessageDialog(this, "Distribución guardada");
        });
        atrasBtn.addActionListener(e -> mostrarMenuPrincipal());

        panel.add(crearDistBtn);
        panel.add(cargarDistBtn);
        panel.add(borrarDistBtn);
        panel.add(editarDistBtn);
        panel.add(guardarDistBtn);
        panel.add(atrasBtn);

        setContentPane(panel);
        revalidate();
    }

    private void mostrarFormularioEditarPerfil() {
        JPanel panel = new JPanel(new GridLayout(4, 2));

        if (perfiles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay perfiles disponibles para editar.");
            return;
        }

        String[] nombresUsuarios = perfiles.keySet().toArray(new String[0]);
        JComboBox<String> comboBoxUsuarios = new JComboBox<>(nombresUsuarios);
        JPasswordField nuevaContraseñaField = new JPasswordField();
        JButton editarBtn = new JButton("Editar");
        JButton atrasBtn = new JButton("Atrás");

        panel.add(new JLabel("Seleccionar usuario:"));
        panel.add(comboBoxUsuarios);
        panel.add(new JLabel("Nueva Contraseña:"));
        panel.add(nuevaContraseñaField);
        panel.add(editarBtn);
        panel.add(atrasBtn);

        editarBtn.addActionListener(e -> {
            String usuarioSeleccionado = (String) comboBoxUsuarios.getSelectedItem();
            String nuevaContraseña = new String(nuevaContraseñaField.getPassword());

            if (usuarioSeleccionado != null && !nuevaContraseña.isEmpty()) {
                Perfil perfil = perfiles.get(usuarioSeleccionado);
                perfil.setContraseña(nuevaContraseña);
                guardarDatos();
                JOptionPane.showMessageDialog(this, "Contraseña cambiada.");
                mostrarMenuPrincipal();
            }
        });

        atrasBtn.addActionListener(e -> mostrarMenuPrincipal());

        setContentPane(panel);
        revalidate();
    }

    private void mostrarFormularioCrearDistribucion() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField nombreDistField = new JTextField();
        JButton crearBtn = new JButton("Crear");
        JButton atrasBtn = new JButton("Atrás");

        panel.add(new JLabel("Nombre de la Distribución:"));
        panel.add(nombreDistField);
        panel.add(crearBtn);
        panel.add(atrasBtn);

        crearBtn.addActionListener(e -> {
            String nombre = nombreDistField.getText();
            if (!nombre.isEmpty()) {
                Distribucion nuevaDistribucion = new Distribucion(nombre);
                perfilActual.añadirDistribucion(nuevaDistribucion);
                distribucionActual = nuevaDistribucion;
                JOptionPane.showMessageDialog(this, "Distribución creada.");
                mostrarMenuDistribuciones();
            }
        });

        atrasBtn.addActionListener(e -> mostrarMenuDistribuciones());

        setContentPane(panel);
        revalidate();
    }

    private void mostrarFormularioCargarDistribucion() {
        if (perfilActual.getDistribuciones().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay distribuciones guardadas.");
            return;
        }

        JPanel panel = new JPanel(new GridLayout(3, 2));
        String[] nombresDistribuciones = perfilActual.getDistribuciones().stream()
                .map(Distribucion::getNombre)
                .toArray(String[]::new);
        JComboBox<String> comboBoxDistribuciones = new JComboBox<>(nombresDistribuciones);
        JButton cargarBtn = new JButton("Cargar");
        JButton atrasBtn = new JButton("Atrás");

        panel.add(new JLabel("Seleccionar distribución:"));
        panel.add(comboBoxDistribuciones);
        panel.add(cargarBtn);
        panel.add(atrasBtn);

        cargarBtn.addActionListener(e -> {
            String distribucionSeleccionada = (String) comboBoxDistribuciones.getSelectedItem();
            if (distribucionSeleccionada != null) {
                distribucionActual = perfilActual.getDistribuciones().stream()
                        .filter(d -> d.getNombre().equals(distribucionSeleccionada))
                        .findFirst()
                        .orElse(null);
                if (distribucionActual != null) {
                    JOptionPane.showMessageDialog(this, "Distribución cargada.");
                    mostrarOpcionesDistribucion();
                }
            }
        });

        atrasBtn.addActionListener(e -> mostrarMenuDistribuciones());

        setContentPane(panel);
        revalidate();
    }

    private void mostrarFormularioBorrarDistribucion() {
        if (perfilActual.getDistribuciones().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay distribuciones para borrar.");
            return;
        }

        JPanel panel = new JPanel(new GridLayout(3, 2));
        String[] nombresDistribuciones = perfilActual.getDistribuciones().stream()
                .map(Distribucion::getNombre)
                .toArray(String[]::new);
        JComboBox<String> comboBoxDistribuciones = new JComboBox<>(nombresDistribuciones);
        JButton borrarBtn = new JButton("Borrar");
        JButton atrasBtn = new JButton("Atrás");

        panel.add(new JLabel("Seleccionar distribución a borrar:"));
        panel.add(comboBoxDistribuciones);
        panel.add(borrarBtn);
        panel.add(atrasBtn);

        borrarBtn.addActionListener(e -> {
            String distribucionSeleccionada = (String) comboBoxDistribuciones.getSelectedItem();
            if (distribucionSeleccionada != null) {
                int confirmacion = JOptionPane.showConfirmDialog(this,
                        "¿Estás seguro de que deseas borrar la distribución " + distribucionSeleccionada + "?",
                        "Confirmación", JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    perfilActual.getDistribuciones().removeIf(d -> d.getNombre().equals(distribucionSeleccionada));
                    guardarDatos();
                    JOptionPane.showMessageDialog(this, "Distribución eliminada.");
                    mostrarMenuDistribuciones();
                }
            }
        });

        atrasBtn.addActionListener(e -> mostrarMenuDistribuciones());

        setContentPane(panel);
        revalidate();
    }

    private void mostrarOpcionesDistribucion() {
        JPanel panel = new JPanel(new GridLayout(5, 1));
        JButton añadirProductoBtn = new JButton("Añadir Producto");
        JButton eliminarProductoBtn = new JButton("Eliminar Producto");
        JButton modificarProductoBtn = new JButton("Modificar Producto");
        JButton similitudesBtn = new JButton("Consultar Similitudes");
        JButton atrasBtn = new JButton("Atrás");

        añadirProductoBtn.addActionListener(e -> mostrarFormularioAñadirProducto());
        eliminarProductoBtn.addActionListener(e -> mostrarFormularioEliminarProducto());
        modificarProductoBtn.addActionListener(e -> mostrarFormularioModificarProducto());
        similitudesBtn.addActionListener(e -> mostrarSimilitudesProductos());
        atrasBtn.addActionListener(e -> mostrarMenuDistribuciones());

        panel.add(añadirProductoBtn);
        panel.add(eliminarProductoBtn);
        panel.add(modificarProductoBtn);
        panel.add(similitudesBtn);
        panel.add(atrasBtn);

        setContentPane(panel);
        revalidate();
    }

    private void mostrarFormularioAñadirProducto() {
        JPanel panel = new JPanel(new GridLayout(5, 2));
        JTextField nombreField = new JTextField();
        JTextField marcaField = new JTextField();
        JTextField precioField = new JTextField();
        JTextField cantidadField = new JTextField();
        JButton añadirBtn = new JButton("Añadir");
        JButton atrasBtn = new JButton("Atrás");

        panel.add(new JLabel("Nombre del Producto:"));
        panel.add(nombreField);
        panel.add(new JLabel("Marca:"));
        panel.add(marcaField);
        panel.add(new JLabel("Precio:"));
        panel.add(precioField);
        panel.add(new JLabel("Cantidad:"));
        panel.add(cantidadField);
        panel.add(añadirBtn);
        panel.add(atrasBtn);

        añadirBtn.addActionListener(e -> {
            String nombre = nombreField.getText();
            String marca = marcaField.getText();
            double precio = Double.parseDouble(precioField.getText());
            int cantidad = Integer.parseInt(cantidadField.getText());

            Producto nuevoProducto = new Producto(nombre, marca, precio, cantidad);
            distribucionActual.añadirProducto(nuevoProducto);
            JOptionPane.showMessageDialog(this, "Producto añadido.");
            guardarDatos();
            mostrarOpcionesDistribucion();
        });

        atrasBtn.addActionListener(e -> mostrarOpcionesDistribucion());

        setContentPane(panel);
        revalidate();
    }

    private void mostrarFormularioEliminarProducto() {
        if (distribucionActual == null || distribucionActual.getProductos().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos en la distribución.");
            return;
        }

        String[] nombresProductos = distribucionActual.getProductos().stream()
                .map(Producto::getNombre)
                .toArray(String[]::new);

        String seleccion = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione un producto a eliminar:",
                "Eliminar Producto",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nombresProductos,
                nombresProductos[0]
        );

        if (seleccion != null) {
            distribucionActual.getProductos().removeIf(p -> p.getNombre().equals(seleccion));
            JOptionPane.showMessageDialog(this, "Producto eliminado.");
            guardarDatos();
        }
    }

    private void mostrarFormularioModificarProducto() {
        if (distribucionActual == null || distribucionActual.getProductos().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos en la distribución.");
            return;
        }

        String[] nombresProductos = distribucionActual.getProductos().stream()
                .map(Producto::getNombre)
                .toArray(String[]::new);

        String seleccion = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione un producto a modificar:",
                "Modificar Producto",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nombresProductos,
                nombresProductos[0]
        );

        if (seleccion != null) {
            Producto productoSeleccionado = distribucionActual.getProductos().stream()
                    .filter(p -> p.getNombre().equals(seleccion))
                    .findFirst()
                    .orElse(null);

            if (productoSeleccionado != null) {
                JTextField precioField = new JTextField(String.valueOf(productoSeleccionado.getPrecio()));
                JTextField cantidadField = new JTextField(String.valueOf(productoSeleccionado.getCantidad()));

                Object[] mensaje = {
                        "Nuevo Precio:", precioField,
                        "Nueva Cantidad:", cantidadField
                };

                int opcion = JOptionPane.showConfirmDialog(this, mensaje, "Modificar Producto", JOptionPane.OK_CANCEL_OPTION);

                if (opcion == JOptionPane.OK_OPTION) {
                    double nuevoPrecio = Double.parseDouble(precioField.getText());
                    int nuevaCantidad = Integer.parseInt(cantidadField.getText());
                    productoSeleccionado.setPrecio(nuevoPrecio);
                    productoSeleccionado.setCantidad(nuevaCantidad);
                    JOptionPane.showMessageDialog(this, "Producto modificado.");
                    guardarDatos();
                }
            }
        }
    }

    private void mostrarSimilitudesProductos() {
        if (distribucionActual == null || distribucionActual.getProductos().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos para comparar.");
            return;
        }

        StringBuilder mensaje = new StringBuilder("Productos similares encontrados:\n");
        List<Producto> productos = distribucionActual.getProductos();

        for (int i = 0; i < productos.size(); i++) {
            Producto p1 = productos.get(i);
            for (int j = i + 1; j < productos.size(); j++) {
                Producto p2 = productos.get(j);
                if (p1.getMarca().equals(p2.getMarca()) && p1.getNombre().equals(p2.getNombre())) {
                    mensaje.append("- ").append(p1.getNombre())
                            .append(" de la marca ").append(p1.getMarca())
                            .append("\n");
                }
            }
        }

        JOptionPane.showMessageDialog(this, mensaje.toString());
    }

    private void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_PERFILES))) {
            oos.writeObject(perfiles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarDatos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_PERFILES))) {
            perfiles = (Map<String, Perfil>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            perfiles = new HashMap<>();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AplicacionDistribucionesPersistente().setVisible(true));
    }
}




