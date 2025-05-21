package com.resources.prog;

import java.util.Comparator;

/**
 *
 * @author Sergio Campos Delgado
 */
public class Usuario implements Comparable <Usuario>{

    protected String userName;
    protected String passwd;
    protected String name;
    protected double balance;
    protected boolean esAdmin;

    /**
     * dinero inical por defecto
     */
    protected static final double initialBalance = 100.00;

    public Usuario(String userName, String passwd, String name, double balance, boolean esAdmin) {
        setUserName(userName);
        setPasswd(passwd);
        setName(name);
        setBalance(balance);
        setEsAdmin(esAdmin);
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
        if (userName.length()<25){
           this.userName = userName; 
            
            
        } else {
            
            throw new IllegalArgumentException("Usuario, setUsername, El nombre es demasiado largo.");
            
        }
    }

    public void setPasswd(String passwd) {
         if (passwd.length()<25){
           this.passwd = passwd; 
            
            
        } else {
            
            throw new IllegalArgumentException("Usuario, setPasswd, La contraseña es demasiado largo.");
            
        }
        
    }

    public void setName(String name) {
        if (name.length()<25){
           this.name = name; 
            
            
        } else {
            
            throw new IllegalArgumentException("Usuario, setName, El nombre es demasiado largo.");
            
        }
        
        
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    @Override
    public String toString() {
        return userName + " \t" + name + " \t" + balance + " \t" + (esAdmin?"admin":"usuario");
    }

    public int compareTo(Usuario o) {
        return this.getUserName().toLowerCase().compareTo(o.getUserName().toLowerCase());
   }

    public static class ComparadorNombre implements Comparator<Usuario> {

        public int compare(Usuario u1, Usuario u2) {
            return u1.getName().toLowerCase().compareTo(u2.getName().toLowerCase());
        }

    }
    
    public static class ComparadorUserName implements Comparator<Usuario> {

        public int compare(Usuario u1, Usuario u2) {
            return u1.getUserName().toLowerCase().compareTo(u2.getUserName().toLowerCase());
        }

    }
    
    public static class ComparadorBalance implements Comparator<Usuario> {

        public int compare(Usuario u1, Usuario u2) {
            return - Double.compare(u1.balance, u2.balance);
        }

    }
    
    
    

}
