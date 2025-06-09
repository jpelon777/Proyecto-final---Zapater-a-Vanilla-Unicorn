/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectologinzapateria;

//import java.awt.Dimension;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

/**
 *
 * @author jpobl
 */
public class MenuPrincipal extends javax.swing.JFrame {
    
    public static JDesktopPane jDesktopPane_menu;
    
    public MenuPrincipal() {
        initComponents();
          
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setTitle("VANILLA UNICORN");
        jDesktopPane_menu = new JDesktopPane();
        
        this.setContentPane(jDesktopPane_menu);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelFondo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        Usuarios = new javax.swing.JMenu();
        menuRegistrar = new javax.swing.JMenuItem();
        menuModificar = new javax.swing.JMenuItem();
        Productos = new javax.swing.JMenu();
        menuAgregar = new javax.swing.JMenuItem();
        menuEditar = new javax.swing.JMenuItem();
        CerrarSesion = new javax.swing.JMenu();
        menuCerrarSesion = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenesInterfaz/Fondo_degradado.jpg"))); // NOI18N
        labelFondo.setMaximumSize(new java.awt.Dimension(2256, 1506));
        labelFondo.setMinimumSize(new java.awt.Dimension(2256, 1506));
        labelFondo.setPreferredSize(new java.awt.Dimension(2256, 1506));

        jMenuBar1.setBackground(new java.awt.Color(30, 144, 255));
        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Usuarios.setBackground(new java.awt.Color(30, 144, 255));
        Usuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenesInterfaz/usuario.png"))); // NOI18N
        Usuarios.setText("USUARIOS");
        Usuarios.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Usuarios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Usuarios.setMargin(new java.awt.Insets(6, 8, 6, 8));
        Usuarios.setPreferredSize(new java.awt.Dimension(150, 50));

        menuRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenesInterfaz/nuevo-cliente.png"))); // NOI18N
        menuRegistrar.setText("Registrar");
        menuRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuRegistrar.setPreferredSize(new java.awt.Dimension(120, 30));
        menuRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRegistrarActionPerformed(evt);
            }
        });
        Usuarios.add(menuRegistrar);

        menuModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenesInterfaz/configuraciones.png"))); // NOI18N
        menuModificar.setText("Modificar");
        menuModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuModificar.setPreferredSize(new java.awt.Dimension(120, 30));
        menuModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuModificarActionPerformed(evt);
            }
        });
        Usuarios.add(menuModificar);

        jMenuBar1.add(Usuarios);

        Productos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenesInterfaz/producto.png"))); // NOI18N
        Productos.setText("PRODUCTOS");
        Productos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Productos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Productos.setPreferredSize(new java.awt.Dimension(150, 50));

        menuAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenesInterfaz/anadir.png"))); // NOI18N
        menuAgregar.setText("Agregar");
        menuAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuAgregar.setPreferredSize(new java.awt.Dimension(120, 30));
        menuAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAgregarActionPerformed(evt);
            }
        });
        Productos.add(menuAgregar);

        menuEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenesInterfaz/configuraciones.png"))); // NOI18N
        menuEditar.setText("Editar");
        menuEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuEditar.setPreferredSize(new java.awt.Dimension(120, 30));
        menuEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEditarActionPerformed(evt);
            }
        });
        Productos.add(menuEditar);

        jMenuBar1.add(Productos);

        CerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenesInterfaz/cerrar-sesion.png"))); // NOI18N
        CerrarSesion.setText("CERRAR SESION");
        CerrarSesion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        CerrarSesion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CerrarSesion.setPreferredSize(new java.awt.Dimension(150, 50));

        menuCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenesInterfaz/cerrar-sesion.png"))); // NOI18N
        menuCerrarSesion.setText("Cerrar sesión");
        menuCerrarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuCerrarSesion.setPreferredSize(new java.awt.Dimension(120, 30));
        menuCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCerrarSesionActionPerformed(evt);
            }
        });
        CerrarSesion.add(menuCerrarSesion);

        jMenuBar1.add(CerrarSesion);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 1692, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(labelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 1131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRegistrarActionPerformed
        
        RegistroInterno registrointerno = new RegistroInterno();
        jDesktopPane_menu.add(registrointerno);
        registrointerno.setVisible(true);
        //registro.setVisible(true);
        
    }//GEN-LAST:event_menuRegistrarActionPerformed

    private void menuModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuModificarActionPerformed
        
        EdicionUsuariosInterno edicionusuariosinterno = new EdicionUsuariosInterno();
        jDesktopPane_menu.add(edicionusuariosinterno);
        edicionusuariosinterno.setVisible(true);
        
        //edicionproductos.setVisible(true);
        
    }//GEN-LAST:event_menuModificarActionPerformed

    private void menuAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAgregarActionPerformed
        
        RegistroProductosInterno registroproductosinterno = new RegistroProductosInterno();
        jDesktopPane_menu.add(registroproductosinterno);
        registroproductosinterno.setVisible(true);
        
        //registroproductos.setVisible(true);
        
    }//GEN-LAST:event_menuAgregarActionPerformed

    private void menuEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEditarActionPerformed
        
        EdicionProductosInterno edicionproductosinterno = new EdicionProductosInterno();
        jDesktopPane_menu.add(edicionproductosinterno);
        edicionproductosinterno.setVisible(true);
        
        //edicionproductos.setVisible(true);
        
    }//GEN-LAST:event_menuEditarActionPerformed

    private void menuCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCerrarSesionActionPerformed
        
        Login login = new Login();
        login.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_menuCerrarSesionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu CerrarSesion;
    private javax.swing.JMenu Productos;
    private javax.swing.JMenu Usuarios;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel labelFondo;
    private javax.swing.JMenuItem menuAgregar;
    private javax.swing.JMenuItem menuCerrarSesion;
    private javax.swing.JMenuItem menuEditar;
    private javax.swing.JMenuItem menuModificar;
    private javax.swing.JMenuItem menuRegistrar;
    // End of variables declaration//GEN-END:variables
}
