import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;  // Asegúrate de que sea el java.util.List
import java.util.Map;


// Clase para representar un perfil de usuario (debe ser Serializable)
class Perfil implements Serializable {
    String usuario;
    String contraseña;
    List<Distribucion> distribuciones; // Lista de distribuciones asociadas al perfil

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

// Clase para representar un producto (debe ser Serializable)
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

// Clase para representar una distribución de productos (debe ser Serializable)
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

    private Map<String, Perfil> perfiles = new HashMap<>(); // Mapa para almacenar los perfiles (usuario -> Perfil)
    private Perfil perfilActual; // Perfil cargado actualmente
    private Distribucion distribucionActual; // Distribución cargada

    // Archivos para la persistencia de datos
    private static final String ARCHIVO_PERFILES = "perfiles.dat";

    // Constructor
    public AplicacionDistribucionesPersistente() {
        setTitle("Aplicación de Gestión con Persistencia");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Cargar los datos al iniciar la aplicación
        cargarDatos();

        mostrarMenuPrincipal();
    }

    // Método para mostrar el menú principal (Gestión de Perfiles)
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

    // Método para mostrar el formulario de creación de perfil
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
                guardarDatos();  // Guardar los datos después de crear el perfil
                mostrarMenuPrincipal();
            }
        });

        atrasBtn.addActionListener(e -> mostrarMenuPrincipal());

        setContentPane(panel);
        revalidate();
    }

    // Método para mostrar el formulario de cargar perfil
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
                perfilActual = perfiles.get(usuario); // Cargar el perfil actual
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

    // Método para mostrar el formulario de borrar perfil
    private void mostrarFormularioBorrarPerfil() {
        JPanel panel = new JPanel(new GridLayout(3, 2));

        if (perfiles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay perfiles para borrar.");
            return;
        }

        // Mostrar lista de usuarios existentes
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
                    perfiles.remove(usuarioSeleccionado);  // Borrar el perfil
                    guardarDatos();  // Guardar los cambios
                    JOptionPane.showMessageDialog(this, "Perfil eliminado.");
                    mostrarMenuPrincipal();  // Regresar al menú principal
                }
            }
        });

        atrasBtn.addActionListener(e -> mostrarMenuPrincipal());

        setContentPane(panel);
        revalidate();
    }


    // Método para mostrar el menú de distribuciones
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

    // Método para mostrar el formulario de editar perfil
    private void mostrarFormularioEditarPerfil() {
        JPanel panel = new JPanel(new GridLayout(4, 2));

        if (perfiles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay perfiles disponibles para editar.");
            return;
        }

        // Mostrar lista de usuarios existentes
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
                perfil.setContraseña(nuevaContraseña); // Actualizar contraseña
                guardarDatos(); // Guardar los cambios
                JOptionPane.showMessageDialog(this, "Contraseña actualizada para " + usuarioSeleccionado + ".");
                mostrarMenuPrincipal(); // Regresar al menú principal
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, introduce una nueva contraseña.");
            }
        });

        atrasBtn.addActionListener(e -> mostrarMenuPrincipal());

        setContentPane(panel);
        revalidate();
    }


    // Método para crear una distribución
    private void mostrarFormularioCrearDistribucion() {
        JPanel panel = new JPanel(new GridLayout(5, 2));
        JTextField nombreField = new JTextField();
        JTextField marcaField = new JTextField();
        JTextField precioField = new JTextField();
        JTextField cantidadField = new JTextField();
        JButton añadirBtn = new JButton("Añadir Producto");
        JButton atrasBtn = new JButton("Atrás");

        panel.add(new JLabel("Nombre del producto:"));
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

            if (distribucionActual == null) {
                distribucionActual = new Distribucion("Nueva Distribución");
            }
            distribucionActual.añadirProducto(nuevoProducto);
            perfilActual.añadirDistribucion(distribucionActual);
            JOptionPane.showMessageDialog(this, "Producto añadido.");
            guardarDatos(); // Guardar datos después de añadir el producto
        });

        atrasBtn.addActionListener(e -> mostrarMenuDistribuciones());

        setContentPane(panel);
        revalidate();
    }

    // Método para cargar una distribución existente
    private void mostrarFormularioCargarDistribucion() {
        List<Distribucion> distribuciones = perfilActual.getDistribuciones();
        String[] nombresDistribuciones = distribuciones.stream()
                .map(Distribucion::getNombre)
                .toArray(String[]::new);

        if (nombresDistribuciones.length == 0) {
            JOptionPane.showMessageDialog(this, "No hay distribuciones disponibles.");
            return;
        }

        String seleccion = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione una distribución:",
                "Cargar Distribución",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nombresDistribuciones,
                nombresDistribuciones[0]
        );

        if (seleccion != null) {
            for (Distribucion d : distribuciones) {
                if (d.getNombre().equals(seleccion)) {
                    distribucionActual = d;
                    JOptionPane.showMessageDialog(this, "Distribución cargada.");
                    break;
                }
            }
        }
    }

    // Método para borrar una distribución
    private void mostrarFormularioBorrarDistribucion() {
        List<Distribucion> distribuciones = perfilActual.getDistribuciones();
        String[] nombresDistribuciones = distribuciones.stream()
                .map(Distribucion::getNombre)
                .toArray(String[]::new);

        if (nombresDistribuciones.length == 0) {
            JOptionPane.showMessageDialog(this, "No hay distribuciones disponibles.");
            return;
        }

        String seleccion = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione una distribución a borrar:",
                "Borrar Distribución",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nombresDistribuciones,
                nombresDistribuciones[0]
        );

        if (seleccion != null) {
            distribuciones.removeIf(d -> d.getNombre().equals(seleccion));
            distribucionActual = null;
            JOptionPane.showMessageDialog(this, "Distribución borrada.");
            guardarDatos(); // Guardar los datos tras borrar la distribución
        }
    }

    // Método para cargar datos desde los archivos
    private void cargarDatos() {
        try (ObjectInputStream oisPerfiles = new ObjectInputStream(new FileInputStream(ARCHIVO_PERFILES))) {
            perfiles = (HashMap<String, Perfil>) oisPerfiles.readObject();
            JOptionPane.showMessageDialog(this, "Datos cargados correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "No se pudieron cargar los datos.");
        }
    }

    // Método para guardar datos en archivos
    private void guardarDatos() {
        try (ObjectOutputStream oosPerfiles = new ObjectOutputStream(new FileOutputStream(ARCHIVO_PERFILES))) {
            oosPerfiles.writeObject(perfiles);
            JOptionPane.showMessageDialog(this, "Datos guardados correctamente.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No se pudieron guardar los datos.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AplicacionDistribucionesPersistente app = new AplicacionDistribucionesPersistente();
            app.setVisible(true);
        });
    }
}
