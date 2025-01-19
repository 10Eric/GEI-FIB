import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Producto implements Serializable {
    String nombre;
    String marca;
    double precio;
    int cantidad;
    private Map<Producto, Integer> similitudes;

    public Producto(String nombre, String marca, double precio, int cantidad) {
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.cantidad = cantidad;
        this.similitudes = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setSimilitud(Producto otroProducto, int valorSimilitud) {
        similitudes.put(otroProducto, valorSimilitud);  // Añadir o actualizar el valor de similitud
    }

    public int getSimilitud(Producto otroProducto) {
        return similitudes.getOrDefault(otroProducto, 0);  // Retorna 0 si no hay similitud definida
    }

    public Map<Producto, Integer> getSimilitudes() {
        return similitudes;
    }
}
