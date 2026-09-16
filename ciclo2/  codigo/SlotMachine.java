/**
 * Simular una maquina tragamonedas.
 * 
 * @author (RodriguezR-VargasC) 
 * @version (1.0 22/08/2026)
 */
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class SlotMachine
{
    private Rectangle body;
    private boolean visible;
    private boolean lastOperationOk;
    private ArrayList<Wheel> wheels;
    private ArrayList<String> symbols;

    public SlotMachine()
    {
        body = new Rectangle();
        body.changeSize(220,250);
        body.moveHorizontal(-40);
        body.moveVertical(30);
        body.changeColor("black");
        
        wheels = new ArrayList<Wheel>();
        symbols = new ArrayList<String>();
        visible = false;
    }

    /**
     * agrega una rueda al cuerpo de la maquina
     */
    public void addWheel(int pos)
    {
        int position = adjustedAddPosition(pos);
        Wheel wheel = new Wheel();
        
        wheels.add(position - 1, wheel);
        arrangeWheels();
        
        if (visible){
            wheel.makeVisible(); 
        }

        operationSucceeded();
    }

    /**
     * elimina una rueda de la maquina
     */
    public void delWheel(int pos)
    {
        if (wheels.size() > 0){
            int position = adjustedWheelPosition(pos);
            
            Wheel wheel = wheels.get(position - 1);
            wheel.makeInvisible();
            
            wheels.remove(position - 1);
            arrangeWheels();
            
            operationSucceeded();
        }
        else{
            operationFailed("no hay ninguna rueda para eliminar");
        }
    }

    /**
     * intercambia dos ruedas
     */
    public void swap(int wheel1, int wheel2)
    {
        if (wheels.isEmpty()){
            operationFailed("no hay ruedas para cambiar");
            return;
        }
        
        int position1 = adjustedWheelPosition(wheel1);
        int position2 = adjustedWheelPosition(wheel2);

        int index1 = position1 - 1;
        int index2 = position2 - 1;
        
        Wheel temporal = wheels.get(index1);

        wheels.set(index1, wheels.get(index2));
        wheels.set(index2, temporal);
        
        arrangeWheels();
        operationSucceeded();
    }

    /**
     * fija una rueda para que no gire
     */
    public void lock(int wheel)
    {
        if (wheels.isEmpty()){
            operationFailed("no hay ruedas para fijar");
            return;
        }
        
        int position = adjustedWheelPosition(wheel);

        wheels.get(position - 1).lock();

        operationSucceeded();
    }

    /**
     * libera una rueda para que pueda girar
     */
    public void unlock(int wheel)
    {
        if (wheels.isEmpty()){
            operationFailed("no hay ruedas por soltar");
            return;
        }
        
        int position = adjustedWheelPosition(wheel);

        wheels.get(position - 1).unlock();

        operationSucceeded();
    }

    /**
     * permite organizar las posiciones de las ruedas
     */
    private void arrangeWheels()
    {
        for (int i = 0; i < wheels.size(); i++){
            wheels.get(i).setPosition(i + 1);
        }
    }

    /**
     * ajusta la posicion para agregar una rueda
     */
    private int adjustedAddPosition(int pos)
    {
        if (pos < 1){
            return 1;
        }

        if (pos > wheels.size() + 1){
            return wheels.size() + 1;
        }

        return pos;
    }

    /**
     * ajusta la posicion de una rueda existente
     */
    private int adjustedWheelPosition(int pos)
    {
        if (pos < 1){
            return 1;
        }

        if (pos > wheels.size()){
            return wheels.size();
        }

        return pos;
    }

    /**
     * crea un simbolo permitido sin darle visualizacion
     */
    public void addSymbol(int pos, String color)
    {
        if (!symbols.contains(color)){

            int position = adjustedSymbolAddPosition(pos);

            symbols.add(position - 1, color);

            operationSucceeded();
        }
        else{
            operationFailed("el simbolo ya existe");
        }
    }

    /**
     * ajusta la posicion para agregar un simbolo
     */
    private int adjustedSymbolAddPosition(int pos)
    {
        if (pos < 1){
            return 1;
        }

        if (pos > symbols.size() + 1){
            return symbols.size() + 1;
        }

        return pos;
    }

    /**
     * elimina un simbolo de la maquina
     */
    public void delSymbol(String symbol)
    {
        if (symbols.contains(symbol)){

            symbols.remove(symbol);

            operationSucceeded();
        }
        else{
            operationFailed("el simbolo no existe");
        }
    }

    /**
     * coloca un simbolo en una rueda
     */
    public void placeSymbol(int wheel, String symbol)
    {
        if (wheels.isEmpty() || symbols.isEmpty()){
            operationFailed("operacion no valida");
            return;
        }

        if (!symbols.contains(symbol)){
            operationFailed("el simbolo no coincide");
            return;
        }

        int position = adjustedWheelPosition(wheel);

        wheels.get(position - 1).setSymbol(symbol);
            
        operationSucceeded();

        updateJackpotAppearance();

        redrawWheels();
    }

    /**
     * hace girar una rueda dejando un simbolo aleatorio
     */
    public void spin(int wheel)
    {
        if (wheels.isEmpty() || symbols.isEmpty()){
            operationFailed("operacion no valida");
            return;
        }
        
        int position = adjustedWheelPosition(wheel);

        Wheel selectedWheel = wheels.get(position - 1);
        
        if (selectedWheel.isLocked()){
            operationFailed("la rueda esta fijada");
            return;
        }

        int randomIndex =
            (int) (Math.random() * symbols.size());

        String randomSymbol =
            symbols.get(randomIndex);
        
        selectedWheel.setSymbol(randomSymbol);

        operationSucceeded();

        updateJackpotAppearance();

        redrawWheels();
    }

    /**
     * hace girar todas las ruedas que no esten fijadas
     */
    public void spin()
    {
        if (wheels.isEmpty()){
            operationFailed("no hay ruedas");
            return;
        }
        
        if (symbols.isEmpty()){
            operationFailed("no hay simbolos");
            return;
        }
        
        for (int i = 0; i < wheels.size(); i++){

            if (!wheels.get(i).isLocked()){

                int randomIndex =
                    (int) (Math.random() * symbols.size());

                wheels.get(i).setSymbol(
                    symbols.get(randomIndex)
                );
            }
        }
        
        operationSucceeded();

        updateJackpotAppearance();

        redrawWheels();
    }

    /**
     * gira una rueda una cantidad determinada de pasos
     */
    public void spin(int wheel, int steps)
    {
        if (wheels.isEmpty() || symbols.isEmpty()){
            operationFailed("operacion no valida");
            return;
        }
        
        int position = adjustedWheelPosition(wheel);

        Wheel selectedWheel =
            wheels.get(position - 1);
        
        if (selectedWheel.isLocked()){
            operationFailed("la rueda esta fija");
            return;
        }
        
        String currentSymbol =
            selectedWheel.getSymbol();

        int currentIndex =
            symbols.indexOf(currentSymbol);
        
        if (currentIndex == -1){
            operationFailed("simbolo no existente");
            return;
        }
        
        for (int i = 0; i < steps; i++){

            currentIndex =
                (currentIndex + 1) % symbols.size();

            selectedWheel.setSymbol(
                symbols.get(currentIndex)
            );

            if (visible){
                Canvas.getCanvas().wait(300);
            }
        }
        
        operationSucceeded();

        updateJackpotAppearance();

        redrawWheels();
    }

    /**
     * deja la maquina en una configuracion dada
     */
    public void spin(String[] setSymbols)
    {
        if (setSymbols == null ||
            setSymbols.length != wheels.size()){

            operationFailed(
                "la configuracion no es valida"
            );

            return;
        }
        
        for (String symbol : setSymbols){

            if (!symbols.contains(symbol)){

                operationFailed(
                    "la configuracion contiene un simbolo no valido"
                );

                return;
            }
        }
        
        for (int i = 0; i < wheels.size(); i++){

            Wheel wheel = wheels.get(i);
            
            if (wheel.isLocked() &&
                !setSymbols[i].equals(wheel.getSymbol())){

                operationFailed(
                    "una rueda fijada no puede cambiar"
                );

                return;
            }
        }
        
        for (int i = 0; i < wheels.size(); i++){

            wheels.get(i).setSymbol(
                setSymbols[i]
            );
        }
        
        operationSucceeded();

        updateJackpotAppearance();

        redrawWheels();
    }

    /**
     * retorna los simbolos permitidos
     */
    public String[] symbols()
    {
        return symbols.toArray(new String[0]);
    }

    /**
     * retorna la cantidad de simbolos distintos
     */
    public int distinctSymbols()
    {
        return symbols.size();
    }

    /**
     * retorna la configuracion actual
     */
    public String[] configuration()
    {
        String[] configuration =
            new String[wheels.size()];
        
        for (int i = 0; i < wheels.size(); i++){

            configuration[i] =
                wheels.get(i).getSymbol();
        }
        
        return configuration;
    }

    /**
     * actualiza la apariencia de la maquina
     */
    private void updateJackpotAppearance()
    {
        if (isJackpot()){
            body.changeColor("green");
        }
        else{
            body.changeColor("black");
        }
    }

    /**
     * indica si la configuracion actual es jackpot
     */
    public boolean isJackpot()
    {
        if (wheels.isEmpty()){
            return false;
        }
        
        String firstSymbol =
            wheels.get(0).getSymbol();
        
        if (firstSymbol == null){
            return false;
        }
        
        for (Wheel wheel : wheels){

            if (!firstSymbol.equals(
                    wheel.getSymbol())){

                return false;
            }
        }

        return true;
    }

    /**
     * hace visible la maquina
     */
    public void makeVisible()
    {    
        body.makeVisible();
        
        for (Wheel wheel : wheels){
            wheel.makeVisible();
        }
        
        visible = true;
    }

    /**
     * hace invisible la maquina
     */
    public void makeInvisible()
    {
        body.makeInvisible();
        
        for (Wheel wheel : wheels){
            wheel.makeInvisible();
        }

        visible = false;
    }

    /**
     * termina el simulador
     */
    public void exit()
    {
        makeInvisible();

        operationSucceeded();
    }

    /**
     * indica si la ultima operacion fue exitosa
     */
    public boolean ok()
    {
        return lastOperationOk;
    }

    /**
     * vuelve a dibujar las ruedas
     */
    private void redrawWheels()
    {
        if (visible){

            for (Wheel wheel : wheels){
                wheel.makeVisible();
            }
        }
    }

    /**
     * registra una operacion fallida
     */
    private void operationFailed(String message)
    {
        lastOperationOk = false;
        
        if (visible){
            JOptionPane.showMessageDialog(
                null,
                message
            );
        }
    }

    /**
     * registra una operacion exitosa
     */
    private void operationSucceeded()
    {
        lastOperationOk = true;
    }
}