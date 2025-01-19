package edu.upc.prop.clusterxx.drivers;

import edu.upc.prop.clusterxx.clases_dominio.Perfil;
import edu.upc.prop.clusterxx.clases_dominio.Prestatgeria;
import edu.upc.prop.clusterxx.controladores.ControladorPrestatgeria;

import java.io.*;
import java.util.*;

public class DriverPrestatgeria {
    private static Perfil perfilActual;
    private static Map<String, ControladorPrestatgeria> prestatgeria = new HashMap<>();
    private static Prestatgeria prestatgeriaActual;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        perfilActual = new Perfil("usuario", "contrasena");
        cargarPrestatgerias();
        mostrarMenuPrestatgerias();
    }

    private static void mostrarMenuPrestatgerias() {
        while (true) {
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Crear Estanteria");
            System.out.println("2. Cargar Estanteria");
            System.out.println("3. Borrar Estanteria");
            System.out.println("4. Editar Estanteria");
            System.out.println("5. Guardar Estanteria");
            System.out.println("6. Salir");
            System.out.println("Introduzca el numero de la opcion deseada:");
            int opcion = pedirEntero();

            switch (opcion) {
                case 1 -> mostrarFormularioCrearPrestatgeria();
                case 2 -> mostrarFormularioCargarPrestatgeria();
                case 3 -> mostrarFormularioBorrarPrestatgeria();
                case 4 -> mostrarFormularioEditarPrestatgeria();
                case 5 -> guardarPrestatgerias();
                case 6 -> {
                    guardarPrestatgerias();
                    System.out.println("Saliendo del programa. Hasta luego!");
                    System.exit(0);
                }
                default -> System.out.println("Opcion no valida.");
            }
        }
    }

    private static void mostrarFormularioCrearPrestatgeria() {
        System.out.print("Nombre de la Estanteria: ");
        String nombre = scanner.nextLine();
        System.out.print("Altura del Estante: ");
        int altura = pedirEntero();
        if (prestatgeria.containsKey(nombre)   ) {
            System.out.println("La Estanteria ya existe, elija otro.");
        }
        if (!nombre.isEmpty()) {
            prestatgeriaActual = new Prestatgeria(nombre, altura);
            perfilActual.getPrestatgeria().add(prestatgeriaActual);
            System.out.println("Estanteria creada.");
        }
        guardarPrestatgerias();
    }

    private static void mostrarFormularioEditarPrestatgeria() {
        if (perfilActual.getPrestatgeria().isEmpty()) {
            System.out.println("No hay Estanteria para editar.");
            return;
        }
        List<Prestatgeria> prestatgerias = perfilActual.getPrestatgeria();
        System.out.println("Seleccione la Estanteria a editar:");
        for (int i = 0; i < prestatgerias.size(); i++) {
            System.out.println((i + 1) + ". " + prestatgerias.get(i).getNom());
        }
        System.out.println((prestatgerias.size() + 1) + ". Atras");

        int seleccion = pedirEntero();

        if (seleccion == prestatgerias.size() + 1) {
            return;
        }

        if (seleccion < 1 || seleccion > prestatgerias.size()) {
            System.out.println("Seleccion no valida.");
            return;
        }

        prestatgeriaActual = prestatgerias.get(seleccion - 1);
        System.out.println("Valores actuales:");
        System.out.println("Nombre: " + prestatgeriaActual.getNom());
        System.out.println("Altura: " + prestatgeriaActual.getAltura());

        // Preguntar si se quiere cambiar el nombre
        System.out.print("Desea cambiar el nombre de la Estanteria? (S/N): ");
        String cambiarNombre = scanner.nextLine();
        if (cambiarNombre.equalsIgnoreCase("s")) {
            System.out.print("Nuevo nombre de la Estanteria: ");
            String nuevoNombre = scanner.nextLine();
            prestatgeriaActual.setNom(nuevoNombre);
            System.out.println("Nombre cambiado.");
        }

        // Preguntar si se quiere cambiar la altura
        System.out.print("Desea cambiar la altura del Estante? (S/N): ");
        String cambiarAltura = scanner.nextLine();
        if (cambiarAltura.equalsIgnoreCase("s")) {
            System.out.print("Nueva altura del Estante: ");
            int nuevaAltura = pedirEntero();
            prestatgeriaActual.setAltura(nuevaAltura);
            System.out.println("Altura editada.");
        }
        guardarPrestatgerias();
    }

    private static void mostrarFormularioCargarPrestatgeria() {
        if (perfilActual.getPrestatgeria().isEmpty()) {
            System.out.println("No hay Estanteria guardadas.");
            return;
        }
        List<Prestatgeria> prestatgerias = perfilActual.getPrestatgeria();
        System.out.println("Seleccione la Estanteria a cargar:");
        for (int i = 0; i < prestatgerias.size(); i++) {
            System.out.println((i + 1) + ". " + prestatgerias.get(i).getNom());
        }
        System.out.println((prestatgerias.size() + 1) + ". Atras");

        int seleccion = pedirEntero();

        if (seleccion == prestatgerias.size() + 1) {
            return;
        }

        if (seleccion < 1 || seleccion > prestatgerias.size()) {
            System.out.println("Seleccion no valida.");
            return;
        }

        prestatgeriaActual = prestatgerias.get(seleccion - 1);
        System.out.println("Estanteria cargada.");
    }

    private static void mostrarFormularioBorrarPrestatgeria() {
        if (perfilActual.getPrestatgeria().isEmpty()) {
            System.out.println("No hay Estanteria para borrar.");
            return;
        }
        List<Prestatgeria> prestatgerias = perfilActual.getPrestatgeria();
        System.out.println("Seleccione la Estanteria a borrar:");
        for (int i = 0; i < prestatgerias.size(); i++) {
            System.out.println((i + 1) + ". " + prestatgerias.get(i).getNom());
        }
        System.out.println((prestatgerias.size() + 1) + ". Atras");

        int seleccion = pedirEntero();
        if (seleccion == prestatgerias.size() + 1) {
            return;
        }

        if (seleccion < 1 || seleccion > prestatgerias.size()) {
            System.out.println("Seleccion no valida.");
            return;
        }

        Prestatgeria distribucio = prestatgerias.get(seleccion - 1);
        perfilActual.getPrestatgeria().remove(distribucio);
        guardarPrestatgerias();
        System.out.println("Estanteria eliminada.");
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

    private static void guardarPrestatgerias() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("prestatgerias.dat"))) {
            oos.writeObject(perfilActual.getPrestatgeria());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void cargarPrestatgerias() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("prestatgerias.dat"))) {
            perfilActual.setPrestatgeria((List<Prestatgeria>) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            perfilActual.setPrestatgeria(new ArrayList<>());
        }
    }


}
