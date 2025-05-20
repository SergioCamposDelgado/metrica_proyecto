package com;

import com.resources.dao.DAOPartida;
import com.resources.dao.DAOUsuario;
import java.util.List;
import com.resources.prog.Baraja;
import com.resources.prog.Carta;
import com.resources.prog.Partida;
import com.resources.prog.Usuario;
import java.sql.Date;
import java.time.LocalDate;

public class App {

    public static void main(String[] args) {

        List<Partida> lista = new DAOPartida().getPartidas();
        
        lista.forEach(System.out::println);
        
        new DAOUsuario().insertUsuario(new Usuario("sergioscd", "123", "Sergio", 200.0, false));
        
        Usuario u = new DAOUsuario().getUsuario("sergioscd");
        u.setBalance(230.0);
        new DAOUsuario().setBalance(u);
    }

    public static void probar(String titulo, List<Carta> mano1, List<Carta> mano2) {
        System.out.println("=== " + titulo + " ===");

        System.out.println("Mano 1:");
        mano1.forEach(System.out::println);
        int v1 = Baraja.evaluarMano(mano1);
        System.out.println("Valor: " + v1 + " (" + Baraja.descripcionMano(v1) + ")\n");

        System.out.println("Mano 2:");
        mano2.forEach(System.out::println);
        int v2 = Baraja.evaluarMano(mano2);
        System.out.println("Valor: " + v2 + " (" + Baraja.descripcionMano(v2) + ")\n");

        int cmp = Baraja.compararManos(mano1, mano2);
        if (cmp < 0) {
            System.out.println("Gana Mano 1\n");
        } else if (cmp > 0) {
            System.out.println("Gana Mano 2\n");
        } else {
            System.out.println("Empate\n");
        }

        System.out.println();
    }

    public void probarPartidas() {

        probar("Par bajo vs Par alto",
                List.of(
                        new Carta(0, 12), // A SPADES
                        new Carta(1, 12), // A HEARTS
                        new Carta(2, 5), // 7 CLUBS
                        new Carta(3, 9), // J DIAMONDS
                        new Carta(0, 8) // 10 SPADES
                ),
                List.of(
                        new Carta(2, 11), // K CLUBS
                        new Carta(3, 11), // K DIAMONDS
                        new Carta(0, 4), // 6 SPADES
                        new Carta(1, 7), // 9 HEARTS
                        new Carta(2, 6) // 8 CLUBS
                )
        );

        probar("Trio bajo vs Trio alto",
                List.of(
                        new Carta(2, 11), // K CLUBS
                        new Carta(3, 11), // K DIAMONDS
                        new Carta(0, 4), // 6 SPADES
                        new Carta(1, 11), // K HEARTS
                        new Carta(2, 6) // 8 CLUBS
                ), List.of(
                new Carta(0, 12), // A SPADES
                new Carta(1, 12), // A HEARTS
                new Carta(2, 5), // 7 CLUBS
                new Carta(3, 12), // A DIAMONDS
                new Carta(0, 8) // 10 SPADES
        )
        );

        probar("Poker bajo vs Poker alto",
                List.of(
                        new Carta(2, 11), // K CLUBS
                        new Carta(3, 11), // K DIAMONDS
                        new Carta(0, 11), // K SPADES
                        new Carta(1, 11), // K HEARTS
                        new Carta(2, 6) // 8 CLUBS
                ), List.of(
                new Carta(0, 12), // A SPADES
                new Carta(1, 12), // A HEARTS
                new Carta(2, 12), // A CLUBS
                new Carta(3, 12), // A DIAMONDS
                new Carta(0, 8) // 10 SPADES
        )
        );

        probar("Doble pareja baja vs Doble pareja alta",
                List.of(
                        new Carta(0, 10), // Q SPADES
                        new Carta(1, 10), // Q HEARTS
                        new Carta(2, 4), // 6 CLUBS
                        new Carta(3, 4), // 6 DIAMONDS
                        new Carta(0, 7) // 9 SPADES
                ),
                List.of(
                        new Carta(0, 10), // Q SPADES
                        new Carta(1, 10), // Q HEARTS
                        new Carta(2, 3), // 5 CLUBS
                        new Carta(3, 3), // 5 DIAMONDS
                        new Carta(0, 7) // 9 SPADES
                )
        );

        probar("Full House vs Full House",
                List.of(
                        new Carta(0, 11), // K
                        new Carta(1, 11),
                        new Carta(2, 11),
                        new Carta(3, 12), // 4
                        new Carta(0, 12) // 4
                ),
                List.of(
                        new Carta(0, 11), // K
                        new Carta(1, 11),
                        new Carta(2, 11),
                        new Carta(3, 5), // 4
                        new Carta(0, 5) // 4
                )
        );

        probar("Tri­o vs Doble pareja",
                List.of(
                        new Carta(0, 9), // J SPADES
                        new Carta(1, 9), // J HEARTS
                        new Carta(2, 9), // J CLUBS
                        new Carta(2, 1), // 3 CLUBS
                        new Carta(3, 5) // 7 DIAMONDS
                ),
                List.of(
                        new Carta(0, 10), // Q SPADES
                        new Carta(1, 10), // Q HEARTS
                        new Carta(2, 3), // 5 CLUBS
                        new Carta(3, 3), // 5 DIAMONDS
                        new Carta(0, 7) // 9 SPADES
                )
        );

        probar("Escalera vs TrÃ­o",
                List.of(
                        new Carta(0, 4), // 6
                        new Carta(0, 5), // 7
                        new Carta(0, 6), // 8
                        new Carta(0, 7), // 9
                        new Carta(1, 8) // 10
                ),
                List.of(
                        new Carta(0, 9), // J
                        new Carta(1, 9),
                        new Carta(2, 9),
                        new Carta(2, 1), // 3
                        new Carta(3, 5) // 7
                )
        );

        probar("Color vs Escalera",
                List.of(
                        new Carta(2, 2), // 4 CLUBS
                        new Carta(2, 5), // 7 CLUBS
                        new Carta(2, 8), // 10 CLUBS
                        new Carta(2, 9), // J CLUBS
                        new Carta(2, 11) // K CLUBS
                ),
                List.of(
                        new Carta(1, 5), // 7
                        new Carta(0, 6), // 8
                        new Carta(2, 7), // 9
                        new Carta(1, 8), // 10
                        new Carta(3, 9) // J
                )
        );

        probar("Full House vs Poker",
                List.of(
                        new Carta(0, 10), // Q
                        new Carta(1, 10),
                        new Carta(2, 10),
                        new Carta(3, 1), // 3
                        new Carta(0, 1) // 3
                ),
                List.of(
                        new Carta(0, 9), // J
                        new Carta(1, 9),
                        new Carta(2, 9),
                        new Carta(3, 9),
                        new Carta(2, 4) // 6
                )
        );

        probar("Escalera de color vs Full House",
                List.of(
                        new Carta(3, 4), // 6 DIAMONDS
                        new Carta(3, 5), // 7
                        new Carta(3, 6), // 8
                        new Carta(3, 7), // 9
                        new Carta(3, 8) // 10
                ),
                List.of(
                        new Carta(0, 11), // K
                        new Carta(1, 11),
                        new Carta(2, 11),
                        new Carta(3, 2), // 4
                        new Carta(0, 2) // 4
                )
        );

        probar("Carta alta vs Carta alta (Kicker)",
                List.of(
                        new Carta(0, 12), // A
                        new Carta(1, 5), // 7
                        new Carta(2, 4), // 6
                        new Carta(3, 3), // 5
                        new Carta(0, 2) // 4
                ),
                List.of(
                        new Carta(0, 12), // A
                        new Carta(1, 4), // 6
                        new Carta(2, 3), // 5
                        new Carta(3, 2), // 4
                        new Carta(0, 1) // 3
                )
        );

        probar("Escalera baja vs Escalera alta",
                List.of(
                        new Carta(3, 12), // A
                        new Carta(1, 0), // 2
                        new Carta(3, 1), // 3
                        new Carta(3, 2), // 4
                        new Carta(3, 3) // 5
                ),
                List.of(
                        new Carta(3, 0), // 2
                        new Carta(1, 1), // 3
                        new Carta(3, 2), // 4
                        new Carta(3, 3), // 5
                        new Carta(3, 4) // 6
                )
        );

        probar("Escalera de color baja vs Escalera alta",
                List.of(
                        new Carta(3, 12), // A
                        new Carta(3, 0), // 2
                        new Carta(3, 1), // 3
                        new Carta(3, 2), // 4
                        new Carta(3, 3) // 5
                ),
                List.of(
                        new Carta(3, 0), // 2
                        new Carta(1, 1), // 3
                        new Carta(3, 2), // 4
                        new Carta(3, 3), // 5
                        new Carta(3, 4) // 6
                )
        );

        probar("Escalera de color baja vs Escalera de color alta",
                List.of(
                        new Carta(3, 12), // A
                        new Carta(3, 0), // 2
                        new Carta(3, 1), // 3
                        new Carta(3, 2), // 4
                        new Carta(3, 3) // 5
                ),
                List.of(
                        new Carta(3, 0), // 2
                        new Carta(3, 1), // 3
                        new Carta(3, 2), // 4
                        new Carta(3, 3), // 5
                        new Carta(3, 4) // 6
                )
        );

        probar("Empate",
                List.of(
                        new Carta(3, 12), // A
                        new Carta(3, 0), // 2
                        new Carta(3, 1), // 3
                        new Carta(3, 2), // 4
                        new Carta(3, 3) // 5
                ),
                List.of(
                        new Carta(3, 12), // A
                        new Carta(3, 0), // 2
                        new Carta(3, 1), // 3
                        new Carta(3, 2), // 4
                        new Carta(3, 3) // 5
                )
        );
    }
}
