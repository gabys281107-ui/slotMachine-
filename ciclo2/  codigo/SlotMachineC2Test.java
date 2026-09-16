import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * pruebas del ciclo 2 de slotmachine
 */
public class SlotMachineC2Test
{
    private SlotMachine machine;

    /**
     * prepara una maquina antes de cada prueba
     */
    @Before
    public void setUp()
    {
        machine = new SlotMachine();
        machine.makeInvisible();

        machine.addWheel(1);
        machine.addWheel(2);
        machine.addWheel(3);

        machine.addSymbol(1, "red");
        machine.addSymbol(2, "blue");
        machine.addSymbol(3, "green");
        machine.addSymbol(4, "yellow");

        machine.placeSymbol(1, "red");
        machine.placeSymbol(2, "blue");
        machine.placeSymbol(3, "green");
    }

    /**
     * verifica que swap intercambie dos ruedas
     */
    @Test
    public void shouldSwapTwoWheels()
    {
        machine.swap(1, 3);

        String[] configuration = machine.configuration();

        assertEquals("green", configuration[0]);
        assertEquals("blue", configuration[1]);
        assertEquals("red", configuration[2]);
        assertTrue(machine.ok());
    }

    /**
     * verifica que swap falle si no hay ruedas
     */
    @Test
    public void shouldNotSwapWithoutWheels()
    {
        SlotMachine emptyMachine = new SlotMachine();
        emptyMachine.makeInvisible();

        emptyMachine.swap(1, 2);

        assertFalse(emptyMachine.ok());
    }

    /**
     * verifica que una rueda fija no gire
     */
    @Test
    public void shouldNotSpinLockedWheel()
    {
        machine.lock(1);

        String before = machine.configuration()[0];

        machine.spin(1, 1);

        String after = machine.configuration()[0];

        assertEquals(before, after);
        assertFalse(machine.ok());
    }

    /**
     * verifica que una rueda liberada pueda girar
     */
    @Test
    public void shouldSpinUnlockedWheel()
    {
        machine.lock(1);
        machine.unlock(1);

        machine.spin(1, 1);

        String[] configuration = machine.configuration();

        assertEquals("blue", configuration[0]);
        assertTrue(machine.ok());
    }

    /**
     * verifica que spin avance el numero indicado de pasos
     */
    @Test
    public void shouldSpinGivenNumberOfSteps()
    {
        machine.placeSymbol(1, "red");

        machine.spin(1, 2);

        String[] configuration = machine.configuration();

        assertEquals("green", configuration[0]);
        assertTrue(machine.ok());
    }

    /**
     * verifica que el giro sea circular
     */
    @Test
    public void shouldSpinCircularly()
    {
        machine.placeSymbol(1, "yellow");

        machine.spin(1, 1);

        String[] configuration = machine.configuration();

        assertEquals("red", configuration[0]);
        assertTrue(machine.ok());
    }

    /**
     * verifica que la maquina llegue a una configuracion dada
     */
    @Test
    public void shouldSetGivenConfiguration()
    {
        String[] expected = {
            "green",
            "yellow",
            "red"
        };

        machine.spin(expected);

        assertArrayEquals(
            expected,
            machine.configuration()
        );

        assertTrue(machine.ok());
    }

    /**
     * verifica que no se acepte un simbolo inexistente
     */
    @Test
    public void shouldNotSetInvalidConfiguration()
    {
        String[] before = machine.configuration();

        String[] invalid = {
            "red",
            "purple",
            "green"
        };

        machine.spin(invalid);

        assertArrayEquals(
            before,
            machine.configuration()
        );

        assertFalse(machine.ok());
    }

    /**
     * verifica que una configuracion con tamaño incorrecto falle
     */
    @Test
    public void shouldNotSetConfigurationWithWrongSize()
    {
        String[] before = machine.configuration();

        String[] invalid = {
            "red",
            "blue"
        };

        machine.spin(invalid);

        assertArrayEquals(
            before,
            machine.configuration()
        );

        assertFalse(machine.ok());
    }
}