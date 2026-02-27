package rpg.model;

import rpg.interfaces.Magico;

// Hechicero es una clase concreta que:
// - Hereda de PersonajeMagico (subclase abstracta intermedia de Personaje)
// - Implementa la interfaz Magico (capacidad de lanzar hechizos)
// Esto demuestra herencia + interfaces al mismo tiempo
public class Hechicero extends PersonajeMagico implements Magico {

    // Atributos propios del Hechicero (encapsulados como private)
    // poderOscuro: daño extra que se suma a todos sus ataques y hechizos
    // cargasUltimas: número de veces que puede potenciar su poder oscuro
    private int poderOscuro;
    private int cargasUltimas;

    // Constructor: recibe los datos básicos y los pasa a PersonajeMagico con super()
    // El 20 es la regeneración de mana base que recibe PersonajeMagico
    // Se inicializan los atributos propios del Hechicero
    public Hechicero(String nombre, int nivel, int saludMaxima, int manaMaximo) {
        super(nombre, nivel, saludMaxima, manaMaximo, 20);
        this.poderOscuro = 20;
        this.cargasUltimas = 3;
    }

    // Override obligatorio del método abstracto atacar() de Personaje
    // Polimorfismo: misma llamada atacar() → comportamiento distinto según el tipo real del objeto
    // El Hechicero gasta mana para lanzar un rayo arcano
    // Si no tiene mana suficiente, informa y no ataca
    @Override
    public void atacar(Personaje objetivo) {
        int costeMana = 10;
        if (getMana() >= costeMana) {
            // El daño escala con el nivel y se suma el poder oscuro acumulado
            int danio = 15 + (getNivel() * 4) + poderOscuro;
            gastarMana(costeMana); // Método heredado de PersonajeMagico
            System.out.println(getNombre() + " lanza un rayo arcano a "
                    + objetivo.getNombre() + " causando " + danio + " de daño.");
            objetivo.recibirDanio(danio); // Método heredado de Personaje
        } else {
            System.out.println(getNombre() + " no tiene suficiente mana.");
        }
    }

    // Override del método lanzarHechizo() definido en la interfaz Magico
    // Hechizo más potente que el ataque básico pero con mayor coste de mana
    // También suma el poder oscuro al daño total
    @Override
    public void lanzarHechizo(Personaje objetivo) {
        int costeMana = 25;
        if (getMana() >= costeMana) {
            // Daño base mayor que atacar(), escala más con el nivel
            int danio = 40 + (getNivel() * 6) + poderOscuro;
            gastarMana(costeMana);
            System.out.println(getNombre() + " lanza Bola de Fuego sobre "
                    + objetivo.getNombre() + " causando " + danio + " de daño.");
            objetivo.recibirDanio(danio);
        } else {
            System.out.println(getNombre() + " no tiene mana suficiente.");
        }
    }

    // Override del método getNombreHechizo() de la interfaz Magico
    // Devuelve el nombre del hechizo característico de esta clase
    @Override
    public String getNombreHechizo() {
        return "Bola de Fuego";
    }

    // Método propio del Hechicero: invoca un familiar arcano
    // Gasta mana. Si no hay suficiente, informa y no hace nada
    public void invocar() {
        int costeMana = 30;
        if (getMana() >= costeMana) {
            gastarMana(costeMana);
            System.out.println(getNombre() + " invoca un familiar arcano.");
        } else {
            System.out.println(getNombre() + " no tiene mana para invocar.");
        }
    }

    // Método propio del Hechicero: aumenta el poder oscuro usando cargas limitadas
    // Cada uso consume una carga y suma 10 al poderOscuro (que incrementa el daño)
    // Solo funciona si quedan cargas disponibles
    public void potenciarPoder() {
        if (cargasUltimas > 0) {
            poderOscuro += 10;
            cargasUltimas--;
            System.out.println(getNombre() + " potencia su poder.");
        } else {
            System.out.println(getNombre() + " no tiene cargas ultimas.");
        }
    }

    // Getters de los atributos propios
    // No hay setters porque poderOscuro y cargasUltimas solo se modifican
    // desde dentro de la clase, a través de potenciarPoder()
    public int getPoderOscuro() { return poderOscuro; }
    public int getCargasUltimas() { return cargasUltimas; }

    // toString sobrescrito: reutiliza el de PersonajeMagico (que a su vez reutiliza el de Personaje)
    // y añade la información propia del Hechicero
    @Override
    public String toString() {
        return super.toString() + " | Poder Oscuro: " + poderOscuro;
    }
}