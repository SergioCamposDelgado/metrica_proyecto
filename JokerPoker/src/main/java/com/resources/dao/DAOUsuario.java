/*
 * DAOUsusarios.
 * Operaciones de acceso a datos para la entidad Usuario
 */
package com.resources.dao;




import com.resources.prog.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Sergio Campos Delgado
 */
public class DAOUsuario {
    
        String url = "jdbc:mariadb://localhost:3306/JokerPokerDB";
        String usuario = "programacion";
        String contraseña = "programacion";
        
    public Connection conectarBD() throws SQLException  {
            Connection conn = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("¡Conexión exitosa a MariaDB! DAOUsuario");
        return conn;
    }
    
    public void desconectarBD(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error al desconectar BD: " + e.getMessage());
        }
    }
    
    public Usuario getUsuario(String login) {
        Usuario u = null;
        Connection conn = null;
        try {
            conn = conectarBD();
            PreparedStatement pst = conn.prepareStatement(
                    "select * from usuarios where login = ?");
            pst.setString(1, login);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                u = new Usuario(login, rs.getString("passwd"), rs.getString("name"), Usuario.getInitialBalance(), false);
            }
        } catch (SQLException e) {
            System.err.println("DAOUsuario, getUsuario:" + e.getMessage());
        } finally {
            desconectarBD(conn);
        }
        
        return u;
    }
    
    public void insertUsuario(Usuario u){

    }
    
    public void setAdmin (Usuario u) {
    
    }
}
