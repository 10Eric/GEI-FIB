package edu.upc.prop.clusterxx;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DriverDistribucio {
    private static Perfil perfilActual;
    private static Distribucio distribucioActual;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        perfilActual = new Perfil("usuario", "contrasena");
        cargarDistribuciones();
        mostrarMenuDistribuciones();
    }

    private static void mostrarMenuDistribuciones() {
        while (true) {
            System.out.println("1. Crear Distribución");
            System.out.println("2. Cargar Distribución");
            System.out.println("3. Borrar Distribución");
            System.out.println("4. Editar Distribución");
            System.out.println("5. Guardar Distribución");
            System.out.println("6. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            switch (opcion) {
                case 1 -> mostrarFormularioCrearDistribucion();
                case 2 -> mostrarFormularioCargarDistribucion();
                case 3 -> mostrarFormularioBorrarDistribucion();
                case 4 -> mostrarFormularioEditarDistribucion();
                case 5 -> guardarDistribuciones();
                case 6 -> {
                    guardarDistribuciones();
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
        scanner.nextLine(); // Consumir nueva línea
        if (!nombre.isEmpty()) {
            Prestatgeria prestatge = new Prestatgeria(altura);
            distribucioActual = new Distribucio(nombre, prestatge);
            perfilActual.getDistribucions().add(distribucioActual);
            System.out.println("Distribución creada.");
        }
        guardarDistribuciones();
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
        System.out.println((distribucions.size() + 1) + ". Atrás");

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

        guardarDistribuciones();
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
        System.out.println((distribucions.size() + 1) + ". Atrás");

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
        System.out.println((distribucions.size() + 1) + ". Atrás");

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
        guardarDistribuciones();
        System.out.println("Distribución eliminada.");
    }

    private static void guardarDistribuciones() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("distribuciones.dat"))) {
            oos.writeObject(perfilActual.getDistribucions());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void cargarDistribuciones() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("distribuciones.dat"))) {
            perfilActual.setDistribucions((List<Distribucio>) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            perfilActual.setDistribucions(new ArrayList<>());
        }
    }


}
