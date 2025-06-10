/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package proyectologinzapateria;

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import libreriaconexion.ConexionMySQL;

/**
 *
 * @author jpobl
 */
public class EdicionProductosInterno extends javax.swing.JInternalFrame {

    private File imagenSeleccionada;
    public EdicionProductosInterno() {
        initComponents();
        this.setSize(new Dimension(800, 610));
        this.setTitle("Edición Productos");
    }

    
    private void mostrarImagenSeleccionada() {
        int fila = tablaCategoria.getSelectedRow();
        if (fila == -1) {
            return;
        }

        int idProducto = (int) tablaCategoria.getValueAt(fila, 0);

        String sql = "SELECT imagen FROM productos WHERE id = ?";

        try {
            PreparedStatement ps = ConexionMySQL.getConexion().prepareStatement(sql);
            ps.setInt(1, idProducto);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String rutaImagen = rs.getString("imagen");
                if (rutaImagen != null && !rutaImagen.isEmpty()) {
                    ImageIcon icono = new ImageIcon(rutaImagen);
                    Image imagen = icono.getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_SMOOTH);
                    lblImagen.setIcon(new ImageIcon(imagen));
                } else {
                    lblImagen.setIcon(null); // No hay imagen
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener imagen: " + e.getMessage());
        }
    }


   private void cargarProductosPorCategoria(String categoria) {
        
        if(!existeTabla("productos")){
            JOptionPane.showMessageDialog(this, "No hay tabla de PRODUCTOS");
        }
        
        boolean conectado = ConexionMySQL.conectar("3306", "zapateria", "root", "71b748ad9b");

        if (!conectado) {
            JOptionPane.showMessageDialog(this, "No se pudo conectar con la base de datos.");
            return;
        }
             
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("ID");
            modelo.addColumn("Nombre");
            modelo.addColumn("Cantidad");
            modelo.addColumn("Precio");
            
            try {
                
                String sql = "SELECT id, nombre, cantidad, precio FROM productos WHERE categoria = ?";
                PreparedStatement ps = ConexionMySQL.getConexion().prepareStatement(sql);
                ps.setString(1, categoria);
                ResultSet rs = ps.executeQuery();

                
                while (rs.next()) {
                    Object [] fila = new Object [4];
                    fila[0] = rs.getInt("id");
                    fila[1] = rs.getString("nombre");
                    fila[2] = rs.getString("cantidad");
                    fila[3] = rs.getString("precio");
                    
                    modelo.addRow(fila);
                }

                
                tablaCategoria.setModel(modelo);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al cargar productos: " + e.getMessage());
            }
    }

    private boolean existeTabla(String nombreTabla) {
        boolean conectado = ConexionMySQL.conectar("3306", "zapateria", "root", "71b748ad9b");
        if (!conectado) {
            JOptionPane.showMessageDialog(this, "No se pudo conectar con la base de datos.");
            return false;
        }

        try {
            String baseDatos = "zapateria";
            String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = ? AND table_name = ?";
            PreparedStatement ps = ConexionMySQL.getConexion().prepareStatement(sql);
            ps.setString(1, baseDatos);
            ps.setString(2, nombreTabla);
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.out.println("Error al comprobar si existe la tabla: " + e.getMessage());
        }
        return false;
    }

    private void seleccionarImagen(){

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imágenes", "jpg", "png", "jpeg");
        fileChooser.setFileFilter(filtro);

        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            imagenSeleccionada = fileChooser.getSelectedFile();
            ImageIcon icon = new ImageIcon(imagenSeleccionada.getAbsolutePath());
            Image img = icon.getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_SMOOTH);
            lblImagen.setIcon(new ImageIcon(img));


        }
    }

    private void actualizarProducto() {
        
        int filaSeleccionada = tablaCategoria.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para editar.");
            return;
        }

        int id = (int) tablaCategoria.getValueAt(filaSeleccionada, 0);
        String nuevoNombre = txtNombre.getText();
        int nuevaCantidad;
        double nuevoPrecio;


        try {
            nuevaCantidad = Integer.parseInt(txtCantidad.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Cantidad inválida.");
            return;
        }


        try {
            nuevoPrecio = Double.parseDouble(txtPrecio.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Precio inválido.");
            return;
        }

        try {
            Connection conn = ConexionMySQL.getConexion();
            String sql;

            PreparedStatement ps;

            if (imagenSeleccionada != null) {

                sql = "UPDATE productos SET nombre = ?, cantidad = ?, precio = ?, imagen = ? WHERE id = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, nuevoNombre);
                ps.setInt(2, nuevaCantidad);
                ps.setDouble(3, nuevoPrecio);
                ps.setString(4, imagenSeleccionada.getAbsolutePath());
                ps.setInt(5, id);
            } else {

                sql = "UPDATE productos SET nombre = ?, cantidad = ?, precio = ? WHERE id = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, nuevoNombre);
                ps.setInt(2, nuevaCantidad);
                ps.setDouble(3, nuevoPrecio);
                ps.setInt(4, id);
            }

            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Producto actualizado.");
            cargarProductosPorCategoria((String) cmbCategoria.getSelectedItem());
            imagenSeleccionada = null; 

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + e.getMessage());
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCategoria = new javax.swing.JTable();
        cmbCategoria = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        lblImagen1 = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JButton();
        txtPrecio = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        btnImagen = new javax.swing.JButton();
        lblImagen = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        btnEliminar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaCategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "NOMBRE", "CANTIDAD", "PRECIO"
            }
        ));
        tablaCategoria.setToolTipText("");
        tablaCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCategoriaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaCategoria);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 20, 521, 282));

        cmbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CALZADO DEPORTIVO", "SANDALIAS", "ZAPATOS", "ZAPATILLAS", "PANTUFLAS", " " }));
        cmbCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCategoriaActionPerformed(evt);
            }
        });
        jPanel1.add(cmbCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 70, 170, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CATEGORIAS:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 20, 170, 32));

        lblImagen1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(lblImagen1, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 118, 170, 130));

        btnActualizar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 290, 170, -1));
        jPanel1.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 475, 150, -1));
        jPanel1.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 435, 150, -1));
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 395, 150, -1));

        btnImagen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnImagen.setText("SELECCIONAR IMAGEN");
        btnImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImagenActionPerformed(evt);
            }
        });
        jPanel1.add(btnImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 518, -1, 30));

        lblImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(348, 395, 180, 150));

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(558, 465, 150, 30));

        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGuardar.setText(" GUARDAR CAMBIOS");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(558, 425, -1, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("CAMBIAR IMAGEN:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 525, 130, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("NUEVO PRECIO:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 475, 103, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("NUEVA CANTIDAD:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 435, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("NUEVO NOMBRE:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 395, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("EDICION DE PRODUCTOS");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 345, -1, -1));

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 797, 13));

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 260, 170, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenesInterfaz/Fondo.jpg"))); // NOI18N
        jLabel7.setText("jLabel3");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 580));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCategoriaMouseClicked

        mostrarImagenSeleccionada();

        int filaSeleccionada = tablaCategoria.getSelectedRow();
        if (filaSeleccionada != -1) {
            String nombre = tablaCategoria.getValueAt(filaSeleccionada, 1).toString();
            String cantidad = tablaCategoria.getValueAt(filaSeleccionada, 2).toString();
            String precio = tablaCategoria.getValueAt(filaSeleccionada, 3).toString();

            txtNombre.setText(nombre);
            txtCantidad.setText(cantidad);
            txtPrecio.setText(precio);
        }

    }//GEN-LAST:event_tablaCategoriaMouseClicked

    private void cmbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoriaActionPerformed
        String categoriaSeleccionada = (String) cmbCategoria.getSelectedItem();
        cargarProductosPorCategoria(categoriaSeleccionada);
    }//GEN-LAST:event_cmbCategoriaActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed

        String categoriaSeleccionada = cmbCategoria.getSelectedItem().toString();
        if (categoriaSeleccionada != null) {
            cargarProductosPorCategoria(categoriaSeleccionada);
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona una categoría.");
        }

    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de editar este producto?", "Confirmar edición", JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            actualizarProducto();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImagenActionPerformed
        seleccionarImagen();
    }//GEN-LAST:event_btnImagenActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        int filaSeleccionada = tablaCategoria.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona un producto para eliminar.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar este producto?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            int idProducto = (int) tablaCategoria.getValueAt(filaSeleccionada, 0);

            try {
                String sql = "DELETE FROM productos WHERE id = ?";
                PreparedStatement ps = ConexionMySQL.getConexion().prepareStatement(sql);
                ps.setInt(1, idProducto);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.");

                // Recargar la tabla después de eliminar
                String categoriaSeleccionada = cmbCategoria.getSelectedItem().toString();
                cargarProductosPorCategoria(categoriaSeleccionada);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar el producto: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImagen;
    private javax.swing.JComboBox<String> cmbCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblImagen1;
    private javax.swing.JTable tablaCategoria;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
