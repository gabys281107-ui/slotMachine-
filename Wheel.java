/**
 * representa una rueda de la maquina tragamonedas
 *
 * @author
 * @version 2026-2
 */
public class Wheel
{
    private Rectangle body;
    private int position;
    private Circle symbolShape;
    private String symbol;
    private boolean visible;
    private boolean locked;

    /**
     * crea una rueda
     */
    public Wheel()
    {
        body = new Rectangle();
        body.changeSize(100,50);
        body.moveVertical(85);
        body.changeColor("yellow");

        symbolShape = new Circle();
        symbolShape.changeSize(30);
        symbolShape.moveHorizontal(60);
        symbolShape.moveVertical(120);

        locked = false;
        symbol = null;
        visible = false;
        position = 1;
    }

    /**
     * fija la rueda
     */
    public void lock()
    {
        locked = true;
    }

    /**
     * libera la rueda
     */
    public void unlock()
    {
        locked = false;
    }

    /**
     * indica si la rueda esta fija
     */
    public boolean isLocked()
    {
        return locked;
    }

    /**
     * hace visible la rueda
     */
    public void makeVisible()
    {
        body.makeVisible();

        if (symbol != null){
            symbolShape.makeVisible();
        }

        visible = true;
    }

    /**
     * hace invisible la rueda
     */
    public void makeInvisible()
    {
        visible = false;
        body.makeInvisible();
        symbolShape.makeInvisible();
    }

    /**
     * cambia la posicion de la rueda
     */
    public void setPosition(int newPosition)
    {
        int distance = (newPosition - position) * 60;

        body.moveHorizontal(distance);
        symbolShape.moveHorizontal(distance);

        position = newPosition;
    }

    /**
     * retorna la posicion de la rueda
     */
    public int getPosition()
    {
        return position;
    }

    /**
     * cambia el simbolo visible de la rueda
     */
    public void setSymbol(String newSymbol)
    {
        symbol = newSymbol;
        symbolShape.changeColor(newSymbol);

        if (visible){
            symbolShape.makeVisible();
        }
    }

    /**
     * retorna el simbolo actual
     */
    public String getSymbol()
    {
        return symbol;
    }

    /**
     * cambia el color del cuerpo de la rueda
     */
    public void setBodyColor(String color)
    {
        body.changeColor(color);
    }

    /**
     * indica si la rueda esta visible
     */
    public boolean isVisible()
    {
        return visible;
    }
}