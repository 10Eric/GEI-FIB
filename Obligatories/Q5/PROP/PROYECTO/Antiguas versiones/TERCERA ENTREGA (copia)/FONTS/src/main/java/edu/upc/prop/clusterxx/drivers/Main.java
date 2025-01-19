package edu.upc.prop.clusterxx.drivers;

import edu.upc.prop.clusterxx.clases_dominio.Distribucio;
import edu.upc.prop.clusterxx.clases_dominio.Perfil;
import edu.upc.prop.clusterxx.clases_dominio.Prestatgeria;
import edu.upc.prop.clusterxx.controladores.ControladorPerfil;
import edu.upc.prop.clusterxx.controladores.ControladorPrestatgeria;
import edu.upc.prop.clusterxx.controladores.ControladorDistribucio;

import java.util.InputMismatchException;

import java.util.*;

public class Main {
    private static Map<String, ControladorPerfil> perfiles = new HashMap<>();
    private static ControladorPerfil perfilActual;
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, ControladorPrestatgeria> prestatgeria = new HashMap<>();
    private static Prestatgeria prestatgeriaActual;
    private static ControladorDistribucio controladorDistribucio;

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
             mostrarMenuPrestatgerias();
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
                case 5 -> guardarDatos();
                case 6 -> {
                    guardarDatos();
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
            perfilActual.getPrestatgerias().add(prestatgeriaActual);
            System.out.println("Estanteria creada.");
        }
        guardarDatos();
    }

    private static void mostrarFormularioEditarPrestatgeria() {
        if (perfilActual.getPrestatgerias().isEmpty()) {
            System.out.println("No hay Estanteria para editar.");
            return;
        }
        List<Prestatgeria> prestatgerias = perfilActual.getPrestatgerias();
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
        guardarDatos();
    }

    private static void mostrarFormularioCargarPrestatgeria() {
        if (perfilActual.getPrestatgerias().isEmpty()) {
            System.out.println("No hay Estanteria guardadas.");
            return;
        }
        List<Prestatgeria> prestatgerias = perfilActual.getPrestatgerias();
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
        crearDistribucionInicial();
        mostrarOpcionesDistribucio();
    }

    private static void mostrarFormularioBorrarPrestatgeria() {
        if (perfilActual.getPrestatgerias().isEmpty()) {
            System.out.println("No hay Estanteria para borrar.");
            return;
        }
        List<Prestatgeria> prestatgerias = perfilActual.getPrestatgerias();
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
        perfilActual.getPrestatgerias().remove(distribucio);
        guardarDatos();
        System.out.println("Estanteria eliminada.");
    }

    private static void crearDistribucionInicial() {
        if (prestatgeriaActual.getDistribucion() == null) {
            //crea ditribucion inicial
            String nombreDistribucion = "Distribucion Predeterminada";
            Distribucio r = new Distribucio(nombreDistribucion);
            prestatgeriaActual.setDistribucion(r);
            controladorDistribucio = new ControladorDistribucio();
            controladorDistribucio.crear_inicial(r, prestatgeriaActual);
        }

        else {
            controladorDistribucio = new ControladorDistribucio();
            controladorDistribucio.crear_inicial(prestatgeriaActual.getDistribucion(), prestatgeriaActual);
        }

    }

    private static void mostrarOpcionesDistribucio() {
        while (true) {
            System.out.println("\nMenu de opciones:");
            System.out.println("1. Añadir Producto");
            System.out.println("2. Eliminar Producto");
            System.out.println("3. Modificar Producto");
            System.out.println("4. Modificar Similitudes");
            System.out.println("5. Consultar Similitudes");
            System.out.println("6. Consultar Productos");
            System.out.println("7. Consultar Distribucion/Intercambiar Productos");
            System.out.println("8. Calcular Distribucion");
            System.out.println("9. Salir");

            System.out.println("Introduzca el numero de la opcion deseada:");
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
                    guardarDatos();
                    System.out.println("Saliendo del programa. Hasta luego!");
                    System.exit(0);
                }
                default -> System.out.println("ERROR - Opcion no valida.");
            }
        }
    }



    private static void consultaDistribucio_Ordenada_Actual() {
        if (controladorDistribucio.getProductesColocats().isEmpty()) {
            System.out.println("ERROR - No hay productos en la Distribucion.");
            return;
        }
        String [][] matrix = controladorDistribucio.getMatrizDistribucion();
        imprimirDistro(matrix);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Desea editar la distribucion? (S/N): ");
        String respuesta = scanner.nextLine();

        if (respuesta.equalsIgnoreCase("s")) {
            intercanviaProductes();
        }
    }




    private static void calcular_distribucion(){

        if (controladorDistribucio.getProductesColocats().isEmpty()) {
            System.out.println("ERROR - No hay productos en la Distribucion.");
            return;
        }
        //llamar al calculo de distribuciones
        //imprimir distribucion
        //llamamos al controlador distribucion para calcularla
        System.out.println("Las Estrategias actuals son: ");
        System.out.println("1.Backtracking ");
        System.out.println("2.TSP ");

        System.out.println("Introduce el numero de la estrategia de calculo que se quiere utilizar : ");
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
        System.out.println("Los productos de la Distribucion son:");
        for (int i = 0; i < n; ++i) {
            System.out.println((i + 1) + "." + productes.get(i));
        }

    }


    private static void agregarProducte() {
        System.out.println("Introduce la informacion del nuevo producto : ");
        System.out.print("Nombre del Producto: ");
        String nombre = scanner.nextLine();

        if (controladorDistribucio.existeix_producte(nombre)) {
            System.out.println("ERROR - Este producto ya existe, prueba de modificarlo o eliminarlo: ");
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
            System.out.println("ERROR - Este producto no existe ");
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

        System.out.println("Desea modificar este producto? (s/n): ");
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

            System.out.println("Desea canviar mas similitudes? (S/N): ");
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
        System.out.println("Los productos de la Distribucion son:");
        for (int i = 0; i < productes.size(); ++i) {
            System.out.println((i + 1) + "." + productes.get(i));
        }
        System.out.println("Selecciona un producto (Introduce el numero del producto que quieras): ");
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
                System.out.println("Seleccion no valida.");
                return;
            }


            System.out.println("Valor de similitud (0-100): ");
            int valorSimilitud = pedirEntero();

            if (valorSimilitud < 0 || valorSimilitud > 100) {
                System.out.println("ERROR - La similitud debe estar entre 0 y 100.");
                return;
            }


            //esto seteara la similitud del producto Produto con el otro producto y con el valor introducido
            controladorDistribucio.set_similitud(Producto, similituds_del_producte.get((seleccion-1)*2), valorSimilitud);

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
            System.out.println("ERROR - No hay similitudes disponibles para este producto");
            return resultat;
        }

        //En resultat tenim parelles d'elements on el primer element es el nom del producte i el segon la seva similitud
        System.out.println("Las similitudes actuales del producto " + producto + " con el resto de productos son:");
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
        System.out.println("Nombre: " + info.get(0));
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
        System.out.println("ERROR - No tenemos productos");
    }

    private static void guardarDatos() {
       /* try {
            controladorPersistenciaMain.guardarMain(perfiles, "TERCERA_ENTREGA.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    private static void cargarDatos() {
       /* try {
            perfiles = (Map<String, ControladorPerfil>) controladorPersistenciaMain.cargarMain("TERCERA_ENTREGA.dat");
        } catch (IOException | ClassNotFoundException e) {
            perfiles = new HashMap<>();
        }*/
    }


}
