package com.resources.dao;

import com.resources.prog.Partida;
import com.resources.prog.Usuario;
import org.junit.jupiter.api.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DAOPartidaTest {

    private static DAOPartida daoPartida;
    private static DAOUsuario daoUsuario;
    private static final String TEST_USER = "test_user";

    @BeforeAll
    static void setUpAll() {
        daoPartida = new DAOPartida();
        daoUsuario = new DAOUsuario();

        // Asegura que el usuario de prueba existe
        Usuario u = daoUsuario.getUsuario(TEST_USER);
        if (u == null) {
            u = new Usuario(TEST_USER, "testpass", "Test User", 100.0, false);
            daoUsuario.insertUsuario(u);
        }
    }

    @Test
    @Order(1)
    void testInsertPartidaYGetPartida() {
        Usuario usuario = daoUsuario.getUsuario(TEST_USER);
        assertNotNull(usuario, "El usuario de prueba no existe");

        Partida partida = new Partida(usuario, true);
        daoPartida.insertPartida(partida);

        Partida fromDb = daoPartida.getPartida(partida.getId());
        assertNotNull(fromDb);
        assertEquals(partida.getId(), fromDb.getId());
        assertEquals(TEST_USER, fromDb.getUser().getUserName());
        assertTrue(fromDb.isUserWins());
        assertEquals(partida.getDate(), fromDb.getDate());
    }

    @Test
    @Order(2)
    void testGetPartidas() {
        List<Partida> partidas = daoPartida.getPartidas();
        assertNotNull(partidas);
        assertFalse(partidas.isEmpty(), "La lista de partidas no debería estar vacía");

        boolean found = partidas.stream()
                .anyMatch(p -> TEST_USER.equals(p.getUser().getUserName()));
        assertTrue(found, "Debería haber al menos una partida del usuario de prueba");
    }

    @Test
    @Order(3)
    void testGetMaxID() {
        List<Partida> partidas = daoPartida.getPartidas();
        if (partidas != null && !partidas.isEmpty()) {
            int maxIdEsperado = partidas.stream()
                    .mapToInt(Partida::getId)
                    .max()
                    .orElse(-1);
            assertEquals(maxIdEsperado, daoPartida.getMaxID());
        } else {
            assertEquals(-1, daoPartida.getMaxID());
        }
    }
}
