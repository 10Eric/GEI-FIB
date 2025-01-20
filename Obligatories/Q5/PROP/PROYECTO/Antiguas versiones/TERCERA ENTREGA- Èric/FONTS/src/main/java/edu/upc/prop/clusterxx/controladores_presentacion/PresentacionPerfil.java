package edu.upc.prop.clusterxx.controladores_presentacion;

import edu.upc.prop.clusterxx.clases_dominio.Perfil;
import edu.upc.prop.clusterxx.controladores.ControladorPerfil;
import edu.upc.prop.clusterxx.controladores_persistencia.ControladorPersistenciaPerfil;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;

public class PresentacionPerfil extends JFrame {
    private static Map<String, ControladorPerfil> perfiles = new HashMap<>();
    private static ControladorPerfil perfilActual;
    private static ControladorPersistenciaPerfil controladorPersistenciaPerfil = new ControladorPersistenciaPerfil();

    public PresentacionPerfil() {
        setTitle("Gestión de Perfiles");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cargarDatos();
        mostrarMenuPrincipal();
    }

    private void mostrarMenuPrincipal() {
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
                mostrarMenuPrincipal();
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

    private void cargarDatos() {
        try {
            perfiles = controladorPersistenciaPerfil.cargarPerfiles("perfiles.dat");
        } catch (IOException | ClassNotFoundException e) {
            perfiles = new HashMap<>();
        }
    }

    private void guardarDatos() {
        try {
            controladorPersistenciaPerfil.guardarPerfiles(perfiles, "perfiles.dat");
           // JOptionPane.showMessageDialog(this, "Datos guardados correctamente.");
        } catch (IOException e) {
            //JOptionPane.showMessageDialog(this, "Error al guardar perfiles.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PresentacionPerfil().setVisible(true));
    }
}
