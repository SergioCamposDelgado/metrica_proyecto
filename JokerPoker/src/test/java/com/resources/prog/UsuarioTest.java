package com.resources.prog;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UsuarioTest {

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        // Crear un usuario válido para las pruebas
        usuario = new Usuario("user1", "password123", "John Doe", 100.0, false);
    }

    @Test
    void testConstructor() {
        assertEquals("user1", usuario.getUserName());
        assertEquals("password123", usuario.getPasswd());
        assertEquals("John Doe", usuario.getName());
        assertEquals(100.0, usuario.getBalance());
        assertFalse(usuario.esAdmin());
    }

    @Test
    void testSettersYGetters() {
        // Modificar el usuario usando setters
        usuario.setUserName("newUser");
        usuario.setPasswd("newPassword");
        usuario.setName("Jane Doe");
        usuario.setBalance(150.0);
        usuario.setEsAdmin(true);

        // Verificar que los cambios se reflejan en los getters
        assertEquals("newUser", usuario.getUserName());
        assertEquals("newPassword", usuario.getPasswd());
        assertEquals("Jane Doe", usuario.getName());
        assertEquals(150.0, usuario.getBalance());
        assertTrue(usuario.esAdmin());
    }

    @Test
    void testToString() {
        String expected = "user1 \tJohn Doe \t100.0 \tusuario";
        assertEquals(expected, usuario.toString());
    }

    @Test
    void testCompareTo() {
        Usuario otroUsuario = new Usuario("user2", "password456", "Alice", 100.0, false);
        Usuario mismoUsuario = new Usuario("user1", "password123", "John Doe", 100.0, false);

        // Comparar usuarios
        assertTrue(usuario.compareTo(otroUsuario) < 0); // "user1" < "user2"
        assertTrue(otroUsuario.compareTo(usuario) > 0); // "user2" > "user1"
        assertEquals(0, usuario.compareTo(mismoUsuario)); // "user1" == "user1"
    }

    @Test
    void testComparadorNombre() {
        // Crear usuarios con nombres diferentes
        Usuario usuario2 = new Usuario("user2", "password456", "Alice", 100.0, false);
        Usuario usuario3 = new Usuario("user3", "password789", "Bob", 100.0, false);
        Usuario usuario = new Usuario("user1", "password123", "John Doe", 100.0, false);

        // Crear comparador
        Usuario.ComparadorNombre comparador = new Usuario.ComparadorNombre();

        // Comparar por nombre (comparación insensible al caso)
        assertTrue(comparador.compare(usuario, usuario2) > 0); // "John Doe" < "Alice" (porque "j" < "a")
        assertTrue(comparador.compare(usuario2, usuario3) < 0); // "Alice" < "Bob" (porque "a" < "b")
        assertEquals(0, comparador.compare(usuario2, usuario2)); // "Alice" == "Alice"
    }

    @Test
    void testComparadorUserName() {
        Usuario usuario2 = new Usuario("user2", "password456", "Alice", 100.0, false);
        Usuario usuario3 = new Usuario("user3", "password789", "Bob", 100.0, false);

        Usuario.ComparadorUserName comparador = new Usuario.ComparadorUserName();

        // Comparar por nombre de usuario
        assertTrue(comparador.compare(usuario, usuario2) < 0); // "user1" < "user2"
        assertTrue(comparador.compare(usuario2, usuario3) < 0); // "user2" < "user3"
        assertEquals(0, comparador.compare(usuario, usuario)); // "user1" == "user1"
    }

    @Test
    void testComparadorBalance() {
        // Crear usuarios con balances diferentes
        Usuario usuario2 = new Usuario("user2", "password456", "Alice", 50.0, false);
        Usuario usuario3 = new Usuario("user3", "password789", "Bob", 150.0, false);

        // El balance del usuario es 100.0
        Usuario usuario = new Usuario("user1", "password123", "John Doe", 100.0, false);

        // Crear comparador
        Usuario.ComparadorBalance comparador = new Usuario.ComparadorBalance();

        // Comparar por balance (esperando que el comparador esté ordenando de mayor a menor)
        assertTrue(comparador.compare(usuario, usuario2) < 0);  // 100.0 > 50.0, por lo que el comparador devolverá un valor negativo.
        assertTrue(comparador.compare(usuario, usuario3) > 0);  // 150.0 > 100.0, por lo que el comparador devolverá un valor positivo.
        assertEquals(0, comparador.compare(usuario, usuario));  // 100.0 == 100.0, por lo que el comparador debe devolver 0.
    }

    @Test
    void testSetUserNameValido() {
        usuario.setUserName("newUser");
        assertEquals("newUser", usuario.getUserName());
    }

    @Test
    void testSetUserNameInvalido() {
        // El nombre de usuario es demasiado largo
        assertThrows(IllegalArgumentException.class, () -> {
            usuario.setUserName("ThisIsAVeryLongUserNameThatExceedsTheLimit");
        });
    }

    @Test
    void testSetPasswdValido() {
        usuario.setPasswd("newPassword");
        assertEquals("newPassword", usuario.getPasswd());
    }

    @Test
    void testSetPasswdInvalido() {
        // La contraseña es demasiado larga
        assertThrows(IllegalArgumentException.class, () -> {
            usuario.setPasswd("ThisIsAVeryLongPasswordThatExceedsTheLimit");
        });
    }

    @Test
    void testSetNameValido() {
        usuario.setName("Jane Smith");
        assertEquals("Jane Smith", usuario.getName());
    }

    @Test
    void testSetNameInvalido() {
        // El nombre es demasiado largo
        assertThrows(IllegalArgumentException.class, () -> {
            usuario.setName("ThisIsAVeryLongNameThatExceedsTheLimit");
        });
    }

    @Test
    void testGetInitialBalance() {
        // Verificamos que el balance inicial es el valor correcto
        assertEquals(100.0, Usuario.getInitialBalance());
    }
}
