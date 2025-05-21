package com.resources.prog;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CartaTest {

    @Test
    void testConstructorYGetters() {
        Carta carta = new Carta(1, 12); // A de HEARTS
        assertEquals("A", carta.getNumero());
        assertEquals("HEARTS", carta.getPalo());
    }

    @Test
    void testToString() {
        Carta carta = new Carta(0, 10); // Q de SPADES
        assertEquals("Q SPADES", carta.toString());
    }

    @Test
    void testGetNumeroInt() {
        Map<String, Integer> esperados = new HashMap<>();
        esperados.put("2", 2);
        esperados.put("3", 3);
        esperados.put("4", 4);
        esperados.put("5", 5);
        esperados.put("6", 6);
        esperados.put("7", 7);
        esperados.put("8", 8);
        esperados.put("9", 9);
        esperados.put("10", 10);
        esperados.put("J", 11);
        esperados.put("Q", 12);
        esperados.put("K", 13);
        esperados.put("A", 14);

        for (int i = 0; i < Carta.numeros.length; i++) {
            Carta carta = new Carta(0, i);
            assertEquals(esperados.get(carta.getNumero()), carta.getNumeroInt());
        }
    }


    @Test
    void testGetPaloInt() {
        for (int i = 0; i < Carta.palos.length; i++) {
            Carta carta = new Carta(i, 0);
            assertEquals(i, carta.getPaloInt());
        }
    }

    @Test
    void testCompareToMayorMenorIgual() {
        Carta c1 = new Carta(0, 12); // A de SPADES
        Carta c2 = new Carta(0, 10); // Q de SPADES
        Carta c3 = new Carta(1, 12); // A de HEARTS
        Carta c4 = new Carta(0, 12); // A de SPADES (igual que c1)

        // Según implementación: A (14) > Q (12) ⇒ c1.compareTo(c2) == -2
        assertEquals(-2, c1.compareTo(c2));
        assertEquals(2, c2.compareTo(c1));   // Q < A

        // Ambos A, pero SPADES < HEARTS ⇒ menor índice gana ⇒ c1.paloInt=0 < c3.paloInt=1 ⇒ resultado debe ser -1
        assertEquals(-1, c1.compareTo(c3));

        // Igual
        assertEquals(0, c1.compareTo(c4));
    }

    @Test
    void testComparadorPorNumero() {
        List<Carta> cartas = Arrays.asList(
                new Carta(0, 5), // 7
                new Carta(0, 11), // K
                new Carta(0, 2)  // 4
        );

        cartas.sort(new Carta.ComparadorPorNumero());

        assertEquals("K SPADES", cartas.get(0).toString());
        assertEquals("7 SPADES", cartas.get(1).toString());
        assertEquals("4 SPADES", cartas.get(2).toString());
    }

    @Test
    void testComparadorPorPalo() {
        List<Carta> cartas = Arrays.asList(
                new Carta(3, 5), // 7 DIAMONDS
                new Carta(2, 6), // 8 CLUBS
                new Carta(0, 7), // 9 SPADES
                new Carta(1, 8)  // 10 HEARTS
        );

        cartas.sort(new Carta.ComparadorPorPalo());

        assertEquals("10 HEARTS", cartas.get(1).toString());
        assertEquals("8 CLUBS", cartas.get(2).toString());
        assertEquals("7 DIAMONDS", cartas.get(3).toString());
        assertEquals("9 SPADES", cartas.get(0).toString());
    }

    @Test
    void testSpritePuedeSerNullSinFallo() {
        // Aunque no cargue el sprite por falta de archivo, no debe lanzar excepción
        Carta carta = new Carta(0, 0);
        assertDoesNotThrow(() -> carta.getSprite());
    }

    static class CartaFalsa extends Carta {
        public CartaFalsa() {
            super(0, 0);
            this.palo = "INVENTADO";
        }
    }

    @Test
    void testPaloInvalidoGetPaloInt() {
        Carta carta = new CartaFalsa();
        assertEquals(-1, carta.getPaloInt());
    }
    
    static class CartaFalsaNumero extends Carta {
        public CartaFalsaNumero() {
            super(0, 0);
            this.numero = "Ñ";
        }
    }

    @Test
    void testNumeroInvalidoGetNumeroInt() {
    	Carta carta = new CartaFalsaNumero();
    	assertEquals(-1, carta.getNumeroInt());
    }


}
