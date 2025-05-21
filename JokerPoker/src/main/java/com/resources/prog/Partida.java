package com.resources.prog;

import com.resources.dao.DAOPartida;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Comparator;

/**
 * Representa una partida de juego entre un usuario y la aplicación.
 * Contiene información sobre el usuario, si ganó o no, la fecha y un ID único.
 * 
 * @author Sergio Campos Delgado
 */
public class Partida implements Comparable<Partida> {

    /** Identificador único de la partida */
    protected int id;

    /** Usuario que participó en la partida */
    protected Usuario user;

    /** Indica si el usuario ganó la partida */
    protected boolean userWins;

    /** Fecha en la que se jugó la partida */
    protected Date date;

    /**
     * Constructor completo de la clase Partida.
     *
     * @param id Identificador de la partida.
     * @param user Usuario que jugó la partida.
     * @param userWins {@code true} si el usuario ganó; {@code false} en caso contrario.
     * @param date Fecha de la partida.
     */
    public Partida(int id, Usuario user, boolean userWins, Date date) {
        this.id = id;
        this.user = user;
        this.userWins = userWins;
        this.date = date;
    }

    /**
     * Constructor que genera automáticamente el ID y la fecha.
     * El ID se obtiene como el máximo ID existente + 1.
     * La fecha se establece como la fecha actual.
     *
     * @param user Usuario que jugó la partida.
     * @param userWins {@code true} si el usuario ganó; {@code false} en caso contrario.
     */
    public Partida(Usuario user, boolean userWins) {
        this.id = new DAOPartida().getMaxID() + 1;
        this.user = user;
        this.userWins = userWins;
        this.date = Date.valueOf(LocalDate.now());
    }

    /** @return ID de la partida */
    public int getId() {
        return id;
    }

    /** @return Usuario de la partida */
    public Usuario getUser() {
        return user;
    }

    /** @return {@code true} si el usuario ganó, {@code false} si perdió */
    public boolean isUserWins() {
        return userWins;
    }

    /** @return Fecha de la partida */
    public Date getDate() {
        return date;
    }

    /**
     * Establece el ID de la partida.
     * @param id Nuevo identificador.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Establece el usuario de la partida.
     * @param user Nuevo usuario.
     */
    public void setUser(Usuario user) {
        this.user = user;
    }

    /**
     * Establece si el usuario ganó o no.
     * @param userWins {@code true} si ganó; {@code false} si perdió.
     */
    public void setUserWins(boolean userWins) {
        this.userWins = userWins;
    }

    /**
     * Establece la fecha de la partida.
     * @param date Nueva fecha.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Devuelve una representación textual de la partida.
     * 
     * @return Cadena con los datos: ID, nombre de usuario, resultado y fecha.
     */
    @Override
    public String toString() {
        String s = "";
        s += id + " ";
        s += user.getUserName() + " ";
        s += userWins + " ";
        s += date;
        return s;
    }

    /**
     * Compara dos partidas por su ID.
     *
     * @param o Otra partida a comparar.
     * @return Valor negativo si esta es menor, positivo si es mayor, 0 si son iguales.
     */
    @Override
    public int compareTo(Partida o) {
        return Integer.compare(this.getId(), o.getId());
    }

    /**
     * Comparador estático por ID.
     */
    public static class ComparadorID implements Comparator<Partida> {
        /**
         * Compara dos partidas por su ID.
         *
         * @param p1 Primera partida.
         * @param p2 Segunda partida.
         * @return Valor negativo si p1 < p2, positivo si p1 > p2, 0 si son iguales.
         */
        @Override
        public int compare(Partida p1, Partida p2) {
            return Integer.compare(p1.getId(), p2.getId());
        }
    }

    /**
     * Comparador estático por fecha.
     */
    public static class ComparadorDate implements Comparator<Partida> {
        /**
         * Compara dos partidas por su fecha.
         *
         * @param p1 Primera partida.
         * @param p2 Segunda partida.
         * @return Valor negativo si p1 es anterior, positivo si es posterior, 0 si son iguales.
         */
        @Override
        public int compare(Partida p1, Partida p2) {
            return p1.getDate().compareTo(p2.getDate());
        }
    }

}
