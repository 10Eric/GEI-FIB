package edu.upc.prop.clusterxx.controladores;
import edu.upc.prop.clusterxx.clases_dominio.Distribucio;
import edu.upc.prop.clusterxx.clases_dominio.Prestatgeria;



import java.io.IOException;
import java.util.Set;
import edu.upc.prop.clusterxx.controladores_persistencia.ControladorPersistenciaPrestatgeria;
import java.io.Serializable;

public class ControladorPrestatgeria implements Serializable {
    private Prestatgeria prestatgeria;
    private static final ControladorPersistenciaPrestatgeria persistencia = new ControladorPersistenciaPrestatgeria();

    public ControladorPrestatgeria(Prestatgeria prestatgeria) {
        this.prestatgeria = prestatgeria;
    }
    public ControladorPrestatgeria(){}

    public String getNom() {
        return prestatgeria.getNom();
    }

    public void setNom(String nom) {
        prestatgeria.setNom(nom);
    }

    public String getNombreDeproductes() {
        return Integer.toString(prestatgeria.getAltura());
    }

    public Integer getAltura() {
        return prestatgeria.getAltura();
    }

    public void setAltura(int altura) {
        prestatgeria.setAltura(altura);
    }

    public Distribucio getDistribucion() {
        return prestatgeria.getDistribucion();
    }

    public void setDistribucion(Distribucio distribucion) {
        prestatgeria.setDistribucion(distribucion);
    }

    public void setDistribucionCtrl(ControladorDistribucio ctrl){
        prestatgeria.setDistribucion(ctrl.getDistribucion());
    }

    public Prestatgeria getPrestatgeria() {
        return prestatgeria;
    }


    public void creaPrest(String nombre, int altura) {
        Prestatgeria prest = new Prestatgeria(nombre,altura);
        this.prestatgeria = prest;
    }

    public void crearPres2(ControladorPrestatgeria prest){
        this.prestatgeria = prest.getPrestatgeria();
    }

    public void guardarPrestatgeria(String nombreUsuario) throws IOException {
        persistencia.guardarPrestatgeria(prestatgeria, nombreUsuario);
    }

    public static ControladorPrestatgeria cargarPrestatgeria(String nombreUsuario, String nombrePrestatgeria) throws IOException, ClassNotFoundException {
        Prestatgeria prestatgeria = persistencia.getPrestatgeria(nombreUsuario, nombrePrestatgeria);
        return prestatgeria != null ? new ControladorPrestatgeria(prestatgeria) : null;
    }

    public static void eliminarPrestatgeria(String nombreUsuario, String nombrePrestatgeria) throws IOException {
        persistencia.deletePrestatgeria(nombreUsuario, nombrePrestatgeria);
    }

    public static void eliminarPrestatgerias(String nombreUsuario) throws IOException, ClassNotFoundException{
        persistencia.deletePrestatgerias(nombreUsuario);
    }

    public static Set<String> cargarTodasPrestatgeries(String usuario) throws IOException, ClassNotFoundException {
        return persistencia.getArxiusPrestatgeria(usuario);
    }

    public static void editarNombreUsuariPrestatgeria(String nombreantiguo, String nuevoNombre) throws IOException, ClassNotFoundException {
        persistencia.editar_nombre_prestatgeria(nombreantiguo, nuevoNombre);
    }

    public static void limpiarDatos() throws IOException {
        persistencia.limpiarDatos();
    }


    public void buscarPrestatgerias(String nombres, String nombre) {
        try {
            prestatgeria  = persistencia.buscarPrestatgeria(nombres, nombre);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
