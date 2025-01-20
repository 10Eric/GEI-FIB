package edu.upc.prop.clusterxx.controladores_presentacion;
import edu.upc.prop.clusterxx.clases_dominio.Distribucio;
import edu.upc.prop.clusterxx.clases_dominio.Perfil;
import edu.upc.prop.clusterxx.clases_dominio.Prestatgeria;
import edu.upc.prop.clusterxx.controladores.ControladorPerfil;
import edu.upc.prop.clusterxx.controladores.ControladorPrestatgeria;
import edu.upc.prop.clusterxx.controladores.ControladorDistribucio;
import edu.upc.prop.clusterxx.controladores_persistencia.ControladorPersistenciaMain;

import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Presentacion_Main extends JFrame {
    private static Map<String, ControladorPerfil> perfiles = new HashMap<>();
    private static ControladorPerfil perfilActual;
    private static Map<String, ControladorPrestatgeria> prestatgeria = new HashMap<>();
    private static Prestatgeria prestatgeriaActual;
    private static ControladorDistribucio controladorDistribucio;
    private static ControladorPersistenciaMain controladorPersistenciaMain = new ControladorPersistenciaMain();

    public Presentacion_Main() {
        setTitle("Gestión de Perfiles");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cargarDatos();
        mostrarMenuPrincipal();
    }

    private void mostrarMenuPrincipal() {
        setTitle("Gestión de Perfiles");
        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));

        JButton crearBtn = new JButton("Crear Perfil");
        JButton cargarBtn = new JButton("Cargar Perfil");
        JButton borrarBtn = new JButton("Borrar Perfil");
        JButton editarBtn = new JButton("Editar Perfil");
        JButton guardarBtn = new JButton("Guardar Cambios");
        JButton salirBtn = new JButton("Salir");

        crearBtn.addActionListener(e -> mostrarFormularioCrearPerfil());
        cargarBtn.addActionListener(e -> mostrarFormularioCargarPerfil());
        borrarBtn.addActionListener(e -> mostrarFormularioBorrarPerfil());
        editarBtn.addActionListener(e -> mostrarFormularioEditarPerfil());
        guardarBtn.addActionListener(e -> guardarDatos());
        salirBtn.addActionListener(e -> {
            guardarDatos();
            System.exit(0);
        });

        panel.add(crearBtn);
        panel.add(cargarBtn);
        panel.add(borrarBtn);
        panel.add(editarBtn);
        panel.add(guardarBtn);
        panel.add(salirBtn);

        setContentPane(panel);
        revalidate();
    }

    private void mostrarFormularioCrearPerfil() {
        JPanel panel = new JPanel(new GridLayout(3, 2,10,10));
        JTextField usuarioField = new JTextField();
        usuarioField.setHorizontalAlignment(SwingConstants.CENTER);
        JPasswordField contraseñaField = new JPasswordField();
        contraseñaField.setHorizontalAlignment(SwingConstants.CENTER);
        JButton crearBtn = new JButton("Crear");
        JButton atrasBtn = new JButton("Atrás");

        panel.add(new JLabel("Usuario:", SwingConstants.CENTER));
        panel.add(usuarioField);
        panel.add(new JLabel("Contraseña:", SwingConstants.CENTER));
        panel.add(contraseñaField);
        panel.add(crearBtn);
        panel.add(atrasBtn);

        crearBtn.addActionListener(e -> {
            String usuario = usuarioField.getText();
            String contraseña = new String(contraseñaField.getPassword());

            if (perfiles.containsKey(usuario)) {
                JOptionPane.showMessageDialog(this, "El usuario ya existe, elija otro.");
            } else {
                Perfil perfil = new Perfil(usuario, contraseña);
                perfiles.put(usuario, new ControladorPerfil(perfil));
                JOptionPane.showMessageDialog(this, "Perfil creado con éxito.");
                mostrarMenuPrincipal();
            }
        });

        atrasBtn.addActionListener(e -> mostrarMenuPrincipal());

        setContentPane(panel);
        revalidate();
    }

    private void mostrarFormularioCargarPerfil() {
        if (perfiles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay perfiles guardados.");
            return;
        }

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField usuarioField = new JTextField();
        usuarioField.setHorizontalAlignment(SwingConstants.CENTER);
        JPasswordField contraseñaField = new JPasswordField();
        contraseñaField.setHorizontalAlignment(SwingConstants.CENTER);
        JButton cargarBtn = new JButton("Cargar");
        JButton atrasBtn = new JButton("Atrás");

        panel.add(new JLabel("Usuario:", SwingConstants.CENTER));
        panel.add(usuarioField);
        panel.add(new JLabel("Contraseña:", SwingConstants.CENTER));
        panel.add(contraseñaField);
        panel.add(cargarBtn);
        panel.add(atrasBtn);

        cargarBtn.addActionListener(e -> {
            String usuario = usuarioField.getText();
            String contraseña = new String(contraseñaField.getPassword());

            if (perfiles.containsKey(usuario) && perfiles.get(usuario).getContrasenya().equals(contraseña)) {
                perfilActual = perfiles.get(usuario);
                JOptionPane.showMessageDialog(this, "Perfil cargado correctamente.");
                convertirYAsignarPrestatgerias();
                mostrarMenuPrincipalPrestatgeria();
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
            }
        });

        atrasBtn.addActionListener(e -> mostrarMenuPrincipal());

        setContentPane(panel);
        revalidate();
    }

    private void mostrarFormularioBorrarPerfil() {
        if (perfiles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay perfiles para borrar.");
            return;
        }

        String[] nombres = perfiles.keySet().toArray(new String[0]);
        String seleccion = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione el perfil a borrar:",
                "Borrar Perfil",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nombres,
                nombres[0]
        );

        if (seleccion != null) {
            perfiles.remove(seleccion);
            JOptionPane.showMessageDialog(this, "Perfil eliminado.");
        }
    }

    private void mostrarFormularioEditarPerfil() {
        if (perfiles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay perfiles disponibles para editar.");
            return;
        }

        String[] nombres = perfiles.keySet().toArray(new String[0]);
        String seleccion = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione el perfil a editar:",
                "Editar Perfil",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nombres,
                nombres[0]
        );

        if (seleccion != null) {
            JPanel loginPanel = new JPanel(new GridLayout(2, 2, 10, 10));
            JPasswordField contraseñaField = new JPasswordField();
            contraseñaField.setHorizontalAlignment(SwingConstants.CENTER);
            JButton loginBtn = new JButton("Login");
            JButton atrasBtn = new JButton("Atrás");

            loginPanel.add(new JLabel("Contraseña:", SwingConstants.CENTER));
            loginPanel.add(contraseñaField);
            loginPanel.add(loginBtn);
            loginPanel.add(atrasBtn);

            loginBtn.addActionListener(e -> {
                String contraseña = new String(contraseñaField.getPassword());

                if (perfiles.get(seleccion).getContrasenya().equals(contraseña)) {
                    ControladorPerfil controladorPerfil = perfiles.get(seleccion);
                    mostrarFormularioEditarPerfil(controladorPerfil);
                } else {
                    JOptionPane.showMessageDialog(this, "Contraseña incorrecta.");
                }
            });

            atrasBtn.addActionListener(e -> mostrarMenuPrincipal());

            setContentPane(loginPanel);
            revalidate();
        }
    }

    private void mostrarFormularioEditarPerfil(ControladorPerfil controladorPerfil) {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField nuevoUsuarioField = new JTextField(controladorPerfil.getUsuari());
        nuevoUsuarioField.setHorizontalAlignment(SwingConstants.CENTER);
        JPasswordField nuevaContraseñaField = new JPasswordField();
        nuevaContraseñaField.setHorizontalAlignment(SwingConstants.CENTER);
        JButton guardarBtn = new JButton("Guardar");
        JButton atrasBtn = new JButton("Atrás");

        panel.add(new JLabel("Nuevo Usuario:", SwingConstants.CENTER));
        panel.add(nuevoUsuarioField);
        panel.add(new JLabel("Nueva Contraseña:", SwingConstants.CENTER));
        panel.add(nuevaContraseñaField);
        panel.add(guardarBtn);
        panel.add(atrasBtn);

        guardarBtn.addActionListener(e -> {
            String nuevoUsuario = nuevoUsuarioField.getText();
            String nuevaContraseña = new String(nuevaContraseñaField.getPassword());

            if (!nuevoUsuario.equals(controladorPerfil.getUsuari()) && perfiles.containsKey(nuevoUsuario)) {
                JOptionPane.showMessageDialog(this, "El nombre de usuario ya existe.");
            } else {
                perfiles.remove(controladorPerfil.getUsuari());
                controladorPerfil.setUsuari(nuevoUsuario);
                controladorPerfil.setContrasenya(nuevaContraseña);
                perfiles.put(nuevoUsuario, controladorPerfil);
                JOptionPane.showMessageDialog(this, "Perfil editado con éxito.");
                mostrarMenuPrincipal();
            }
        });

        atrasBtn.addActionListener(e -> mostrarMenuPrincipal());

        setContentPane(panel);
        revalidate();
    }

    private static void convertirYAsignarPrestatgerias() {
        for (Prestatgeria p : perfilActual.getPrestatgerias()) {
            prestatgeria.put(p.getNom(), new ControladorPrestatgeria(p));
        }
    }


    private void mostrarMenuPrincipalPrestatgeria() {
        setTitle("Gestión de Prestatgerias");
        JPanel panel = new JPanel(new GridLayout(7, 1, 10, 10));

        JButton crearBtn = new JButton("Crear Prestatgeria");
        JButton cargarBtn = new JButton("Cargar Prestatgeria");
        JButton editarBtn = new JButton("Editar Prestatgeria");
        JButton borrarBtn = new JButton("Borrar Prestatgeria");
        JButton guardarBtn = new JButton("Guardar Cambios");
        JButton atrasBtn = new JButton("Atrás");
        JButton salirBtn = new JButton("Salir");

        crearBtn.addActionListener(e -> mostrarFormularioCrearPrestatgeria());
        cargarBtn.addActionListener(e -> mostrarFormularioCargarPrestatgeria());
        editarBtn.addActionListener(e -> mostrarFormularioEditarPrestatgeria());
        borrarBtn.addActionListener(e -> mostrarFormularioBorrarPrestatgeria());
        guardarBtn.addActionListener(e -> guardarDatos());
        atrasBtn.addActionListener(e -> mostrarMenuPrincipal());
        salirBtn.addActionListener(e -> {
            guardarDatos();
            System.exit(0);
        });

        panel.add(crearBtn);
        panel.add(cargarBtn);
        panel.add(editarBtn);
        panel.add(borrarBtn);
        panel.add(guardarBtn);
        panel.add(atrasBtn);
        panel.add(salirBtn);

        setContentPane(panel);
        revalidate();
    }

    private void mostrarFormularioCrearPrestatgeria() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField nombreField = new JTextField();
        nombreField.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField alturaField = new JTextField();
        alturaField.setHorizontalAlignment(SwingConstants.CENTER);
        JButton crearBtn = new JButton("Crear");
        JButton atrasBtn = new JButton("Atrás");

        panel.add(new JLabel("Nombre de la Prestatgeria:", SwingConstants.CENTER));
        panel.add(nombreField);
        panel.add(new JLabel("Altura de la Prestatgeria:", SwingConstants.CENTER));
        panel.add(alturaField);
        panel.add(crearBtn);
        panel.add(atrasBtn);

        crearBtn.addActionListener(e -> {
            String nombre = nombreField.getText();
            try {
                int altura = Integer.parseInt(alturaField.getText());
                if (prestatgeria.containsKey(nombre)) {
                    JOptionPane.showMessageDialog(this, "La prestatgeria ya existe, elija otro nombre.");
                } else {
                    prestatgeriaActual = new Prestatgeria(nombre, altura);
                    prestatgeria.put(nombre, new ControladorPrestatgeria(prestatgeriaActual));
                    perfilActual.getPrestatgerias().add(prestatgeriaActual);
                    JOptionPane.showMessageDialog(this, "Prestatgeria creada con éxito.");
                    mostrarMenuPrincipalPrestatgeria();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese una altura válida.");
            }
        });

        atrasBtn.addActionListener(e -> mostrarMenuPrincipalPrestatgeria());

        setContentPane(panel);
        revalidate();
    }

    private void mostrarFormularioCargarPrestatgeria() {
        if (perfilActual.getPrestatgerias().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay prestatgerías disponibles.");
            return;
        }

        String[] nombres = prestatgeria.keySet().toArray(new String[0]);

        String seleccion = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione una prestatgeria para cargar:",
                "Cargar Prestatgeria",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nombres,
                nombres[0]
        );

        if (seleccion != null) {
            prestatgeriaActual = prestatgeria.get(seleccion).getPrestatgeria();
            JOptionPane.showMessageDialog(this, "Prestatgeria cargada.");
            crearDistribucionInicial();
            mostrarMenuPrincipalDistribucio();
        }
    }

    private void mostrarFormularioEditarPrestatgeria() {
        if (prestatgeria.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay prestatgerías para editar.");
            return;
        }

        String[] nombres = prestatgeria.keySet().toArray(new String[0]);

        String seleccion = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione una prestatgeria para editar:",
                "Editar Prestatgeria",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nombres,
                nombres[0]
        );

        if (seleccion != null) {
            prestatgeriaActual = prestatgeria.get(seleccion).getPrestatgeria();

            JTextField nuevoNombreField = new JTextField(prestatgeriaActual.getNom());
            JTextField nuevaAlturaField = new JTextField(String.valueOf(prestatgeriaActual.getAltura()));

            Object[] mensaje = {
                    "Nuevo Nombre:", nuevoNombreField,
                    "Nueva Altura:", nuevaAlturaField
            };

            int opcion = JOptionPane.showConfirmDialog(this, mensaje, "Editar Prestatgeria", JOptionPane.OK_CANCEL_OPTION);
            if (opcion == JOptionPane.OK_OPTION) {
                prestatgeria.remove(prestatgeriaActual.getNom());
                prestatgeriaActual.setNom(nuevoNombreField.getText());
                prestatgeriaActual.setAltura(Integer.parseInt(nuevaAlturaField.getText()));
                prestatgeria.put(prestatgeriaActual.getNom(), new ControladorPrestatgeria(prestatgeriaActual));
                JOptionPane.showMessageDialog(this, "Prestatgeria editada con éxito.");
            }
        }
    }

    private void mostrarFormularioBorrarPrestatgeria() {
        if (prestatgeria.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay prestatgerías para borrar.");
            return;
        }

        String[] nombres = prestatgeria.keySet().toArray(new String[0]);

        String seleccion = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione una prestatgeria para borrar:",
                "Borrar Prestatgeria",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nombres,
                nombres[0]
        );

        if (seleccion != null) {
            prestatgeria.remove(seleccion);
            perfilActual.getPrestatgerias().removeIf(p -> p.getNom().equals(seleccion));
            JOptionPane.showMessageDialog(this, "Prestatgeria borrada.");
        }
    }

    private static void crearDistribucionInicial() {
        if (prestatgeriaActual.getDistribucion() == null) {
            //crea ditribucion inicial
            String nombreDistribucion = "Distribucion Predeterminada";
            Distribucio r = new Distribucio(nombreDistribucion);
            prestatgeriaActual.setDistribucion(r);
            controladorDistribucio = new ControladorDistribucio();
            controladorDistribucio.crear_inicial(r, prestatgeriaActual);
        }

        else {
            controladorDistribucio = new ControladorDistribucio();
            controladorDistribucio.crear_inicial(prestatgeriaActual.getDistribucion(), prestatgeriaActual);
        }

    }

    private void mostrarMenuPrincipalDistribucio() {
        setTitle("Gestión de Distribuciones");
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
        JButton atrasBtn = new JButton("Atrás");
        JButton salirBtn = new JButton("Salir");

        agregarBtn.addActionListener(e -> mostrarFormularioAgregarProducto());
        eliminarBtn.addActionListener(e -> mostrarFormularioEliminarProducto());
        modificarBtn.addActionListener(e -> mostrarFormularioModificarProducto());
        modificarSimBtn.addActionListener(e -> mostrarFormularioModificarSimilitudes());
        consultarSimBtn.addActionListener(e -> mostrarFormularioConsultarSimilitudes());
        consultarProdBtn.addActionListener(e -> mostrarFormularioConsultarProductos());
        calcularBtn.addActionListener(e -> mostrarFormularioCalcularDistribucion());
        mostrarDistribucionBtn.addActionListener(e -> mostrarDistribucion());
        guardarBtn.addActionListener(e -> guardarDatos());
        atrasBtn.addActionListener(e -> mostrarMenuPrincipalPrestatgeria());
        salirBtn.addActionListener(e -> {
            guardarDatos();
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
        panel.add(atrasBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
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
            else pos = controladorDistribucio.getProductesColocats().get(controladorDistribucio.getProductesColocats().size() - 1).getPos() + 1;
            controladorDistribucio.afegeix_producte(nombre, marca, precio, cantidad,pos, cantidad);
            JOptionPane.showMessageDialog(this, "Producto añadido con éxito.");
            mostrarMenuPrincipalDistribucio();
        });

        atrasBtn.addActionListener(e -> mostrarMenuPrincipalDistribucio());

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
                    mostrarMenuPrincipalDistribucio();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese valores válidos para el precio y la cantidad.");
                }
            });

            atrasBtn.addActionListener(e -> mostrarMenuPrincipalDistribucio());

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
                mostrarMenuPrincipalDistribucio(); // Regresa al menú principal
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor numérico válido para la similitud.");
            }
        });

        atrasBtn.addActionListener(e -> mostrarMenuPrincipalDistribucio());

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

        atrasBtn.addActionListener(e -> mostrarMenuPrincipalDistribucio());

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

        atrasBtn.addActionListener(e -> mostrarMenuPrincipalDistribucio());

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
        atrasBtn.addActionListener(e -> mostrarMenuPrincipalDistribucio());

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
        atrasBtn.addActionListener(e -> mostrarMenuPrincipalDistribucio());

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

    private JLabel createCenteredLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        return label;
    }


    private static void guardarDatos() {
        try {
            controladorPersistenciaMain.guardarMain(perfiles,"TERCERA_ENTREGA.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void cargarDatos() {
        try {
            perfiles = controladorPersistenciaMain.cargarMain("TERCERA_ENTREGA.dat");
        } catch (IOException | ClassNotFoundException e) {
            perfiles = new HashMap<>();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Presentacion_Main().setVisible(true));
    }
}
