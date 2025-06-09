
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectologinzapateria.Clases;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.draw.LineSeparator;
import java.awt.Color;
import java.io.FileOutputStream;

public class CrearPDF {

    public static String generarPDF(String nombreArchivo, String nombres, String apellidos, String correo, String contraseña, String rutaImagenIzquierda, String rutaImagenDerecha) {
        try {
            Document documento = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
            documento.open();

          
            PdfPTable tablaImagenes = new PdfPTable(2);
            tablaImagenes.setWidthPercentage(100);
            tablaImagenes.setWidths(new float[]{1f, 1f});
            tablaImagenes.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            
            if (rutaImagenIzquierda != null && !rutaImagenIzquierda.isEmpty()) {
                try {
                    Image imgIzq = Image.getInstance(rutaImagenIzquierda);
                    imgIzq.scaleToFit(80, 80);
                    PdfPCell celdaIzq = new PdfPCell(imgIzq);
                    celdaIzq.setBorder(Rectangle.NO_BORDER);
                    celdaIzq.setHorizontalAlignment(Element.ALIGN_LEFT);
                    tablaImagenes.addCell(celdaIzq);
                } catch (Exception ex) {
                    System.err.println("No se pudo cargar imagen izquierda: " + ex.getMessage());
                    tablaImagenes.addCell("");
                }
            } else {
                tablaImagenes.addCell("");
            }

            
            if (rutaImagenDerecha != null && !rutaImagenDerecha.isEmpty()) {
                try {
                    Image imgDer = Image.getInstance(rutaImagenDerecha);
                    imgDer.scaleToFit(80, 80);
                    PdfPCell celdaDer = new PdfPCell(imgDer);
                    celdaDer.setBorder(Rectangle.NO_BORDER);
                    celdaDer.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    tablaImagenes.addCell(celdaDer);
                } catch (Exception ex) {
                    System.err.println("No se pudo cargar imagen derecha: " + ex.getMessage());
                    tablaImagenes.addCell("");
                }
            } else {
                tablaImagenes.addCell("");
            }

            documento.add(tablaImagenes);
            documento.add(Chunk.NEWLINE);

         
            Font fontTitulo = new Font(Font.HELVETICA, 24, Font.BOLD, new Color(102, 0, 153));
            Paragraph titulo = new Paragraph("Registro de Usuario", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            LineSeparator separator = new LineSeparator();
            separator.setLineColor(new Color(204, 153, 255));
            documento.add(new Chunk(separator));
            documento.add(Chunk.NEWLINE);

          
            Font fontSubtitulo = new Font(Font.HELVETICA, 16, Font.BOLD, Color.DARK_GRAY);
            Paragraph subtitulo = new Paragraph("Información de registro", fontSubtitulo);
            subtitulo.setSpacingAfter(10);
            documento.add(subtitulo);

            
            PdfPTable tabla = new PdfPTable(2);
            tabla.setWidthPercentage(100);
            tabla.setSpacingBefore(10f);
            tabla.setSpacingAfter(10f);
            tabla.setWidths(new float[]{2f, 5f});

            Font fontHeader = new Font(Font.HELVETICA, 12, Font.BOLD, Color.WHITE);
            Font fontCelda = new Font(Font.HELVETICA, 12);

            PdfPCell th1 = new PdfPCell(new Phrase(" ", fontHeader));
            PdfPCell th2 = new PdfPCell(new Phrase(" ", fontHeader));
            th1.setBackgroundColor(new Color(102, 0, 153));
            th2.setBackgroundColor(new Color(102, 0, 153));
            th1.setHorizontalAlignment(Element.ALIGN_CENTER);
            th2.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(th1);
            tabla.addCell(th2);

            tabla.addCell(celda("Nombre completo:", fontCelda));
            tabla.addCell(celda(nombres + " " + apellidos, fontCelda));

            tabla.addCell(celda("Correo electrónico:", fontCelda));
            tabla.addCell(celda(correo, fontCelda));

            tabla.addCell(celda("Contraseña:", fontCelda));
            tabla.addCell(celda(contraseña, fontCelda));

            documento.add(tabla);

            
            Font fontMensaje = new Font(Font.HELVETICA, 12, Font.ITALIC, Color.GRAY);
            Paragraph mensaje = new Paragraph("Gracias por registrarte en el sistema de Vanilla Unicorn.", fontMensaje);
            mensaje.setAlignment(Element.ALIGN_CENTER);
            mensaje.setSpacingBefore(20);
            documento.add(mensaje);

            documento.close();
            System.out.println("Se ha generado tu comprobante de registro en PDF. ");
            return nombreArchivo;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static PdfPCell celda(String texto, Font fuente) {
        PdfPCell celda = new PdfPCell(new Phrase(texto, fuente));
        celda.setPadding(8f);
        celda.setBorderColor(Color.LIGHT_GRAY);
        return celda;
    }
}

