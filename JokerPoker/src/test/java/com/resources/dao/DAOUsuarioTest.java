package com.resources.dao;

import com.resources.prog.Usuario;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DAOUsuarioTest {

    private static DAOUsuario dao;
    private static final String TEST_USER = "testuser";

    @BeforeAll
    static void setup() {
        dao = new DAOUsuario();
    }

    @BeforeEach
    void cleanDatabase() {
        try (Connection conn = dao.conectarBD()) {
            PreparedStatement pst = conn.prepareStatement("DELETE FROM usuarios WHERE userName = ?");
            pst.setString(1, TEST_USER);
            pst.executeUpdate();
        } catch (SQLException e) {
            fail("Error limpiando la base de datos: " + e.getMessage());
        }
    }

    @Test
    @Order(1)
    void testInsertUsuario() {
        Usuario u = new Usuario(TEST_USER, "pass123", "Test User", 100.0, false);
        dao.insertUsuario(u);

        Usuario fromDb = dao.getUsuario(TEST_USER);
        assertNotNull(fromDb);
        assertEquals("Test User", fromDb.getName());
        assertEquals(100.0, fromDb.getBalance());
        assertFalse(fromDb.esAdmin());
    }

    @Test
    @Order(2)
    void testSetAdmin() {
        Usuario u = new Usuario(TEST_USER, "pass123", "Test User", 100.0, false);
        dao.insertUsuario(u);
        u.setEsAdmin(true);
        dao.setAdmin(u);

        Usuario fromDb = dao.getUsuario(TEST_USER);
        assertTrue(fromDb.esAdmin());
    }

    @Test
    @Order(3)
    void testSetBalance() {
        Usuario u = new Usuario(TEST_USER, "pass123", "Test User", 100.0, false);
        dao.insertUsuario(u);
        u.setBalance(500.0);
        dao.setBalance(u);

        Usuario fromDb = dao.getUsuario(TEST_USER);
        assertEquals(500.0, fromDb.getBalance());
    }

    @Test
    @Order(4)
    void testUpdateInfo() {
        Usuario u = new Usuario(TEST_USER, "pass123", "Test User", 100.0, false);
        dao.insertUsuario(u);
        u.setPasswd("newpass");
        u.setName("Updated Name");
        u.setBalance(250.0);
        u.setEsAdmin(true);
        dao.updateInfo(u);

        Usuario fromDb = dao.getUsuario(TEST_USER);
        assertEquals("Updated Name", fromDb.getName());
        assertEquals(250.0, fromDb.getBalance());
        assertTrue(fromDb.esAdmin());
    }

    @Test
    @Order(5)
    void testGetUsuarios() {
        Usuario u1 = new Usuario(TEST_USER, "pass123", "Test User", 100.0, false);
        dao.insertUsuario(u1);

        List<Usuario> usuarios = dao.getUsuarios();
        assertNotNull(usuarios);
        assertTrue(usuarios.stream().anyMatch(u -> u.getUserName().equals(TEST_USER)));
    }

    @Test
    @Order(6)
    void testGetUsuarioNotFound() {
        Usuario u = dao.getUsuario("usuario_que_no_existe");
        assertNull(u);
    }
}
