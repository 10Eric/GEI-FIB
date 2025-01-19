package edu.upc.prop.clusterxx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.Serializable;
import java.util.Scanner;

public class Prestatgeria implements Serializable {
    private int numeroDePrestages;
    private int altura;
    private ArrayList<ProducteColocat> productesColocats;  // Relación uno a muchos con ProducteColocat
    private boolean cambiosManualesRealizados;

    // Constructor
    public Prestatgeria(int numeroDePrestages, int altura) {
        this.numeroDePrestages = numeroDePrestages;
        this.altura = altura;
        this.productesColocats = new ArrayList<>();
        this.cambiosManualesRealizados = false;
    }

    // Método para obtener un producto por su posición
    public Producte obtenirProductePos(int pos) {
        for (ProducteColocat producteColocat : productesColocats) {
            if (producteColocat.getPos() == pos) {
                return producteColocat.getProducte();
            }
        }
        return null; // Retorna null si no se encuentra un producto en esa posición
    }

    // Método para agregar un producto colocado, con validación de altura
    public boolean afegirProducteColocat(ProducteColocat producteColocat) {
        if (producteColocat.esValidaAltura(this.altura)) {
            productesColocats.add(producteColocat);
            return true;
        }
        return false;  // No se agrega si la altura es inválida
    }

    public void imprimirDistribucion() {
        if (productesColocats.isEmpty()) {
            System.out.println("No hay productos en la prestatgeria.");
            return;
        }

        int medida = (productesColocats.size() % altura == 0) ? productesColocats.size() / altura : productesColocats.size() / altura + 1;

        String[][] matrix = new String[altura][medida];
        for (ProducteColocat producteColocat : productesColocats) {
            int row = (producteColocat.getPos() - 1) / medida;
            int col = (producteColocat.getPos() - 1) % medida;
            matrix[row][col] = producteColocat.getProducte().getNom();
        }

        int[] maxColWidths = new int[medida];
        for (int j = 0; j < medida; j++) {
            for (int i = 0; i < altura; i++) {
                if (matrix[i][j] != null) {
                    maxColWidths[j] = Math.max(maxColWidths[j], matrix[i][j].length());
                } else {
                    maxColWidths[j] = Math.max(maxColWidths[j], "Vacio".length());
                }
            }
        }

        for (int i = 0; i < altura; i++) {
            StringBuilder output = new StringBuilder();
            for (int j = 0; j < medida; j++) {
                if (matrix[i][j] != null) {
                    output.append(String.format("%-" + maxColWidths[j] + "s", matrix[i][j]));
                } else {
                    output.append(String.format("%-" + maxColWidths[j] + "s", "Vacio"));
                }
                output.append(" ");
            }
            System.out.println(output);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("¿Desea editar la distribución? (s/n): ");
        String respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("s")) {
            System.out.println("Seleccione el primer producto a intercambiar:");
            for (int i = 0; i < productesColocats.size(); i++) {
                System.out.println((i + 1) + ". " + productesColocats.get(i).getProducte().getNom());
            }
            int seleccion1 = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            System.out.println("Seleccione el segundo producto a intercambiar:");
            for (int i = 0; i < productesColocats.size(); i++) {
                System.out.println((i + 1) + ". " + productesColocats.get(i).getProducte().getNom());
            }
            int seleccion2 = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            // Swap the positions of the two products
            if (seleccion1 > 0 && seleccion1 <= productesColocats.size() && seleccion2 > 0 && seleccion2 <= productesColocats.size()) {
                ProducteColocat p1 = productesColocats.get(seleccion1 - 1);
                ProducteColocat p2 = productesColocats.get(seleccion2 - 1);

                int tempPos = p1.getPos();
                int tempAltura = p1.getAltura();
                p1.setManualmenteModificado(true);
                p2.setManualmenteModificado(true);
                p1.setPos(p2.getPos());
                p1.setAltura(p2.getAltura());
                p2.setPos(tempPos);
                p2.setAltura(tempAltura);
                cambiosManualesRealizados = true;
                System.out.println("Productos intercambiados.");
            } else {
                System.out.println("Selección no válida.");
            }

            // Show the updated distribution
            imprimirDistribucion();
        }
    }

    // Método para ver la distribución de productos
    public void verDistribucionProductos() {
        if (productesColocats.isEmpty()) {
            System.out.println("No hay productos en la prestatgeria.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        boolean mantenerCambios = false;

        if (cambiosManualesRealizados) {
            System.out.print("¿Desea mantener los cambios manuales? (s/n): ");
            String respuesta = scanner.nextLine();
            mantenerCambios = respuesta.equalsIgnoreCase("s");
            if (!mantenerCambios) {
                cambiosManualesRealizados = false;
                for (ProducteColocat producteColocat : productesColocats) {
                    producteColocat.setManualmenteModificado(false);
                }
            }
        }

        List<ProducteColocat> arrangedProductes = arrangeProductsBySimilarity(productesColocats, mantenerCambios);
        productesColocats.clear();
        productesColocats.addAll(arrangedProductes);
        int medida;
        if (productesColocats.size()% altura == 0) {
            medida = productesColocats.size()/altura;
        } else {
            medida = productesColocats.size()/altura + 1;
        }

        String[][] matrix = new String[altura][medida];
        for (ProducteColocat producteColocat : productesColocats) {
            int row = (producteColocat.getPos() - 1) / medida;
            int col = (producteColocat.getPos() - 1) % medida;
            matrix[row][col] = producteColocat.getProducte().getNom();
        }

       int[] maxColWidths = new int[medida];
        for (int j = 0; j < medida; j++) {
            for (int i = 0; i < altura; i++) {
                if (matrix[i][j] != null) {
                    maxColWidths[j] = Math.max(maxColWidths[j], matrix[i][j].length());
                }
                else {
                    maxColWidths[j] = Math.max(maxColWidths[j], "Vacio".length());
                }
            }
        }

        for (int i = 0; i < altura; i++) {
            StringBuilder output = new StringBuilder();
            for (int j = 0; j < medida; j++) {
                if (matrix[i][j] != null) {
                    output.append(String.format("%-" + maxColWidths[j] + "s", matrix[i][j]));
                } else {
                    output.append(String.format("%-" + maxColWidths[j] + "s", "Vacio"));
                }
                output.append(" ");
            }
            System.out.println(output);
        }
    }

    private List<ProducteColocat> arrangeProductsBySimilarity(List<ProducteColocat> productes, boolean mantenerCambios) {
        int medida;
        if (productesColocats.size()% altura == 0) {
            medida = productesColocats.size()/altura;
        } else {
            medida = productesColocats.size()/altura + 1;
        }
        ProducteColocat[] arranged = new ProducteColocat[productes.size()];
        int[] posicionesFijas = new int[productes.size()];

        // Si hay cambios manuales, ubicar esos productos en su posición fija
        if (mantenerCambios) {
            for (ProducteColocat producte : productes) {
                if (producte.isManualmenteModificado()) {
                    int pos = producte.getPos() - 1;
                    arranged[pos] = producte;
                    posicionesFijas[pos] = 1;  // Marcar posición como fija
                }
                else {
                    arranged[producte.getPos()-1] = null;
                }
            }
        }

        // Generar una lista de productos no modificados
        ProducteColocat[] noModificados = new ProducteColocat[productes.size()];
        int idxNoModificados = 0;
        for (ProducteColocat producte : productes) {
            if (!producte.isManualmenteModificado()) {
                noModificados[idxNoModificados++] = producte;
            }
        }

        // Generar permutaciones de productos no modificados manualmente
        List<ProducteColocat[]> permutaciones = new ArrayList<>();
        generarPermutaciones(noModificados, 0, idxNoModificados, permutaciones);

        int maxSimilitud = -1;
        ProducteColocat[] mejorDisposicion = new ProducteColocat[arranged.length];

        for (ProducteColocat[] disposicion : permutaciones) {
            int pos = 0;
            ProducteColocat[] tempArranged = arranged.clone();

            // Colocar productos no fijos en posiciones vacías de `tempArranged`
            for (int i = 0; i < tempArranged.length; i++) {
                if (posicionesFijas[i] == 0) {
                    tempArranged[i] = disposicion[pos++];
                }
            }

            int similitudTotal = 0;
            for (int i = 0; i < tempArranged.length; i++) {
                Producte p1 = tempArranged[i].getProducte();
                Producte pLeft = tempArranged[(i - 1 + tempArranged.length) % tempArranged.length].getProducte();
                Producte pRight = tempArranged[(i + 1) % tempArranged.length].getProducte();
                similitudTotal += calculateSimilarity(p1,pLeft) + calculateSimilarity(p1,pRight);
            }

            // Si la disposición actual tiene mayor similitud, se guarda como la mejor
            if (similitudTotal > maxSimilitud) {
                maxSimilitud = similitudTotal;
                System.arraycopy(tempArranged, 0, mejorDisposicion, 0, arranged.length);
            }
        }

        // Ajustar posiciones y alturas en la disposición final
        for (int i = 0; i < mejorDisposicion.length; i++) {
            if (mejorDisposicion[i] != null) {
                mejorDisposicion[i].setPos(i + 1);
                mejorDisposicion[i].setAltura((i / medida) + 1);
            }
        }

        // Convertir a una lista final compacta
        return Arrays.asList(mejorDisposicion);
    }

    private void generarPermutaciones(ProducteColocat[] productos, int index, int size, List<ProducteColocat[]> permutaciones) {
        if (index == size - 1) {
            permutaciones.add(productos.clone());
            return;
        }

        for (int i = index; i < size; i++) {
            ProducteColocat temp = productos[i];
            productos[i] = productos[index];
            productos[index] = temp;

            generarPermutaciones(productos, index + 1, size, permutaciones);

            // Revertir intercambio
            temp = productos[i];
            productos[i] = productos[index];
            productos[index] = temp;
        }
    }

    private int calculateSimilarity(Producte p1, Producte p2) {
        if (p1 == null || p2 == null) return 0;
        return p1.getSimilitud(p2);
    }

    // Getters y setters
    public int getNumeroDePrestages() {
        return numeroDePrestages;
    }

    public void setNumeroDePrestages(int numeroDePrestages) {
        this.numeroDePrestages = numeroDePrestages;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public ArrayList<ProducteColocat> getProductesColocats() {
        return productesColocats;
    }

    public void setProductesColocats(ArrayList<ProducteColocat> productesColocats) {
        this.productesColocats = productesColocats;
    }

    public boolean isCambiosManualesRealizados() {
        return cambiosManualesRealizados;
    }

    public void setCambiosManualesRealizados(boolean cambiosManualesRealizados) {
        this.cambiosManualesRealizados = cambiosManualesRealizados;
    }
}
