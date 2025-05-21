package com.resources.prog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BarajaTest {

    private Baraja baraja;

    @BeforeEach
    void setUp() {
        baraja = new Baraja();
    }

    // ----------------------
    // Test de creación y reparto
    // ----------------------

    @Test
    void testBarajaContiene52Cartas() {
        List<Carta> mano = baraja.repartirMano(52);
        assertEquals(52, mano.size());
        assertTrue(baraja.estaVacia());
    }

    @Test
    void testRepartirUnaCarta() {
        Carta carta = baraja.repartir();
        assertNotNull(carta);
    }

    @Test
    void testRepartirMano() {
        List<Carta> mano = baraja.repartirMano(5);
        assertEquals(5, mano.size());
        assertEquals(47, baraja.repartirMano(47).size());
        assertTrue(baraja.estaVacia());
    }

    @RepeatedTest(3)
    void testBarajarCambiaOrden() {
        List<Carta> antes = new Baraja().repartirMano(52);
        baraja.barajar();
        List<Carta> despues = baraja.repartirMano(52);
        // No se garantiza que cambie, pero estadísticamente debería
        assertNotEquals(antes.toString(), despues.toString());
    }

    // ----------------------
    // Test de evaluarMano
    // ----------------------

    @Test
    void testEvaluarMano_CartaAlta() {
        List<Carta> mano = List.of(
                new Carta(0, 0), // 2
                new Carta(1, 2), // 4
                new Carta(2, 5), // 7
                new Carta(3, 8), // 10
                new Carta(0, 10) // Q
        );
        assertEquals(0, Baraja.evaluarMano(mano));
    }

    @Test
    void testEvaluarMano_Pareja() {
        List<Carta> mano = List.of(
                new Carta(0, 1),
                new Carta(1, 1),
                new Carta(2, 5),
                new Carta(3, 8),
                new Carta(0, 10)
        );
        assertEquals(1, Baraja.evaluarMano(mano));
    }

    @Test
    void testEvaluarMano_DoblePareja() {
        List<Carta> mano = List.of(
                new Carta(0, 1), new Carta(1, 1), // 3
                new Carta(2, 5), new Carta(3, 5), // 7
                new Carta(0, 10) // Q
        );
        assertEquals(2, Baraja.evaluarMano(mano));
    }

    @Test
    void testEvaluarMano_Trio() {
        List<Carta> mano = List.of(
                new Carta(0, 1), new Carta(1, 1), new Carta(2, 1), // 3
                new Carta(3, 5), new Carta(0, 10)
        );
        assertEquals(3, Baraja.evaluarMano(mano));
    }

    @Test
    void testEvaluarMano_EscaleraBaja() {
        List<Carta> mano = List.of(
                new Carta(0, 0), // 2
                new Carta(1, 1), // 3
                new Carta(2, 2), // 4
                new Carta(3, 3), // 5
                new Carta(0, 12) // A
        );
        assertEquals(4, Baraja.evaluarMano(mano));
    }

    @Test
    void testEvaluarMano_Escalera() {
        List<Carta> mano = List.of(
                new Carta(0, 4), // 6
                new Carta(1, 5), // 7
                new Carta(2, 6), // 8
                new Carta(3, 7), // 9
                new Carta(0, 8) // 10
        );
        assertEquals(5, Baraja.evaluarMano(mano));
    }

    @Test
    void testEvaluarMano_Color() {
        List<Carta> mano = List.of(
                new Carta(2, 1),
                new Carta(2, 5),
                new Carta(2, 7),
                new Carta(2, 9),
                new Carta(2, 10)
        );
        assertEquals(6, Baraja.evaluarMano(mano));
    }

    @Test
    void testEvaluarMano_FullHouse() {
        List<Carta> mano = List.of(
                new Carta(0, 1), new Carta(1, 1), new Carta(2, 1), // 3
                new Carta(3, 4), new Carta(0, 4) // 6
        );
        assertEquals(7, Baraja.evaluarMano(mano));
    }

    @Test
    void testEvaluarMano_Poker() {
        List<Carta> mano = List.of(
                new Carta(0, 2), new Carta(1, 2), new Carta(2, 2), new Carta(3, 2),
                new Carta(0, 4)
        );
        assertEquals(8, Baraja.evaluarMano(mano));
    }

    @Test
    void testEvaluarMano_EscaleraColorBaja() {
        List<Carta> mano = List.of(
                new Carta(0, 0), // 2
                new Carta(0, 1), // 3
                new Carta(0, 2), // 4
                new Carta(0, 3), // 5
                new Carta(0, 12) // A
        );
        assertEquals(9, Baraja.evaluarMano(mano));
    }

    @Test
    void testEvaluarMano_EscaleraColor() {
        List<Carta> mano = List.of(
                new Carta(1, 4),
                new Carta(1, 5),
                new Carta(1, 6),
                new Carta(1, 7),
                new Carta(1, 8)
        );
        assertEquals(10, Baraja.evaluarMano(mano));
    }

    // ----------------------
    // Test de descripcionMano
    // ----------------------

    @Test
    void testDescripcionManoTodosLosCasos() {
        assertEquals("Escalera de color", Baraja.descripcionMano(10));
        assertEquals("Escalera de color baja", Baraja.descripcionMano(9));
        assertEquals("Poker", Baraja.descripcionMano(8));
        assertEquals("Full House", Baraja.descripcionMano(7));
        assertEquals("Color", Baraja.descripcionMano(6));
        assertEquals("Escalera", Baraja.descripcionMano(5));
        assertEquals("Escalera baja", Baraja.descripcionMano(4));
        assertEquals("Trio", Baraja.descripcionMano(3));
        assertEquals("Doble pareja", Baraja.descripcionMano(2));
        assertEquals("Pareja", Baraja.descripcionMano(1));
        assertEquals("Carta alta", Baraja.descripcionMano(0));
        assertEquals("Carta alta", Baraja.descripcionMano(-1));
    }

    // ----------------------
    // Test de compararManos
    // ----------------------

    @Test
    void testCompararManos_ValorDiferente() {
        List<Carta> mano1 = List.of(new Carta(0, 0), new Carta(1, 1), new Carta(2, 2), new Carta(3, 3), new Carta(0, 12)); // escalera baja
        List<Carta> mano2 = List.of(new Carta(0, 12), new Carta(1, 12), new Carta(2, 12), new Carta(3, 12), new Carta(0, 2)); // poker
        assertEquals(-1, Baraja.compararManos(mano2, mano1));
    }

    @Test
    void testCompararManos_ValorIgual_MismaMano() {
        List<Carta> mano1 = List.of(
                new Carta(1, 10), new Carta(2, 10), new Carta(3, 10),
                new Carta(0, 3), new Carta(1, 3) // Full House
        );
        List<Carta> mano2 = List.of(
                new Carta(1, 10), new Carta(2, 10), new Carta(3, 10),
                new Carta(0, 3), new Carta(1, 3)
        );
        assertEquals(0, Baraja.compararManos(mano1, mano2));
    }

    @Test
    void testCompararManos_ColorVsColor() {
        List<Carta> mano1 = List.of(
                new Carta(2, 1),
                new Carta(2, 3),
                new Carta(2, 5),
                new Carta(2, 6),
                new Carta(2, 8)
        );
        List<Carta> mano2 = List.of(
                new Carta(1, 2),
                new Carta(1, 4),
                new Carta(1, 6),
                new Carta(1, 7),
                new Carta(1, 9)
        );
        int cmp = Baraja.compararManos(mano1, mano2);
        assertTrue(cmp != 0);
    }
}
