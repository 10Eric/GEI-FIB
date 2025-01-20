package edu.upc.prop.clusterxx.controladores_presentacion;
import edu.upc.prop.clusterxx.controladores.ControladorPerfil;
import javax.swing.*;
import java.awt.*;

public class MostrarEditarPerfilView {
    private JPanel panel;
    private Presentacion_Main controller;

    public MostrarEditarPerfilView(Presentacion_Main controller) {
        this.controller = controller;
        panel = new JPanel(new GridLayout(3, 2, 10, 10));

        JTextField usuarioField = new JTextField(controller.get_usuari_perfil());
        usuarioField.setHorizontalAlignment(SwingConstants.CENTER);
        JPasswordField contraseñaField = new JPasswordField(controller.get_contraseña_perfil());
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

            if (!nuevoUsuario.equals(controller.get_usuari_perfil()) && controller.getUsuarios().contains(nuevoUsuario)) {
                JOptionPane.showMessageDialog(controller, "El nombre de usuario ya existe.");
            } else {
                String usuario = controller.get_usuari_perfil();
                controller.removeUsuarios(controller.get_usuari_perfil());
                controller.set_usuari_perfil(nuevoUsuario);
                controller.set_contraseña_perfil(nuevaContraseña);
                controller.creaPerfil(nuevoUsuario, nuevaContraseña);
                controller.editarnom_UsuariPrestatgeria(usuario, nuevoUsuario);
                controller.eliminarPerfil(usuario);
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
