package com.resources.prog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Representa una baraja de cartas estándar que permite barajar, repartir cartas y evaluar manos de tipo póker.
 * 
 * @author Sergio Campos Delgado
 */
public class Baraja {

    private List<Carta> cartas;

    /**
     * Constructor que inicializa una baraja completa con todas las combinaciones posibles de cartas.
     */
    public Baraja() {
        cartas = new ArrayList<>();
        for (int palo = 0; palo < Carta.palos.length; palo++) {
            for (int numero = 0; numero < Carta.numeros.length; numero++) {
                cartas.add(new Carta(palo, numero));
            }
        }
    }

    /**
     * Baraja aleatoriamente las cartas de la baraja.
     */
    public void barajar() {
        Collections.shuffle(cartas);
    }

    /**
     * Reparte una carta de la parte superior de la baraja.
     * 
     * @return La carta repartida.
     */
    public Carta repartir() {
        return cartas.remove(0);
    }

    /**
     * Reparte una mano de cartas con la cantidad especificada.
     *
     * @param cantidad Número de cartas a repartir.
     * @return Lista de cartas que forman la mano.
     */
    public List<Carta> repartirMano(int cantidad) {
        List<Carta> mano = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            mano.add(repartir());
        }
        return mano;
    }

    /**
     * Indica si la baraja está vacía.
     *
     * @return {@code true} si no quedan cartas en la baraja, {@code false} en caso contrario.
     */
    public boolean estaVacia() {
        return cartas.isEmpty();
    }

    /**
     * Evalúa el valor de una mano de 5 cartas según reglas básicas de póker.
     *
     * @param mano Lista de cartas a evaluar.
     * @return Valor numérico de la mano (entre 0 y 10).
     */
    public static int evaluarMano(List<Carta> mano) {
        Map<String, Integer> conteoNumeros = new HashMap<>();
        Map<String, Integer> conteoPalos = new HashMap<>();
        List<Integer> valores = new ArrayList<>();

        for (Carta c : mano) {
            conteoNumeros.put(c.getNumero(), conteoNumeros.getOrDefault(c.getNumero(), 0) + 1);
            conteoPalos.put(c.getPalo(), conteoPalos.getOrDefault(c.getPalo(), 0) + 1);
            valores.add(c.getNumeroInt());
        }

        Collections.sort(valores);

        boolean escalera = true;
        boolean escaleraBaja = false;
        for (int i = 1; i < valores.size(); i++) {
            if (valores.get(i) != valores.get(i - 1) + 1) {
                escalera = false;
                break;
            }
        }

        if (!escalera &&
            valores.contains(14) &&
            valores.contains(2) &&
            valores.contains(3) &&
            valores.contains(4) &&
            valores.contains(5)) {
            escaleraBaja = true;
        }

        boolean color = conteoPalos.size() == 1;

        if (escalera && color) return 10; // Escalera de color
        if (escaleraBaja && color) return 9; // Escalera de color baja
        if (conteoNumeros.containsValue(4)) return 8; // Poker
        if (conteoNumeros.containsValue(3) && conteoNumeros.containsValue(2)) return 7; // Full House
        if (color) return 6; // Color
        if (escalera) return 5; // Escalera
        if (escaleraBaja) return 4; // Escalera baja
        if (conteoNumeros.containsValue(3)) return 3; // Trío
        if (Collections.frequency(new ArrayList<>(conteoNumeros.values()), 2) == 2) return 2; // Doble pareja
        if (conteoNumeros.containsValue(2)) return 1; // Pareja

        return 0; // Carta alta
    }

    /**
     * Devuelve una descripción textual del valor de una mano.
     *
     * @param valor Valor numérico de la mano.
     * @return Descripción textual del tipo de jugada.
     */
    public static String descripcionMano(int valor) {
        String res;
        switch (valor) {
            case 10: res = "Escalera de color"; break;
            case 9: res = "Escalera de color baja"; break;
            case 8: res = "Poker"; break;
            case 7: res = "Full House"; break;
            case 6: res = "Color"; break;
            case 5: res = "Escalera"; break;
            case 4: res = "Escalera baja"; break;
            case 3: res = "Trio"; break;
            case 2: res = "Doble pareja"; break;
            case 1: res = "Pareja"; break;
            default: res = "Carta alta"; break;
        };
        return res;
    }

    /**
     * Compara dos manos de cartas y determina cuál es superior.
     *
     * @param mano1 Primera mano de cartas.
     * @param mano2 Segunda mano de cartas.
     * @return Valor negativo si mano2 es mejor, positivo si mano1 es mejor, 0 si hay empate.
     */
    public static int compararManos(List<Carta> mano1, List<Carta> mano2) {
        int valor1 = evaluarMano(mano1);
        int valor2 = evaluarMano(mano2);

        if (valor1 != valor2) {
            return Integer.compare(valor2, valor1);
        }

        if (valor1 == 0 || valor1 == 10 || valor1 == 5 || valor1 == 6) {
            List<Integer> valores1 = obtenerValoresOrdenados(mano1);
            List<Integer> valores2 = obtenerValoresOrdenados(mano2);

            for (int i = 0; i < valores1.size(); i++) {
                int cmp = Integer.compare(valores2.get(i), valores1.get(i));
                if (cmp != 0) return cmp;
            }
        }

        if (valor1 == 8 || valor1 == 3 || valor1 == 1) {
            List<Integer> valores1 = obtenerValoresOrdenados(mano1);
            List<Integer> valores2 = obtenerValoresOrdenados(mano2);

            int v1 = -1, v2 = -1;
            int paso = 0;
            for (int i = 0; i < valores1.size() - 1 && paso < 2; i++) {
                if (v1 == -1 && valores1.get(i).equals(valores1.get(i + 1))) {
                    v1 = valores1.get(i);
                    paso++;
                }
                if (v2 == -1 && valores2.get(i).equals(valores2.get(i + 1))) {
                    v2 = valores2.get(i);
                    paso++;
                }
            }
            int cmp = Integer.compare(v2, v1);
            if (cmp != 0) return cmp;
        }

        if (valor1 == 7 || valor1 == 2) {
            List<Integer> valores1 = obtenerValoresOrdenados(mano1);
            List<Integer> valores2 = obtenerValoresOrdenados(mano2);

            int v1 = -1, v2 = -1, v1b = -1, v2b = -1;
            for (int i = 0; i < valores1.size() - 1; i++) {
                if (v1 == -1 && valores1.get(i).equals(valores1.get(i + 1))) {
                    v1 = valores1.get(i);
                }
                if (v2 == -1 && valores2.get(i).equals(valores2.get(i + 1))) {
                    v2 = valores2.get(i);
                }
                if (v1b == -1 && valores1.get(i).equals(valores1.get(i + 1)) && v1 != valores1.get(i)) {
                    v1b = valores1.get(i);
                }
                if (v2b == -1 && valores2.get(i).equals(valores2.get(i + 1)) && v2 != valores2.get(i)) {
                    v2b = valores2.get(i);
                }
            }

            int cmp = (v1 != v2) ? Integer.compare(v2, v1) : Integer.compare(v2b, v1b);
            if (cmp != 0) return cmp;
        }

        return 0;
    }

    /**
     * Función auxiliar que ordena los valores numéricos de las cartas de una mano en orden descendente.
     *
     * @param mano Lista de cartas.
     * @return Lista de valores enteros ordenados.
     */
    private static List<Integer> obtenerValoresOrdenados(List<Carta> mano) {
        return mano.stream()
                .map(Carta::getNumeroInt)
                .sorted((a, b) -> b - a)
                .collect(Collectors.toList());
    }
}
