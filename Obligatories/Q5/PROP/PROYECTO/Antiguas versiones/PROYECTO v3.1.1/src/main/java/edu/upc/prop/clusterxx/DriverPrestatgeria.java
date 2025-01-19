package edu.upc.prop.clusterxx;

import java.util.List;
import java.util.Scanner;

public class DriverPrestatgeria {
    private static Distribucio distribucioActual;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        crearDistribucionInicial();
        mostrarOpcionesDistribucion();
    }

    private static void crearDistribucionInicial() {
        String nombreDistribucion = "Distribucion Predeterminada";
        System.out.print("Ingrese la altura del Prestatge: ");
        int altura = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        Prestatgeria prestatgeria = new Prestatgeria(altura);
        distribucioActual = new Distribucio(nombreDistribucion, prestatgeria);
    }

    private static void mostrarOpcionesDistribucion() {
        while (true) {
            System.out.println("1. Añadir Producto");
            System.out.println("2. Eliminar Producto");
            System.out.println("3. Modificar Producto");
            System.out.println("4. Consultar Similitudes");
            System.out.println("5. Calcular Distribución");
            System.out.println("6. Ver Distribución");
            System.out.println("7. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            switch (opcion) {
                case 1 -> agregarProducte();
                case 2 -> eliminarProducte();
                case 3 -> modificarProducte();
                case 4 -> consultarSimilituds();
                case 5 -> distribucioActual.getPrestatge().verDistribucionProductos();
                case 6 -> distribucioActual.getPrestatge().imprimirDistribucion();
                case 7 -> {
                    // guardarDatos(); // Implement this method if needed
                    System.exit(0);
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }



    private static void agregarProducte() {
        System.out.print("Nombre del Producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        Producte nuevoProducto = new Producte(nombre, marca, precio, cantidad);
        distribucioActual.agregaProducte(nuevoProducto);
        Prestatgeria prestatgeria = distribucioActual.getPrestatge();
        int pos = prestatgeria.getProductesColocats().size() + 1;
        int altura = Math.min(cantidad, prestatgeria.getAltura());
        ProducteColocat producteColocat = new ProducteColocat(pos, altura, nuevoProducto);
        prestatgeria.afegirProducteColocat(producteColocat);

        while (true) {
            System.out.print("¿Desea establecer similitud con otro producto? (s/n): ");
            String respuesta = scanner.nextLine();
            if (!respuesta.equalsIgnoreCase("s")) {
                break;
            }

            System.out.println("Seleccione el producto con el que desea establecer similitud:");
            List<Producte> productes = distribucioActual.getProductes();
            for (int i = 0; i < productes.size(); i++) {
                System.out.println((i + 1) + ". " + productes.get(i).getNom());
            }
            int seleccion = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            if (seleccion < 1 || seleccion > productes.size()) {
                System.out.println("Selección no válida.");
                continue;
            }

            Producte productoExistente = productes.get(seleccion - 1);
            if (productoExistente.equals(nuevoProducto)) {
                System.out.println("No puede seleccionar el mismo producto.");
                continue;
            }

            System.out.print("Valor de similitud (0-100): ");
            int valorSimilitud = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            if (valorSimilitud < 0 || valorSimilitud > 100) {
                System.out.println("Error: La similitud debe estar entre 0 y 100.");
                continue;
            }

            nuevoProducto.setSimilitud(productoExistente, valorSimilitud);
            productoExistente.setSimilitud(nuevoProducto, valorSimilitud);
        }

        System.out.println("Producto añadido.");
    }



    private static void eliminarProducte() {
        if (distribucioActual.getProductes().isEmpty()) {
            System.out.println("No hay productos en la distribución.");
            return;
        }
        List<Producte> productes = distribucioActual.getProductes();
        System.out.println("Seleccione el producto a eliminar:");
        for (int i = 0; i < productes.size(); i++) {
            System.out.println((i + 1) + ". " + productes.get(i).getNom());
        }
        System.out.println((productes.size() + 1) + ". Salir");

        int seleccion = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        if (seleccion == productes.size() + 1) {
            return;
        }

        if (seleccion < 1 || seleccion > productes.size()) {
            System.out.println("Selección no válida.");
            return;
        }

        Producte producte = productes.get(seleccion - 1);
        distribucioActual.eliminaProducte(producte);
        Prestatgeria prestatgeria = distribucioActual.getPrestatge();
        prestatgeria.getProductesColocats().removeIf(pc -> pc.getProducte().equals(producte));
        System.out.println("Producto eliminado.");
    }

    private static void modificarProducte() {
        List<Producte> productes = distribucioActual.getProductes();
        if (productes.isEmpty()) {
            System.out.println("No hay productos en la distribución.");
            return;
        }

        System.out.println("Seleccione el Producto a modificar:");
        for (int i = 0; i < productes.size(); i++) {
            System.out.println((i + 1) + ". " + productes.get(i).getNom());
        }

        int seleccion = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        if (seleccion < 1 || seleccion > productes.size()) {
            System.out.println("Selección no válida.");
            return;
        }

        Producte producteSeleccionado = productes.get(seleccion - 1);
        System.out.println("Valores actuales del producto:");
        System.out.println("Nombre: " + producteSeleccionado.getNom());
        System.out.println("Precio: " + producteSeleccionado.getPreu());
        System.out.println("Stock: " + producteSeleccionado.getQuantitat());

        System.out.print("¿Desea modificar este producto? (s/n): ");
        String respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("s")) {
            System.out.print("Nuevo Precio: ");
            double nuevoPrecio = scanner.nextDouble();
            System.out.print("Nueva Cantidad: ");
            int nuevaCantidad = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            producteSeleccionado.setPreu(nuevoPrecio);
            producteSeleccionado.setQuantitat(nuevaCantidad);
            System.out.println("Producto modificado.");
        }
    }

    private static void consultarSimilituds() {
        if (distribucioActual.getProductes().isEmpty()) {
            System.out.println("No hay productos para comparar.");
            return;
        }
        List<Producte> productes = distribucioActual.getProductes();
        System.out.println("Seleccione el primer producto:");
        for (int i = 0; i < productes.size(); i++) {
            System.out.println((i + 1) + ". " + productes.get(i).getNom());
        }
        System.out.println((productes.size() + 1) + ". Salir");

        int seleccion1 = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        if (seleccion1 == productes.size() + 1) {
            return;
        }

        if (seleccion1 < 1 || seleccion1 > productes.size()) {
            System.out.println("Selección no válida.");
            return;
        }

        Producte producte1 = productes.get(seleccion1 - 1);
        for (Producte producte : productes) {
            if (producte != producte1) {
                int similitudes = producte1.getSimilitud(producte);
                System.out.println(producte1.getNom() + " con " + producte.getNom() + " - " + similitudes);
            }
        }

        System.out.print("¿Desea cambiar la similitud? (s/n): ");
        String respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("s")) {
            System.out.println("Seleccione el segundo producto:");
            for (int i = 0; i < productes.size(); i++) {
                System.out.println((i + 1) + ". " + productes.get(i).getNom());
            }
            System.out.println((productes.size() + 1) + ". Salir");

            int seleccion2 = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            if (seleccion2 == productes.size() + 1) {
                return;
            }

            if (seleccion2 < 1 || seleccion2 > productes.size()) {
                System.out.println("Selección no válida.");
                return;
            }

            Producte producte2 = productes.get(seleccion2 - 1);

            if (producte1.equals(producte2)) {
                System.out.println("Por favor, seleccione productos diferentes.");
                return;
            }

            int similitud = producte1.getSimilitud(producte2);
            System.out.println("La similitud entre " + producte1.getNom() + " y " + producte2.getNom() + " es: " + similitud);
            System.out.print("Nueva similitud (0-100): ");
            int nuevaSimilitud = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            if (nuevaSimilitud < 0 || nuevaSimilitud > 100) {
                System.out.println("Error: La similitud debe estar entre 0 y 100.");
            } else {
                producte1.setSimilitud(producte2, nuevaSimilitud);
                System.out.println("Similitud actualizada.");
            }
        }
    }
}
