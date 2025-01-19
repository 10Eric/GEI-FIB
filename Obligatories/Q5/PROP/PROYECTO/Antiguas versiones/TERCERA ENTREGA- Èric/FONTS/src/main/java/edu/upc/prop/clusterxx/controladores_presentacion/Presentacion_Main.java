package edu.upc.prop.clusterxx.controladores_presentacion;
import edu.upc.prop.clusterxx.clases_dominio.Distribucio;
import edu.upc.prop.clusterxx.clases_dominio.Prestatgeria;
import edu.upc.prop.clusterxx.clases_dominio.Perfil;
import edu.upc.prop.clusterxx.controladores.ControladorPerfil;
import edu.upc.prop.clusterxx.controladores.ControladorPrestatgeria;
import edu.upc.prop.clusterxx.controladores.ControladorDistribucio;
import edu.upc.prop.clusterxx.controladores_persistencia.ControladorPersistenciaMain;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Presentacion_Main extends JFrame {
    private static Map<String, ControladorPerfil> perfiles = new HashMap<>();
    private static ControladorPerfil perfilActual;
    private static Map<String, ControladorPrestatgeria> prestatgeria = new HashMap<>();
    private static Prestatgeria prestatgeriaActual;
    private static ControladorDistribucio controladorDistribucio;
    private static ControladorPersistenciaMain controladorPersistenciaMain = new ControladorPersistenciaMain();

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

    public Map<String, ControladorPerfil> getPerfiles() {
        return perfiles;
    }

    public ControladorPerfil getPerfilActual() {
        return perfilActual;
    }

    public void setPerfilActual(ControladorPerfil perfilActual) {
        this.perfilActual = perfilActual;
    }

    public Map<String, ControladorPrestatgeria> getPrestatgeria() {
        return prestatgeria;
    }

    public void setPrestatgeriaActual(Prestatgeria prestatgeriaActual) {
        this.prestatgeriaActual = prestatgeriaActual;
    }

    public Prestatgeria getPrestatgeriaActual() {
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

    public void mostrarEditarPerfil(ControladorPerfil controladorPerfil) {
       MostrarEditarPerfilView vista = new MostrarEditarPerfilView(this, controladorPerfil);
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
        actualizarVista(vista.getPanel());
    }

    public void mostrarFormularioConsultarSimilitudes() {
        ConsultaSimilitudesView vista = new ConsultaSimilitudesView(this);
        actualizarVista(vista.getPanel());
    }

    public void mostrarFormularioConsultarProductos() {
        ConsultarProductosView vista = new ConsultarProductosView(this);
        actualizarVista(vista.getPanel());
        mostrarMenuPrincipalDistribucio();
    }

    public void mostrarFormularioCalcularDistribucion() {
        CalculaDistribucionView vista = new CalculaDistribucionView(this);
        actualizarVista(vista.getPanel());
    }

    public void mostrarDistribucion(String[][] matriz, String titulo) {
        MostrarMatrizResultadosView vista = new MostrarMatrizResultadosView(this, matriz, titulo);
        actualizarVista(vista.getPanel());
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
        actualizarVista(vista.getPanel());
    }


    private void actualizarVista(JPanel nuevoPanel) {
        setContentPane(nuevoPanel);
        revalidate();
        repaint();
        setVisible(true);
    }

    public static void guardarDatos() {
        try {
            controladorPersistenciaMain.guardarMain(perfiles, "TERCERA_ENTREGA.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cargarDatos() {
        try {
            perfiles = controladorPersistenciaMain.cargarMain("TERCERA_ENTREGA.dat");
        } catch (IOException | ClassNotFoundException e) {
            perfiles = new HashMap<>();
        }
    }

    public static void convertirYAsignarPrestatgerias() {
        for (Prestatgeria p : perfilActual.getPrestatgerias()) {
            prestatgeria.put(p.getNom(), new ControladorPrestatgeria(p));
        }
    }

    public static void crearDistribucionInicial() {
        if (prestatgeriaActual.getDistribucion() == null) {
            String nombreDistribucion = "Distribucion Predeterminada";
            Distribucio r = new Distribucio(nombreDistribucion);
            prestatgeriaActual.setDistribucion(r);
            controladorDistribucio = new ControladorDistribucio();
            controladorDistribucio.crear_inicial(r, prestatgeriaActual);
        } else {
            controladorDistribucio = new ControladorDistribucio();
            controladorDistribucio.crear_inicial(prestatgeriaActual.getDistribucion(), prestatgeriaActual);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Presentacion_Main());
    }
}

