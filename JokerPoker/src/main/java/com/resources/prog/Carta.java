package com.resources.prog;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 * Representa una carta de una baraja estándar con palo, valor y sprite asociado.
 * Proporciona métodos de comparación y acceso a los atributos de la carta.
 * 
 * También contiene clases internas para cargar los sprites de las cartas y realizar comparaciones personalizadas.
 * 
 * @author Sergio Campos Delgado
 */
public class Carta implements Comparable<Carta> {

    /** Arreglo con los valores de las cartas en orden ascendente. */
    public static final String[] numeros = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

    /** Arreglo con los palos de las cartas. */
    public static final String[] palos = {"SPADES", "HEARTS", "CLUBS", "DIAMONDS"};

    /** Valor de la carta (por ejemplo, "A", "10", "J"). */
    protected String numero;

    /** Palo de la carta (por ejemplo, "HEARTS", "SPADES"). */
    protected String palo;

    /** Imagen (sprite) de la carta. */
    protected BufferedImage sprite;

    /** Cargador de sprites de cartas. */
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

    /**
     * Crea una nueva carta a partir de índices de palo y número.
     * 
     * @param paloIndex Índice del palo en el arreglo {@link #palos}.
     * @param numeroIndex Índice del número en el arreglo {@link #numeros}.
     */
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

    /**
     * Devuelve el valor de la carta como cadena.
     * 
     * @return Valor de la carta.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Devuelve el palo de la carta.
     * 
     * @return Palo de la carta.
     */
    public String getPalo() {
        return palo;
    }

    /**
     * Devuelve el valor numérico de la carta para propósitos de comparación.
     * 
     * @return Valor entero de la carta (de 2 a 14).
     */
    public int getNumeroInt() {
        switch (numero) {
            case "2": return 2;
            case "3": return 3;
            case "4": return 4;
            case "5": return 5;
            case "6": return 6;
            case "7": return 7;
            case "8": return 8;
            case "9": return 9;
            case "10": return 10;
            case "J": return 11;
            case "Q": return 12;
            case "K": return 13;
            case "A": return 14;
            default: return -1;
        }
    }

    /**
     * Devuelve el índice del palo de la carta.
     * 
     * @return Índice del palo (0 a 3), o -1 si no se encuentra.
     */
    public int getPaloInt() {
        for (int i = 0; i < palos.length; i++) {
            if (palos[i].equals(palo)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Devuelve la imagen asociada a la carta.
     * 
     * @return Sprite de la carta.
     */
    public BufferedImage getSprite() {
        return sprite;
    }

    @Override
    public String toString() {
        return numero + " " + palo;
    }

    /**
     * Compara esta carta con otra según su valor y luego por su palo.
     * 
     * @param otro Carta a comparar.
     * @return Entero indicando el orden: -2, -1, 0, 1, o 2.
     */
    @Override
    public int compareTo(Carta otro) {
        int n1 = this.getNumeroInt(), n2 = otro.getNumeroInt();
        int p1 = this.getPaloInt(), p2 = otro.getPaloInt();

        if (n1 > n2) return -2;
        if (n2 > n1) return 2;
        if (p1 > p2) return 1;
        if (p2 > p1) return -1;
        return 0;
    }

    /**
     * Comparador que ordena las cartas principalmente por su valor.
     */
    public static class ComparadorPorNumero implements Comparator<Carta> {
        @Override
        public int compare(Carta c1, Carta c2) {
            return c1.compareTo(c2);
        }
    }

    /**
     * Comparador que ordena las cartas principalmente por su palo.
     */
    public static class ComparadorPorPalo implements Comparator<Carta> {
        @Override
        public int compare(Carta c1, Carta c2) {
            int p1 = c1.getPaloInt(), p2 = c2.getPaloInt();
            int n1 = c1.getNumeroInt(), n2 = c2.getNumeroInt();

            if (p1 > p2) return 2;
            if (p2 > p1) return -2;
            if (n1 > n2) return -1;
            if (n2 > n1) return 1;
            return 0;
        }
    }

    /**
     * Cargador de sprites de cartas desde una imagen.
     * Divide una imagen en subimágenes representando cada carta individual.
     */
    public static class CardSpriteLoader {

        private final BufferedImage[][] cards;
        private static BufferedImage fondoCartas;
        private static BufferedImage fondoCarta;
        private static BufferedImage topJoker;
        private static BufferedImage topPlayer;

        // Carga imágenes adicionales utilizadas en la interfaz
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

        /** Mapeo de nombres de palos a índices de fila en la imagen. */
        static private final Map<String, Integer> paloMap = Map.of(
                "spades", 3,
                "hearts", 0,
                "clubs", 1,
                "diamonds", 2
        );

        /** Mapeo de valores de cartas a índices de columna en la imagen. */
        static private final Map<String, Integer> valorMap = Map.ofEntries(
                Map.entry("2", 0), Map.entry("3", 1), Map.entry("4", 2), Map.entry("5", 3),
                Map.entry("6", 4), Map.entry("7", 5), Map.entry("8", 6), Map.entry("9", 7),
                Map.entry("10", 8), Map.entry("J", 9), Map.entry("Q", 10), Map.entry("K", 11), Map.entry("A", 12)
        );

        /**
         * Constructor que carga la imagen de las cartas y la divide en una matriz de sprites.
         * 
         * @param imagenPath Ruta de la imagen de la baraja.
         * @throws IOException Si ocurre un error al leer la imagen.
         */
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

        /**
         * Devuelve el sprite correspondiente a un valor y palo específico.
         * 
         * @param palo Nombre del palo (ej. "hearts").
         * @param valor Valor de la carta (ej. "K").
         * @return Imagen de la carta.
         * @throws IllegalArgumentException Si el palo o el valor no existen.
         */
        public BufferedImage getCard(String palo, String valor) {
            Integer row = paloMap.get(palo.toLowerCase());
            Integer col = valorMap.get(valor.toUpperCase());
            if (row == null || col == null) {
                throw new IllegalArgumentException("Palo o numero invalido: " + palo + ", " + valor);
            }
            return cards[row][col];
        }

        /** @return Imagen de fondo para todas las cartas. */
        public static BufferedImage getFondoCartas() {
            return fondoCartas;
        }

        /** @return Imagen del fondo de una carta individual. */
        public static BufferedImage getFondoCarta() {
            return fondoCarta;
        }

        /** @return Imagen decorativa del Joker. */
        public static BufferedImage getTopJoker() {
            return topJoker;
        }

        /** @return Imagen decorativa para el jugador. */
        public static BufferedImage getTopPlayer() {
            return topPlayer;
        }
    }
}
