/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectologinzapateria.Clases;


import java.util.regex.Pattern;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import libreriaconexion.ConexionMySQL;
import java.sql.SQLException;




public class Validacion {
    private static final String EMAIL = "^[a-zA-Z0-9._%+-]+@(gmail|outlook|itoaxaca|yahoo|hotmail)\\.(com|edu\\.mx|mx)$";
    private static final String NAME = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+(\\s[a-zA-ZáéíóúÁÉÍÓÚñÑ]+)*$";

    
    
    public static boolean validarCorreo(String Correo) {
        return Pattern.matches(EMAIL, Correo.toLowerCase());
    }
    
            public static boolean correoExiste(String correo) {
            boolean existe = false;
            String sql = "SELECT COUNT(*) FROM usuarios WHERE correo = ?";

            try {
                
                if (ConexionMySQL.getConexion() == null) {
                    boolean conectado = ConexionMySQL.conectar("3306", "zapateria", "root", "71b748ad9");
                    if (!conectado) {
                        System.out.println("No se pudo establecer conexión a la base de datos.");
                        return false; 
                    }
                }

                try (PreparedStatement ps = ConexionMySQL.getConexion().prepareStatement(sql)) {
                    ps.setString(1, correo);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            existe = rs.getInt(1) > 0;
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return existe;
        }



    public static boolean validarLetras(String Nombre) {
        return Pattern.matches(NAME, Nombre);
    }
    
    public static boolean validarLlave(String llaveAcceso){
        String key = "12345";
        
        if( llaveAcceso.equals(key)){
            return true;
        }
        return false;
    }

    public static boolean validarCamposLlenos(String... campos) {
        for (String campo : campos) {
            if (campo.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
    public static boolean validarContraseña(String Contrasena) {
        
        if (Contrasena.length() < 8) {
            return false;
        }

        boolean tieneMayuscula = false;
        boolean tieneMinuscula = false;
        boolean tieneNumero = false;

        for (int i = 0; i < Contrasena.length(); i++) {
            char c = Contrasena.charAt(i);
            if (Character.isUpperCase(c)) {
                tieneMayuscula = true;
            }
            if (Character.isLowerCase(c)) {
                tieneMinuscula = true;
            }
            if (Character.isDigit(c)) {
                tieneNumero = true;
            }
        }

        return tieneMayuscula && tieneMinuscula && tieneNumero;
    }
}

