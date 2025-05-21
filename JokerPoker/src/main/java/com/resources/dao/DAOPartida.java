/*
 * DAOPartida.
 * Operaciones de acceso a datos para la entidad Partida
 */
package com.resources.dao;

import com.resources.prog.Partida;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
        System.out.println("Conexion exitosa a MariaDB! DAOPartida");
        return conn;

    }

    public void desconectarBD(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error al desconectar BD, DAOPartida: " + e.getMessage());
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
                p = new Partida(rs.getInt("id"), new DAOUsuario().getUsuario(rs.getString("userName")), rs.getBoolean("userWins"), rs.getDate("fecha"));
            }
        } catch (SQLException e) {
            System.err.println("DAOPartida, getPartida:" + e.getMessage());
        } finally {
            desconectarBD(conn);
        }

        return p;
    }

    public void insertPartida(Partida p) {
        Connection conn = null;
        try {
            conn = conectarBD();
            PreparedStatement pst = conn.prepareStatement("INSERT INTO partidas (id, userName, userWins, fecha) VALUE (?, ?, ?, ?)");
            pst.setInt(1, p.getId());
            pst.setString(2, p.getUser().getUserName());
            pst.setBoolean(3, p.isUserWins());
            pst.setDate(4, p.getDate());

            pst.executeUpdate();

        } catch (SQLException e) {
            System.err.println("DAOPartida, insertPartida:" + e.getMessage());
        } finally {
            desconectarBD(conn);
        }

    }

    public List<Partida> getPartidas() {
        List<Partida> lista = null;
        Connection conn = null;
        try {
            lista = new ArrayList();
            conn = conectarBD();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM partidas");
            while (rs.next()) {
                Partida p = new Partida(
                        rs.getInt("ID"),
                        new DAOUsuario().getUsuario(rs.getString("USERNAME")),
                        rs.getBoolean("USERWINS"),
                        rs.getDate("FECHA")
                );

                lista.add(p);
            }

        } catch (SQLException e) {
            System.err.println("DAOPartida, getPartidas:" + e.getMessage());
        } finally {
            desconectarBD(conn);
        }

        return lista;
    }

    public int getMaxID() {
        int id = -1;

        try {

            List<Partida> lista = this.getPartidas();

            lista.sort(
                    (p1, p2)
                    -> p2.getId() - p1.getId()
            );

            id = lista.get(0).getId();
        } catch (NullPointerException e) {
            System.out.println("DAOPartida, getMaxID: ");
            e.printStackTrace();
        }

        return id;
    }

}
