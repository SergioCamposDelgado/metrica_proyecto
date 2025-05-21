/*
 * DAOUsuario.
 * Operaciones de acceso a datos para la entidad Usuario.
 */
package com.resources.dao;

import com.resources.prog.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAOUsuario que permite realizar operaciones CRUD sobre la tabla "usuarios"
 * en la base de datos JokerPokerDB.
 * 
 * Métodos disponibles:
 * - Obtener usuario por nombre
 * - Insertar nuevo usuario
 * - Modificar balance o rol
 * - Actualizar toda la información
 * - Obtener lista completa de usuarios
 * 
 * Utiliza JDBC para conectarse a MariaDB.
 * 
 * @author Alicia Guerrero Márquez
 */
public class DAOUsuario {

    /** URL de conexión a la base de datos */
    String url = "jdbc:mariadb://localhost:3306/JokerPokerDB";

    /** Nombre de usuario para la base de datos */
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
        System.out.println("Conexion exitosa a MariaDB! DAOUsuario");
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
            System.err.println("Error al desconectar BD, DAOUSUARIO: " + e.getMessage());
        }
    }

    /**
     * Recupera un usuario por su nombre de usuario.
     * 
     * @param userName Nombre de usuario.
     * @return Objeto {@code Usuario} si existe, o {@code null} si no se encuentra.
     */
    public Usuario getUsuario(String userName) {
        Usuario u = null;
        Connection conn = null;
        try {
            conn = conectarBD();
            PreparedStatement pst = conn.prepareStatement(
                    "SELECT * FROM usuarios WHERE userName = ?");
            pst.setString(1, userName);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                u = new Usuario(
                        rs.getString("userName"),
                        rs.getString("passwd"),
                        rs.getString("name"),
                        rs.getDouble("balance"),
                        rs.getBoolean("esAdmin")
                );
            }
        } catch (SQLException e) {
            System.err.println("DAOUsuario, getUsuario: " + e.getMessage());
        } finally {
            desconectarBD(conn);
        }
        return u;
    }

    /**
     * Inserta un nuevo usuario en la base de datos.
     * 
     * @param u Objeto {@code Usuario} a insertar.
     */
    public void insertUsuario(Usuario u) {
        Connection conn = null;
        try {
            conn = conectarBD();
            PreparedStatement pst = conn.prepareStatement(
                "INSERT INTO usuarios (userName, passwd, name, balance, esAdmin) VALUES (?, ?, ?, ?, ?)");
            pst.setString(1, u.getUserName());
            pst.setString(2, u.getPasswd());
            pst.setString(3, u.getName());
            pst.setDouble(4, u.getBalance());
            pst.setBoolean(5, u.esAdmin());
            pst.execute();
        } catch (SQLException e) {
            System.err.println("DAOUsuario, insertUsuario: " + e.getMessage());
        } finally {
            desconectarBD(conn);
        }
    }

    /**
     * Modifica el campo {@code esAdmin} de un usuario en la base de datos.
     * 
     * @param u Usuario con el nuevo valor de {@code esAdmin}.
     */
    public void setAdmin(Usuario u) {
        Connection conn = null;
        try {
            conn = conectarBD();
            PreparedStatement pst = conn.prepareStatement(
                "UPDATE usuarios SET esAdmin = ? WHERE userName = ?");
            pst.setBoolean(1, u.esAdmin());
            pst.setString(2, u.getUserName());
            pst.execute();
        } catch (SQLException e) {
            System.err.println("DAOUsuario, setAdmin: " + e.getMessage());
        } finally {
            desconectarBD(conn);
        }
    }

    /**
     * Modifica el saldo ({@code balance}) de un usuario.
     * 
     * @param u Usuario con el nuevo saldo.
     */
    public void setBalance(Usuario u) {
        Connection conn = null;
        try {
            conn = conectarBD();
            PreparedStatement pst = conn.prepareStatement(
                "UPDATE usuarios SET balance = ? WHERE userName = ?");
            pst.setDouble(1, u.getBalance());
            pst.setString(2, u.getUserName());
            pst.execute();
        } catch (SQLException e) {
            System.err.println("DAOUsuario, setBalance: " + e.getMessage());
        } finally {
            desconectarBD(conn);
        }
    }

    /**
     * Devuelve una lista con todos los usuarios registrados en la base de datos.
     * 
     * @return Lista de objetos {@code Usuario}.
     */
    public List<Usuario> getUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        Connection conn = null;
        try {
            conn = conectarBD();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM usuarios");
            while (rs.next()) {
                Usuario u = new Usuario(
                        rs.getString("userName"),
                        rs.getString("passwd"),
                        rs.getString("name"),
                        rs.getDouble("balance"),
                        rs.getBoolean("esAdmin")
                );
                lista.add(u);
            }
        } catch (SQLException e) {
            System.err.println("DAOUsuario, getUsuarios: " + e.getMessage());
        } finally {
            desconectarBD(conn);
        }
        return lista;
    }

    /**
     * Actualiza toda la información de un usuario en la base de datos.
     * 
     * @param u Usuario con la información actualizada.
     */
    public void updateInfo(Usuario u) {
        Connection conn = null;
        try {
            conn = conectarBD();
            PreparedStatement pst = conn.prepareStatement(
                "UPDATE usuarios SET passwd = ?, name = ?, balance = ?, esAdmin = ? WHERE userName = ?");
            pst.setString(1, u.getPasswd());
            pst.setString(2, u.getName());
            pst.setDouble(3, u.getBalance());
            pst.setBoolean(4, u.esAdmin());
            pst.setString(5, u.getUserName());
            pst.execute();
        } catch (SQLException e) {
            System.err.println("DAOUsuario, updateInfo: " + e.getMessage());
        } finally {
            desconectarBD(conn);
        }
    }

}
