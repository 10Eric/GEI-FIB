package edu.upc.prop.clusterxx.controladores_presentacion;
import edu.upc.prop.clusterxx.controladores.ControladorPerfil;
import edu.upc.prop.clusterxx.controladores.ControladorPrestatgeria;
import edu.upc.prop.clusterxx.controladores.ControladorDistribucio;

import java.util.*;
import javax.swing.*;
import java.io.IOException;

public class Presentacion_Main extends JFrame {

    private static Set<String> usuarios = new HashSet<>();
    private static Set<String> prestatgerias = new HashSet<>();


    private static ControladorPerfil perfilActual;
    private static ControladorDistribucio controladorDistribucio;

    private static ControladorPrestatgeria prestatgeriaActual;
    private static ControladorPrestatgeria ControladorPrestatgerias = new ControladorPrestatgeria();

    private static ControladorPerfil ControladorPerfilAux = new ControladorPerfil();


    public Presentacion_Main() {
        setTitle("Gestión de Perfiles");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cargarDatos();
        mostrarMenuPrincipal();
        setVisible(true);
    }

    public ControladorDistribucio getControladorDistribucio() {
        return controladorDistribucio;
    }

    public Set<String> getUsuarios() {
        return usuarios;
    }

    public void removeUsuarios(String usuario_r){
       if (usuarios.contains(usuario_r)){
           usuarios.remove(usuario_r);
       }
    }

    public void removePrestatgeria_llista(String prestatgeria){
        if (prestatgerias.contains(prestatgeria)) {
            prestatgerias.remove(prestatgeria);
        }

    }



    public ControladorPerfil getPerfilActual() {
        return perfilActual;
    }

    public void setPerfilActual(ControladorPerfil perfilActual) {
        this.perfilActual = perfilActual;
    }

    public Set<String> getPrestatgerias() {
        return prestatgerias;
    }

    public void setPrestatgeriaActual(ControladorPrestatgeria prestatgeriaActual) {
        this.prestatgeriaActual = prestatgeriaActual;
    }

    public ControladorPrestatgeria getPrestatgeriaActual() {
        return prestatgeriaActual;
    }

    public void mostrarMenuPrincipal() {
        MenuPrincipalView vista = new MenuPrincipalView(this);
        actualizarVista(vista.getPanel());
    }

    public void mostrarFormularioCrearPerfil() {
        CrearPerfilView vista = new CrearPerfilView(this);
        actualizarVista(vista.getPanel());
    }

    public void mostrarFormularioCargarPerfil() {
        CargarPerfilView vista = new CargarPerfilView(this);
        actualizarVista(vista.getPanel());
    }

    public void mostrarFormularioBorrarPerfil() {
        BorrarPerfilView vista = new BorrarPerfilView(this);
        actualizarVista(vista.getPanel());
        mostrarMenuPrincipal();
    }

    public void mostrarFormularioEditarPerfil() {
        EditarPerfilView vista = new EditarPerfilView(this);
        if (vista.getPanel() != null) {
            actualizarVista(vista.getPanel());
        }
    }

    public void mostrarEditarPerfil() {
        MostrarEditarPerfilView vista = new MostrarEditarPerfilView(this);
        actualizarVista(vista.getPanel());
    }

    public void mostrarMenuPrincipalPrestatgeria() {
        MenuPrestatgeriaView vista = new MenuPrestatgeriaView(this);
        actualizarVista(vista.getPanel());
    }

    public void mostrarFormularioCrearPrestatgeria() {
        CrearPrestatgeriaView vista = new CrearPrestatgeriaView(this);
        actualizarVista(vista.getPanel());
    }

    public void mostrarFormularioCargarPrestatgeria() {
        CargarPrestatgeriaView vista = new CargarPrestatgeriaView(this);
        if (vista.getPanel() != null) {
            actualizarVista(vista.getPanel());
        }
    }

    public void mostrarFormularioEditarPrestatgeria() {
        EditarPrestatgeriaView vista = new EditarPrestatgeriaView(this);
        if (vista.getPanel() != null) {
            actualizarVista(vista.getPanel());
        }
        mostrarMenuPrincipalPrestatgeria();
    }

    public void mostrarFormularioBorrarPrestatgeria() {
        BorrarPrestatgeriaView vista = new BorrarPrestatgeriaView(this);
        if (vista.getPanel() != null) {
            actualizarVista(vista.getPanel());
        }
        mostrarMenuPrincipalPrestatgeria();
    }

    public void mostrarMenuPrincipalDistribucio() {
        MenuDistribucionView vista = new MenuDistribucionView(this);
        actualizarVista(vista.getPanel());
    }

    public void mostrarFormularioAgregarProducto() {
        AgregarProductoView vista = new AgregarProductoView(this);
        actualizarVista(vista.getPanel());
    }

    public void mostrarFormularioEliminarProducto() {
        EliminarProductoView vista = new EliminarProductoView(this);
        if (vista.getPanel() != null) {
            actualizarVista(vista.getPanel());
        }
        mostrarMenuPrincipalDistribucio();
    }

    public void mostrarFormularioModificarProducto() {
        ModificarProductoView vista = new ModificarProductoView(this);
        if (vista.getPanel() != null) {
            actualizarVista(vista.getPanel());
        }
    }

    public void mostrarFormularioModificarSimilitudes() {
        ModificarSimilitudesView vista = new ModificarSimilitudesView(this);
        if (vista.getPanel() != null) {
            actualizarVista(vista.getPanel());
        }
    }

    public void mostrarFormularioConsultarSimilitudes() {
        ConsultaSimilitudesView vista = new ConsultaSimilitudesView(this);
        if (vista.getPanel() != null) {
            actualizarVista(vista.getPanel());
        }
    }

    public void mostrarFormularioConsultarProductos() {
        ConsultarProductosView vista = new ConsultarProductosView(this);
        if (vista.getPanel() != null) {
            actualizarVista(vista.getPanel());
        }
        mostrarMenuPrincipalDistribucio();
    }

    public void mostrarFormularioCalcularDistribucion() {
        CalculaDistribucionView vista = new CalculaDistribucionView(this);
        if (vista.getPanel() != null) {
            actualizarVista(vista.getPanel());
        }
    }

    public void mostrarDistribucion(String[][] matriz, String titulo) {
        MostrarMatrizResultadosView vista = new MostrarMatrizResultadosView(this, matriz, titulo);
        if (vista.getPanel() != null) {
            actualizarVista(vista.getPanel());
        }
    }

    public void ModificarPosicionProductos() {
        ModificarPosicionProductosView vista = new ModificarPosicionProductosView(this);
        if (vista.getPanel() != null) {
            actualizarVista(vista.getPanel());
        }
        MostrarMatrizResultadosEditable(controladorDistribucio.getMatrizDistribucion(), "Distribución de Productos");
    }

    public void MostrarMatrizResultadosEditable(String[][] matriz, String titulo) {
        MostrarMatrizResultadosEditableView vista = new MostrarMatrizResultadosEditableView(this, matriz, titulo);
        if (vista.getPanel() != null) {
            actualizarVista(vista.getPanel());
        }
    }


    private void actualizarVista(JPanel nuevoPanel) {
        setContentPane(nuevoPanel);
        revalidate();
        repaint();
        setVisible(true);
    }

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

    private static void guardarDatosPrestatgeria() {
        try {
            if (prestatgeriaActual != null) {
                prestatgeriaActual.guardarPrestatgeria(perfilActual.getUsuari());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cargarDatos() {
        try {
            ControladorPerfil per = new ControladorPerfil();
            usuarios = per.cargarTodosPerfiles();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void cargarPrestatgerias() {
        try {
            ControladorPrestatgeria prest = new ControladorPrestatgeria();
            prestatgerias = prest.cargarTodasPrestatgeries(perfilActual.getUsuari());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

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

    public ControladorPerfil obtenerPerfil(String usuario) {
        try {
            ControladorPerfilAux.cargarPerfil(usuario);
            return ControladorPerfilAux;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

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

    public void buscarPerfiles(String usuario) {
        try {
            ControladorPerfilAux.buscarPerfil(usuario);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Presentacion_Main());
    }

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

    public void borrarPrestatgerias(String usuario) {
        try {
            ControladorPrestatgeria.eliminarPrestatgerias(usuario);
            prestatgerias.clear();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void editarnom_UsuariPrestatgeria(String nombre, String nuevoNombre) {
        try {
            ControladorPrestatgerias.editarNombreUsuariPrestatgeria(nombre, nuevoNombre);
            prestatgerias.remove(nombre);
            prestatgerias.add(nuevoNombre);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void eliminarPrestatgeria(String nombre) {
        try {
            ControladorPrestatgeria.eliminarPrestatgeria(perfilActual.getUsuari(), nombre);
            prestatgerias.remove(nombre);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buscarPrestatgerias(String nombre) {
        ControladorPrestatgerias.buscarPrestatgerias(perfilActual.getUsuari(), nombre);

    }



    public ControladorPerfil getControladorPerfiles() {
        return ControladorPerfilAux;
    }
    public ControladorPrestatgeria getControladorPrestatgerias() {
        return ControladorPrestatgerias;
    }

    public String get_usuari_perfil() {
        if (ControladorPerfilAux != null && ControladorPerfilAux.getPerfil() != null) {
            return ControladorPerfilAux.getUsuari();
        }
        return null;  // Si no hay perfil cargado, retorna null
    }

    public String get_contraseña_perfil() {
        if (ControladorPerfilAux != null && ControladorPerfilAux.getPerfil() != null) {
            return ControladorPerfilAux.getContrasenya();
        }
        return null;
    }

    public void set_contraseña_perfil(String contra) {
        if (ControladorPerfilAux != null && ControladorPerfilAux.getPerfil() != null) {
            ControladorPerfilAux.setContrasenya(contra);
        }
    }

    public void set_usuari_perfil(String usuari) {
        if (ControladorPerfilAux != null && ControladorPerfilAux.getPerfil() != null) {
            ControladorPerfilAux.setUsuari(usuari);
        }
    }

      public ArrayList<String> get_productes(){
        return controladorDistribucio.getProductes();
    }

   public void intercambiar_productes(int index1, int index2){
        controladorDistribucio.intercambiar_productes(index1,index2);
        }

    public void set_precio_producto(String seleccion, double nuevoPrecio){
        controladorDistribucio.set_precio_producto(seleccion, nuevoPrecio);
    }

    public void set_cantidad_producto(String seleccion, int nuevaCantidad){
        controladorDistribucio.set_cantidad_producto(seleccion, nuevaCantidad);
    }

    public boolean es_buit(){
        return getUsuarios().isEmpty();
    }

    public boolean exists_prestatgeria(String nombre){
        return getPrestatgerias().contains(nombre);
    }

    public boolean exists_producte(String nombre){
        return getControladorDistribucio().existeix_producte(nombre);
    }

    public void eliminaProducte(String seleccion){
        controladorDistribucio.eliminaProducte(seleccion);
    }

    public ArrayList<String> get_similituds_distribucio (String producto){
        return controladorDistribucio.getSimilitudsProducte(producto);
    }

    public boolean teProductesColocats() {
        return controladorDistribucio.getProductesColocats().isEmpty();
    }

    public boolean afegeix_producte(String nombre, String marca, double precio, int cantidad, int pos, int altura){
        controladorDistribucio.afegeix_producte(nombre, marca, precio, cantidad,pos, cantidad);
        return true;
    }

    public int get_posicio_final() {
        return controladorDistribucio.getProductesColocats().getLast().getPos() + 1;
    }

    public String[][] getMatrizDistribucion(){
        return controladorDistribucio.getMatrizDistribucion();
    }

    public boolean existeix_controlador_perfil() {
       return ControladorPerfilAux.getPerfil() != null;
    }
}
