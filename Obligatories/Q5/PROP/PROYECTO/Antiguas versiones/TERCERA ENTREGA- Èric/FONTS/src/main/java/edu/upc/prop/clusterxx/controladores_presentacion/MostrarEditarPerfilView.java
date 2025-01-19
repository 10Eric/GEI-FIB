package edu.upc.prop.clusterxx.controladores_presentacion;
import edu.upc.prop.clusterxx.controladores.ControladorPerfil;
import javax.swing.*;
import java.awt.*;

public class MostrarEditarPerfilView {
    private JPanel panel;
    private Presentacion_Main controller;
    private ControladorPerfil perfil;

    public MostrarEditarPerfilView(Presentacion_Main controller, ControladorPerfil perfil) {
        this.controller = controller;
        panel = new JPanel(new GridLayout(3, 2, 10, 10));

        JTextField usuarioField = new JTextField(perfil.getUsuari());
        usuarioField.setHorizontalAlignment(SwingConstants.CENTER);
        JPasswordField contraseñaField = new JPasswordField(perfil.getContrasenya());
        contraseñaField.setHorizontalAlignment(SwingConstants.CENTER);
        JButton guardarBtn = new JButton("Guardar");
        JButton atrasBtn = new JButton("Atrás");

        panel.add(new JLabel("Usuario:", SwingConstants.CENTER));
        panel.add(usuarioField);
        panel.add(new JLabel("Contraseña:", SwingConstants.CENTER));
        panel.add(contraseñaField);
        panel.add(guardarBtn);
        panel.add(atrasBtn);

        guardarBtn.addActionListener(e -> {
            String nuevoUsuario = usuarioField.getText();
            String nuevaContraseña = new String(contraseñaField.getPassword());

            if (!nuevoUsuario.equals(perfil.getUsuari()) && controller.getPerfiles().containsKey(nuevoUsuario)) {
                JOptionPane.showMessageDialog(controller, "El nombre de usuario ya existe.");
            } else {
                controller.getPerfiles().remove(perfil.getUsuari());
                perfil.setUsuari(nuevoUsuario);
                perfil.setContrasenya(nuevaContraseña);
                controller.getPerfiles().put(nuevoUsuario, perfil);
                JOptionPane.showMessageDialog(controller, "Perfil editado con éxito.");
                controller.mostrarMenuPrincipal();
            }
        });

        atrasBtn.addActionListener(e -> controller.mostrarMenuPrincipal());

    }

    public JPanel getPanel() {
        return panel;
    }
}
