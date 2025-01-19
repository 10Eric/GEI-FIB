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

    public String getContraseña() {
        return contraseña;
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
    private Map<Producto, Integer> similitudes;

    public Producto(String nombre, String marca, double precio, int cantidad) {
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.cantidad = cantidad;
        this.similitudes = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
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

    public void setSimilitud(Producto otroProducto, int valorSimilitud) {
        similitudes.put(otroProducto, valorSimilitud);  // Añadir o actualizar el valor de similitud
    }

    public int getSimilitud(Producto otroProducto) {
        return similitudes.getOrDefault(otroProducto, 0);  // Retorna 0 si no hay similitud definida
    }

    public Map<Producto, Integer> getSimilitudes() {
        return similitudes;
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

    public int consultarSimilitud(Producto p1, Producto p2) {
        return p1.getSimilitud(p2);
    }
    public Producto getProductoPorNombre(String nombre) {
        // Recorremos la lista de productos para buscar el producto por su nombre
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto; // Si encontramos el producto, lo devolvemos
            }
        }
        // Si no se encuentra el producto, devolvemos null o puedes lanzar una excepción
        return null;
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
        JPasswordField contraseñaActualField = new JPasswordField();
        JPasswordField nuevaContraseñaField = new JPasswordField();
        JButton editarBtn = new JButton("Editar");
        JButton atrasBtn = new JButton("Atrás");

        panel.add(new JLabel("Seleccionar usuario:"));
        panel.add(comboBoxUsuarios);
        panel.add(new JLabel("Contraseña Actual:"));
        panel.add(contraseñaActualField);
        panel.add(new JLabel("Nueva Contraseña:"));
        panel.add(nuevaContraseñaField);
        panel.add(editarBtn);
        panel.add(atrasBtn);

        editarBtn.addActionListener(e -> {
            String usuarioSeleccionado = (String) comboBoxUsuarios.getSelectedItem();
            String contraseñaActual = new String(contraseñaActualField.getPassword());
            String nuevaContraseña = new String(nuevaContraseñaField.getPassword());

            if (usuarioSeleccionado != null && !nuevaContraseña.isEmpty()) {
                Perfil perfil = perfiles.get(usuarioSeleccionado);
                if (perfil.getContraseña().equals(contraseñaActual)) {
                    perfil.setContraseña(nuevaContraseña);
                    guardarDatos();
                    JOptionPane.showMessageDialog(this, "Contraseña cambiada.");
                    mostrarMenuPrincipal();
                }
                else {
                    JOptionPane.showMessageDialog(this, "Contraseña actual incorrecta.");
                }
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

    private void mostrarEstadoActual(JPanel panel) {
        String estado = "Perfil: " + perfilActual.getUsuario();
        if (distribucionActual != null) {
            estado += " | Distribución: " + distribucionActual.getNombre();
        }
        panel.add(new JLabel(estado), BorderLayout.NORTH);
    }

    private void mostrarOpcionesDistribucion() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel opcionesPanel = new JPanel(new GridLayout(5, 1));
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

        opcionesPanel.add(añadirProductoBtn);
        opcionesPanel.add(eliminarProductoBtn);
        opcionesPanel.add(modificarProductoBtn);
        opcionesPanel.add(similitudesBtn);
        opcionesPanel.add(atrasBtn);

        mostrarEstadoActual(panel);  // Mostrar el perfil y distribución actuales
        panel.add(opcionesPanel, BorderLayout.CENTER);

        setContentPane(panel);
        revalidate();
    }

    private void mostrarFormularioAñadirProducto() {
        JPanel panel = new JPanel(new GridLayout(7, 1)); // Incrementamos las filas
        JTextField nombreField = new JTextField();
        JTextField marcaField = new JTextField();
        JTextField precioField = new JTextField();
        JTextField cantidadField = new JTextField();
        JComboBox<String> productosCombo = new JComboBox<>(); // Desplegable de productos
        JTextField similitudField = new JTextField(); // Campo para ingresar la similitud
        JButton añadirBtn = new JButton("Añadir");
        JButton atrasBtn = new JButton("Atrás");

        // Llenar el JComboBox con los productos de la distribución actual
        List<Producto> productos = distribucionActual.getProductos();
        productosCombo.addItem("Ninguno");
        for (Producto producto : productos) {
            productosCombo.addItem(producto.getNombre());
        }

        panel.add(new JLabel("Nombre del Producto:"));
        panel.add(nombreField);
        panel.add(new JLabel("Marca:"));
        panel.add(marcaField);
        panel.add(new JLabel("Precio:"));
        panel.add(precioField);
        panel.add(new JLabel("Cantidad:"));
        panel.add(cantidadField);
        panel.add(new JLabel("Similitud con otro producto (opcional):"));
        panel.add(productosCombo);
        panel.add(new JLabel("Valor de similitud (0-100):"));
        panel.add(similitudField);
        panel.add(añadirBtn);
        panel.add(atrasBtn);

        añadirBtn.addActionListener(e -> {
            String nombre = nombreField.getText();
            String marca = marcaField.getText();
            double precio = Double.parseDouble(precioField.getText());
            int cantidad = Integer.parseInt(cantidadField.getText());

            Producto nuevoProducto = new Producto(nombre, marca, precio, cantidad);
            distribucionActual.añadirProducto(nuevoProducto);

            // Comprobar si se ha seleccionado un producto para la similitud
            String productoSeleccionado = (String) productosCombo.getSelectedItem();
            if (!"Ninguno".equals(productoSeleccionado)) {
                Producto productoExistente = distribucionActual.getProductoPorNombre(productoSeleccionado);
                int valorSimilitud = Integer.parseInt(similitudField.getText());

                // Establecer la similitud bidireccional
                nuevoProducto.setSimilitud(productoExistente, valorSimilitud);
                productoExistente.setSimilitud(nuevoProducto, valorSimilitud);
            }

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

        JPanel panel = new JPanel(new GridLayout(4, 1));
        JComboBox<String> producto1Combo = new JComboBox<>();
        JComboBox<String> producto2Combo = new JComboBox<>();
        JTextField similitudField = new JTextField();
        JButton actualizarBtn = new JButton("Actualizar Similitud");
        JButton atrasBtn = new JButton("Atrás");

        // Llenar los desplegables con los productos de la distribución
        List<Producto> productos = distribucionActual.getProductos();
        for (Producto producto : productos) {
            producto1Combo.addItem(producto.getNombre());
            producto2Combo.addItem(producto.getNombre());
        }

        panel.add(new JLabel("Selecciona el primer producto:"));
        panel.add(producto1Combo);
        panel.add(new JLabel("Selecciona el segundo producto:"));
        panel.add(producto2Combo);
        panel.add(new JLabel("Valor de similitud:"));
        panel.add(similitudField);
        panel.add(actualizarBtn);
        panel.add(atrasBtn);

        ActionListener actualizarSimilitudAction = e -> {
            String producto1Nombre = (String) producto1Combo.getSelectedItem();
            String producto2Nombre = (String) producto2Combo.getSelectedItem();

            // Evitar que se seleccione el mismo producto
            if (producto1Nombre.equals(producto2Nombre)) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione productos diferentes.");
                return;
            }

            // Obtener los productos seleccionados
            Producto producto1 = distribucionActual.getProductoPorNombre(producto1Nombre);
            Producto producto2 = distribucionActual.getProductoPorNombre(producto2Nombre);

            if (producto1 != null && producto2 != null) {
                // Obtener la similitud actual
                int similitudActual = producto1.getSimilitud(producto2);
                similitudField.setText(String.valueOf(similitudActual));
            } else {
                JOptionPane.showMessageDialog(this, "Error al obtener los productos.");
            }
        };

        // Actualiza la similitud al cambiar las selecciones de productos
        producto1Combo.addActionListener(actualizarSimilitudAction);
        producto2Combo.addActionListener(actualizarSimilitudAction);

        // Acción para el botón de actualizar similitud
        actualizarBtn.addActionListener(e -> {
            String producto1Nombre = (String) producto1Combo.getSelectedItem();
            String producto2Nombre = (String) producto2Combo.getSelectedItem();

            if (producto1Nombre.equals(producto2Nombre)) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione productos diferentes.");
                return;
            }

            // Obtener los productos seleccionados
            Producto producto1 = distribucionActual.getProductoPorNombre(producto1Nombre);
            Producto producto2 = distribucionActual.getProductoPorNombre(producto2Nombre);

            if (producto1 != null && producto2 != null) {
                // Pedir al usuario el nuevo valor de similitud
                String nuevaSimilitudStr = JOptionPane.showInputDialog(this, "Ingrese el nuevo valor de similitud (entero):");
                try {
                    int nuevaSimilitud = Integer.parseInt(nuevaSimilitudStr);

                    // Actualizar la similitud en ambos productos (asumiendo que es simétrica)
                    producto1.setSimilitud(producto2, nuevaSimilitud);
                    producto2.setSimilitud(producto1, nuevaSimilitud);

                    // Actualizar el campo de similitud visualmente
                    similitudField.setText(String.valueOf(nuevaSimilitud));

                    // Mensaje de éxito
                    JOptionPane.showMessageDialog(this, "Similitud actualizada con éxito.");
                    guardarDatos(); // Guardar cambios si es necesario
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor numérico entero válido.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Error al obtener los productos.");
            }
        });

        // Acción para el botón "Atrás"
        atrasBtn.addActionListener(e -> mostrarOpcionesDistribucion());

        setContentPane(panel);
        revalidate();
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




