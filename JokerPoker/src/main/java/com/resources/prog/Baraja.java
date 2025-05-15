package com.resources.prog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Baraja {

    private List<Carta> cartas;

    public Baraja() {
        cartas = new ArrayList<>();
        for (int palo = 0; palo < Carta.palos.length; palo++) {
            for (int numero = 0; numero < Carta.numeros.length; numero++) {
                cartas.add(new Carta(palo, numero));
            }
        }
    }

    public void barajar() {
        Collections.shuffle(cartas);
    }

    public Carta repartir() {
        return cartas.remove(0);
    }

    public List<Carta> repartirMano(int cantidad) {
        List<Carta> mano = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            mano.add(repartir());
        }
        return mano;
    }

    public boolean estaVacia() {
        return cartas.isEmpty();
    }

    // Evaluador de manos básicas (para 5 cartas tipo poker)
    public static int evaluarMano(List<Carta> mano) {
        Map<String, Integer> conteoNumeros = new HashMap<>();
        Map<String, Integer> conteoPalos = new HashMap<>();
        List<Integer> valores = new ArrayList<>();

        for (Carta c : mano) {
            conteoNumeros.put(c.getNumero(), conteoNumeros.getOrDefault(c.getNumero(), 0) + 1);
            conteoPalos.put(c.getPalo(), conteoPalos.getOrDefault(c.getPalo(), 0) + 1);
            valores.add(c.getNumeroInt());
        }

        Collections.sort(valores); // Ascendente

        // Detectar escalera normal
        boolean escalera = true;
        boolean escaleraBaja = false;
        for (int i = 1; i < valores.size(); i++) {
            if (valores.get(i) != valores.get(i - 1) + 1) {
                escalera = false;
                break;
            }
        }

        if (!escalera
                && valores.contains(14)
                && valores.contains(2)
                && valores.contains(3)
                && valores.contains(4)
                && valores.contains(5)) {

            escaleraBaja = true;
        }

        boolean color = conteoPalos.size() == 1;

        if (escalera && color) {
            return 10; // Escalera de color
        }

        if (escaleraBaja && color) {
            return 9; // Escalera de color baja
        }
        if (conteoNumeros.containsValue(4)) {
            return 8; // Poker
        }
        if (conteoNumeros.containsValue(3) && conteoNumeros.containsValue(2)) {
            return 7; // Full House
        }
        if (color) {
            return 6; // Color
        }
        if (escalera) {
            return 5; // Escalera
        }

        if (escaleraBaja) {
            return 4; // Escalera Baja
        }
        if (conteoNumeros.containsValue(3)) {
            return 3; // Trío
        }
        if (Collections.frequency(new ArrayList<>(conteoNumeros.values()), 2) == 2) {
            return 2; // Doble pareja
        }
        if (conteoNumeros.containsValue(2)) {
            return 1; // Pareja
        }
        return 0; // Carta alta
    }

    public static String descripcionMano(int valor) {
        String res;
        switch (valor) {
            case 10:
                res = "Escalera de color";
                break;
            case 9:
                res = "Escalera de color baja";
                break;
            case 8:
                res = "Poker";
                break;
            case 7:
                res = "Full House";
                break;
            case 6:
                res = "Color";
                break;
            case 5:
                res = "Escalera";
                break;
            case 4:
                res = "Escalera baja";
                break;
            case 3:
                res = "Trío";
                break;
            case 2:
                res = "Doble pareja";
                break;
            case 1:
                res = "Pareja";
                break;
            default:
                res = "Carta alta";
                break;
        };
        return res;
    }

    public static int compararManos(List<Carta> mano1, List<Carta> mano2) {
        int valor1 = evaluarMano(mano1);
        int valor2 = evaluarMano(mano2);

        // Si las manos tienen valores diferentes (Escalera, Full House, etc.)
        if (valor1 != valor2) {
            return Integer.compare(valor2, valor1);
        }

        // Si ambas manos son Carta alta (valor 0), comparamos los kickers
        List<Integer> valores1 = obtenerValoresOrdenados(mano1);
        List<Integer> valores2 = obtenerValoresOrdenados(mano2);

        for (int i = 0; i < valores1.size(); i++) {
            int cmp = Integer.compare(valores2.get(i), valores1.get(i));
            if (cmp != 0) {
                return cmp;
            }
        }

        // Si ambas manos tienen el mismo valor y las cartas son iguales (total empate)
        return 0;
    }

    // Función auxiliar para obtener los valores de las cartas ordenados
    private static List<Integer> obtenerValoresOrdenados(List<Carta> mano) {
        List<Integer> valores = mano.stream()
                .map(c -> c.getNumeroInt())
                .sorted((a, b) -> b - a) // Orden descendente
                .collect(Collectors.toList());
        return valores;
    }
}
