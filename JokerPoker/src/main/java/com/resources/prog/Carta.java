package com.resources.prog;

import java.util.Comparator;

/**
 *
 * @author Sergio Campos Delgado
 */
public class Carta implements Comparable {

    public static final char[] numeros = {'A', 'K', 'Q', 'J', '7', '6', '5', '4', '3', '2'};
    public static final String[] palos = {"HEARTS", "SPADES", "CLUBS", "DIAMONDS"};

    protected char numero;
    protected String palo;

    public Carta(int palo, int numero) {
        this.palo = palos[palo];
        this.numero = numeros[numero];
    }

    public char getNumero() {
        return numero;
    }

    public String getPalo() {
        return palo;
    }

    public int getNumeroInt() {
        int res = -1;
        for (int i = 0; i < numeros.length && res == -1; i++) {
            if (numeros[i] == numero) {
                res = i;
            }
        }
        return res;
    }

    public int getPaloInt() {
        int res = -1;
        for (int i = 0; i < palos.length && res == -1; i++) {
            if (palos[i].equals(palo)) {
                res = i;
            }
        }
        return res;
    }

    @Override
    public String toString() {

        String s = "";
        s += numero;
        s += " ";
        s += palo;

        return s;
    }

    @Override
    public int compareTo(Object o) {
        Carta otro = (Carta) o;
        int n1 = this.getNumeroInt(), n2 = otro.getNumeroInt(), p1 = this.getPaloInt(), p2 = otro.getPaloInt();

        if (n1 > n2) {
            return 2;
        } else if (n2 > n1) {
            return -2;
        } else if (p1 > p2) {
            return 1;
        } else if (p2 > p1) {
            return -1;
        } else {
            return 0;
        }
    }

    public class ComparadorPorNumero implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            Carta c1 = (Carta) o1;
            Carta c2 = (Carta) o2;
            
            int n1 = c1.getNumeroInt(), n2 = c2.getNumeroInt(), p1 = c1.getPaloInt(), p2 = c2.getPaloInt();

            if (n1 > n2) {
                return 2;
            } else if (n2 > n1) {
                return -2;
            } else if (p1 > p2) {
                return 1;
            } else if (p2 > p1) {
                return -1;
            } else {
                return 0;
            }
        }

    }

    public class ComparadorPorPalo implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            Carta c1 = (Carta) o1;
            Carta c2 = (Carta) o2;

            int n1 = c1.getNumeroInt(), n2 = c2.getNumeroInt(), p1 = c1.getPaloInt(), p2 = c2.getPaloInt();
            
            if (p1 > p2) {
                return 2;
            } else if (p2 > p1) {
                return -2;
            } else if (n1 > n2) {
                return 1;
            } else if (n2 > n1) {
                return -1;
            } else {
                return 0;
            }

        }

    }
}
