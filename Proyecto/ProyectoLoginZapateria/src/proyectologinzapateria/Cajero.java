/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectologinzapateria;
import proyectologinzapateria.Clases.Ticket;
import java.awt.Image;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import libreriaconexion.ConexionMySQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import javax.swing.ImageIcon;


/**
 *
 * @author jpobl
 */
public class Cajero extends javax.swing.JFrame {

   
    
    public Cajero() {
        initComponents();
        
    }
    
    private void actualizarCarrito() {
    String[] columnas = {"ID", "NOMBRE", "CANTIDAD","PRECIO"};
    DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

    for (Object[] producto : Ticket.obtenerProductos()) {
        modelo.addRow(producto);
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaCategorias = new javax.swing.JList<>();
        jSeparator1 = new javax.swing.JSeparator();
        btnSalir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnCarrito = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaCategoria = new javax.swing.JTable();
        lblImagen = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listaCategorias.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CATEGORIAS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        listaCategorias.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        listaCategorias.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "CALZADO DEPORTIVO", "SANDALIAS", "ZAPATOS", "ZAPATILLAS", "PANTUFLAS" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaCategorias.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaCategoriasValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listaCategorias);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 169, 171));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 15, 477));

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalir.setText("SALIR");
        btnSalir.setPreferredSize(new java.awt.Dimension(72, 30));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setText("PRODUCTOS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, 64));

        btnAgregar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAgregar.setText("AGREGAR AL CARRITO");
        btnAgregar.setPreferredSize(new java.awt.Dimension(161, 30));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 340, -1, -1));

        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.setPreferredSize(new java.awt.Dimension(105, 30));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 390, -1, -1));

        btnCarrito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenesInterfaz/carrito.png"))); // NOI18N
        btnCarrito.setText("CARRITO");
        btnCarrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarritoActionPerformed(evt);
            }
        });
        jPanel1.add(btnCarrito, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, -1, -1));

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
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCategoriaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaCategoria);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 111, -1, 182));

        lblImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(536, 331, 150, 125));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenesInterfaz/tacon3.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(301, 37, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("IMAGEN DEL PRODUCTO");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(536, 300, 150, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenesInterfaz/Fondo.jpg"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 480));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void listaCategoriasValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaCategoriasValueChanged
            if (!evt.getValueIsAdjusting()) {
                String categoriaSeleccionada = listaCategorias.getSelectedValue();
                cargarProductosPorCategoria(categoriaSeleccionada);
            }   
    }//GEN-LAST:event_listaCategoriasValueChanged

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        
            int filaSeleccionada = tablaCategoria.getSelectedRow();

               if (filaSeleccionada == -1) {
                    JOptionPane.showMessageDialog(this, "Selecciona un producto para agregar al carrito.");
                    return;
                }

                    DefaultTableModel modelo = (DefaultTableModel) tablaCategoria.getModel();

                    int idProducto = (int) modelo.getValueAt(filaSeleccionada, 0);
                    String nombre = (String) modelo.getValueAt(filaSeleccionada, 1);
                    int cantidadDisponible = Integer.parseInt(modelo.getValueAt(filaSeleccionada, 2).toString());
                    int precio=Integer.parseInt(modelo.getValueAt(filaSeleccionada,3).toString());
                    

                    if (cantidadDisponible <= 0) {
                        JOptionPane.showMessageDialog(this, "No hay suficiente cantidad disponible.");
                        return;
                    }


                int nuevaCantidad = cantidadDisponible - 1;
                modelo.setValueAt(nuevaCantidad, filaSeleccionada, 2);

                boolean conectado = ConexionMySQL.conectar("3306", "zapateria", "root", "71b748ad9b");
                if (conectado) {
                    try {
                        Connection conexion = ConexionMySQL.getConexion(); // Obtiene la conexión establecida
                        PreparedStatement ps = conexion.prepareStatement(
                            "UPDATE productos SET cantidad = ? WHERE id = ?"
                        );
                        ps.setInt(1, nuevaCantidad);
                        ps.setInt(2, idProducto);
                        ps.executeUpdate();
                        ps.close();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(this, "Error al actualizar la base de datos: " + e.getMessage());
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo conectar a la base de datos.");
                    return;
                }

                // Verificar si ya está en el carrito
                boolean encontrado = false;
                for (Object[] item : Ticket.obtenerProductos()) {
                    if ((int) item[0] == idProducto) {
                        
                        int cantidadEnCarrito = Integer.parseInt(item[2].toString());
                        item[2] = cantidadEnCarrito + 1;
                        encontrado = true;
                        break;
                    }
                }

                    if (!encontrado) {
                        
                        Object[] producto = new Object[]{idProducto, nombre, 1,precio};
                        Ticket.agregarProducto(producto);
                    }

                    actualizarCarrito();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        String categoriaSeleccionada = listaCategorias.getSelectedValue();
        if (categoriaSeleccionada != null) {
            cargarProductosPorCategoria(categoriaSeleccionada);
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona una categoría.");
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        Login login = new Login();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnCarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarritoActionPerformed
        Carrito ventana = new Carrito();
        ventana.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCarritoActionPerformed

    private void tablaCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCategoriaMouseClicked
        
        mostrarImagenSeleccionada();

    }//GEN-LAST:event_tablaCategoriaMouseClicked

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
            java.util.logging.Logger.getLogger(Cajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cajero().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCarrito;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JList<String> listaCategorias;
    private javax.swing.JTable tablaCategoria;
    // End of variables declaration//GEN-END:variables
}
