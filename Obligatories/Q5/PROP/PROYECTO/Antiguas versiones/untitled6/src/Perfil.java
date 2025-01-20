import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Perfil implements Serializable {
    String usuario;
    String contraseña;
    List<Distribucion> distribuciones;

    public Perfil(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.distribuciones = new ArrayList<>();
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public List<Distribucion> getDistribuciones() {
        return distribuciones;
    }

    public void añadirDistribucion(Distribucion distribucion) {
        distribuciones.add(distribucion);
    }

    public void eliminarDistribucion(Distribucion distribucion) {
        distribuciones.remove(distribucion);
    }
}