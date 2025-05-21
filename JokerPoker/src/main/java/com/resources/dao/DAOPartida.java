/*
 * DAOPartida.
 * Operaciones de acceso a datos para la entidad Partida.
 */
package com.resources.dao;

import com.resources.prog.Partida;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAOPartida que permite realizar operaciones CRUD sobre la tabla
 * "partidas" en la base de datos JokerPokerDB.
 *
 * Métodos disponibles:
 * 
 * - Obtener partida por ID
 * - Insertar nueva partida
 * - Obtener lista completa de partidas
 * - Obtener el ID máximo de partida registrada
 *
 * 
 * Utiliza JDBC para conectarse a MariaDB.
 * 
 * @author Alicia Guerrero Márquez
 */
public class DAOPartida {

    /** URL de conexión a la base de datos */
    String url = "jdbc:mariadb://localhost:3306/JokerPokerDB";

    /** Usuario de la base de datos */
    String usuario = "programacion";

    /** Contraseña del usuario de la base de datos */
    String contraseña = "programacion";

    /**
     * Establece la conexión con la base de datos.
     *
     * @return Objeto {@code Connection} activo.
     * @throws SQLException si ocurre un error de conexión.
     */
    public Connection conectarBD() throws SQLException {
        Connection conn = DriverManager.getConnection(url, usuario, contraseña);
        System.out.println("Conexion exitosa a MariaDB! DAOPartida");
        return conn;
    }

    /**
     * Cierra la conexión con la base de datos.
     *
     * @param conn Conexión a cerrar.
     */
    public void desconectarBD(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error al desconectar BD, DAOPartida: " + e.getMessage());
        }
    }

    /**
     * Recupera una partida según su ID.
     *
     * @param id Identificador único de la partida.
     * @return Objeto {@code Partida} si se encuentra, o {@code null} si no existe.
     */
    public Partida getPartida(int id) {
        Partida p = null;
        Connection conn = null;
        try {
            conn = conectarBD();
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM partidas WHERE id = ?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                p = new Partida(
                        rs.getInt("id"),
                        new DAOUsuario().getUsuario(rs.getString("userName")),
                        rs.getBoolean("userWins"),
                        rs.getDate("fecha")
                );
            }
        } catch (SQLException e) {
            System.err.println("DAOPartida, getPartida: " + e.getMessage());
        } finally {
            desconectarBD(conn);
        }
        return p;
    }

    /**
     * Inserta una nueva partida en la base de datos.
     *
     * @param p Objeto {@code Partida} a insertar.
     */
    public void insertPartida(Partida p) {
        Connection conn = null;
        try {
            conn = conectarBD();
            PreparedStatement pst = conn.prepareStatement(
                "INSERT INTO partidas (id, userName, userWins, fecha) VALUES (?, ?, ?, ?)");
            pst.setInt(1, p.getId());
            pst.setString(2, p.getUser().getUserName());
            pst.setBoolean(3, p.isUserWins());
            pst.setDate(4, p.getDate());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("DAOPartida, insertPartida: " + e.getMessage());
        } finally {
            desconectarBD(conn);
        }
    }

    /**
     * Devuelve una lista con todas las partidas registradas en la base de datos.
     *
     * @return Lista de objetos {@code Partida}.
     */
    public List<Partida> getPartidas() {
        List<Partida> lista = new ArrayList<>();
        Connection conn = null;
        try {
            conn = conectarBD();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM partidas");
            while (rs.next()) {
                Partida p = new Partida(
                        rs.getInt("id"),
                        new DAOUsuario().getUsuario(rs.getString("userName")),
                        rs.getBoolean("userWins"),
                        rs.getDate("fecha")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            System.err.println("DAOPartida, getPartidas: " + e.getMessage());
        } finally {
            desconectarBD(conn);
        }
        return lista;
    }

    /**
     * Obtiene el ID máximo de todas las partidas registradas.
     * 
     * @return El mayor valor de ID encontrado, o -1 si no hay registros.
     */
    public int getMaxID() {
        int id = -1;
        try {
            List<Partida> lista = this.getPartidas();
            lista.sort((p1, p2) -> p2.getId() - p1.getId());
            id = lista.get(0).getId();
        } catch (NullPointerException e) {
            System.out.println("DAOPartida, getMaxID: ");
            e.printStackTrace();
        }
        return id;
    }

}
