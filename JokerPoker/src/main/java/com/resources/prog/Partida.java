/*

 Clase Partida.

 */
package com.resources.prog;

import com.resources.dao.DAOPartida;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Sergio Campos Delgado
 */
public class Partida {

    protected int id;
    protected Usuario user;
    protected boolean userWins;
    protected Date date;

    public Partida(int id, Usuario user, boolean userWins, Date date) {
        this.id = id;
        this.user = user;
        this.userWins = userWins;
        this.date = date;
    }

    public Partida(Usuario user, boolean userWins) {
        this.id = new DAOPartida().getMaxID() + 1;
        this.user = user;
        this.userWins = userWins;
        this.date = Date.valueOf(LocalDate.now());
    }

    public int getId() {
        return id;
    }

    public Usuario getUser() {
        return user;
    }

    public boolean isUserWins() {
        return userWins;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public void setUserWins(boolean userWins) {
        this.userWins = userWins;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public String toString() {
        String s = "";
        s+= id +" ";
        s+= user.getUserName() +" ";
        s+= userWins + " ";
        s+= date;
        return s;
    }

}
