import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Distribucion implements Serializable {
    String nombre;
    List<Producto> productos;

    public Distribucion(String nombre) {
        this.nombre = nombre;
        this.productos = new ArrayList<>();
    }

    public void añadirProducto(Producto producto) {
        productos.add(producto);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public String getNombre() {
        return nombre;
    }

    public int consultarSimilitud(Producto p1, Producto p2) {
        return p1.getSimilitud(p2);
    }
    public Producto getProductoPorNombre(String nombre) {
        // Recorremos la lista de productos para buscar el producto por su nombre
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto; // Si encontramos el producto, lo devolvemos
            }
        }
        // Si no se encuentra el producto, devolvemos null o puedes lanzar una excepción
        return null;
    }
}
