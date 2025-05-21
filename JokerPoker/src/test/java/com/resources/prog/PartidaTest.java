package com.resources.prog;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.resources.dao.DAOPartida;

import java.sql.Date;
import java.time.LocalDate;

class PartidaTest {

    private Usuario usuario;
    private Partida partida;
    private DAOPartida daoPartidaMock;

    @BeforeEach
    void setUp() {
        daoPartidaMock = mock(DAOPartida.class);
        when(daoPartidaMock.getMaxID()).thenReturn(10);  // Supongamos que el ID máximo es 10 para las pruebas.

        usuario = new Usuario("user1", "password123", "John Doe", 100.0, false);
        partida = new Partida(1, usuario, true, Date.valueOf(LocalDate.of(2025, 5, 21)));  // Partida con id 1
    }

    @Test
    void testConstructorConParametros() {
        assertEquals(1, partida.getId());
        assertEquals(usuario, partida.getUser());
        assertTrue(partida.isUserWins());
        assertEquals(Date.valueOf(LocalDate.of(2025, 5, 21)), partida.getDate());
    }

    @Test
    void testConstructorSinId() {
        Partida partidaSinId = new Partida(usuario, true);
        assertNotNull(partidaSinId.getId());  // El ID debe ser generado
        assertEquals(usuario, partidaSinId.getUser());
        assertTrue(partidaSinId.isUserWins());
        assertEquals(Date.valueOf(LocalDate.now()), partidaSinId.getDate());
    }

    @Test
    void testGettersYSetters() {
        Partida p = new Partida(1, usuario, false, Date.valueOf(LocalDate.of(2023, 1, 1)));
        
        p.setId(2);
        p.setUser(new Usuario("user2", "password456", "Jane Doe", 50.0, true));  // Cambio de Usuario
        p.setUserWins(true);  // El usuario ahora ha ganado
        p.setDate(Date.valueOf(LocalDate.of(2025, 5, 21)));  // Cambiar la fecha
        
        assertEquals(2, p.getId());
        assertEquals("user2", p.getUser().getUserName());  // Verificación del nuevo Usuario
        assertTrue(p.isUserWins());
        assertEquals(Date.valueOf(LocalDate.of(2025, 5, 21)), p.getDate());
    }

    @Test
    void testToString() {
        String expected = "1 user1 true 2025-05-21";
        assertEquals(expected, partida.toString());
    }

    @Test
    void testCompareTo() {
        Partida p1 = new Partida(1, usuario, true, Date.valueOf(LocalDate.of(2025, 5, 21)));
        Partida p2 = new Partida(2, usuario, false, Date.valueOf(LocalDate.of(2025, 5, 21)));
        
        assertTrue(p1.compareTo(p2) < 0);  // p1 < p2
        assertTrue(p2.compareTo(p1) > 0);  // p2 > p1
        assertEquals(0, p1.compareTo(p1));  // p1 == p1
    }

    @Test
    void testComparadorID() {
        Partida p1 = new Partida(1, usuario, true, Date.valueOf(LocalDate.of(2025, 5, 21)));
        Partida p2 = new Partida(2, usuario, false, Date.valueOf(LocalDate.of(2025, 5, 21)));
        
        Partida.ComparadorID comparador = new Partida.ComparadorID();
        
        assertTrue(comparador.compare(p1, p2) < 0);  // p1 < p2
        assertTrue(comparador.compare(p2, p1) > 0);  // p2 > p1
        assertEquals(0, comparador.compare(p1, p1));  // p1 == p1
    }

    @Test
    void testComparadorDate() {
        Partida p1 = new Partida(1, usuario, true, Date.valueOf(LocalDate.of(2025, 5, 21)));
        Partida p2 = new Partida(2, usuario, false, Date.valueOf(LocalDate.of(2024, 5, 21)));
        
        Partida.ComparadorDate comparador = new Partida.ComparadorDate();
        
        assertTrue(comparador.compare(p1, p2) > 0);  // p1 > p2 (p1 es más reciente)
        assertTrue(comparador.compare(p2, p1) < 0);  // p2 < p1
        assertEquals(0, comparador.compare(p1, p1));  // p1 == p1
    }
}
