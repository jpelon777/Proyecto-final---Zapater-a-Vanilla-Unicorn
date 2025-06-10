/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectologinzapateria.Clases;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;



/**
 *
 * @author jpobl
 */
public class EnviarCorreo {
    
    public static void enviarCorreoConPDF(String destinatario, String asunto, String rutaPDF) {
        
        final String remitente = "vanillaunicorn6800@gmail.com"; 
        final String contraseña = "rrghdxdaryfghoom";      

        
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, contraseña);
            }
        });

        try {
            
            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(remitente));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            mensaje.setSubject(asunto);

            
            BodyPart mensajeTexto = new MimeBodyPart();
            mensajeTexto.setText("Hola, se ha generado tu comprobante de registro en PDF.");

            
            MimeBodyPart adjunto = new MimeBodyPart();
            DataSource fuente = new FileDataSource(rutaPDF);
            adjunto.setDataHandler(new DataHandler(fuente));
            adjunto.setFileName(rutaPDF);

            
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mensajeTexto);
            multipart.addBodyPart(adjunto);

            mensaje.setContent(multipart);

            
            Transport.send(mensaje);
            System.out.println("Correo enviado correctamente.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    
        
}
