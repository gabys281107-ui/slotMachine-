import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * representa la maquina tragamonedas.
 * guarda las ruedas y los simbolos que se pueden usar.
 */
public class SlotMachine {

    private ArrayList<Wheel> wheels;
    private ArrayList<String> symbols;
    private boolean visible;
    private boolean lastOperationOk;

    /**
     * crea una maquina tragamonedas vacia.
     */
    public SlotMachine() {
        wheels = new ArrayList<Wheel>();
        symbols = new ArrayList<String>();
        visible = false;
        lastOperationOk = true;
    }

    /**
     * agrega una rueda en la posicion indicada.
     */
    public void addWheel(int pos) {
        if (pos < 1) {
            pos = 1;
        }

        if (pos > wheels.size() + 1) {
            pos = wheels.size() + 1;
        }

        Wheel newWheel = new Wheel();

        wheels.add(pos - 1, newWheel);

        arrangeWheels();
        updateJackpotAppearance();

        if (visible) {
            newWheel.makeVisible();
        }

        lastOperationOk = true;
    }

    /**
     * elimina una rueda de la posicion indicada.
     */
    public void delWheel(int pos) {
        if (wheels.size() == 0) {
            showError("no hay ruedas");
            return;
        }

        if (pos < 1) {
            pos = 1;
        }

        if (pos > wheels.size()) {
            pos = wheels.size();
        }

        Wheel wheel = wheels.get(pos - 1);

        wheel.makeInvisible();
        wheels.remove(pos - 1);

        arrangeWheels();
        updateJackpotAppearance();

        lastOperationOk = true;
    }

    /**
     * organiza las ruedas una al lado de la otra.
     */
    private void arrangeWheels() {
        for (int i = 0; i < wheels.size(); i++) {
            wheels.get(i).setPosition(i + 1);
        }
    }

    /**
     * agrega un simbolo en la posicion indicada.
     */
    public void addSymbol(int pos, String color) {
        if (color == null || symbols.contains(color)) {
            showError("no se puede agregar el simbolo");
            return;
        }

        if (pos < 1) {
            pos = 1;
        }

        if (pos > symbols.size() + 1) {
            pos = symbols.size() + 1;
        }

        symbols.add(pos - 1, color);

        lastOperationOk = true;
    }

    /**
     * elimina un simbolo de la maquina.
     */
    public void delSymbol(String symbol) {
        if (!symbols.contains(symbol)) {
            showError("el simbolo no existe");
            return;
        }

        symbols.remove(symbol);

        for (Wheel wheel : wheels) {
            if (symbol.equals(wheel.getSymbol())) {
                wheel.setSymbol("white");
            }
        }

        updateJackpotAppearance();

        lastOperationOk = true;
    }

    /**
     * coloca un simbolo en una rueda.
     */
    public void placeSymbol(int wheel, String symbol) {
        if (wheels.size() == 0 || !symbols.contains(symbol)) {
            showError("no se puede colocar el simbolo");
            return;
        }

        if (wheel < 1) {
            wheel = 1;
        }

        if (wheel > wheels.size()) {
            wheel = wheels.size();
        }

        wheels.get(wheel - 1).setSymbol(symbol);

        updateJackpotAppearance();

        lastOperationOk = true;
    }

    /**
     * gira una rueda y coloca un simbolo al azar.
     */
    public void spin(int wheel) {
        if (wheels.size() == 0 || symbols.size() == 0) {
            showError("no hay ruedas o simbolos para girar");
            return;
        }

        if (wheel < 1) {
            wheel = 1;
        }

        if (wheel > wheels.size()) {
            wheel = wheels.size();
        }

        int random = (int) (Math.random() * symbols.size());

        String symbol = symbols.get(random);

        wheels.get(wheel - 1).setSymbol(symbol);

        updateJackpotAppearance();

        lastOperationOk = true;
    }

    /**
     * gira todas las ruedas.
     */
    public void spin() {
        if (wheels.size() == 0 || symbols.size() == 0) {
            showError("no hay ruedas o simbolos para girar");
            return;
        }

        for (Wheel wheel : wheels) {
            int random = (int) (Math.random() * symbols.size());

            String symbol = symbols.get(random);

            wheel.setSymbol(symbol);
        }

        updateJackpotAppearance();

        lastOperationOk = true;
    }

    /**
     * devuelve los simbolos de la maquina.
     */
    public String[] symbols() {
        String[] result = new String[symbols.size()];

        for (int i = 0; i < symbols.size(); i++) {
            result[i] = symbols.get(i);
        }

        return result;
    }

    /**
     * devuelve los simbolos que muestran las ruedas.
     */
    public String[] configuration() {
        String[] result = new String[wheels.size()];

        for (int i = 0; i < wheels.size(); i++) {
            result[i] = wheels.get(i).getSymbol();
        }

        return result;
    }

    /**
     * cuenta cuantos simbolos diferentes se estan mostrando.
     */
    public int distinctSymbols() {
        ArrayList<String> different = new ArrayList<String>();

        for (Wheel wheel : wheels) {
            String symbol = wheel.getSymbol();

            if (symbol != null && !different.contains(symbol)) {
                different.add(symbol);
            }
        }

        return different.size();
    }

    /**
     * dice si todas las ruedas muestran el mismo simbolo.
     */
    public boolean isJackpot() {
        if (wheels.size() == 0) {
            return false;
        }

        String firstSymbol = wheels.get(0).getSymbol();

        if (firstSymbol == null || firstSymbol.equals("white")) {
            return false;
        }

        for (Wheel wheel : wheels) {
            if (!firstSymbol.equals(wheel.getSymbol())) {
                return false;
            }
        }

        return true;
    }

    /**
     * cambia la apariencia cuando hay jackpot.
     */
    private void updateJackpotAppearance() {
        String color = "yellow";

        if (isJackpot()) {
            color = "green";
        }

        for (Wheel wheel : wheels) {
            wheel.setBodyColor(color);
        }
    }

    /**
     * muestra la maquina y sus ruedas.
     */
    public void makeVisible() {
        visible = true;

        for (Wheel wheel : wheels) {
            wheel.makeVisible();
        }

        lastOperationOk = true;
    }

    /**
     * oculta la maquina y sus ruedas.
     */
    public void makeInvisible() {
        for (Wheel wheel : wheels) {
            wheel.makeInvisible();
        }

        visible = false;

        lastOperationOk = true;
    }

    /**
     * termina la maquina.
     */
    public void exit() {
        makeInvisible();

        wheels.clear();
        symbols.clear();

        lastOperationOk = true;
    }

    /**
     * guarda el error y muestra un mensaje si la maquina esta visible.
     */
    private void showError(String message) {
        lastOperationOk = false;

        if (visible) {
            JOptionPane.showMessageDialog(null, message);
        }
    }

    /**
     * dice si la ultima operacion se pudo realizar.
     */
    public boolean ok() {
        return lastOperationOk;
    }
}