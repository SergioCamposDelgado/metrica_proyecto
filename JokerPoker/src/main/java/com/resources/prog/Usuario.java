/*

 Clase Usuario.

 */


package com.resources.prog;

/**
 *
 * @author Sergio Campos Delgado
 */
public class Usuario {

    protected String userName;
    protected String passwd;
    protected String name;
    protected double balance;
    protected boolean esAdmin;

    /**
     * dinero inical por defecto
     */
    protected static final double initialBalance = 20.00;

    public Usuario(String userName, String passwd, String name, double balance, boolean esAdmin) {
        this.userName = userName;
        this.passwd = passwd;
        this.name = name;
        this.balance = balance;
        this.esAdmin = esAdmin;
    }

    public String getUserName() {
        return userName;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public boolean esAdmin() {
        return esAdmin;
    }

    public static double getInitialBalance() {
        return initialBalance;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    @Override
    public String toString() {
        return name + "\t" + balance;
    }

}
