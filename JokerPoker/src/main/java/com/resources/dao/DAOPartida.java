/*
 * DAOPartida.
 * Crear una partida.
 */
package com.resources.dao;

import com.resources.prog.Partida;
import com.resources.prog.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Alicia Guerrero Márquez
 */
public class DAOPartida {

    String url = "jdbc:mariadb://localhost:3306/JokerPokerDB";
    String usuario = "programacion";
    String contraseña = "programacion";

    public Connection conectarBD() throws SQLException {
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
    
    
    public Partida getPartida(int id) {
        Partida p = null;
        Connection conn = null;
        try {
            conn = conectarBD();
            PreparedStatement pst = conn.prepareStatement(
                    "select * from partidas where id = ?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                p = new Partida (rs.getInt("id"), new DAOUsuario().getUsuario(rs.getString("user")), rs.getBoolean("userWins"), rs.getDate("date"));
            }
        } catch (SQLException e) {
            System.err.println("DAOUsuario, getPartida:" + e.getMessage());
        } finally {
            desconectarBD(conn);
        }
        
        return p;
    }
   
    public void insertPartida (Partida p){
        Connection conn = null;
        try {
            conn = conectarBD();
            PreparedStatement pst = conn.prepareStatement("INSERT INTO partidas (id, user, userWins, date) VALUE (?, ?, ?, ?)");
            pst.setInt(1, p.getId());
            pst.setString(2, p.getUser().getUserName());
            pst.setBoolean(3, p.isUserWins());
            pst.setDate(4, p.getDate());

            ResultSet rs = pst.executeQuery();
            
        } catch (SQLException e) {
            System.err.println("DAOPartida, getPartida:" + e.getMessage());
        } finally {
            desconectarBD(conn);
        }
        
    }
    
    
    

}
