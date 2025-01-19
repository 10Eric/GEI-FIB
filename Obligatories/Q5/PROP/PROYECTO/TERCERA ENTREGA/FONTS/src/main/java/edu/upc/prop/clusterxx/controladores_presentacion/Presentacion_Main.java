package edu.upc.prop.clusterxx.controladores_presentacion;
import edu.upc.prop.clusterxx.controladores.ControladorPerfil;
import edu.upc.prop.clusterxx.controladores.ControladorPrestatgeria;
import edu.upc.prop.clusterxx.controladores.ControladorDistribucio;

import java.util.*;
import javax.swing.*;
import java.io.IOException;

/**
 * Esta clase representa la ventana principal de la aplicación de gestión de perfiles y prestatgerías.
 */
public class Presentacion_Main extends JFrame {

    private static Set<String> usuarios = new HashSet<>();
    private static Set<String> prestatgerias = new HashSet<>();


    private static ControladorPerfil perfilActual;
    private static ControladorDistribucio controladorDistribucio;

    private static ControladorPrestatgeria prestatgeriaActual;
    private static ControladorPrestatgeria ControladorPrestatgerias = new ControladorPrestatgeria();

    private static ControladorPerfil ControladorPerfilAux = new ControladorPerfil();


    /**
     * Constructor de la clase Presentacion_Min.
     * Inicializa la ventana principal y carga los datos.
     */
    public Presentacion_Main() {
        setTitle("Gestión de Perfiles");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cargarDatos();
        mostrarMenuPrincipal();
        setVisible(true);
    }

    /**
     * Obtiene el controlador de distribución.
     *
     * @return el controlador de distribución
     */
    public ControladorDistribucio getControladorDistribucio() {
        return controladorDistribucio;
    }

    /**
     * Obtiene el conjunto de usuarios.
     *
     * @return el conjunto de usuarios
     */
    public Set<String> getUsuarios() {
        return usuarios;
    }

    /**
     * Elimina un usuario del conjunto de usuarios.
     *
     * @param usuario_r el usuario a eliminar
     */
    public void removeUsuarios(String usuario_r){
       if (usuarios.contains(usuario_r)){
           usuarios.remove(usuario_r);
       }
    }

    /**
     * Elimina una prestatgeria del conjunto de prestatgerias.
     *
     * @param prestatgeria la prestatgeria a eliminar
     */
    public void removePrestatgeria_llista(String prestatgeria){
        if (prestatgerias.contains(prestatgeria)) {
            prestatgerias.remove(prestatgeria);
        }

    }

    /**
     * Obtiene el perfil actual.
     *
     * @return el perfil actual
     */
    public ControladorPerfil getPerfilActual() {
        return perfilActual;
    }

    /**
     * Establece el perfil actual.
     *
     * @param perfilActual el perfil a establecer como actual
     */
    public void setPerfilActual(ControladorPerfil perfilActual) {
        this.perfilActual = perfilActual;
    }

    /**
     * Obtiene el conjunto de prestatgerias.
     *
     * @return el conjunto de prestatgerias
     */
    public Set<String> getPrestatgerias() {
        return prestatgerias;
    }

    /**
     * Establece la prestatgeria actual.
     *
     * @param prestatgeriaActual la prestatgeria a establecer como actual
     */
    public void setPrestatgeriaActual(ControladorPrestatgeria prestatgeriaActual) {
        this.prestatgeriaActual = prestatgeriaActual;
    }

    /**
     * Obtiene la prestatgeria actual.
     *
     * @return la prestatgeria actual
     */
    public ControladorPrestatgeria getPrestatgeriaActual() {
        return prestatgeriaActual;
    }

    /**
     * Muestra el menú principal.
     */
    public void mostrarMenuPrincipal() {
        MenuPrincipalView vista = new MenuPrincipalView(this);
        actualizarVista(vista.getPanel());
    }

    /**
     * Muestra el formulario para crear un perfil.
     */
    public void mostrarFormularioCrearPerfil() {
        CrearPerfilView vista = new CrearPerfilView(this);
        actualizarVista(vista.getPanel());
    }

    /**
     * Muestra el formulario para cargar un perfil.
     */
    public void mostrarFormularioCargarPerfil() {
        CargarPerfilView vista = new CargarPerfilView(this);
        actualizarVista(vista.getPanel());
    }

    /**
     * Muestra el formulario para borrar un perfil.
     */
    public void mostrarFormularioBorrarPerfil() {
        BorrarPerfilView vista = new BorrarPerfilView(this);
        actualizarVista(vista.getPanel());
        mostrarMenuPrincipal();
    }

    /**
     * Muestra el formulario para editar un perfil.
     */
    public void mostrarFormularioEditarPerfil() {
        EditarPerfilView vista = new EditarPerfilView(this);
        if (vista.getPanel() != null) {
            actualizarVista(vista.getPanel());
        }
    }

    /**
     * Muestra la vista para editar un perfil.
     */
    public void mostrarEditarPerfil() {
        MostrarEditarPerfilView vista = new MostrarEditarPerfilView(this);
        actualizarVista(vista.getPanel());
    }

    /**
     * Muestra el menú principal de prestatgerias.
     */
    public void mostrarMenuPrincipalPrestatgeria() {
        MenuPrestatgeriaView vista = new MenuPrestatgeriaView(this);
        actualizarVista(vista.getPanel());
    }

    /**
     * Muestra el formulario para crear una prestatgeria.
     */
    public void mostrarFormularioCrearPrestatgeria() {
        CrearPrestatgeriaView vista = new CrearPrestatgeriaView(this);
        actualizarVista(vista.getPanel());
    }

    /**
     * Muestra el formulario para cargar una prestatgeria.
     */
    public void mostrarFormularioCargarPrestatgeria() {
        CargarPrestatgeriaView vista = new CargarPrestatgeriaView(this);
        if (vista.getPanel() != null) {
            actualizarVista(vista.getPanel());
        }
    }

    /**
     * Muestra el formulario para editar una prestatgeria.
     */
    public void mostrarFormularioEditarPrestatgeria() {
        EditarPrestatgeriaView vista = new EditarPrestatgeriaView(this);
        if (vista.getPanel() != null) {
            actualizarVista(vista.getPanel());
        }
        mostrarMenuPrincipalPrestatgeria();
    }

    /**
     * Muestra el formulario para borrar una prestatgeria.
     */
    public void mostrarFormularioBorrarPrestatgeria() {
        BorrarPrestatgeriaView vista = new BorrarPrestatgeriaView(this);
        if (vista.getPanel() != null) {
            actualizarVista(vista.getPanel());
        }
        mostrarMenuPrincipalPrestatgeria();
    }

    /**
     * Muestra el menú principal de distribución.
     */
    public void mostrarMenuPrincipalDistribucio() {
        MenuDistribucionView vista = new MenuDistribucionView(this);
        actualizarVista(vista.getPanel());
    }

    /**
     * Muestra el formulario para agregar un producto.
     */
    public void mostrarFormularioAgregarProducto() {
        AgregarProductoView vista = new AgregarProductoView(this);
        actualizarVista(vista.getPanel());
    }

    /**
     * Muestra el formulario para eliminar un producto.
     */
    public void mostrarFormularioEliminarProducto() {
        EliminarProductoView vista = new EliminarProductoView(this);
        if (vista.getPanel() != null) {
            actualizarVista(vista.getPanel());
        }
        mostrarMenuPrincipalDistribucio();
    }

    /**
     * Muestra el formulario para modificar un producto.
     */
    public void mostrarFormularioModificarProducto() {
        ModificarProductoView vista = new ModificarProductoView(this);
        if (vista.getPanel() != null) {
            actualizarVista(vista.getPanel());
        }
    }

    /**
     * Muestra el formulario para modificar las similitudes entre productos.
     */
    public void mostrarFormularioModificarSimilitudes() {
        ModificarSimilitudesView vista = new ModificarSimilitudesView(this);
        if (vista.getPanel() != null) {
            actualizarVista(vista.getPanel());
        }
    }

    /**
     * Muestra el formulario para consultar las similitudes entre productos.
     */
    public void mostrarFormularioConsultarSimilitudes() {
        ConsultaSimilitudesView vista = new ConsultaSimilitudesView(this);
        if (vista.getPanel() != null) {
            actualizarVista(vista.getPanel());
        }
    }

    /**
     * Muestra el formulario para consultar productos.
     */
    public void mostrarFormularioConsultarProductos() {
        ConsultarProductosView vista = new ConsultarProductosView(this);
        if (vista.getPanel() != null) {
            actualizarVista(vista.getPanel());
        }
        mostrarMenuPrincipalDistribucio();
    }

    /**
     * Muestra el formulario para calcular la distribución.
     */
    public void mostrarFormularioCalcularDistribucion() {
        CalculaDistribucionView vista = new CalculaDistribucionView(this);
        if (vista.getPanel() != null) {
            actualizarVista(vista.getPanel());
        }
    }

    /**
     * Muestra la distribución en una matriz de resultados.
     *
     * @param matriz la matriz de resultados a mostrar
     * @param titulo el título de la vista
     */
    public void mostrarDistribucion(String[][] matriz, String titulo) {
        MostrarMatrizResultadosView vista = new MostrarMatrizResultadosView(this, matriz, titulo);
        if (vista.getPanel() != null) {
            actualizarVista(vista.getPanel());
        }
    }

    /**
     * Muestra la vista para modificar la posición de los productos.
     */
    public void ModificarPosicionProductos() {
        ModificarPosicionProductosView vista = new ModificarPosicionProductosView(this);
        if (vista.getPanel() != null) {
            actualizarVista(vista.getPanel());
        }
        MostrarMatrizResultadosEditable(controladorDistribucio.getMatrizDistribucion(), "Distribución de Productos");
    }

    /**
     * Muestra la matriz de resultados editable.
     *
     * @param matriz la matriz de resultados a mostrar
     * @param titulo el título de la vista
     */
    public void MostrarMatrizResultadosEditable(String[][] matriz, String titulo) {
        MostrarMatrizResultadosEditableView vista = new MostrarMatrizResultadosEditableView(this, matriz, titulo);
        if (vista.getPanel() != null) {
            actualizarVista(vista.getPanel());
        }
    }


    /**
     * Actualiza la vista con un nuevo panel.
     *
     * @param nuevoPanel el nuevo panel a mostrar
     */
    private void actualizarVista(JPanel nuevoPanel) {
        setContentPane(nuevoPanel);
        revalidate();
        repaint();
        setVisible(true);
    }

    /**
     * Guarda los datos del perfil y la prestatgeria actual.
     */
    public static void guardarDatos() {
        try {
            if (perfilActual != null) {
                perfilActual.guardarPerfil();
                guardarDatosPrestatgeria();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Guarda los datos de la prestatgeria actual.
     */
    private static void guardarDatosPrestatgeria() {
        try {
            if (prestatgeriaActual != null) {
                prestatgeriaActual.guardarPrestatgeria(perfilActual.getUsuari());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga los datos de los perfiles.
     */
    public static void cargarDatos() {
        try {
            ControladorPerfil per = new ControladorPerfil();
            usuarios = per.cargarTodosPerfiles();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga los datos de las prestatgerias.
     */
    public static void cargarPrestatgerias() {
        try {
            ControladorPrestatgeria prest = new ControladorPrestatgeria();
            prestatgerias = prest.cargarTodasPrestatgeries(perfilActual.getUsuari());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Crea la distribución inicial.
     */
    public static void crearDistribucionInicial() {
        if (prestatgeriaActual.getDistribucion() == null) {
            String nombreDistribucion = "Distribucion Predeterminada";
            controladorDistribucio = new ControladorDistribucio(nombreDistribucion);
            prestatgeriaActual.setDistribucionCtrl(controladorDistribucio);
            controladorDistribucio.crear_inicialCtrl(controladorDistribucio, prestatgeriaActual);
        } else {
            controladorDistribucio = new ControladorDistribucio();
            controladorDistribucio.crear_inicialCtrlPrestatgeria(prestatgeriaActual);
        }
    }

    /**
     * Obtiene el perfil de un usuario.
     *
     * @param usuario el nombre del usuario
     * @return el perfil del usuario
     */
    public ControladorPerfil obtenerPerfil(String usuario) {
        try {
            ControladorPerfilAux.cargarPerfil(usuario);
            return ControladorPerfilAux;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Elimina un perfil.
     *
     * @param usuario el nombre del usuario
     */
    public void eliminarPerfil(String usuario) {

        try {
            ControladorPerfilAux.eliminarPerfil(usuario);
            if (usuarios.contains(usuario)) {
                usuarios.remove(usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Busca perfiles de un usuario.
     *
     * @param usuario el nombre del usuario
     */
    public void buscarPerfiles(String usuario) {
        try {
            ControladorPerfilAux.buscarPerfil(usuario);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método principal para iniciar la aplicación.
     *
     * @param args los argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Presentacion_Main());
    }

    /**
     * Crea un perfil.
     *
     * @param usuario el nombre del usuario
     * @param contraseña la contraseña del usuario
     */
    public void creaPerfil(String usuario, String contraseña) {
        ControladorPerfil perf = new ControladorPerfil();
        perf.creaPerfil(usuario, contraseña);
        usuarios.add(usuario);
        try {
            perf.guardarPerfil();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Crea una prestatgeria.
     *
     * @param usuario el nombre del usuario
     */
    public void creaPrestatgeria2(String usuario) {
        ControladorPrestatgeria perf = new ControladorPrestatgeria();
        perf.crearPres2(prestatgeriaActual);
        prestatgerias.add(usuario);
        perfilActual.añadirPrestatgeriaCtrl(perf);
        try {
            perf.guardarPrestatgeria(perfilActual.getUsuari());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Crea una prestatgeria.
     *
     * @param nombre el nombre de la prestatgeria
     * @param altura la altura de la prestatgeria
     */
    public void creaPrestatgeria(String nombre, int altura) {
        ControladorPrestatgeria prest = new ControladorPrestatgeria();
        prest.creaPrest(nombre, altura);
        prestatgerias.add(nombre);
        perfilActual.añadirPrestatgeriaCtrl(prest);
        try {
            prest.guardarPrestatgeria(perfilActual.getUsuari());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Borra todas las prestatgerias de un usuario.
     *
     * @param usuario el nombre del usuario
     */
    public void borrarPrestatgerias(String usuario) {
        try {
            ControladorPrestatgeria.eliminarPrestatgerias(usuario);
            prestatgerias.clear();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Edita el nombre de una prestatgeria.
     *
     * @param nombre el nombre actual de la prestatgeria
     * @param nuevoNombre el nuevo nombre de la prestatgeria
     */
    public void editarnom_UsuariPrestatgeria(String nombre, String nuevoNombre) {
        try {
            ControladorPrestatgerias.editarNombreUsuariPrestatgeria(nombre, nuevoNombre);
            prestatgerias.remove(nombre);
            prestatgerias.add(nuevoNombre);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Elimina una prestatgeria.
     *
     * @param nombre el nombre de la prestatgeria
     */
    public void eliminarPrestatgeria(String nombre) {
        try {
            ControladorPrestatgeria.eliminarPrestatgeria(perfilActual.getUsuari(), nombre);
            prestatgerias.remove(nombre);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Busca prestatgerias de un usuario.
     *
     * @param nombre el nombre de la prestatgeria
     */
    public void buscarPrestatgerias(String nombre) {
        ControladorPrestatgerias.buscarPrestatgerias(perfilActual.getUsuari(), nombre);

    }

    /**
     * Obtiene el controlador de perfiles.
     *
     * @return el controlador de perfiles
     */
    public ControladorPerfil getControladorPerfiles() {
        return ControladorPerfilAux;
    }

    /**
     * Obtiene el controlador de prestatgerias.
     *
     * @return el controlador de prestatgerias
     */
    public ControladorPrestatgeria getControladorPrestatgerias() {
        return ControladorPrestatgerias;
    }

    /**
     * Obtiene el nombre de usuario del perfil actual.
     *
     * @return el nombre de usuario del perfil actual
     */
    public String get_usuari_perfil() {
        if (ControladorPerfilAux != null && ControladorPerfilAux.getPerfil() != null) {
            return ControladorPerfilAux.getUsuari();
        }
        return null;  // Si no hay perfil cargado, retorna null
    }

    /**
     * Obtiene la contraseña del perfil actual.
     *
     * @return la contraseña del perfil actual
     */
    public String get_contraseña_perfil() {
        if (ControladorPerfilAux != null && ControladorPerfilAux.getPerfil() != null) {
            return ControladorPerfilAux.getContrasenya();
        }
        return null;
    }

    /**
     * Establece la contraseña del perfil actual.
     *
     * @param contra la nueva contraseña
     */
    public void set_contraseña_perfil(String contra) {
        if (ControladorPerfilAux != null && ControladorPerfilAux.getPerfil() != null) {
            ControladorPerfilAux.setContrasenya(contra);
        }
    }

    /**
     * Establece el nombre de usuario del perfil actual.
     *
     * @param usuari el nuevo nombre de usuario
     */
    public void set_usuari_perfil(String usuari) {
        if (ControladorPerfilAux != null && ControladorPerfilAux.getPerfil() != null) {
            ControladorPerfilAux.setUsuari(usuari);
        }
    }

    /**
     * Obtiene la lista de productos.
     *
     * @return la lista de productos
     */
    public ArrayList<String> get_productes(){
        return controladorDistribucio.getProductes();
    }

    /**
     * Intercambia la posición de dos productos.
     *
     * @param index1 el índice del primer producto
     * @param index2 el índice del segundo producto
     */
   public void intercambiar_productes(int index1, int index2){
        controladorDistribucio.intercambiar_productes(index1,index2);
        }

    /**
     * Establece el precio de un producto.
     *
     * @param seleccion el nombre del producto
     * @param nuevoPrecio el nuevo precio del producto
     */
    public void set_precio_producto(String seleccion, double nuevoPrecio){
        controladorDistribucio.set_precio_producto(seleccion, nuevoPrecio);
    }

    /**
     * Establece la cantidad de un producto.
     *
     * @param seleccion el nombre del producto
     * @param nuevaCantidad la nueva cantidad del producto
     */
    public void set_cantidad_producto(String seleccion, int nuevaCantidad){
        controladorDistribucio.set_cantidad_producto(seleccion, nuevaCantidad);
    }

    /**
     * Verifica si el set de usuarios está vacío.
     *
     * @return true si el set de usuarios está vacío, false en caso contrario
     */
    public boolean es_buit(){
        return getUsuarios().isEmpty();
    }

    /**
     * Verifica si una prestatgeria existe.
     *
     * @param nombre el nombre de la prestatgeria
     * @return true si la prestatgeria existe, false en caso contrario
     */
    public boolean exists_prestatgeria(String nombre){
        return getPrestatgerias().contains(nombre);
    }

    /**
     * Verifica si un producto existe.
     *
     * @param nombre el nombre del producto
     * @return true si el producto existe, false en caso contrario
     */
    public boolean exists_producte(String nombre){
        return getControladorDistribucio().existeix_producte(nombre);
    }

    /**
     * Elimina un producto.
     *
     * @param seleccion el nombre del producto a eliminar
     */
    public void eliminaProducte(String seleccion){
        controladorDistribucio.eliminaProducte(seleccion);
    }

    /**
     * Obtiene las similitudes de un producto en la distribución.
     *
     * @param producto el nombre del producto
     * @return la lista de similitudes del producto
     */
    public ArrayList<String> get_similituds_distribucio (String producto){
        return controladorDistribucio.getSimilitudsProducte(producto);
    }

    /**
     * Verifica si hay productos colocados.
     *
     * @return true si hay productos colocados, false en caso contrario
     */
    public boolean teProductesColocats() {
        return controladorDistribucio.getProductesColocats().isEmpty();
    }

    /**
     * Añade un producto a la distribución.
     *
     * @param nombre el nombre del producto
     * @param marca la marca del producto
     * @param precio el precio del producto
     * @param cantidad la cantidad del producto
     * @param pos la posición del producto
     * @param altura la altura del producto
     * @return true si el producto se añadió con éxito, false en caso contrario
     */
    public boolean afegeix_producte(String nombre, String marca, double precio, int cantidad, int pos, int altura){
        controladorDistribucio.afegeix_producte(nombre, marca, precio, cantidad,pos, cantidad);
        return true;
    }

    /**
     * Obtiene la posición final de los productos colocados.
     *
     * @return la posición final de los productos colocados
     */
    public int get_posicio_final() {
        return controladorDistribucio.getProductesColocats().getLast().getPos() + 1;
    }

    /**
     * Obtiene la matriz de distribución.
     *
     * @return la matriz de distribución
     */
    public String[][] getMatrizDistribucion(){
        return controladorDistribucio.getMatrizDistribucion();
    }

    /**
     * Verifica si existe un controlador de perfil.
     *
     * @return true si existe un controlador de perfil, false en caso contrario
     */
    public boolean existeix_controlador_perfil() {
       return ControladorPerfilAux.getPerfil() != null;
    }
}
