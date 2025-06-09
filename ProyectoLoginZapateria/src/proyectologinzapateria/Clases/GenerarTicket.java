/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectologinzapateria.Clases;

/**
 *
 * @author PBA GOAT
 */
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.draw.LineSeparator;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.awt.Color;

public class GenerarTicket {

    public static String TicketPDF(String rutaImagenIzquierda, String rutaImagenDerecha) {
        String nombreArchivo = "Ticket_de_compra_" + System.currentTimeMillis() + ".pdf";

        try {
            Document doc = new Document(PageSize.A5); // Tamaño tipo ticket
            PdfWriter.getInstance(doc, new FileOutputStream(nombreArchivo));
            doc.open();

            
            Font tituloFont = new Font(Font.HELVETICA, 16, Font.BOLD, new Color(0, 102, 204));
            Font normalFont = new Font(Font.HELVETICA, 11, Font.NORMAL);
            Font boldFont = new Font(Font.HELVETICA, 12, Font.BOLD);

          
            Locale español = new Locale("es", "ES");
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", español);
            String fechaFormateada = formato.format(new Date());

            
            PdfPTable tablaEncabezado = new PdfPTable(2);
            tablaEncabezado.setWidthPercentage(100);
            tablaEncabezado.setWidths(new float[]{1f, 1f});

            Image imgIzquierda = Image.getInstance(rutaImagenIzquierda);
            imgIzquierda.scaleToFit(60, 60);
            PdfPCell celdaIzquierda = new PdfPCell(imgIzquierda);
            celdaIzquierda.setBorder(Rectangle.NO_BORDER);
            celdaIzquierda.setHorizontalAlignment(Element.ALIGN_LEFT);

            Image imgDerecha = Image.getInstance(rutaImagenDerecha);
            imgDerecha.scaleToFit(60, 60);
            PdfPCell celdaDerecha = new PdfPCell(imgDerecha);
            celdaDerecha.setBorder(Rectangle.NO_BORDER);
            celdaDerecha.setHorizontalAlignment(Element.ALIGN_RIGHT);

            tablaEncabezado.addCell(celdaIzquierda);
            tablaEncabezado.addCell(celdaDerecha);
            doc.add(tablaEncabezado);

            doc.add(Chunk.NEWLINE);

            // Título y fecha
            Paragraph titulo = new Paragraph("Zapatería Vanilla Unicorn", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            doc.add(titulo);

            Paragraph fecha = new Paragraph("Fecha: " + fechaFormateada, normalFont);
            fecha.setAlignment(Element.ALIGN_CENTER);
            doc.add(fecha);

            doc.add(Chunk.NEWLINE);
            doc.add(new LineSeparator());
            doc.add(Chunk.NEWLINE);

            // Tabla de productos
            PdfPTable tabla = new PdfPTable(5);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new float[]{1.2f, 3f, 1.2f, 1.5f, 1.7f});

            String[] encabezados = {"ID", "Nombre", "Cant.", "Precio", "Subtotal"};
            for (String encabezado : encabezados) {
                PdfPCell celdaEncabezado = new PdfPCell(new Phrase(encabezado, boldFont));
                celdaEncabezado.setBackgroundColor(new Color(230, 230, 250));
                celdaEncabezado.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla.addCell(celdaEncabezado);
            }

            int total = 0;
            ArrayList<Object[]> productos = Ticket.obtenerProductos(); // ← Tu método local de productos

            for (Object[] prod : productos) {
                int id = (int) prod[0];
                String nombre = prod[1].toString();
                int cantidad = (int) prod[2];
                int precio = (int) prod[3];
                int subtotal = cantidad * precio;
                total += subtotal;

                tabla.addCell(new Phrase(String.valueOf(id), normalFont));
                tabla.addCell(new Phrase(nombre, normalFont));
                tabla.addCell(new Phrase(String.valueOf(cantidad), normalFont));
                tabla.addCell(new Phrase("$" + precio, normalFont));
                tabla.addCell(new Phrase("$" + subtotal, normalFont));
            }

            doc.add(tabla);
            doc.add(Chunk.NEWLINE);
            doc.add(new LineSeparator());
            doc.add(Chunk.NEWLINE);

            Paragraph totalP = new Paragraph("TOTAL: $" + total, boldFont);
            totalP.setAlignment(Element.ALIGN_RIGHT);
            doc.add(totalP);

            doc.add(Chunk.NEWLINE);

            Paragraph gracias = new Paragraph("¡Gracias por su compra!", normalFont);
            gracias.setAlignment(Element.ALIGN_CENTER);
            doc.add(gracias);

            doc.close();
            return nombreArchivo;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}



