/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import modelos.Usuario;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilerias.Conexion;

/**
 *
 * @author Samue
 */
public class UsuarioControlador {

    public static boolean RegistrarUsuario(Usuario usuario) {
        String query = "INSERT INTO usuarios (id, nombre, alias, contraseña, correo, edad, peso, sexo) VALUES (0, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection conexion = Conexion.getConexion();
            
            PreparedStatement pstmt = conexion.prepareStatement(query);

            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getAlias());
            pstmt.setString(3, usuario.getContraseña());
            pstmt.setString(4, usuario.getCorreo());
            pstmt.setInt(5, usuario.getEdad());
            pstmt.setDouble(6, usuario.getPeso());
            pstmt.setString(7, usuario.getSexo());


            return pstmt.executeUpdate() > 0; 

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static boolean iniciarSesion(String usuario, String contraseña){
        String query = "SELECT * FROM usuarios WHERE alias = ? AND contraseña = ?";
        
        try {
            Connection conexion = Conexion.getConexion();
            
            PreparedStatement pstmt = conexion.prepareStatement(query);
            pstmt.setString(1, usuario);
            pstmt.setString(2, contraseña);

            return pstmt.executeQuery().next(); 

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
