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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Sergio Campos Delgado
 */
public class DAOUsuario {

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
            System.err.println("Error al desconectar BD, DAOUSUARIO: " + e.getMessage());
        }
    }

    public Usuario getUsuario(String userName) {
        Usuario u = null;
        Connection conn = null;
        try {
            conn = conectarBD();
            PreparedStatement pst = conn.prepareStatement(
                    "select * from usuarios where userName = ?");
            pst.setString(1, userName);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                u = new Usuario(rs.getString("userName"), rs.getString("passwd"), rs.getString("name"), rs.getDouble("balance"), rs.getBoolean("esAdmin"));
            }
        } catch (SQLException e) {
            System.err.println("DAOUsuario, getUsuario:" + e.getMessage());
        } finally {
            desconectarBD(conn);
        }

        return u;
    }

    public void insertUsuario(Usuario u) {
        Connection conn = null;
        try {
            conn = conectarBD();
            PreparedStatement pst = conn.prepareStatement("INSERT INTO usuarios (userName, passwd, name, balance, esAdmin) VALUE (?, ?, ?, ?, ?)");
            pst.setString(1, u.getUserName());
            pst.setString(2, u.getPasswd());
            pst.setString(3, u.getName());
            pst.setDouble(4, u.getBalance());
            pst.setBoolean(5, u.esAdmin());
            pst.execute();

        } catch (SQLException e) {
            System.err.println("DAOUsuario, insertUsuario:" + e.getMessage());
        } finally {
            desconectarBD(conn);
        }

    }

    public void setAdmin(Usuario u) {
        Connection conn = null;
        try {
            conn = conectarBD();
            PreparedStatement pst = conn.prepareStatement("UPDATE usuarios SET esAdmin = true where userName = ?");
            pst.setString(1, u.getUserName());
            ResultSet rs = pst.executeQuery();

        } catch (SQLException e) {
            System.err.println("DAOUsuario, setAdmin:" + e.getMessage());
        } finally {
            desconectarBD(conn);
        }

    }

    public void setBalance(Usuario u) {
        Connection conn = null;
        try {
            conn = conectarBD();
            PreparedStatement pst = conn.prepareStatement("UPDATE usuarios SET balance = ? where username = ?");
            pst.setDouble(1, u.getBalance());
            pst.setString(2, u.getUserName());
            pst.execute();

        } catch (SQLException e) {
            System.err.println("DAOUsuario, setBalance:" + e.getMessage());
        } finally {
            desconectarBD(conn);
        }

    }

    public List<Usuario> getUsuarios() {
        List<Usuario> lista = null;
        Connection conn = null;
        try {
            lista = new ArrayList();
            conn = conectarBD();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM usuarios");
            while (rs.next()) {
                Usuario u1 = new Usuario(
                        rs.getString("userName"),
                        rs.getString("passwd"),
                        rs.getString("name"),
                        rs.getDouble("balance"),
                        rs.getBoolean("esAdmin")
                );

                lista.add(u1);

            }

        } catch (SQLException e) {
            System.err.println("DAOUsuario, setBalance:" + e.getMessage());
        } finally {
            desconectarBD(conn);
        }

        return lista;
    }
        public void updateInfo(Usuario u) {
        Connection conn = null;
        try {
            conn = conectarBD();
            PreparedStatement pst = conn.prepareStatement("UPDATE usuarios SET passwd = ?, name = ?, balance = ?, esAdmin = ? WHERE username = ?");
            pst.setString(1, u.getPasswd());
            pst.setString(1, u.getName());
            pst.setDouble(1, u.getBalance());
            pst.setBoolean(1, u.esAdmin());
            pst.setString(1, u.getUserName());
            pst.execute();

        } catch (SQLException e) {
            System.err.println("DAOUsuario, updateInfo:" + e.getMessage());
        } finally {
            desconectarBD(conn);
        }

    }
    

}
