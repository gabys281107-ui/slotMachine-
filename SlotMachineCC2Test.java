import static org.junit.Assert.*;
import org.junit.Test;

/**
 * pruebas compartidas del ciclo 2
 */
public class SlotMachineCC2Test
{
    /**
     * verifica que al intercambiar dos ruedas validas
     * cada una quede mostrando el simbolo de la otra
     *
     * prueba compartida: MoralesS-RojasH
     */
    @Test
    public void accordingMsRhShouldSwapSymbolsBetweenTwoValidWheels()
    {
        SlotMachine slotMachine = new SlotMachine();

        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(2, "blue");

        slotMachine.addWheel(1);
        slotMachine.addWheel(2);

        slotMachine.placeSymbol(1, "red");
        slotMachine.placeSymbol(2, "blue");

        slotMachine.swap(1, 2);

        assertEquals(
            "blue",
            slotMachine.configuration()[0]
        );

        assertEquals(
            "red",
            slotMachine.configuration()[1]
        );
    }

    /**
     * verifica que al dejar todas las ruedas con
     * el mismo simbolo se detecte el jackpot
     *
     * prueba compartida: MoralesS-RojasH
     */
    @Test
    public void accordingMsRhShouldDetectJackpotAfterForcedSpin()
    {
        SlotMachine slotMachine = new SlotMachine();

        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(2, "blue");

        slotMachine.addWheel(1);
        slotMachine.addWheel(2);

        slotMachine.spin(
            new String[]{"red", "red"}
        );

        assertTrue(
            slotMachine.isJackpot()
        );
    }
}