/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package proyectologinzapateria;

import PaquetePrincipal.SonidoTecla;
import java.awt.Dimension;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import libreriaconexion.ConexionMySQL;
import proyectologinzapateria.Clases.CrearPDF;
import proyectologinzapateria.Clases.Encoder;
import proyectologinzapateria.Clases.EnviarCorreo;
import proyectologinzapateria.Clases.Validacion;

/**
 *
 * @author jpobl
 */
public class RegistroInterno extends javax.swing.JInternalFrame {

    /**
     * Creates new form RegistoInterno
     */
    public RegistroInterno() {
        initComponents();
        this.setSize(new Dimension(725,535));
        this.setTitle("Nuevo Usuario");
        
        configurarSonidos();
        errorNombres.setVisible(false);
        errorApellidos.setVisible(false);
        errorCorreo.setVisible(false);
        errorContraseña.setVisible(false);
        errorContraseña2.setVisible(false);
        errorContraseña3.setVisible(false);
        errorConfirmarCon.setVisible(false);
        errorRol.setVisible(false);
        errorLlave.setVisible(false);
        
    }
    
    private void configurarSonidos(){
        
        SonidoTecla.configurarSonidoGeneral(txtConfirmarContraseña, "bubble");
    }

    
    private boolean existeTabla(String nombreTabla) {
        
        boolean conectado = ConexionMySQL.conectar("3306", "zapateria", "root", "71b748ad9b");
            
            if (!conectado) {
            JOptionPane.showMessageDialog(this, "No se pudo conectar con la base de datos.");
            return false;
            }
        
            try {
                    String baseDatos = "zapateria";

                    String sql = "SELECT COUNT(*) FROM information_schema.tables " +
                                 "WHERE table_schema = ? AND table_name = ?";

                    try (PreparedStatement ps = ConexionMySQL.getConexion().prepareStatement(sql)) {
                        ps.setString(1, baseDatos);
                        ps.setString(2, nombreTabla);

                        ResultSet rs = ps.executeQuery();

                        if (rs.next()) {
                            int count = rs.getInt(1);
                            return count > 0;
                        }
                    }

            } catch (SQLException e) {
                    System.out.println("Error al comprobar si existe la tabla: " + e.getMessage());
              }

            return false;    
    }
    
    public void registrarEnBD(String nombre, String apellidos, String correo, String contrasena, String rol, String mensaje) {
        String sql = "INSERT INTO usuarios (Nombre, Apellidos, Correo, Contraseña, Rol) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionMySQL.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setString(2, apellidos);
            stmt.setString(3, correo);
            stmt.setString(4, contrasena);
            stmt.setString(5, rol);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, mensaje);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar: " + e.getMessage());
        }
    }
    
    
    protected boolean registrarUsuario() {
        
        String nombre = txtNombres.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String correo = txtCorreo.getText().trim().toLowerCase();
        String contrasena = txtContraseña.getText();
        String conEncriptada = Encoder.encriptar(contrasena);
        String confirmar = txtConfirmarContraseña.getText();
        String llaveAcceso = txtLlave.getText().trim();
        String rol = comboRol.getSelectedItem().toString();
       
        if (!Validacion.validarCamposLlenos(nombre, apellidos, correo, contrasena, confirmar)) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return false;
        }

        if (!Validacion.validarLetras(nombre) ) {
            errorNombres.setVisible(true);
            return false;
        }
        
        if (!Validacion.validarLetras(apellidos) ) {
            errorApellidos.setVisible(true);
            return false;
        }

        if (!Validacion.validarCorreo(correo)) {
            errorCorreo.setVisible(true);
            return false;
        }
        
        if (Validacion.correoExiste(correo)) {
            JOptionPane.showMessageDialog(this, "Este correo ya está registrado.");
            return false;
        }

        if (!Validacion.validarContraseña(contrasena)) {
            errorContraseña.setVisible(true);
            errorContraseña2.setVisible(true);
            errorContraseña3.setVisible(true);
            return false;
        }

        if (!contrasena.equals(confirmar)) {
            errorConfirmarCon.setVisible(true);
            return false;
        }

        if(!existeTabla("usuarios")){
            ConexionMySQL.crearTabla("usuarios","Nombre,Apellidos,Correo,Contraseña,Rol"); //Aqui añadimos el rol, que puede ser usuario normal o administrador
        }
        
        if(rol.equalsIgnoreCase("Cajero")){
            if(!Validacion.validarLlave(llaveAcceso)){
                
                registrarEnBD(nombre, apellidos, correo, conEncriptada, rol, "Cajero registrado exitosamente");
                return true;
            }else{
                errorRol.setVisible(true);
                return false;
            }
        } else if(rol.equalsIgnoreCase("Administrador")){
            if(Validacion.validarLlave(llaveAcceso)){
                registrarEnBD(nombre, apellidos, correo, conEncriptada, rol, "Administrador registrado exitosamente");
                return true;
            }else{
                errorLlave.setVisible(true);
                return false;
            }
        }else{
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un ROL");
            return false;
        }
    
    }

    private void limpiarCampos() {
        
        txtNombres.setText("");
        txtApellidos.setText("");
        txtContraseña.setText("");
        txtConfirmarContraseña.setText("");
        comboRol.setSelectedIndex(2);
       
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtLlave = new javax.swing.JTextField();
        txtContraseña = new javax.swing.JTextField();
        txtConfirmarContraseña = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        txtCorreo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        captchaPanel = new Captcha_Componentes.CaptchaPanel();
        errorNombres = new javax.swing.JLabel();
        errorApellidos = new javax.swing.JLabel();
        errorCorreo = new javax.swing.JLabel();
        errorContraseña3 = new javax.swing.JLabel();
        errorConfirmarCon = new javax.swing.JLabel();
        errorContraseña = new javax.swing.JLabel();
        errorContraseña2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        comboRol = new javax.swing.JComboBox<>();
        errorRol = new javax.swing.JLabel();
        errorLlave = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setPreferredSize(new java.awt.Dimension(100, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("CORREO");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 70, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("CONTRASEÑA");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, -1, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("CONFIRMAR CONTRASEÑA");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 270, 170, 20));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("NOMBRE(S)");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("APELLIDOS");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, -1, -1));
        jPanel1.add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 180, 30));
        jPanel1.add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 240, 30));
        jPanel1.add(txtLlave, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 180, 110, 30));
        jPanel1.add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 140, 30));
        jPanel1.add(txtConfirmarContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 290, 130, 30));

        btnAceptar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnAceptar.setText("ACEPTAR");
        btnAceptar.setPreferredSize(new java.awt.Dimension(90, 30));
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, 100, -1));

        btnCancelar.setText("CANCELAR");
        btnCancelar.setPreferredSize(new java.awt.Dimension(90, 30));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 100, -1));

        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.setPreferredSize(new java.awt.Dimension(90, 30));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 420, -1, -1));
        jPanel1.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 260, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("LLAVE ADMINISTRADOR");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 160, 160, -1));
        jPanel1.add(captchaPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 290, 160, 120));

        errorNombres.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        errorNombres.setForeground(new java.awt.Color(255, 0, 0));
        errorNombres.setText("¡Debe contener solo letras!");
        jPanel1.add(errorNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 180, 20));

        errorApellidos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        errorApellidos.setForeground(new java.awt.Color(255, 0, 0));
        errorApellidos.setText("¡Debe contener solo letras!");
        jPanel1.add(errorApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 240, 20));

        errorCorreo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        errorCorreo.setForeground(new java.awt.Color(255, 0, 0));
        errorCorreo.setText("¡Dominio invalido!");
        jPanel1.add(errorCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 260, 20));

        errorContraseña3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        errorContraseña3.setForeground(new java.awt.Color(255, 0, 0));
        errorContraseña3.setText("¡Debe contener al menos un número!");
        errorContraseña3.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel1.add(errorContraseña3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 260, 20));

        errorConfirmarCon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        errorConfirmarCon.setForeground(new java.awt.Color(255, 0, 0));
        errorConfirmarCon.setText("¡Las contraseñas no coinciden!");
        jPanel1.add(errorConfirmarCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 320, 220, -1));

        errorContraseña.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        errorContraseña.setForeground(new java.awt.Color(255, 0, 0));
        errorContraseña.setText("¡Deben ser 8 caracteres!");
        errorContraseña.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel1.add(errorContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 180, 20));

        errorContraseña2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        errorContraseña2.setForeground(new java.awt.Color(255, 0, 0));
        errorContraseña2.setText("¡Debe contener al menos una mayúscula!");
        errorContraseña2.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel1.add(errorContraseña2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 290, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("ROL");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, 70, -1));

        comboRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Cajero", " " }));
        comboRol.setSelectedIndex(2);
        comboRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboRolActionPerformed(evt);
            }
        });
        jPanel1.add(comboRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, 140, 30));

        errorRol.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        errorRol.setForeground(new java.awt.Color(255, 0, 0));
        errorRol.setText("¡Este rol no necesita una llave!");
        jPanel1.add(errorRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, 240, 20));

        errorLlave.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        errorLlave.setForeground(new java.awt.Color(255, 0, 0));
        errorLlave.setText("¡Este rol necesita una llave!");
        jPanel1.add(errorLlave, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, 240, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenesInterfaz/Fondo.jpg"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed

        /** Aqui debemos de añadir un if, para comprobar el captcha
        // Si el captcha es correcto hace el registro
        **/// Si no, mostrara un mensaje diciendo que lo vuelva a intentar

        if(captchaPanel.verificarCaptcha()){ //-> Modificar el codigo para que aparte de ser booleno, el metodo sea de acceso publico 😡
            //captchaVerifiado, sera un booleano, si es true hara el registro

            if(registrarUsuario()){

                String nombre = txtNombres.getText().trim();
                String apellidos = txtApellidos.getText().trim();
                String correo = txtCorreo.getText().trim().toLowerCase();
                String contrasena = txtContraseña.getText();

                
                //tacon izq
                URL urlIZQ = getClass().getResource("/imagenesInterfaz/tacon.png");
                //tacon der
                URL urlDER = getClass().getResource("/imagenesInterfaz/nuevo-cliente.png");
                
                String rutaIzquierda = urlIZQ.toString();
                String rutaDerecha = urlDER.toString();
                
                
                String archivoPDF = CrearPDF.generarPDF("credenciales.pdf", nombre, apellidos, correo, contrasena, rutaIzquierda, rutaDerecha);
                EnviarCorreo.enviarCorreoConPDF( correo, "Registro Nuevo Usuario", archivoPDF);
                
            }    

        } else{
            JOptionPane.showMessageDialog(this, "Verifica el captcha y vuelva a intentarlo 👍");
        }

    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        this.limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void comboRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboRolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboRolActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnLimpiar;
    private Captcha_Componentes.CaptchaPanel captchaPanel;
    private javax.swing.JComboBox<String> comboRol;
    private javax.swing.JLabel errorApellidos;
    private javax.swing.JLabel errorConfirmarCon;
    private javax.swing.JLabel errorContraseña;
    private javax.swing.JLabel errorContraseña2;
    private javax.swing.JLabel errorContraseña3;
    private javax.swing.JLabel errorCorreo;
    private javax.swing.JLabel errorLlave;
    private javax.swing.JLabel errorNombres;
    private javax.swing.JLabel errorRol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtConfirmarContraseña;
    private javax.swing.JTextField txtContraseña;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtLlave;
    private javax.swing.JTextField txtNombres;
    // End of variables declaration//GEN-END:variables
}
