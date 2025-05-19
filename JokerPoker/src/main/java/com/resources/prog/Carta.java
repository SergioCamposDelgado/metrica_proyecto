package com.resources.prog;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 * @author Sergio Campos Delgado
 */
public class Carta implements Comparable<Carta> {

    public static final String[] numeros = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    public static final String[] palos = {"SPADES", "HEARTS", "CLUBS", "DIAMONDS"};

    protected String numero;
    protected String palo;
    protected BufferedImage sprite;

    private static final CardSpriteLoader loader;

    static {
        CardSpriteLoader temp = null;
        try {
            temp = new CardSpriteLoader("8BitDeck.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        loader = temp;
    }

    public Carta(int paloIndex, int numeroIndex) {
        this.palo = palos[paloIndex];
        this.numero = numeros[numeroIndex];
        if (loader != null) {
            try {
                this.sprite = loader.getCard(this.palo.toLowerCase(), this.numero.toUpperCase());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                this.sprite = null;
            }
        }
    }

    public String getNumero() {
        return numero;
    }

    public String getPalo() {
        return palo;
    }

    public int getNumeroInt() {
        switch (numero) {
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "10":
                return 10;
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            case "A":
                return 14;
            default:
                return -1;
        }
    }

    public int getPaloInt() {
        for (int i = 0; i < palos.length; i++) {
            if (palos[i].equals(palo)) {
                return i;
            }
        }
        return -1;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    @Override
    public String toString() {
        return numero + " " + palo;
    }

    @Override
    public int compareTo(Carta otro) {
        int n1 = this.getNumeroInt(), n2 = otro.getNumeroInt();
        int p1 = this.getPaloInt(), p2 = otro.getPaloInt();

        if (n1 > n2) {
            return -2;
        }
        if (n2 > n1) {
            return 2;
        }
        if (p1 > p2) {
            return 1;
        }
        if (p2 > p1) {
            return -1;
        }
        return 0;
    }

    public static class ComparadorPorNumero implements Comparator<Carta> {

        @Override
        public int compare(Carta c1, Carta c2) {
            return c1.compareTo(c2);
        }
    }

    public static class ComparadorPorPalo implements Comparator<Carta> {

        @Override
        public int compare(Carta c1, Carta c2) {
            int p1 = c1.getPaloInt(), p2 = c2.getPaloInt();
            int n1 = c1.getNumeroInt(), n2 = c2.getNumeroInt();

            if (p1 > p2) {
                return 2;
            }
            if (p2 > p1) {
                return -2;
            }
            if (n1 > n2) {
                return -1;
            }
            if (n2 > n1) {
                return 1;
            }
            return 0;
        }
    }

    public static class CardSpriteLoader {

        private final BufferedImage[][] cards;
        private static BufferedImage fondoCartas;
        private static BufferedImage fondoCarta;
        private static BufferedImage topJoker;
        private static BufferedImage topPlayer;

        static {
            BufferedImage fondoCartas = null;
            BufferedImage fondoCarta = null;
            BufferedImage topJoker = null;
            BufferedImage topPlayer = null;
            try {
                fondoCartas = ImageIO.read(new File("fondoCartas.png"));
                fondoCarta = ImageIO.read(new File("fondoCarta.png"));
                topJoker = ImageIO.read(new File("topJoker.png"));
                topPlayer = ImageIO.read(new File("topPlayer.png"));
            } catch (IOException e) {
                System.out.println("Cartas.CardSpriteLoader: ");
                e.printStackTrace();
            }
            CardSpriteLoader.fondoCartas = fondoCartas;
            CardSpriteLoader.fondoCarta = fondoCarta;
            CardSpriteLoader.topJoker = topJoker;
            CardSpriteLoader.topPlayer = topPlayer;
        }

        static private final Map<String, Integer> paloMap = Map.of(
                "spades", 3, // Fila 3 en la imagen
                "hearts", 0, // Fila 0 en la imagen
                "clubs", 1, // Fila 1 en la imagen
                "diamonds", 2 // Fila 2 en la imagen
        );

        private final Map<String, Integer> valorMap = Map.ofEntries(
                Map.entry("2", 0),
                Map.entry("3", 1),
                Map.entry("4", 2),
                Map.entry("5", 3),
                Map.entry("6", 4),
                Map.entry("7", 5),
                Map.entry("8", 6),
                Map.entry("9", 7),
                Map.entry("10", 8),
                Map.entry("J", 9),
                Map.entry("Q", 10),
                Map.entry("K", 11),
                Map.entry("A", 12)
        );

        public CardSpriteLoader(String imagenPath) throws IOException {
            BufferedImage deckImagen = ImageIO.read(new File(imagenPath));
            int rows = 4;
            int cols = 13;

            int cardWidth = deckImagen.getWidth() / cols;
            int cardHeight = deckImagen.getHeight() / rows;

            cards = new BufferedImage[rows][cols];
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    cards[row][col] = deckImagen.getSubimage(
                            col * cardWidth,
                            row * cardHeight,
                            cardWidth,
                            cardHeight
                    );
                }
            }

        }

        public BufferedImage getCard(String palo, String valor) {
            Integer row = paloMap.get(palo.toLowerCase());
            Integer col = valorMap.get(valor.toUpperCase());
            if (row == null || col == null) {
                throw new IllegalArgumentException("Palo o número inválido: " + palo + ", " + valor);
            }
            return cards[row][col];
        }

        public static BufferedImage getFondoCartas() {
            return fondoCartas;
        }

        public static BufferedImage getFondoCarta() {
            return fondoCarta;
        }

        public static BufferedImage getTopJoker() {
            return topJoker;
        }

        public static BufferedImage getTopPlayer() {
            return topPlayer;
        }
    }
}
