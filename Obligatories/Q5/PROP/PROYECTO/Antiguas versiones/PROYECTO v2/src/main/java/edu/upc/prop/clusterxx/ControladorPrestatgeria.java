package edu.upc.prop.clusterxx;
import java.util.Scanner;
import java.io.Serializable;

public class ControladorPrestatgeria implements Serializable {
    private Prestatgeria prestatgeria;
    private Scanner scanner = new Scanner(System.in);

    // Constructor
    public ControladorPrestatgeria(Prestatgeria prestatgeria) {
        this.prestatgeria = prestatgeria;
    }

    // Método para agregar un producto colocado a la prestatgeria
    public void afegirProducteColocat() {
        System.out.print("Posición: ");
        int pos = scanner.nextInt();
        System.out.print("Altura: ");
        int altura = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        System.out.print("Nombre del Producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        Producte producte = new Producte(nombre, marca, precio, cantidad);
        ProducteColocat producteColocat = new ProducteColocat(pos, altura, producte);

        if (prestatgeria.afegirProducteColocat(producteColocat)) {
            System.out.println("Producto colocado correctamente.");
        } else {
            System.out.println("Error al colocar el producto. Altura inválida.");
        }
    }

    // Método para obtener un producto por su posición
    public void obtenirProductePos() {
        System.out.print("Posición: ");
        int pos = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        Producte producte = prestatgeria.obtenirProductePos(pos);
        if (producte != null) {
            System.out.println("Producto en posición " + pos + ": " + producte);
        } else {
            System.out.println("No se encontró producto en la posición " + pos);
        }
    }

    // Método para listar los productos colocados
    public void llistarProductesColocats() {
        for (ProducteColocat producteColocat : prestatgeria.getProductesColocats()) {
            System.out.println("Posición: " + producteColocat.getPos() + ", Producto: " + producteColocat.getProducte().getNom());
        }
    }
}
