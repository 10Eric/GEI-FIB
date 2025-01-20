package edu.upc.prop.clusterxx.drivers;

import edu.upc.prop.clusterxx.clases_dominio.Perfil;
import edu.upc.prop.clusterxx.controladores.ControladorPerfil;

import java.io.*;
import java.util.*;

public class DriverPerfil {
    private static Map<String, ControladorPerfil> perfiles = new HashMap<>();
    private static ControladorPerfil perfilActual;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        cargarDatos();
        mostrarMenuPrincipal();
    }

    private static void mostrarMenuPrincipal() {
        while (true) {
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Crear Perfil");
            System.out.println("2. Cargar Perfil");
            System.out.println("3. Borrar Perfil");
            System.out.println("4. Editar Perfil");
            System.out.println("5. Salir");
            System.out.println("Introduzca el numero de la opcion deseada:");
            int opcion = pedirEntero();

            switch (opcion) {
                case 1 -> mostrarFormularioCrearPerfil();
                case 2 -> mostrarFormularioCargarPerfil();
                case 3 -> mostrarFormularioBorrarPerfil();
                case 4 -> mostrarFormularioEditarPerfil();
                case 5 -> {
                    guardarDatos();
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    System.exit(0);
                }
                default -> System.out.println("Opcion no valida.");
            }
        }
    }

    private static void mostrarFormularioCrearPerfil() {
        System.out.print("Usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        if (perfiles.containsKey(usuario)   ) {
            System.out.println("El usuario ya existe, elija otro.");
        } else {
            Perfil perfil = new Perfil(usuario, contraseña);
            ControladorPerfil controladorPerfil = new ControladorPerfil(perfil);
            perfiles.put(usuario, controladorPerfil); // Guardar el ControladorPerfil
            System.out.println("Perfil creado con exito.");
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
        System.out.println((usuarios.size() + 1) + ". Atras");

        int seleccion = pedirEntero();

        if (seleccion == usuarios.size() + 1) {
            return;
        }

        if (seleccion < 1 || seleccion > usuarios.size()) {
            System.out.println("Seleccion no valida.");
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
        System.out.println((usuarios.size() + 1) + ". Atras");

        int seleccion = pedirEntero();

        if (seleccion == usuarios.size() + 1) {
            return;
        }

        if (seleccion < 1 || seleccion > usuarios.size()) {
            System.out.println("Seleccion no válida.");
            return;
        }

        System.out.println(1 + ". Editar Usuario");
        System.out.println(2 + ". Cambiar Contraseña");
        System.out.println(3 + ". Atrás");

        int seleccion2 = pedirEntero();

        if (seleccion2 == 3) {
            return;
        }

        if (seleccion2 < 1 || seleccion > 3) {
            System.out.println("Seleccion no valida.");
            return;
        }

        ControladorPerfil controladorPerfil = perfiles.get(usuarios.get(seleccion - 1));

        if (seleccion2 == 2) {
            System.out.print("Contraseña Actual: ");
            String contraseñaActual = scanner.nextLine();
            System.out.print("Nueva Contraseña: ");
            String nuevaContraseña = scanner.nextLine();

            if (controladorPerfil.getContrasenya().equals(contraseñaActual)) {
                controladorPerfil.setContrasenya(nuevaContraseña);
                guardarDatos();
                System.out.println("Contraseña cambiada.");
            } else {
                System.out.println("Contraseña incorrecta.");
            }
        }

        if (seleccion2 == 1) {
            String usuario = usuarios.get(seleccion - 1);
            System.out.print("Nuevo nombre de usuario: ");
            String nuevoUsuario = scanner.nextLine();

            if (perfiles.containsKey(nuevoUsuario)) {
                System.out.println("El nombre de usuario ya existe, elija otro.");
            }
            else {
                String usuarioAnterior = controladorPerfil.getUsuari();
                controladorPerfil.setUsuari(nuevoUsuario);

                // Actualizar la clave en el Map
                perfiles.remove(usuarioAnterior);
                perfiles.put(nuevoUsuario, controladorPerfil);

                guardarDatos();
                System.out.println("Nombre de usuario cambiado.");
            }
        }
    }
    // Métodos auxiliares para entrada de datos seguros
    private static int pedirEntero() {
        int numero = 0;
        while (true) {
            try {
                numero = scanner.nextInt();
                scanner.nextLine(); // Consumir nueva línea
                break;
            } catch (InputMismatchException e) {
                System.out.println("ERROR - Entrada no valida. Por favor, ingrese un numero entero.");
                scanner.nextLine(); // Consumir entrada no válida
            }
        }
        return numero;
    }

    private static double pedirDouble() {
        double numero = 0.0;
        while (true) {
            try {
                numero = scanner.nextDouble();
                scanner.nextLine(); // Consumir nueva línea
                break;
            } catch (InputMismatchException e) {
                System.out.println("ERROR - Entrada no valida. Por favor, ingrese un numero decimal.");
                scanner.nextLine(); // Consumir entrada no válida
            }
        }
        return numero;
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
            perfiles = (Map<String, ControladorPerfil>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            perfiles = new HashMap<>();
        }
    }
}
