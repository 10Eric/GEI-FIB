package edu.upc.prop.clusterxx;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static Map<String, Perfil> perfiles = new HashMap<>();
    private static Perfil perfilActual;
    private static Distribucio distribucioActual;
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
            mostrarMenuDistribuciones();
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
        System.out.println((usuarios.size() + 1) + ". Salir");

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
        System.out.println((usuarios.size() + 1) + ". Salir");

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

    private static void mostrarMenuDistribuciones() {
        while (true) {
            System.out.println("1. Crear Distribución");
            System.out.println("2. Cargar Distribución");
            System.out.println("3. Borrar Distribución");
            System.out.println("4. Editar Distribución");
            System.out.println("5. Guardar Distribución");
            System.out.println("6. Atrás");
            System.out.println("7. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            switch (opcion) {
                case 1 -> mostrarFormularioCrearDistribucion();
                case 2 -> mostrarFormularioCargarDistribucion();
                case 3 -> mostrarFormularioBorrarDistribucion();
                case 4 -> mostrarFormularioEditarDistribucion();
                case 5 -> {
                    guardarDatos();
                    System.out.println("Distribución guardada");
                }
                case 6 -> {
                    return;
                }
                case 7 -> {
                    guardarDatos();
                    System.exit(0);
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private static void mostrarFormularioCrearDistribucion() {
        System.out.print("Nombre de la Distribución: ");
        String nombre = scanner.nextLine();
        System.out.print("Altura del Prestatge: ");
        int altura = scanner.nextInt();
        System.out.print("Número de Prestages: ");
        int numeroDePrestages = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea
        if (!nombre.isEmpty()) {
            Prestatgeria prestatge = new Prestatgeria(numeroDePrestages, altura);
            Distribucio nuevaDistribucion = new Distribucio(nombre, prestatge);
            perfilActual.añadirDistribucion(nuevaDistribucion);
            distribucioActual = nuevaDistribucion;
            System.out.println("Distribución creada.");
        }
    }

    private static void mostrarFormularioEditarDistribucion() {
        if (perfilActual.getDistribucions().isEmpty()) {
            System.out.println("No hay distribuciones para editar.");
            return;
        }
        List<Distribucio> distribucions = perfilActual.getDistribucions();
        System.out.println("Seleccione la Distribución a editar:");
        for (int i = 0; i < distribucions.size(); i++) {
            System.out.println((i + 1) + ". " + distribucions.get(i).getNom());
        }
        System.out.println((distribucions.size() + 1) + ". Salir");

        int seleccion = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        if (seleccion == distribucions.size() + 1) {
            return;
        }

        if (seleccion < 1 || seleccion > distribucions.size()) {
            System.out.println("Selección no válida.");
            return;
        }

        distribucioActual = distribucions.get(seleccion - 1);
        System.out.println("Valores actuales:");
        System.out.println("Nombre: " + distribucioActual.getNom());
        System.out.println("Altura: " + distribucioActual.getPrestatge().getAltura());
        System.out.println("Número de Prestages: " + distribucioActual.getPrestatge().getNumeroDePrestages());

        // Preguntar si se quiere cambiar el nombre
        System.out.print("¿Desea cambiar el nombre de la Distribución? (s/n): ");
        String cambiarNombre = scanner.nextLine();
        if (cambiarNombre.equalsIgnoreCase("s")) {
            System.out.print("Nuevo nombre de la Distribución: ");
            String nuevoNombre = scanner.nextLine();
            distribucioActual.setNom(nuevoNombre);
        }

        // Preguntar si se quiere cambiar la altura
        System.out.print("¿Desea cambiar la altura del Prestatge? (s/n): ");
        String cambiarAltura = scanner.nextLine();
        if (cambiarAltura.equalsIgnoreCase("s")) {
            System.out.print("Nueva altura del Prestatge: ");
            int nuevaAltura = scanner.nextInt();
            distribucioActual.getPrestatge().setAltura(nuevaAltura);
            scanner.nextLine(); // Consumir nueva línea
        }

        // Preguntar si se quiere cambiar el número de prestatges
        System.out.print("¿Desea cambiar el número de Prestages? (s/n): ");
        String cambiarNumeroDePrestages = scanner.nextLine();
        if (cambiarNumeroDePrestages.equalsIgnoreCase("s")) {
            System.out.print("Nuevo número de Prestages: ");
            int nuevoNumeroDePrestages = scanner.nextInt();
            distribucioActual.getPrestatge().setNumeroDePrestages(nuevoNumeroDePrestages);
            scanner.nextLine(); // Consumir nueva línea
        }

        System.out.println("Distribución editada.");
    }

    private static void mostrarFormularioCargarDistribucion() {
        if (perfilActual.getDistribucions().isEmpty()) {
            System.out.println("No hay distribuciones guardadas.");
            return;
        }
        List<Distribucio> distribucions = perfilActual.getDistribucions();
        System.out.println("Seleccione la Distribución a cargar:");
        for (int i = 0; i < distribucions.size(); i++) {
            System.out.println((i + 1) + ". " + distribucions.get(i).getNom());
        }
        System.out.println((distribucions.size() + 1) + ". Salir");

        int seleccion = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        if (seleccion == distribucions.size() + 1) {
            return;
        }

        if (seleccion < 1 || seleccion > distribucions.size()) {
            System.out.println("Selección no válida.");
            return;
        }

        distribucioActual = distribucions.get(seleccion - 1);
        System.out.println("Distribución cargada.");
        mostrarOpcionesDistribucion();
    }

    private static void mostrarFormularioBorrarDistribucion() {
        if (perfilActual.getDistribucions().isEmpty()) {
            System.out.println("No hay distribuciones para borrar.");
            return;
        }
        List<Distribucio> distribucions = perfilActual.getDistribucions();
        System.out.println("Seleccione la Distribución a borrar:");
        for (int i = 0; i < distribucions.size(); i++) {
            System.out.println((i + 1) + ". " + distribucions.get(i).getNom());
        }
        System.out.println((distribucions.size() + 1) + ". Salir");

        int seleccion = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        if (seleccion == distribucions.size() + 1) {
            return;
        }

        if (seleccion < 1 || seleccion > distribucions.size()) {
            System.out.println("Selección no válida.");
            return;
        }

        Distribucio distribucio = distribucions.get(seleccion - 1);
        perfilActual.getDistribucions().remove(distribucio);
        guardarDatos();
        System.out.println("Distribución eliminada.");
    }

    private static void mostrarOpcionesDistribucion() {
        ControladorDistribucio ctrlDistribucio = new ControladorDistribucio(distribucioActual);
        while (true) {
            System.out.println("1. Añadir Producto");
            System.out.println("2. Eliminar Producto");
            System.out.println("3. Modificar Producto");
            System.out.println("4. Consultar Similitudes");
            System.out.println("5. Calcular Distribución");
            System.out.println("6. Ver Distribución");
            System.out.println("7. Atrás");
            System.out.println("8. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            switch (opcion) {
                case 1 -> ctrlDistribucio.agregarProducte();
                case 2 -> ctrlDistribucio.eliminarProducte();
                case 3 -> ctrlDistribucio.modificarProducte();
                case 4 -> ctrlDistribucio.consultarSimilituds();
                case 5 -> distribucioActual.getPrestatge().verDistribucionProductos();
                case 6 -> distribucioActual.getPrestatge().imprimirDistribucion();
                case 7 -> {
                    return;
                }
                case 8 -> {
                    guardarDatos();
                    System.exit(0);
                }
                default -> System.out.println("Opción no válida.");
            }
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
