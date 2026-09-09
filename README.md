# slot machine - ciclo 1

este proyecto corresponde al ciclo 1 del simulador de una maquina tragamonedas.

el objetivo es representar una maquina formada por varias ruedas. cada rueda puede mostrar un simbolo y la maquina permite modificar su configuracion, girar las ruedas y revisar si se obtuvo un jackpot.

## funcionalidades realizadas

durante este ciclo se implementaron las siguientes funciones:

- crear una maquina tragamonedas
- agregar ruedas
- eliminar ruedas
- agregar simbolos
- eliminar simbolos
- colocar simbolos en una rueda
- girar una rueda
- girar todas las ruedas
- consultar los simbolos disponibles
- consultar la configuracion actual
- contar los simbolos diferentes
- revisar si la configuracion es jackpot
- mostrar la maquina
- ocultar la maquina
- terminar el simulador
- consultar si la ultima operacion fue correcta

## clases principales

### SlotMachine

es la clase principal del proyecto.

se encarga de:

- guardar las ruedas
- guardar los simbolos disponibles
- controlar las operaciones de la maquina
- organizar las ruedas
- girar las ruedas
- revisar el jackpot
- controlar la apariencia
- controlar si una operacion fue correcta

### Wheel

representa una rueda de la maquina.

cada rueda contiene:

- una posicion
- un simbolo
- un Rectangle para representar la rueda
- un Circle para representar el simbolo
- un estado de visibilidad

tambien permite cambiar su posicion, cambiar su simbolo, mostrarla y ocultarla.

## clases de apoyo

para la parte grafica se utilizaron clases del proyecto shapes:

- Canvas
- Circle
- Rectangle
- Triangle

estas clases permiten dibujar los elementos necesarios para representar la maquina.

## pruebas

se creo la clase:

`SlotMachineTest.java`

esta clase contiene pruebas junit para revisar el funcionamiento del simulador.

entre los casos probados se encuentran:

- crear una maquina vacia
- agregar y eliminar ruedas
- agregar y eliminar simbolos
- evitar simbolos repetidos
- colocar simbolos
- manejar posiciones fuera del rango
- detectar jackpot
- detectar una configuracion que no es jackpot
- girar una rueda
- girar todas las ruedas
- detectar operaciones incorrectas
- terminar la maquina

resultado final:

```text
20 pruebas ejecutadas
0 errores
0 fallos
