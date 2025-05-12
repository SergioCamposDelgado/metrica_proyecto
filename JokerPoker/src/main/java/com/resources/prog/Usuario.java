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

    /**
     * dinero inical por defecto
     */
    protected static final double initialBalance = 20.00;

    public Usuario(String userName, String passwd, String name, double balance) {
        this.userName = userName;
        this.passwd = passwd;
        this.name = name;
        this.balance = balance;
    }

    public String getUserName() {
        return userName;
    }

    private String getPasswd() {
        return passwd;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
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

    @Override
    public String toString() {
        return name + "\t" + balance;
    }

}
