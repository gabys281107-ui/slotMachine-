Retrospectiva – Ciclo 2
1. ¿Cuáles fueron los mini-ciclos definidos? ¿Por qué?

Para este ciclo dividimos el trabajo en los siguientes mini-ciclos:

MC1 – Intercambio de ruedas: implementación de swap(int wheel1, int wheel2).
MC2 – Bloqueo de ruedas: implementación de lock(int wheel) y unlock(int wheel).
MC3 – Giro por pasos: implementación de spin(int wheel, int steps).
MC4 – Configuración dada: implementación de spin(String[] setSymbols).
MC5 – Refactorización: ajustes en SlotMachine y Wheel para evitar repetir código y mantener organizadas las responsabilidades.
MC6 – Pruebas: creación y ejecución de pruebas propias y pruebas compartidas.
MC7 – Diseño: actualización del diagrama de clases y diagramas de secuencia en Astah.

Se dividió de esta forma para desarrollar y revisar cada funcionalidad por separado antes de integrarla con las demás.

2. ¿Cuál es el estado actual del proyecto en términos de mini-ciclos? ¿Por qué?

Los mini-ciclos definidos para el Ciclo 2 se encuentran completados. Actualmente la máquina permite intercambiar ruedas, fijarlas y desfijarlas, girar una rueda un número determinado de pasos y dejar la máquina en una configuración dada.

También se actualizaron las clases SlotMachine y Wheel, se realizaron las pruebas correspondientes y se actualizaron los diagramas de Astah para que representen el funcionamiento actual del código.

3. ¿Cuál fue el tiempo total invertido por cada integrante?
Gabriela: 17 horas
Mathias: 18 horas

Total: 35 horas
4. ¿Cuál consideran que fue el mayor logro? ¿Por qué?

El mayor logro fue poder agregar las nuevas funcionalidades del Ciclo 2 sin tener que rehacer el proyecto anterior. Se logró extender el funcionamiento de la máquina reutilizando las clases que ya existían y agregando nuevos comportamientos como swap, lock, unlock y las nuevas versiones de spin.

También fue importante lograr que las pruebas funcionaran correctamente, ya que permitieron comprobar que los cambios nuevos no dañaran las funcionalidades anteriores.

5. ¿Cuál fue el mayor problema técnico? ¿Qué hicieron para resolverlo?

Uno de los principales problemas fue mantener coordinada la parte lógica de la máquina con su representación gráfica. Por ejemplo, en algunos momentos al eliminar o girar ruedas quedaban símbolos visibles o desaparecían elementos que no debían desaparecer.

Para solucionarlo revisamos métodos como makeVisible(), makeInvisible(), setSymbol(), arrangeWheels() y redrawWheels(), buscando que cada objeto actualizara correctamente su estado visual.

También tuvimos que revisar que los diagramas de Astah coincidieran exactamente con el código final.

6. ¿Qué hicieron bien como equipo? ¿Qué se comprometen a mejorar?

Como equipo hicimos bien la división del trabajo en funcionalidades pequeñas y la revisión constante de los cambios realizados. También usamos pruebas para comprobar que cada nueva funcionalidad funcionara antes de continuar.

Nos comprometemos a mejorar la organización del trabajo y a mantener actualizados al mismo tiempo el código, las pruebas y los diagramas, para no tener que realizar muchas correcciones al final.

7. Considerando las prácticas XP incluidas en los laboratorios, ¿cuál fue la más útil? ¿Por qué?

La práctica más útil fue el uso de pruebas automáticas, porque permitió verificar rápidamente si las nuevas funcionalidades funcionaban correctamente después de realizar cambios en el código.

También fue útil la refactorización, ya que permitió reorganizar algunos métodos y reducir código repetido sin cambiar el comportamiento esperado del simulador.

8. ¿Qué referencias usaron? ¿Cuál fue la más útil?

Las principales referencias utilizadas fueron:

- Guía del Proyecto Inicial Ciclo 1.
- Guía del Proyecto Inicial Ciclo 2.
- Proyecto shapes de BlueJ.
- Documentación de Java.
- Casos de prueba compartidos en el wiki del curso.

La referencia más útil fue la guía del Ciclo 2, porque allí se especificaban las nuevas funcionalidades que debíamos implementar y los requisitos que debía cumplir la máquina.
