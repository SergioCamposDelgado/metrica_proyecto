package com.resources.prog;

import java.util.Comparator;

/**
 * Representa un usuario del sistema, con información como nombre de usuario,
 * contraseña, nombre real, balance económico y rol (administrador o usuario regular).
 * 
 * Ofrece validaciones básicas y múltiples comparadores para ordenar listas de usuarios.
 * 
 * @author Sergio Campos Delgado
 */
public class Usuario implements Comparable<Usuario> {

    /** Nombre de usuario (único) */
    protected String userName;

    /** Contraseña del usuario */
    protected String passwd;

    /** Nombre real del usuario */
    protected String name;

    /** Saldo actual del usuario */
    protected double balance;

    /** Indica si el usuario es administrador */
    protected boolean esAdmin;

    /** Dinero inicial por defecto para nuevos usuarios */
    protected static final double initialBalance = 100.00;

    /**
     * Constructor completo del usuario.
     *
     * @param userName Nombre de usuario.
     * @param passwd Contraseña.
     * @param name Nombre real.
     * @param balance Saldo inicial.
     * @param esAdmin {@code true} si es administrador, {@code false} si es usuario normal.
     */
    public Usuario(String userName, String passwd, String name, double balance, boolean esAdmin) {
        setUserName(userName);
        setPasswd(passwd);
        setName(name);
        setBalance(balance);
        setEsAdmin(esAdmin);
    }

    /** @return Nombre de usuario */
    public String getUserName() {
        return userName;
    }

    /** @return Contraseña del usuario */
    public String getPasswd() {
        return passwd;
    }

    /** @return Nombre real del usuario */
    public String getName() {
        return name;
    }

    /** @return Saldo del usuario */
    public double getBalance() {
        return balance;
    }

    /** @return {@code true} si es administrador, {@code false} si no */
    public boolean esAdmin() {
        return esAdmin;
    }

    /** @return Balance inicial por defecto */
    public static double getInitialBalance() {
        return initialBalance;
    }

    /**
     * Establece el nombre de usuario, validando su longitud.
     * @param userName Nuevo nombre de usuario (máximo 24 caracteres).
     * @throws IllegalArgumentException si excede el límite.
     */
    public void setUserName(String userName) {
        if (userName.length() < 25) {
            this.userName = userName;
        } else {
            throw new IllegalArgumentException("Usuario, setUsername, El nombre es demasiado largo.");
        }
    }

    /**
     * Establece la contraseña del usuario, validando su longitud.
     * @param passwd Nueva contraseña (máximo 24 caracteres).
     * @throws IllegalArgumentException si excede el límite.
     */
    public void setPasswd(String passwd) {
        if (passwd.length() < 25) {
            this.passwd = passwd;
        } else {
            throw new IllegalArgumentException("Usuario, setPasswd, La contraseña es demasiado larga.");
        }
    }

    /**
     * Establece el nombre real del usuario, validando su longitud.
     * @param name Nombre real (máximo 24 caracteres).
     * @throws IllegalArgumentException si excede el límite.
     */
    public void setName(String name) {
        if (name.length() < 25) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Usuario, setName, El nombre es demasiado largo.");
        }
    }

    /**
     * Establece el saldo del usuario.
     * @param balance Nuevo saldo.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Establece si el usuario es administrador.
     * @param esAdmin {@code true} si lo es, {@code false} en caso contrario.
     */
    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    /**
     * Devuelve una representación en texto del usuario.
     * 
     * @return Cadena con formato: userName, nombre, saldo y rol (admin/usuario).
     */
    @Override
    public String toString() {
        return userName + " \t" + name + " \t" + balance + " \t" + (esAdmin ? "admin" : "usuario");
    }

    /**
     * Compara dos usuarios por nombre de usuario (insensible a mayúsculas).
     *
     * @param o Usuario a comparar.
     * @return Negativo si este es menor, positivo si es mayor, 0 si son iguales.
     */
    @Override
    public int compareTo(Usuario o) {
        return this.getUserName().toLowerCase().compareTo(o.getUserName().toLowerCase());
    }

    /**
     * Comparador estático por nombre real del usuario.
     */
    public static class ComparadorNombre implements Comparator<Usuario> {
        @Override
        public int compare(Usuario u1, Usuario u2) {
            return u1.getName().toLowerCase().compareTo(u2.getName().toLowerCase());
        }
    }

    /**
     * Comparador estático por nombre de usuario.
     */
    public static class ComparadorUserName implements Comparator<Usuario> {
        @Override
        public int compare(Usuario u1, Usuario u2) {
            return u1.getUserName().toLowerCase().compareTo(u2.getUserName().toLowerCase());
        }
    }

    /**
     * Comparador estático por balance, en orden descendente.
     */
    public static class ComparadorBalance implements Comparator<Usuario> {
        @Override
        public int compare(Usuario u1, Usuario u2) {
            return -Double.compare(u1.balance, u2.balance);
        }
    }
}
