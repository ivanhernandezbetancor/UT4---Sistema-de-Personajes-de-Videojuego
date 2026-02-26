package rpg.model;

public class Guerrero extends PersonajeFisico{

    private int escudo;
    private int rabia;

    // Constructor

    public Guerrero(String nombre, int nivel) {
        super(nombre, nivel, 120, 18, 15); // saludMaxima, fuerza y armadura
        this.escudo = 20;
        this.rabia = 0;
    }

    // Cada guerrero tiene su propia forma de atacar
    // Usa golpe básico y además acumula rabia

    @Override
    public void atacar(Personaje objetivo) {
        cargarAtaque();
        int danio = golpeBasico();

        System.out.println(getNombre() + "golpea a " + objetivo.getNombre() + " causando " + danio + " de daño ");

        objetivo.recibirDanio(danio);

        // El guerrero gana rabia al atacar
        
        rabia = Math.min(100, rabia + 10);
    }

    // Ataque especial que solo se puede usar si tiene suficiente rabia

    public void golpeFurioso(Personaje objetivo) {
        if (rabia >= 50) {
            int danio = getFuerza() * 3;

            System.out.println(getNombre() + "lanza un golpe furioso y genera " + danio + "de daño");
            
            objetivo.recibirDanio(danio);
            rabia = rabia - 50;
        } else {
            System.out.println(getNombre() + " no tiene suficiente rabia");
        }
    }

    // Getters

    public int getEscudo() {
        return escudo;
    }

    public int getRabia() {
        return rabia;
    }



}
