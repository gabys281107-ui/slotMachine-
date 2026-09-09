import static org.junit.Assert.*;
import org.junit.Test;

/**
 * prueba las funciones principales de la maquina.
 */
public class SlotMachineTest {

    /**
     * revisa que una maquina nueva este vacia.
     */
    @Test
    public void deberiaCrearMaquinaVacia() {
        SlotMachine machine = new SlotMachine();

        assertEquals(0, machine.symbols().length);
        assertEquals(0, machine.configuration().length);
        assertFalse(machine.isJackpot());
        assertTrue(machine.ok());
    }

    /**
     * revisa que se puedan agregar simbolos.
     */
    @Test
    public void deberiaAgregarSimbolos() {
        SlotMachine machine = new SlotMachine();

        machine.addSymbol(1, "red");
        machine.addSymbol(2, "blue");
        machine.addSymbol(3, "green");

        String[] expected = {"red", "blue", "green"};

        assertArrayEquals(expected, machine.symbols());
        assertTrue(machine.ok());
    }

    /**
     * revisa que no se repitan simbolos.
     */
    @Test
    public void noDeberiaAgregarSimbolosRepetidos() {
        SlotMachine machine = new SlotMachine();

        machine.addSymbol(1, "red");
        machine.addSymbol(2, "red");

        assertEquals(1, machine.symbols().length);
        assertFalse(machine.ok());
    }

    /**
     * revisa que se puedan colocar simbolos en las ruedas.
     */
    @Test
    public void deberiaColocarSimbolos() {
        SlotMachine machine = new SlotMachine();

        machine.addWheel(1);
        machine.addWheel(2);
        machine.addWheel(3);

        machine.addSymbol(1, "red");
        machine.addSymbol(2, "blue");
        machine.addSymbol(3, "green");

        machine.placeSymbol(1, "red");
        machine.placeSymbol(2, "blue");
        machine.placeSymbol(3, "green");

        String[] expected = {"red", "blue", "green"};

        assertArrayEquals(expected, machine.configuration());
        assertTrue(machine.ok());
    }

    /**
     * revisa las posiciones menores y mayores al limite.
     */
    @Test
    public void deberiaAjustarLasPosiciones() {
        SlotMachine machine = new SlotMachine();

        machine.addWheel(1);
        machine.addWheel(2);
        machine.addWheel(3);

        machine.addSymbol(1, "red");
        machine.addSymbol(2, "blue");

        machine.placeSymbol(0, "red");
        machine.placeSymbol(50, "blue");

        String[] configuration = machine.configuration();

        assertEquals("red", configuration[0]);
        assertEquals("blue", configuration[2]);
    }

    /**
     * revisa que se pueda eliminar una rueda.
     */
    @Test
    public void deberiaEliminarUnaRueda() {
        SlotMachine machine = new SlotMachine();

        machine.addWheel(1);
        machine.addWheel(2);
        machine.addWheel(3);

        machine.delWheel(2);

        assertEquals(2, machine.configuration().length);
        assertTrue(machine.ok());
    }

    /**
     * revisa que se pueda eliminar un simbolo.
     */
    @Test
    public void deberiaEliminarUnSimbolo() {
        SlotMachine machine = new SlotMachine();

        machine.addSymbol(1, "red");
        machine.addSymbol(2, "blue");
        machine.addSymbol(3, "green");

        machine.delSymbol("blue");

        String[] expected = {"red", "green"};

        assertArrayEquals(expected, machine.symbols());
        assertTrue(machine.ok());
    }

    /**
     * revisa una configuracion que no es jackpot.
     */
    @Test
    public void noDeberiaSerJackpot() {
        SlotMachine machine = new SlotMachine();

        machine.addWheel(1);
        machine.addWheel(2);
        machine.addWheel(3);

        machine.addSymbol(1, "red");
        machine.addSymbol(2, "blue");

        machine.placeSymbol(1, "red");
        machine.placeSymbol(2, "blue");
        machine.placeSymbol(3, "red");

        assertFalse(machine.isJackpot());
        assertEquals(2, machine.distinctSymbols());
    }

    /**
     * revisa una configuracion ganadora.
     */
    @Test
    public void deberiaSerJackpot() {
        SlotMachine machine = new SlotMachine();

        machine.addWheel(1);
        machine.addWheel(2);
        machine.addWheel(3);

        machine.addSymbol(1, "red");

        machine.placeSymbol(1, "red");
        machine.placeSymbol(2, "red");
        machine.placeSymbol(3, "red");

        assertTrue(machine.isJackpot());
        assertEquals(1, machine.distinctSymbols());
    }

    /**
     * revisa que girar una rueda deje un simbolo valido.
     */
    @Test
    public void deberiaGirarUnaRueda() {
        SlotMachine machine = new SlotMachine();

        machine.addWheel(1);
        machine.addSymbol(1, "red");
        machine.addSymbol(2, "blue");

        machine.spin(1);

        String symbol = machine.configuration()[0];

        assertTrue(symbol.equals("red") || symbol.equals("blue"));
        assertTrue(machine.ok());
    }

    /**
     * revisa que todas las ruedas puedan girar.
     */
    @Test
    public void deberiaGirarTodasLasRuedas() {
        SlotMachine machine = new SlotMachine();

        machine.addWheel(1);
        machine.addWheel(2);
        machine.addWheel(3);

        machine.addSymbol(1, "red");
        machine.addSymbol(2, "blue");
        machine.addSymbol(3, "green");

        machine.spin();

        String[] configuration = machine.configuration();

        for (String symbol : configuration) {
            assertTrue(
                symbol.equals("red") ||
                symbol.equals("blue") ||
                symbol.equals("green")
            );
        }

        assertTrue(machine.ok());
    }

    /**
     * revisa que una operacion incorrecta cambie ok.
     */
    @Test
    public void deberiaReconocerUnaOperacionIncorrecta() {
        SlotMachine machine = new SlotMachine();

        machine.spin();

        assertFalse(machine.ok());
    }

    /**
     * revisa que exit deje la maquina vacia.
     */
    @Test
    public void deberiaTerminarLaMaquina() {
        SlotMachine machine = new SlotMachine();

        machine.addWheel(1);
        machine.addSymbol(1, "red");

        machine.exit();

        assertEquals(0, machine.configuration().length);
        assertEquals(0, machine.symbols().length);
        assertTrue(machine.ok());
    }
    /**
 * revisa que no se pueda eliminar una rueda si no hay ruedas.
 */
@Test
public void noDeberiaEliminarRuedaSiEstaVacia() {
    SlotMachine machine = new SlotMachine();

    machine.delWheel(1);

    assertFalse(machine.ok());
    assertEquals(0, machine.configuration().length);
}

/**
 * revisa que no se pueda eliminar un simbolo que no existe.
 */
@Test
public void noDeberiaEliminarSimboloInexistente() {
    SlotMachine machine = new SlotMachine();

    machine.addSymbol(1, "red");
    machine.delSymbol("blue");

    assertFalse(machine.ok());
    assertEquals(1, machine.symbols().length);
}

/**
 * revisa que no se pueda colocar un simbolo que no existe.
 */
@Test
public void noDeberiaColocarSimboloInexistente() {
    SlotMachine machine = new SlotMachine();

    machine.addWheel(1);
    machine.addSymbol(1, "red");

    machine.placeSymbol(1, "blue");

    assertFalse(machine.ok());
    assertNull(machine.configuration()[0]);
}

/**
 * revisa una posicion menor que uno al agregar simbolos.
 */
@Test
public void deberiaAjustarPosicionMenorAlAgregarSimbolo() {
    SlotMachine machine = new SlotMachine();

    machine.addSymbol(1, "blue");
    machine.addSymbol(0, "red");

    String[] expected = {"red", "blue"};

    assertArrayEquals(expected, machine.symbols());
}

/**
 * revisa una posicion muy grande al agregar simbolos.
 */
@Test
public void deberiaAjustarPosicionMayorAlAgregarSimbolo() {
    SlotMachine machine = new SlotMachine();

    machine.addSymbol(1, "red");
    machine.addSymbol(50, "blue");

    String[] expected = {"red", "blue"};

    assertArrayEquals(expected, machine.symbols());
}

/**
 * revisa que no se pueda girar sin ruedas.
 */
@Test
public void noDeberiaGirarSinRuedas() {
    SlotMachine machine = new SlotMachine();

    machine.addSymbol(1, "red");
    machine.spin();

    assertFalse(machine.ok());
}

/**
 * revisa que no se pueda girar sin simbolos.
 */
@Test
public void noDeberiaGirarSinSimbolos() {
    SlotMachine machine = new SlotMachine();

    machine.addWheel(1);
    machine.spin();

    assertFalse(machine.ok());
}
}
