package edu.upc.prop.clusterxx;

import java.io.*;
import java.util.*;

public class DriverPerfil {
    private static Map<String, Perfil> perfiles = new HashMap<>();
    private static Perfil perfilActual;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        cargarDatos();
        mostrarMenuPrincipal();
    }

    private static void mostrarMenuPrincipal() {
        while (true) {
            System.out.println("1. Crear Perfil");
            System.out.println("2. Cargar Perfil");
            System.out.println("3. Borrar Perfil");
            System.out.println("4. Editar Perfil");
            System.out.println("5. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            switch (opcion) {
                case 1 -> mostrarFormularioCrearPerfil();
                case 2 -> mostrarFormularioCargarPerfil();
                case 3 -> mostrarFormularioBorrarPerfil();
                case 4 -> mostrarFormularioEditarPerfil();
                case 5 -> {
                    guardarDatos();
                    System.exit(0);
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private static void mostrarFormularioCrearPerfil() {
        System.out.print("Usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        if (perfiles.containsKey(usuario)) {
            System.out.println("El usuario ya existe, elija otro.");
        } else {
            perfiles.put(usuario, new Perfil(usuario, contraseña));
            System.out.println("Perfil creado con éxito.");
            guardarDatos();
        }
    }

    private static void mostrarFormularioCargarPerfil() {
        if (perfiles.isEmpty()) {
            System.out.println("No hay perfiles guardados.");
            return;
        }
        System.out.print("Usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        if (perfiles.containsKey(usuario) && perfiles.get(usuario).getContrasenya().equals(contraseña)) {
            perfilActual = perfiles.get(usuario);
            System.out.println("Perfil cargado correctamente.");
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }

    private static void mostrarFormularioBorrarPerfil() {
        if (perfiles.isEmpty()) {
            System.out.println("No hay perfiles para borrar.");
            return;
        }

        System.out.println("Seleccione el Perfil a borrar:");
        List<String> usuarios = new ArrayList<>(perfiles.keySet());
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println((i + 1) + ". " + usuarios.get(i));
        }
        System.out.println((usuarios.size() + 1) + ". Atrás");

        int seleccion = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        if (seleccion == usuarios.size() + 1) {
            return;
        }

        if (seleccion < 1 || seleccion > usuarios.size()) {
            System.out.println("Selección no válida.");
            return;
        }

        String usuario = usuarios.get(seleccion - 1);
        perfiles.remove(usuario);
        guardarDatos();
        System.out.println("Perfil eliminado.");
    }

    private static void mostrarFormularioEditarPerfil() {
        if (perfiles.isEmpty()) {
            System.out.println("No hay perfiles disponibles para editar.");
            return;
        }

        System.out.println("Seleccione el Perfil a editar:");
        List<String> usuarios = new ArrayList<>(perfiles.keySet());
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println((i + 1) + ". " + usuarios.get(i));
        }
        System.out.println((usuarios.size() + 1) + ". Atrás");

        int seleccion = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        if (seleccion == usuarios.size() + 1) {
            return;
        }

        if (seleccion < 1 || seleccion > usuarios.size()) {
            System.out.println("Selección no válida.");
            return;
        }

        String usuario = usuarios.get(seleccion - 1);
        System.out.print("Contraseña Actual: ");
        String contraseñaActual = scanner.nextLine();
        System.out.print("Nueva Contraseña: ");
        String nuevaContraseña = scanner.nextLine();

        if (perfiles.get(usuario).getContrasenya().equals(contraseñaActual)) {
            perfiles.get(usuario).setContrasenya(nuevaContraseña);
            guardarDatos();
            System.out.println("Contraseña cambiada.");
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }

    private static void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("perfiles.dat"))) {
            oos.writeObject(perfiles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void cargarDatos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("perfiles.dat"))) {
            perfiles = (Map<String, Perfil>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            perfiles = new HashMap<>();
        }
    }
}
