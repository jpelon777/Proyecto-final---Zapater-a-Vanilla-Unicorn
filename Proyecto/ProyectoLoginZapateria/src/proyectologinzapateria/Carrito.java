/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectologinzapateria;

import proyectologinzapateria.Clases.Ticket;
import proyectologinzapateria.Clases.GenerarTicket;
import java.awt.Desktop;
import java.awt.Image;
import java.io.File;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.List;
import libreriaconexion.ConexionMySQL;


/**
 *
 * @author jpobl
 */
public class Carrito extends javax.swing.JFrame {

   private DefaultTableModel modeloTabla;
    
    public Carrito() {
        initComponents();
        actualizarCarrito();   
    }
    
     
    public void cargarCarrito() {
        modeloTabla.setRowCount(0); 
        List<Object[]> productos = Ticket.obtenerProductos(); 

        for (Object[] producto : productos) {
            modeloTabla.addRow(producto);
        }
    }
    
    
    private void actualizarCarrito() {
    String[] columnas = {"ID", "NOMBRE", "CANTIDAD", "PRECIO", "SUBTOTAL"};
    DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

    int total = 0;

    for (Object[] producto : Ticket.obtenerProductos()) {
        int id = (int) producto[0];
        String nombre = (String) producto[1];
        int cantidad = (int) producto[2];
        int precio = (int) producto[3];
        int subtotal = cantidad * precio;
        total += subtotal;

        modelo.addRow(new Object[]{id, nombre, cantidad, precio, subtotal});
    }

    tablaCarrito.setModel(modelo);

   
    lblTotal.setText("  " + total);
}

    
    
    private void mostrarImagenSeleccionada() {
        int fila = tablaCarrito.getSelectedRow();
        if (fila == -1) {
            return;
        }

        int idProducto = (int) tablaCarrito.getValueAt(fila, 0);

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
                    lblImagen.setIcon(null); 
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener imagen: " + e.getMessage());
        }
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        lblImagen = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCarrito = new javax.swing.JTable();
        btnTicket = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setText("CARRITO");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, -1, 27));

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminar.setText("ELIMINAR PRODUCTO");
        btnEliminar.setPreferredSize(new java.awt.Dimension(100, 30));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 160, -1));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("VOLVER");
        jButton1.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 160, -1));

        lblImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, 170, 120));

        tablaCarrito.setModel(new javax.swing.table.DefaultTableModel(
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
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
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
        tablaCarrito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCarritoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaCarrito);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, -1, 160));

        btnTicket.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTicket.setText("GENERAR TICKET");
        btnTicket.setPreferredSize(new java.awt.Dimension(100, 30));
        btnTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTicketActionPerformed(evt);
            }
        });
        jPanel1.add(btnTicket, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, 160, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel2.setText("TOTAL:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 240, -1, -1));

        lblTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, 100, 20));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenesInterfaz/carrito.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenesInterfaz/Fondo.jpg"))); // NOI18N
        jLabel3.setText("jLabel3");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 450));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
      
            int filaSeleccionada = tablaCarrito.getSelectedRow();

            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona un producto para eliminar.");
                return;
            }

            DefaultTableModel modelo = (DefaultTableModel) tablaCarrito.getModel();

            int idProducto = (int) modelo.getValueAt(filaSeleccionada, 0);
            

            boolean conectado = ConexionMySQL.conectar("3306", "zapateria", "root", "71b748ad9b");
            if (conectado) {
                try {
                    Connection conexion = ConexionMySQL.getConexion();
                    PreparedStatement ps = conexion.prepareStatement(
                        "UPDATE productos SET cantidad = cantidad + 1 WHERE id = ?"
                    );
                    ps.setInt(1, idProducto);
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


            for (Object[] producto : Ticket.obtenerProductos()) {
                if ((int) producto[0] == idProducto) {
                    int nuevaCantidad = (int) producto[2] - 1;

                    if (nuevaCantidad > 0) {
                        producto[2] = nuevaCantidad; 
                    } else {
                        Ticket.eliminarProducto(idProducto); 
                    }

                    break;
                }
            }

            actualizarCarrito(); 


    }//GEN-LAST:event_btnEliminarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
            Cajero ventana = new Cajero();
            ventana.setVisible(true);
            this.dispose(); 

    }//GEN-LAST:event_jButton1ActionPerformed

    private void tablaCarritoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCarritoMouseClicked

        mostrarImagenSeleccionada();

    }//GEN-LAST:event_tablaCarritoMouseClicked

    private void btnTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTicketActionPerformed
        // Aqui tenemos que agregar una funcion que nos permita generar un ticket
        // Similar a la funcion para generar un pdf
       
        String rutaIzquierda = new File("src/imagenesInterfaz/clientes.png").getAbsolutePath();
        String rutaDerecha = new File("src/imagenesInterfaz/tacon.png").getAbsolutePath();

        
        String archivo = GenerarTicket.TicketPDF(rutaIzquierda, rutaDerecha);

        if (archivo != null) {
            int r = JOptionPane.showConfirmDialog(this, "Ticket generado correctamente.\n¿Desea abrirlo?", "Ticket", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                try {
                    Desktop.getDesktop().open(new File(archivo));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "No se pudo abrir el archivo.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error al generar el ticket.");
        }

    }//GEN-LAST:event_btnTicketActionPerformed

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
            java.util.logging.Logger.getLogger(Carrito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Carrito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Carrito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Carrito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Carrito().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnTicket;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tablaCarrito;
    // End of variables declaration//GEN-END:variables
}
