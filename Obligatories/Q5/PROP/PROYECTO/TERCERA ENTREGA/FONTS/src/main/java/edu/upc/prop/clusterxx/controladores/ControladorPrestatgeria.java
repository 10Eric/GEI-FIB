package edu.upc.prop.clusterxx.controladores;
import edu.upc.prop.clusterxx.clases_dominio.Distribucio;
import edu.upc.prop.clusterxx.clases_dominio.Prestatgeria;



import java.io.IOException;
import java.util.Set;
import edu.upc.prop.clusterxx.controladores_persistencia.ControladorPersistenciaPrestatgeria;
import java.io.Serializable;

/**
 * Esta clase controla las operaciones relacionadas con la prestatgeria.
 */
public class ControladorPrestatgeria implements Serializable {
    private Prestatgeria prestatgeria;
    private static final ControladorPersistenciaPrestatgeria persistencia = new ControladorPersistenciaPrestatgeria();

    /**
     * Constructor que inicializa el controlador con una prestatgeria.
     *
     * @param prestatgeria la prestatgeria a controlar
     */
    public ControladorPrestatgeria(Prestatgeria prestatgeria) {
        this.prestatgeria = prestatgeria;
    }

    /**
     * Constructor por defecto.
     */
    public ControladorPrestatgeria() {}

    /**
     * Devuelve el nombre de la prestatgeria.
     *
     * @return el nombre de la prestatgeria
     */
    public String getNom() {
        return prestatgeria.getNom();
    }

    /**
     * Establece el nombre de la prestatgeria.
     *
     * @param nom el nuevo nombre de la prestatgeria
     */
    public void setNom(String nom) {
        prestatgeria.setNom(nom);
    }

    /**
     * Devuelve el número de productos en la prestatgeria.
     *
     * @return el número de productos
     */
    public String getNombreDeproductes() {
        return Integer.toString(prestatgeria.getAltura());
    }

    /**
     * Devuelve la altura de la prestatgeria.
     *
     * @return la altura de la prestatgeria
     */
    public Integer getAltura() {
        return prestatgeria.getAltura();
    }

    /**
     * Establece la altura de la prestatgeria.
     *
     * @param altura la nueva altura de la prestatgeria
     */
    public void setAltura(int altura) {
        prestatgeria.setAltura(altura);
    }

    /**
     * Devuelve la distribución de la prestatgeria.
     *
     * @return la distribución de la prestatgeria
     */
    public Distribucio getDistribucion() {
        return prestatgeria.getDistribucion();
    }

    /**
     * Establece la distribución de la prestatgeria.
     *
     * @param distribucion la nueva distribución de la prestatgeria
     */
    public void setDistribucion(Distribucio distribucion) {
        prestatgeria.setDistribucion(distribucion);
    }

    /**
     * Establece la distribución de la prestatgeria utilizando un controlador de distribución.
     *
     * @param ctrl el controlador de distribución
     */
    public void setDistribucionCtrl(ControladorDistribucio ctrl) {
        prestatgeria.setDistribucion(ctrl.getDistribucion());
    }

    /**
     * Devuelve la prestatgeria.
     *
     * @return la prestatgeria
     */
    public Prestatgeria getPrestatgeria() {
        return prestatgeria;
    }

    /**
     * Crea una nueva prestatgeria con el nombre y la altura especificados.
     *
     * @param nombre el nombre de la prestatgeria
     * @param altura la altura de la prestatgeria
     */
    public void creaPrest(String nombre, int altura) {
        Prestatgeria prest = new Prestatgeria(nombre, altura);
        this.prestatgeria = prest;
    }

    /**
     * Crea una nueva prestatgeria utilizando otro controlador de prestatgeria.
     *
     * @param prest el controlador de prestatgeria
     */
    public void crearPres2(ControladorPrestatgeria prest) {
        this.prestatgeria = prest.getPrestatgeria();
    }

    /**
     * Guarda la prestatgeria actual.
     *
     * @param nombreUsuario el nombre del usuario
     * @throws IOException si ocurre un error al guardar la prestatgeria
     */
    public void guardarPrestatgeria(String nombreUsuario) throws IOException {
        persistencia.guardarPrestatgeria(prestatgeria, nombreUsuario);
    }

    /**
     * Carga una prestatgeria a partir del nombre de usuario y el nombre de la prestatgeria.
     *
     * @param nombreUsuario el nombre del usuario
     * @param nombrePrestatgeria el nombre de la prestatgeria
     * @return el controlador de prestatgeria cargado
     * @throws IOException si ocurre un error al cargar la prestatgeria
     * @throws ClassNotFoundException si la clase de la prestatgeria no se encuentra
     */
    public static ControladorPrestatgeria cargarPrestatgeria(String nombreUsuario, String nombrePrestatgeria) throws IOException, ClassNotFoundException {
        Prestatgeria prestatgeria = persistencia.getPrestatgeria(nombreUsuario, nombrePrestatgeria);
        return prestatgeria != null ? new ControladorPrestatgeria(prestatgeria) : null;
    }

    /**
     * Elimina una prestatgeria a partir del nombre de usuario y el nombre de la prestatgeria.
     *
     * @param nombreUsuario el nombre del usuario
     * @param nombrePrestatgeria el nombre de la prestatgeria
     * @throws IOException si ocurre un error al eliminar la prestatgeria
     */
    public static void eliminarPrestatgeria(String nombreUsuario, String nombrePrestatgeria) throws IOException {
        persistencia.deletePrestatgeria(nombreUsuario, nombrePrestatgeria);
    }

    /**
     * Elimina todas las prestatgeries de un usuario.
     *
     * @param nombreUsuario el nombre del usuario
     * @throws IOException si ocurre un error al eliminar las prestatgeries
     * @throws ClassNotFoundException si la clase de las prestatgeries no se encuentra
     */
    public static void eliminarPrestatgerias(String nombreUsuario) throws IOException, ClassNotFoundException {
        persistencia.deletePrestatgerias(nombreUsuario);
    }

    /**
     * Carga todas las prestatgeries de un usuario.
     *
     * @param usuario el nombre del usuario
     * @return un conjunto con los nombres de todas las prestatgeries
     * @throws IOException si ocurre un error al cargar las prestatgeries
     * @throws ClassNotFoundException si la clase de las prestatgeries no se encuentra
     */
    public static Set<String> cargarTodasPrestatgeries(String usuario) throws IOException, ClassNotFoundException {
        return persistencia.getArxiusPrestatgeria(usuario);
    }

    /**
     * Edita el nombre de usuario de una prestatgeria.
     *
     * @param nombreantiguo el nombre antiguo del usuario
     * @param nuevoNombre el nuevo nombre del usuario
     * @throws IOException si ocurre un error al editar el nombre
     * @throws ClassNotFoundException si la clase de la prestatgeria no se encuentra
     */
    public static void editarNombreUsuariPrestatgeria(String nombreantiguo, String nuevoNombre) throws IOException, ClassNotFoundException {
        persistencia.editar_nombre_prestatgeria(nombreantiguo, nuevoNombre);
    }

    /**
     * Limpia todos los datos de las prestatgeries.
     *
     * @throws IOException si ocurre un error al limpiar los datos
     */
    public static void limpiarDatos() throws IOException {
        persistencia.limpiarDatos();
    }

    /**
     * Busca una prestatgeria a partir del nombre de usuario y el nombre de la prestatgeria.
     *
     * @param nombres el nombre del usuario
     * @param nombre el nombre de la prestatgeria
     */
    public void buscarPrestatgerias(String nombres, String nombre) {
        try {
            prestatgeria = persistencia.buscarPrestatgeria(nombres, nombre);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
