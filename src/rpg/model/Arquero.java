package rpg.model;

public class Arquero extends PersonajeFisico {

    private int flechas;
    private int precision;

    // Constructor

    public Arquero(String nombre, int nivel) {
        super(nombre, nivel, 90, 14, 8); // saludMaxima, fuerza y armadura
        this.flechas = 30;
        this.precision = 75;
    }

    // Cada arquero tiene su forma de atacar
    // Si tiene flechas dispara, sino ataca cuerpo a cuerpo

    @Override
    public void atacar(Personaje objetivo) {

        if (flechas > 0) {
            int danio = golpeBasico() + (precision / 10);

            System.out.println(getNombre() + " dispara una flecha a " + objetivo.getNombre() + " causando " + danio + " de daño ");

            objetivo.recibirDanio(danio);
            flechas = flechas - 1;
        } else {
            System.out.println(getNombre() + " no tiene flechas y ataca cuerpo a cuerpo");

            int danio = golpeBasico() / 2;
            objetivo.recibirDanio(danio);
        }
    }

    // Ataque especial (dispara varias flechas a la vez)

    public void disparoRapido(Personaje objetivo) {

        if (flechas >= 3) {

            int danio = golpeBasico() * 2;

            System.out.println(getNombre() + " lanza un disparo rápido causando " + danio + " de daño ");

            objetivo.recibirDanio(danio);
            flechas = flechas - 3;

        } else {
            System.out.println(getNombre() + " no tiene suficientes flechas para el disparo rápido");

        }
    }

    // Getters

    public int getFlechas() {
        return flechas;
    }

    public int getPrecision() {
        return precision;
    }

}
