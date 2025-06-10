/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectologinzapateria.Clases;

/**
 *
 * @author PBA GOAT
 */

import java.util.ArrayList;
import java.util.Iterator;

public class Ticket {
    public static ArrayList<Object[]> productosCarrito = new ArrayList<>();

    public static void agregarProducto(Object[] producto) {
        productosCarrito.add(producto);
    }

    public static ArrayList<Object[]> obtenerProductos() {
        return productosCarrito;
    }
    
    
    public static void eliminarProducto(int idProducto) {
        Iterator<Object[]> iter = productosCarrito.iterator();
        while (iter.hasNext()) {
            Object[] item = iter.next();
            if ((int) item[0] == idProducto) {
                iter.remove();
                break;
            }
        }
    }
    
    public static int calcularTotal() {
    int total = 0;
    for (Object[] producto : productosCarrito) {
        int cantidad = (int) producto[2];
        int precio = (int) producto[3];
        total += cantidad * precio;
        }
    return total;
    }
}


