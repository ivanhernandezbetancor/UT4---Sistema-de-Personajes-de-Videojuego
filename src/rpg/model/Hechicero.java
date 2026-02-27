package rpg.model;

import rpg.interfaces.Magico;

// Hechicero hereda de PersonajeMagico (subclase abstracta intermedia)
// e implementa Magico (capacidad de lanzar hechizos)
public class Hechicero extends PersonajeMagico implements Magico {

    // Atributos propios del Hechicero (mínimo 2 requeridos)
    private int mana;
    private int manaMaximo;

    // Constructor
    public Hechicero(String nombre, int nivel, int saludMaxima, int manaMaximo) {
        super(nombre, nivel, saludMaxima);
        this.manaMaximo = manaMaximo;
        this.mana = manaMaximo;
    }

    // Override obligatorio de atacar(Personaje objetivo)
    // Polimorfismo: misma llamada → comportamiento distinto al de otros personajes
    @Override
    public void atacar(Personaje objetivo) {
        int costeMana = 10;
        if (mana >= costeMana) {
            int danio = 15 + (getNivel() * 4);
            mana -= costeMana;
            System.out.println(getNombre() + " lanza un rayo arcano a "
                    + objetivo.getNombre() + " causando " + danio + " de daño. "
                    + "[Mana restante: " + mana + "]");
            objetivo.recibirDanio(danio);
        } else {
            System.out.println(getNombre() + " no tiene suficiente mana para atacar.");
        }
    }

    // Implementación de la interfaz Magico
    @Override
    public void lanzarHechizo(Personaje objetivo) {
        int costeMana = 25;
        if (mana >= costeMana) {
            int danio = 40 + (getNivel() * 6);
            mana -= costeMana;
            System.out.println(getNombre() + " lanza Bola de Fuego sobre "
                    + objetivo.getNombre() + " causando " + danio + " de daño masivo. "
                    + "[Mana restante: " + mana + "]");
            objetivo.recibirDanio(danio);
        } else {
            System.out.println(getNombre() + " no tiene suficiente mana para lanzar el hechizo.");
        }
    }

    // Método propio adicional: invocar familiar
    public void invocar() {
        int costeMana = 30;
        if (mana >= costeMana) {
            mana -= costeMana;
            System.out.println(getNombre() + " invoca un familiar arcano para ayudarle en combate. "
                    + "[Mana restante: " + mana + "]");
        } else {
            System.out.println(getNombre() + " no tiene suficiente mana para invocar.");
        }
    }

    // Getters y Setters de atributos propios
    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = Math.max(0, Math.min(mana, manaMaximo));
    }

    public int getManaMaximo() {
        return manaMaximo;
    }

    public void setManaMaximo(int manaMaximo) {
        this.manaMaximo = manaMaximo;
    }

    // toString sobrescrito: reutiliza el de Personaje con super.toString()
    @Override
    public String toString() {
        return super.toString() + " | Mana: " + mana + "/" + manaMaximo;
    }
}
