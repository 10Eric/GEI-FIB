package edu.upc.prop.clusterxx.drivers;

import edu.upc.prop.clusterxx.clases_dominio.ProducteColocat;
import edu.upc.prop.clusterxx.controladores.ControladorDistribucio;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DriverDistibucio {
    private static ControladorDistribucio controladorDistribucio;
    //private static int altura;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        crearDistribucionInicial();
        mostrarOpcionesDistribucio();
    }

    private static void crearDistribucionInicial() {
        //crea ditribucion inicial
        String nombreDistribucion = "Distribucion Predeterminada";
        System.out.println("Introduzca la altura del Prestatge Predeterminado: ");
        int altura = pedirEntero();
        controladorDistribucio = new ControladorDistribucio();
        controladorDistribucio.crea_distribucio_inicial(nombreDistribucion,altura);

    }

    private static void mostrarOpcionesDistribucio() {
        while (true) {
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Añadir Producto");
            System.out.println("2. Eliminar Producto");
            System.out.println("3. Modificar Producto");
            System.out.println("4. Modificar Similitudes");
            System.out.println("5. Consultar Similitudes");
            System.out.println("6. Consultar Productos");
            System.out.println("7. Consultar Distribucion/Intercambiar Productos");
            System.out.println("8. Calcular Distribucion");
            System.out.println("9. Salir");

            System.out.println("Introduzca el número de la opción deseada:");
            int opcion = pedirEntero();

            switch (opcion) {
                case 1 -> agregarProducte();
                case 2 -> eliminarProducte();
                case 3 -> modificarProducte();
                case 4 -> modificaSimilituds();
                case 5 -> imprimeSimilituds();
                case 6 -> consultaProductes();
                case 7 -> consultaDistribucio_Ordenada_Actual();
                case 8 -> calcular_distribucion();
                case 9 -> {
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    System.exit(0);
                }
                default -> System.out.println("ERROR - Opción no válida.");
            }
        }
    }



    private static void consultaDistribucio_Ordenada_Actual() {
        if (controladorDistribucio.getProductesColocats().isEmpty()) {
            System.out.println("ERROR - No hay productos en la Distribucio.");
            return;
        }
        String [][] matrix = controladorDistribucio.getMatrizDistribucion();
        imprimirDistro(matrix);

        Scanner scanner = new Scanner(System.in);
        System.out.print("¿Desea editar la distribución? (s/n): ");
        String respuesta = scanner.nextLine();

        if (respuesta.equalsIgnoreCase("s")) {
            intercanviaProductes();
        }
    }




private static void calcular_distribucion(){

    if (controladorDistribucio.getProductesColocats().isEmpty()) {
        System.out.println("ERROR - No hay productos en la Distribucio.");
        return;
    }
        //llamar al calculo de distribuciones
    //imprimir distribucion
    //llamamos al controlador distribucion para calcularla
    System.out.print("Les Estrategies actuals son: ");
    System.out.println("1.Backtracking ");
    System.out.println("2.TSP ");

    System.out.println("Introduxeix el numero de la estrategia de calcul que es vol utilitzar : ");
    int estrategia = pedirEntero();

    String [][] matrix;

    if (estrategia == 1 || estrategia == 2) controladorDistribucio.calcula_distribucio(estrategia);
    else{
        System.out.print("ERROR - Numero invalido ");
        return;
    }
    matrix = controladorDistribucio.getMatrizDistribucion();
    imprimirDistro(matrix);

}

    private static void imprimirDistro(String[][] matrix) {
        ArrayList<String> altura_medida  = controladorDistribucio.get_altura_medida();
        int medida = Integer.parseInt(altura_medida.get(1));
        int altura = Integer.parseInt(altura_medida.get(0));


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


    //devuelve un string el numero de productos que hay en la estanteria y imprime el numero de productos
    //NO ESTAN ORDENADOS

    private static void consultaProductes() {
        ArrayList<String> productes = controladorDistribucio.getProductes();
        int n = productes.size();
        if (n == 0) {
            error_no_hi_ha_productes();
            return;
        }
        System.out.println("Els productes de la Distribucio son:");
        for (int i = 0; i < n; ++i) {
            System.out.println((i + 1) + "." + productes.get(i));
        }

    }


    private static void agregarProducte() {
        System.out.println("Introduxeix la informació del nou producte : ");
        System.out.print("Nombre del Producto: ");
        String nombre = scanner.nextLine();

        if (controladorDistribucio.existeix_producte(nombre)) {
            System.out.println("ERROR - Aquest producte ya existeix, proba a modificar-lo o eliminar-lo: ");
            return;
        }

        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = pedirDouble();
        if (precio < 0) {
            System.out.println("ERROR - El precio tiene que ser mayor o igual a 0 ");
            return;
        }
        System.out.print("Cantidad: ");
        int cantidad = pedirEntero();
        if (cantidad < 0) {
            System.out.println("ERROR - La cantidad tiene que ser mayor o igual a 0 ");
            return;
        }

       int pos = (controladorDistribucio.getProductesColocats().size() + 1);
       int altura = Integer.parseInt(controladorDistribucio.get_altura_medida().get(0));
       int alturas = (Math.min(cantidad,altura));
       controladorDistribucio.afegeix_producte(nombre, marca, precio, cantidad, pos, alturas);
        System.out.println("Producto Añadido.");
        System.out.print("Quiere modificar las similitudes del producto? (S/N): ");
        String respuesta = scanner.nextLine();

        if (respuesta.equals("S") || respuesta.equals("s")) {
            //modificamos las similitudes del producto con los productos que queramos
            modificaSimilitudProducte(nombre);
        }


    }


    private static void eliminarProducte() {

        String producte = imprimeix_productes_i_get_producte();
        if (producte.equals("null")) return;
        if (!controladorDistribucio.existeix_producte(producte)) {
            System.out.println("ERROR - Aquest producte no existeix ");
            return;
        }
        controladorDistribucio.eliminaProducte(producte);
        System.out.println("Producto eliminado.");
    }


    private static void modificarProducte() {
        String producte = imprimeix_productes_i_get_producte();
        if (producte.equals("null")) return;

        //ya hace la comprovacion de que sea valido
        consulta_info_producte(producte);

        System.out.println("¿Desea modificar este producto? (s/n): ");
        String respuesta = scanner.nextLine();
        if (respuesta.equals("S") || respuesta.equals("s")) {
            System.out.println("Nuevo Precio: ");
            double nuevoPrecio = pedirDouble();
            if (nuevoPrecio < 0) {
                System.out.println("ERROR - Precio incorrecto (p < 0) ");
                return;
            }

            System.out.println("Nueva Cantidad: ");
            int nuevaCantidad = pedirEntero();

            if (nuevaCantidad < 0) {
                System.out.println("ERROR - Cantidad incorrecta (p < 0)");
            }
            controladorDistribucio.set_cantidad_producto(producte,nuevaCantidad);
            controladorDistribucio.set_precio_producto(producte,nuevoPrecio);
            System.out.println("Producto modificado.");
        }


    }

    //imrpime todas las similitudes y podemos ir cambiando las similitudes que queremos

    private static void modificaSimilituds() {
        while (true) {
            System.out.println("Seleccione el producto al que quiere cambiar su similitud:");

            //imprimimos los productos y seleccionamos uno
            String producte = imprimeix_productes_i_get_producte();

            if (producte != "null") modificaSimilitudProducte(producte);

            System.out.println("¿Desea canviar mas similitudes? (s/n): ");
            String respuesta = scanner.nextLine();
            if (!respuesta.equalsIgnoreCase("s")) {
                break;
            }

        }

    }

    //Imprime la lista de productos disponible
    //Devuelve nulo si se selecciona un producto fuera de la lista
    //Devuelve el string seleccionado

    private static String imprimeix_productes_i_get_producte() {
        ArrayList<String> productes = controladorDistribucio.getProductes();
        if (productes.size() == 0) {
            error_no_hi_ha_productes();
            return "null";
        }
        System.out.println("Els productes de la Distribucio son:");
        for (int i = 0; i < productes.size(); ++i) {
            System.out.println((i + 1) + "." + productes.get(i));
        }
        System.out.println("Selecciona un producte (Introdueix el numero del producte que vulguis): ");
        int producto = pedirEntero();
        if (producto < 1 || producto > productes.size()) {
            System.out.println("ERROR - Producto Seleccionado incorrecto");
            return "null";
        }
        else return productes.get(producto - 1);
    }


    private static void modificaSimilitudProducte(String Producto) {
        while (true) {
            //Este array lleva el nombre del producto en la posicion i y la similitud en la posicion i+1
            ArrayList<String> similituds_del_producte = imprimeSimilitud(Producto);
            if (similituds_del_producte.isEmpty()) {
                return;
            }

            System.out.println("Seleccione el producto con el que desea establecer similitud(introducir numero):");

            int seleccion = pedirEntero();

            if (seleccion < 1 || seleccion > (similituds_del_producte.size()/2)) {
                System.out.println("Selección no válida.");
                return;
            }


            System.out.println("Valor de similitud (0-100): ");
            int valorSimilitud = pedirEntero();

            if (valorSimilitud < 0 || valorSimilitud > 100) {
                System.out.println("ERROR - La similitud debe estar entre 0 y 100.");
                return;
            }

            //esto seteara la similitud del producto Produto con el otro producto y con el valor introducido
            controladorDistribucio.set_similitud(Producto, similituds_del_producte.get(seleccion - 1), valorSimilitud);

            System.out.println("¿Desea canviar mas similitudes de este producto? (s/n): ");
            String respuesta = scanner.nextLine();
            if (!respuesta.equalsIgnoreCase("s")) {
                break;
            }
        }

    }


    //muestra por pantalla las similitudes del producto que ha entrado como string y devuelve el array de strings
    private static ArrayList<String> imprimeSimilitud(String producto) {
        //imprimimos los productos ya disponibles

        //ens mostra les similituds del producte amb tota la resta
        ArrayList<String> resultat = controladorDistribucio.getSimilitudsProducte(producto);
        if (resultat.isEmpty()) {
            System.out.println("ERROR - No hi ha similituds disponibles per a aquest producte");
            return resultat;
        }

        //En resultat tenim parelles d'elements on el primer element es el nom del producte i el segon la seva similitud
        System.out.println("Les similituds actuals del producte " + producto + " amb la resta de productes son:");
        int cont = 0;
        for (int i = 0; i < resultat.size(); i += 2) {
            System.out.println((cont+1) + ". " + resultat.get(i) + " con " + producto + " - " + resultat.get(i + 1));
            ++cont;
        }
        return resultat;

    }


    private static void imprimeSimilituds() {

        ArrayList<String> productes = controladorDistribucio.getProductes();
        int n = productes.size();

        if (n == 0) {
            error_no_hi_ha_productes();
            return;
        }
        for (int i = 0; i < n; ++i) {
            imprimeSimilitud(productes.get(i));
        }

    }


    private static void consulta_info_producte(String producte) {
        ArrayList<String> info = controladorDistribucio.get_info_producte(producte);
        System.out.println("Valores actuales del producto:");
        System.out.println("Nombr: " + info.get(0));
        System.out.println("Marca: " + info.get(1));
        System.out.println("Precio: " + info.get(2));
        System.out.println("Stock: " + info.get(3));
    }

    private static void intercanviaProductes() {
        System.out.println("Seleccione el primer producto a intercambiar:");
        for (int i = 0; i < controladorDistribucio.getProductesColocats().size(); i++) {
            System.out.println((i + 1) + ". " + controladorDistribucio.getProductesColocats().get(i).getProducte().getNom());
        }
        int seleccion1 = pedirEntero();

        int r = controladorDistribucio.getNumero_prestatges();
        if (seleccion1 < 0 && seleccion1 > r ) {
            System.out.println("ERROR - Producto seleccionado incorrecto");
            return;
        }

        System.out.println("Seleccione el segundo producto a intercambiar:");
        for (int i = 0; i < controladorDistribucio.getProductesColocats().size(); i++) {
            System.out.println((i + 1) + ". " + controladorDistribucio.getProductesColocats().get(i).getProducte().getNom());
        }
        int seleccion2 = pedirEntero();

        if (seleccion2 < 0 && seleccion2 > r ) {
            System.out.println("ERROR - Producto seleccionado incorrecto");
            return;
        }

        controladorDistribucio.intercambiar_productes(seleccion1 - 1, seleccion2 - 1);
        System.out.println("Productos intercambiados.");
    }


///////////////////////////////
    //Casos de error repetidos

    private static void error_no_hi_ha_productes() {
        System.out.println("ERROR - No tenim productes");
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
                System.out.println("ERROR - Entrada no válida. Por favor, ingrese un número entero.");
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
                System.out.println("ERROR - Entrada no válida. Por favor, ingrese un número decimal.");
                scanner.nextLine(); // Consumir entrada no válida
            }
        }
        return numero;
    }


}
