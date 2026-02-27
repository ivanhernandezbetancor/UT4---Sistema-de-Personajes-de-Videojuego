package rpg.model;

import rpg.interfaces.Curable;
import rpg.interfaces.Defendible;

// Paladin hereda de PersonajeFisico (subclase abstracta intermedia)
// e implementa Curable y Defendible (capacidades múltiples mediante interfaces)
public class Paladin extends PersonajeFisico implements Curable, Defendible {

    // Atributos propios del Paladín (mínimo 2 requeridos)
    private int fuerza;
    private int armadura;

    // Constructor
    public Paladin(String nombre, int nivel, int saludMaxima, int fuerza, int armadura) {
        super(nombre, nivel, saludMaxima);
        this.fuerza = fuerza;
        this.armadura = armadura;
    }

    // Override obligatorio de atacar(Personaje objetivo)
    // Polimorfismo: misma llamada → comportamiento distinto al de otros personajes
    @Override
    public void atacar(Personaje objetivo) {
        int danio = fuerza + (getNivel() * 2);
        System.out.println(getNombre() + " golpea con su martillo sagrado a "
                + objetivo.getNombre() + " causando " + danio + " de daño.");
        objetivo.recibirDanio(danio);
    }

    // Implementación de la interfaz Curable
    @Override
    public void curar(Personaje objetivo) {
        int cantidadCura = 20 + (getNivel() * 3);
        int saludAntes = objetivo.getSalud();
        objetivo.setSalud(objetivo.getSalud() + cantidadCura);
        System.out.println(getNombre() + " cura a " + objetivo.getNombre()
                + " restaurando " + (objetivo.getSalud() - saludAntes) + " puntos de salud.");
    }

    // Implementación de la interfaz Defendible
    @Override
    public void defender() {
        System.out.println(getNombre() + " levanta su escudo sagrado. "
                + "Armadura aumentada temporalmente. [Armadura base: " + armadura + "]");
    }

    // Método propio adicional: golpe de escudo
    public void golpeEscudo(Personaje objetivo) {
        int danio = armadura / 2;
        System.out.println(getNombre() + " golpea con su escudo a "
                + objetivo.getNombre() + " causando " + danio + " de daño.");
        objetivo.recibirDanio(danio);
    }

    // Getters y Setters de atributos propios
    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getArmadura() {
        return armadura;
    }

    public void setArmadura(int armadura) {
        this.armadura = armadura;
    }

    // toString sobrescrito: reutiliza el de Personaje con super.toString()
    @Override
    public String toString() {
        return super.toString() + " | Fuerza: " + fuerza + " | Armadura: " + armadura;
    }
}
