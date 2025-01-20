package edu.upc.prop.clusterxx;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Crear algunos productos de ejemplo
        Producte p1 = new Producte("Marca1", "Producto A", 10.0f, "5", "001");
        Producte p2 = new Producte("Marca2", "Producto B", 15.0f, "3", "002");

        // Crear distribución
        Distribucio distribucio = new Distribucio("Distribuidor 1");
        ControladorDistribucio ctrlDistribucio = new ControladorDistribucio(distribucio);

        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 4) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Agregar producto");
            System.out.println("2. Eliminar producto");
            System.out.println("3. Listar productos");
            System.out.println("4. Salir");
            opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la marca del producto: ");
                    String marca = scanner.nextLine();
                    System.out.print("Ingrese el nombre del producto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese el precio del producto: ");
                    float precio = scanner.nextFloat();
                    System.out.print("Ingrese la cantidad del producto: ");
                    String cantidad = scanner.next();
                    System.out.print("Ingrese el ID del producto: ");
                    String id = scanner.next();

                    Producte nuevoProducto = new Producte(marca, nombre, precio, cantidad, id);
                    ctrlDistribucio.agregarProducte(nuevoProducto);
                    System.out.println("Producto agregado correctamente.");
                    break;

                case 2:
                    System.out.print("Ingrese el ID del producto a eliminar: ");
                    String idEliminar = scanner.next();
                    ctrlDistribucio.eliminarProducte(idEliminar);
                    break;

                case 3:
                    System.out.println("Lista de productos:");
                    ctrlDistribucio.llistarProductes();
                    break;

                case 4:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }

        scanner.close();
    }
}
